package com.xiaopeng.speech.protocol.node.phone;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.lzy.okgo.cache.CacheEntity;
import com.xiaopeng.datalog.MoleEvent;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.actorapi.ResultActor;
import com.xiaopeng.speech.actorapi.SupportActor;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.bean.SliceData;
import com.xiaopeng.speech.common.bean.Value;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.FeedUIEvent;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.DeviceInfoKey;
import com.xiaopeng.speech.protocol.VocabDefine;
import com.xiaopeng.speech.protocol.bean.XpListTypeEnum;
import com.xiaopeng.speech.protocol.bean.XpListWidget;
import com.xiaopeng.speech.protocol.event.ContextEvent;
import com.xiaopeng.speech.protocol.event.PhoneEvent;
import com.xiaopeng.speech.protocol.event.query.QueryPhoneEvent;
import com.xiaopeng.speech.protocol.node.phone.bean.CallLogs;
import com.xiaopeng.speech.protocol.node.phone.bean.Contact;
import com.xiaopeng.speech.protocol.node.phone.bean.PhoneBean;
import com.xiaopeng.speech.protocol.utils.CarTypeUtils;
import com.xiaopeng.speech.protocol.utils.DeflaterUtils;
import com.xiaopeng.speech.proxy.HotwordEngineProxy;
import com.xiaopeng.speech.speechwidget.ContentWidget;
import com.xiaopeng.speech.speechwidget.ListWidget;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import com.xiaopeng.speech.speechwidget.SupportWidget;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class PhoneNode extends SpeechNode<PhoneListener> {
    public static final String CALL_VIEW_PAGE_ID = "XPPlugin_BlueToothCallApp://BlueToothCallView?from=speech";
    private static final int CHUNKSIZE = 262144;
    private static final String COMMAND_SPLIT = "#";
    public static final String INTENT_IN_COMING_PHONE = "来电话";
    private static final long SET_EXIT_CALL_TIMEOUT = 150;
    public static final String SKILL_NAME = "离线来电话";
    private static final int STOP_DIALOG_OPT_FORCE = 0;
    public static final String TASK_PHONE = "来电话";
    public static final int TRIGGER_ID = 10001;
    private static volatile int curNotifyType = 0;
    private static volatile int curTriggerID = -1;
    private static volatile boolean ifOnThePhone;
    private volatile String deviceId;
    private volatile String duiWidget;
    private String mRawQuery;
    private List<PhoneBean> phoneBeanList;
    private final String TAG = "PhoneNode";
    private final String SLOT_ENABLE_TTS = "enable_tts";
    private final String EN_DISABLE_SPEECH_CALLING = "Voice control is not available when calling.";
    private IBinder mBinder = new Binder();
    private volatile int syncResultCode = 0;

    public PhoneNode() {
        SpeechClient.instance().getSpeechState().setCanExitFlag(true);
    }

    public void syncContacts(List<Contact> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        boolean isOverseasCarType = CarTypeUtils.isOverseasCarType();
        for (Contact contact : list) {
            if (!isOverseasCarType) {
                contact.setName(contact.getName().replaceAll("[0-9a-zA-Z]", ""));
            }
            arrayList.add(contact.getName());
        }
        SpeechClient.instance().getAgent().updateVocab(VocabDefine.CONTACT, (String[]) arrayList.toArray(new String[arrayList.size()]), true);
    }

    public void syncContacts(List<Contact.PhoneInfo> list, String str) {
        this.deviceId = str;
        if (list == null || list.size() <= 0) {
            return;
        }
        if (!CarTypeUtils.isOverseasCarType() || CarTypeUtils.isE38EU() || CarTypeUtils.isE28AEU()) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            try {
                for (Contact.PhoneInfo phoneInfo : list) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", phoneInfo.getId());
                    String name = phoneInfo.getName();
                    if (!CarTypeUtils.isE38EU() && !CarTypeUtils.isE28AEU() && !TextUtils.isEmpty(name)) {
                        name = name.replaceAll("[0-9]", "");
                        if (isEnglish(name)) {
                            name = name.toUpperCase();
                        } else if (!CarTypeUtils.isE38ZH() && !CarTypeUtils.isE28AZH() && !CarTypeUtils.isF30ZH() && !CarTypeUtils.isD21ZH()) {
                            name = name.replaceAll("[a-zA-Z]", "");
                        }
                    }
                    phoneInfo.setName(name);
                    jSONObject2.put("name", phoneInfo.getName());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put(CacheEntity.DATA, jSONArray);
                jSONObject.put("deveiceId", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String valueOf = String.valueOf(jSONObject);
            byte[] bytes = valueOf.getBytes();
            LogUtils.i("PhoneNode", "uncompress source data size " + bytes.length);
            byte[] bytes2 = DeflaterUtils.compressForGzip(valueOf).getBytes();
            LogUtils.i("PhoneNode", "compress data size： " + bytes2.length);
            byte[][] divideArray = divideArray(bytes2, 262144);
            if (divideArray == null) {
                return;
            }
            for (byte[] bArr : divideArray) {
                LogUtils.i("PhoneNode", "divide data");
                if (bArr != null) {
                    SpeechClient.instance().getAgent().uploadContact(VocabDefine.CONTACT, new SliceData(bArr, bytes2.length), 1);
                }
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (Contact.PhoneInfo phoneInfo2 : list) {
            arrayList.add(phoneInfo2.getName());
        }
        SpeechClient.instance().getAgent().updateVocab(VocabDefine.CONTACT, (String[]) arrayList.toArray(new String[arrayList.size()]), true);
    }

    public boolean isEnglish(String str) {
        return str.matches("^[a-zA-Z]*");
    }

    public byte[][] divideArray(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int length = bArr.length;
        int ceil = (int) Math.ceil(length / i);
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, ceil, i);
        int i2 = 0;
        int i3 = 0;
        while (i2 < ceil) {
            int i4 = i3 + i;
            if (i4 > length) {
                System.arraycopy(bArr, i3, bArr2[i2], 0, bArr.length - i3);
            } else {
                System.arraycopy(bArr, i3, bArr2[i2], 0, i);
            }
            i2++;
            i3 = i4;
        }
        return bArr2;
    }

    public void syncCallLogs(List<CallLogs> list, String str) {
        if (list == null || list.size() <= 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            for (CallLogs callLogs : list) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("id", callLogs.getId());
                jSONObject2.put("name", callLogs.getName());
                jSONObject2.put("time", callLogs.getTime());
                jSONObject2.put("call_count", callLogs.getCallCount());
                callLogs.setName(callLogs.getName().replaceAll("[0-9a-zA-Z]", ""));
                jSONArray.put(jSONObject2);
            }
            jSONObject.put(CacheEntity.DATA, jSONArray);
            jSONObject.put("deveiceId", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SpeechClient.instance().getAgent().uploadContacts(VocabDefine.CALLLOGS, String.valueOf(jSONObject), 1);
    }

    public void cleanContacts() {
        SpeechClient.instance().getAgent().updateVocab(VocabDefine.CONTACT, null, false);
    }

    public void stopSpeechDialog(int i) {
        LogUtils.i(this, "stopDialog option: " + i);
        if ((CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) && curTriggerID != -1) {
            LogUtils.i("PhoneNode", "in trigger dialog, can not stop dialog.");
        } else if (i == 0) {
            SpeechClient.instance().getWakeupEngine().stopDialog();
        } else {
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.SCRIPT_QUIT, "{\"data\":{\"domain\":\"phone\"}}");
        }
    }

    public void setBTStatus(boolean z) {
        LogUtils.i(this, "setBTStatus:%b", Boolean.valueOf(z));
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle("联系人");
        listWidget.setExtraType("phone");
        listWidget.addExtra(DeviceInfoKey.PHONE_BLUETOOTH, String.valueOf(z));
        SpeechClient.instance().getActorBridge().send(new ResultActor(PhoneEvent.QUERY_BLUETOOTH).setResult(listWidget));
    }

    @SpeechAnnotation(event = PhoneEvent.QUERY_SYNC_BLUETOOTH)
    public void onQuerySyncBluetooth(String str, String str2) {
        Value queryData = SpeechClient.instance().getQueryInjector().queryData(QueryPhoneEvent.GET_BLUETOOTH_STATUS, null);
        Value queryData2 = SpeechClient.instance().getQueryInjector().queryData(QueryPhoneEvent.GET_CONTACT_SYNC_STATUS, null);
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle("联系人");
        listWidget.setExtraType("phone");
        listWidget.addExtra("deviceId", this.deviceId);
        if (queryData != null) {
            listWidget.addExtra(DeviceInfoKey.PHONE_BLUETOOTH, String.valueOf(queryData.getBoolean()));
        }
        if (queryData2 != null) {
            listWidget.addExtra("phone_sync", String.valueOf(queryData2.getInteger()));
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(PhoneEvent.QUERY_SYNC_BLUETOOTH).setResult(listWidget));
    }

    public void onQuerySyncBluetooth(String str, String str2, boolean z, int i) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle("联系人");
        listWidget.setExtraType("phone");
        listWidget.addExtra("deviceId", this.deviceId);
        listWidget.addExtra(DeviceInfoKey.PHONE_BLUETOOTH, String.valueOf(z));
        listWidget.addExtra("phone_sync", String.valueOf(i));
        SpeechClient.instance().getActorBridge().send(new ResultActor(PhoneEvent.QUERY_SYNC_BLUETOOTH).setResult(listWidget));
    }

    public void incomingCallRing(String str, String str2, boolean z) {
        boolean z2;
        String str3;
        LogUtils.i("incomingCallRing, enable tts: " + z);
        try {
            z2 = SpeechClient.instance().getSpeechState().isMicrophoneMute();
        } catch (Exception unused) {
            z2 = false;
        }
        String str4 = "";
        if (TextUtils.isEmpty(str2)) {
            str3 = "";
        } else {
            str3 = str2 + "来电话了，要接听还是拒绝？";
        }
        if (!TextUtils.isEmpty(str)) {
            str3 = str + "来电话了，要接听还是拒绝？";
        }
        if (z2) {
            str3 = "来电话了，麦克风已禁用，通话中可在电话应用内取消静音哦";
        }
        try {
            str4 = new JSONObject().put("tts", str3).put("来电号码", str2).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            str4 = new JSONObject().put("isLocalSkill", true).put("来电人", str).put("来电号码", str2).put("tts", str3).put(WakeupResult.REASON_COMMAND, "command://phone.in.accept#command://phone.in.reject").put("enable_tts", z).toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str5 = str4;
        SpeechClient.instance().getSpeechState().setCanExitFlag(false);
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            if (curNotifyType != 2) {
                LogUtils.i("PhoneNode", "clear disable info, type: " + curNotifyType);
                enableWakeup();
                SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", CarTypeUtils.isOverseasCarType() ? "Voice control is not available when calling." : "通话中，语音不可用", 2);
                curNotifyType = 2;
            } else {
                LogUtils.i("PhoneNode", "cur notify type is already toast");
            }
            if (!CarTypeUtils.isOverseasCarType()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("triggerID", IpcConfig.MessageCenterConfig.IPC_ID_READ_ALL_MESSAGE);
                    LinkedList linkedList = new LinkedList();
                    linkedList.add(0);
                    jSONObject.put("soundArea", new JSONArray((Collection) linkedList));
                    jSONObject.put("tts", str3);
                    jSONObject.put("repeatNum", 3);
                    int newTriggerDialog = SpeechClient.instance().getAgent().newTriggerDialog(10001, new int[]{0}, jSONObject.toString());
                    LogUtils.i("PhoneNode", "trigger dialog result code: " + newTriggerDialog);
                    setPhoneCallStatusWithBinder(this.mBinder, 1);
                    if (newTriggerDialog == 0) {
                        curTriggerID = 10001;
                        return;
                    }
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            curTriggerID = 10001;
        } else {
            SpeechClient.instance().getAgent().triggerIntentWithBinder(this.mBinder, "离线来电话", "来电话", "来电话", str5);
        }
        setPhoneCallStatusWithBinder(this.mBinder, 1);
    }

    public int incomingCallRing(String str, String str2, boolean z, String str3) {
        boolean z2;
        LogUtils.i("incomingCallRing, trigger data: " + str3);
        try {
            z2 = SpeechClient.instance().getSpeechState().isMicrophoneMute();
        } catch (Exception unused) {
            z2 = false;
        }
        if (!TextUtils.isEmpty(str2)) {
            String str4 = str2 + "来电话了，要接听还是拒绝？";
        }
        if (!TextUtils.isEmpty(str)) {
            String str5 = str + "来电话了，要接听还是拒绝？";
        }
        int i = -1;
        if (z2) {
            return -1;
        }
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            if (curNotifyType != 2) {
                LogUtils.i("PhoneNode", "clear disable info, type: " + curNotifyType);
                enableWakeup();
                SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", CarTypeUtils.isOverseasCarType() ? "Voice control is not available when calling." : "通话中，语音不可用", 2);
                curNotifyType = 2;
            } else {
                LogUtils.i("PhoneNode", "cur notify type is already toast");
            }
            if (!CarTypeUtils.isOverseasCarType()) {
                try {
                    i = SpeechClient.instance().getAgent().newTriggerDialog(10001, new int[]{0}, str3);
                    LogUtils.i("PhoneNode", "trigger dialog result code: " + i);
                    setPhoneCallStatusWithBinder(this.mBinder, 1);
                    if (i == 0) {
                        curTriggerID = 10001;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
            curTriggerID = 10001;
            setPhoneCallStatusWithBinder(this.mBinder, 1);
            return i;
        }
        LogUtils.e("PhoneNode", "car type not support");
        return -1;
    }

    public synchronized void incomingCallRing(String str, String str2) {
        incomingCallRing(str, str2, true);
    }

    public void outgoingCallRing() {
        LogUtils.i("outgoingCallRing");
        SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.SCRIPT_QUIT, "{\"data\":{\"domain\":\"phone\"}}");
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            if (curNotifyType != 2) {
                LogUtils.i("PhoneNode", "clear disable info, type: " + curNotifyType);
                enableWakeup();
                SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", CarTypeUtils.isOverseasCarType() ? "Voice control is not available when calling." : "通话中，语音不可用", 2);
                curNotifyType = 2;
            } else {
                LogUtils.i("PhoneNode", "cur notify type is already toast");
            }
        }
        stopSpeechDialog();
        setPhoneCallStatusWithBinder(this.mBinder, 2);
    }

    public synchronized void callOffhook() {
        ifOnThePhone = true;
        LogUtils.i("callOffhook");
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            leaveTrigger();
            if (curNotifyType != 1) {
                LogUtils.i("PhoneNode", "update disable info type from %s to info_flow", Integer.valueOf(curNotifyType));
                enableWakeup();
                curNotifyType = 1;
                SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", CarTypeUtils.isOverseasCarType() ? "Voice control is not available when calling." : "通话中，语音不可用", 1);
            } else {
                LogUtils.i("PhoneNode", "cur notify type is info_flow");
            }
        }
        stopSpeechDialog();
        SpeechClient.instance().getSpeechState().setCanExitFlag(true);
        setPhoneCallStatusWithBinder(this.mBinder, 3);
    }

    public synchronized void callEnd() {
        LogUtils.i("PhoneNode", "callEnd");
        ifOnThePhone = false;
        if (!CarTypeUtils.is3DCarType() && !CarTypeUtils.isXmart5()) {
            stopSpeechDialog();
            SpeechClient.instance().getSpeechState().setCanExitFlag(true);
            setPhoneCallStatusWithBinder(this.mBinder, 0);
        }
        leaveTrigger();
        curNotifyType = 0;
        SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", 2);
        SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", 1);
        SpeechClient.instance().getSpeechState().setCanExitFlag(true);
        setPhoneCallStatusWithBinder(this.mBinder, 0);
    }

    public void stopSpeechDialog() {
        LogUtils.i("PhoneNode", "stopSpeechDialog");
        if ((CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) && curTriggerID != -1) {
            LogUtils.i("PhoneNode", "in trigger dialog, can not stop dialog.");
        } else {
            SpeechClient.instance().getWakeupEngine().stopDialogReason("PhoneNode");
        }
    }

    public void setPhoneCallStatus(int i) {
        SpeechClient.instance().getSpeechState().setPhoneCallStatus(i);
    }

    public void setPhoneCallStatusWithBinder(IBinder iBinder, int i) {
        SpeechClient.instance().getSpeechState().setPhoneCallStatusWithBinder(iBinder, i);
    }

    public void leaveTrigger() {
        LogUtils.i("PhoneNode", "leave trigger, curTriggerID: %s", Integer.valueOf(curTriggerID));
        try {
            if (!CarTypeUtils.isOverseasCarType()) {
                SpeechClient.instance().getAgent().leaveTriggerWithID(10001);
            }
            curTriggerID = -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void enableMainWakeup() {
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            if (ifOnThePhone) {
                LogUtils.i("PhoneNode", "on the phone, can not enable wakeup.");
                return;
            } else if (curTriggerID != -1) {
                LogUtils.i("PhoneNode", "in trigger dialog, can not enable wakeup.");
                return;
            }
        }
        enableWakeup();
        LogUtils.i("PhoneNode", "startRecord----------");
        setPhoneCallStatusWithBinder(this.mBinder, 5);
    }

    public synchronized void disableMainWakeup() {
        disableWakeup();
        LogUtils.i("PhoneNode", "stopRecord-------");
        setPhoneCallStatusWithBinder(this.mBinder, 4);
    }

    public boolean isFastWakeup() {
        return SpeechClient.instance().getWakeupEngine().isDefaultEnableWakeup();
    }

    private void enableWakeup() {
        LogUtils.i("PhoneNode", "enableWakeup, curNotifyType: " + curNotifyType);
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            LogUtils.i("PhoneNode", "3d carType or h93 enable");
            if (curNotifyType == 0) {
                LogUtils.i("PhoneNode", "no disable info");
                return;
            }
            if (curNotifyType == 2) {
                LogUtils.i("PhoneNode", "clear toast disable info.");
                SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", 2);
            } else if (curNotifyType == 1) {
                LogUtils.i("PhoneNode", "clear info_flow disable info");
                SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", 1);
            }
            curNotifyType = 0;
        } else if (!CarTypeUtils.isOverseasCarType() || CarTypeUtils.isE38EU() || CarTypeUtils.isE28AEU()) {
            SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 2, "蓝牙电话", 1);
            SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mBinder, 4, "蓝牙电话", 1);
            LogUtils.i("enableWakeup clear hot words");
            SpeechClient.instance().getHotwordEngine().removeHotWords(HotwordEngineProxy.BY_BLUETOOTH_PHONE);
        } else {
            SpeechClient.instance().getWakeupEngine().enableWakeupEnhance(null);
            SpeechClient.instance().getWakeupEngine().enableMainWakeupWord(this.mBinder);
        }
    }

    private void disableWakeup() {
        if (CarTypeUtils.is3DCarType() || CarTypeUtils.isXmart5()) {
            LogUtils.i("PhoneNode", "3d carType disableWakeup");
            if (curNotifyType != 0) {
                LogUtils.i("PhoneNode", "have been disabled");
                return;
            }
            curNotifyType = 2;
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 1, "蓝牙电话", CarTypeUtils.isOverseasCarType() ? "Voice control is not available when calling." : "通话中，语音不可用", 2);
        } else if (!CarTypeUtils.isOverseasCarType()) {
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 2, "蓝牙电话", (CarTypeUtils.isE38ZH() || CarTypeUtils.isD55ZH() || CarTypeUtils.isE28ZH()) ? "通话中，语音不可用" : "通话中暂停服务，一会再叫我", 1);
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 4, "蓝牙电话", (CarTypeUtils.isE38ZH() || CarTypeUtils.isD55ZH() || CarTypeUtils.isE28ZH()) ? "通话中，语音不可用" : "通话中暂停服务，一会再叫我", 1);
            LogUtils.i("disableWakeup");
        } else if (CarTypeUtils.isE38EU() || CarTypeUtils.isE28AEU()) {
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 2, "蓝牙电话", "Voice control is not available when calling.", 1);
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mBinder, 4, "蓝牙电话", "Voice control is not available when calling.", 1);
            LogUtils.i("E38V disableWakeup");
        } else {
            SpeechClient.instance().getWakeupEngine().disableWakeupEnhance(null);
            SpeechClient.instance().getWakeupEngine().disableMainWakeupWord(this.mBinder);
        }
    }

    @SpeechAnnotation(event = PhoneEvent.QUERY_CONTACTS)
    public void onQueryContacts(String str, String str2) {
        PhoneBean fromJson = PhoneBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onQueryContacts(str, fromJson);
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.QUERY_DETAIL_PHONEINFO)
    public void onQueryDetailPhoneInfo(String str, String str2) {
        JSONArray optJSONArray;
        try {
            this.duiWidget = str2;
            JSONObject jSONObject = new JSONObject(str2);
            ArrayList arrayList = new ArrayList();
            if (jSONObject.has("content") && (optJSONArray = jSONObject.optJSONArray("content")) != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        Contact.PhoneInfo phoneInfo = new Contact.PhoneInfo();
                        phoneInfo.setName(optJSONObject.optString(SpeechWidget.WIDGET_TITLE));
                        if (optJSONObject.has(SpeechWidget.WIDGET_SUBTITLE) && !TextUtils.isEmpty(optJSONObject.optString(SpeechWidget.WIDGET_SUBTITLE))) {
                            phoneInfo.setNumber(optJSONObject.optString(SpeechWidget.WIDGET_SUBTITLE));
                        }
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject(SpeechWidget.WIDGET_EXTRA);
                        if (optJSONObject2 != null) {
                            phoneInfo.setId(optJSONObject2.optString("id"));
                        }
                        arrayList.add(phoneInfo);
                    }
                }
            }
            LogUtils.d("PhoneNode", "onQueryDetailPhoneInfo data = " + str2);
            JSONObject optJSONObject3 = jSONObject.optJSONObject(SpeechWidget.WIDGET_EXTRA);
            if (optJSONObject3 != null) {
                this.mRawQuery = optJSONObject3.optString("raw_query");
            }
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((PhoneListener) obj).onQueryDetailPhoneInfo(arrayList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void replySupport(String str, boolean z, String str2, boolean z2) {
        SupportWidget supportWidget = new SupportWidget();
        supportWidget.setSupport(z);
        supportWidget.addExtra(DeviceInfoKey.PHONE_BLUETOOTH, String.valueOf(z2));
        supportWidget.setTTS(str2);
        SpeechClient.instance().getActorBridge().send(new SupportActor(str).setResult(supportWidget));
    }

    public void postContactsResult(List<PhoneBean> list, boolean z) {
        postContactsResult("联系人", list, z);
    }

    public void postContactsResult(String str, List<PhoneBean> list, boolean z) {
        this.phoneBeanList = list;
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(str);
        listWidget.setExtraType("phone");
        listWidget.addExtra(DeviceInfoKey.PHONE_BLUETOOTH, String.valueOf(z));
        for (PhoneBean phoneBean : list) {
            ContentWidget contentWidget = new ContentWidget();
            contentWidget.setTitle(phoneBean.getName());
            contentWidget.setSubTitle(phoneBean.getNumber());
            contentWidget.addExtra("phone", phoneBean.toJson());
            listWidget.addContentWidget(contentWidget);
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(PhoneEvent.QUERY_CONTACTS).setResult(listWidget));
    }

    public void postDetailPhoneInfoResult(List<Contact.PhoneInfo> list) {
        List<Contact.PhoneInfo> list2 = list;
        if (list2 == null) {
            return;
        }
        try {
            if (!CarTypeUtils.isE38ZH() && !CarTypeUtils.isE28AZH() && !CarTypeUtils.isF30ZH() && !CarTypeUtils.isE28AEU() && !CarTypeUtils.isE38EU()) {
                if (CarTypeUtils.isH93ZH()) {
                    syncContactList(list);
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (Contact.PhoneInfo phoneInfo : list) {
                    if (phoneInfo != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(SpeechWidget.WIDGET_SUBTITLE, phoneInfo.getNumber());
                        jSONObject.put(SpeechWidget.WIDGET_TITLE, phoneInfo.getName());
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("任意联系人", phoneInfo.getName());
                        jSONObject2.put("号码", phoneInfo.getNumber());
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("phone", jSONObject2.toString());
                        jSONObject.put(SpeechWidget.WIDGET_EXTRA, jSONObject3);
                        jSONArray.put(jSONObject);
                    }
                }
                JSONObject jSONObject4 = new JSONObject(this.duiWidget);
                jSONObject4.put("content", jSONArray);
                LogUtils.d("PhoneNode", "postDetailPhoneInfoResult, widget: " + jSONObject4);
                SpeechClient.instance().getAgent().sendEvent(ContextEvent.WIDGET_LIST, jSONObject4.toString());
                return;
            }
            JSONObject jSONObject5 = new JSONObject();
            try {
                jSONObject5.put("widgetName", "default");
                jSONObject5.put("duiWidget", SpeechWidget.TYPE_LIST);
                jSONObject5.put("widgetId", UUID.randomUUID());
                jSONObject5.put("count", list.size());
                int i = 0;
                jSONObject5.put("tipsTimeout", 0);
                jSONObject5.put("type", SpeechWidget.TYPE_LIST);
                JSONArray jSONArray2 = new JSONArray();
                while (i < list.size()) {
                    JSONObject jSONObject6 = new JSONObject();
                    JSONObject jSONObject7 = new JSONObject();
                    Contact.PhoneInfo phoneInfo2 = list2.get(i);
                    jSONObject7.put("phone", "{\"任意联系人\": \"" + phoneInfo2.getName() + "\", \"号码\": \"" + phoneInfo2.getNumber() + "\"}");
                    jSONObject6.put(SpeechWidget.WIDGET_EXTRA, jSONObject7);
                    jSONObject6.put(SpeechWidget.WIDGET_TITLE, phoneInfo2.getName());
                    jSONObject6.put(SpeechWidget.WIDGET_SUBTITLE, phoneInfo2.getNumber());
                    jSONArray2.put(jSONObject6);
                    i++;
                    list2 = list;
                }
                jSONObject5.put("content", jSONArray2);
                jSONObject5.put("name", "default");
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("phone_sync", "1");
                jSONObject8.put(SpeechWidget.WIDGET_TITLE, "联系人");
                jSONObject8.put("extraType", "phone");
                jSONObject8.put(DeviceInfoKey.PHONE_BLUETOOTH, true);
                if (!TextUtils.isEmpty(this.mRawQuery)) {
                    jSONObject8.put("raw_query", this.mRawQuery);
                }
                jSONObject5.put(SpeechWidget.WIDGET_EXTRA, jSONObject8);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put(MoleEvent.KEY_PAGE_ID, CALL_VIEW_PAGE_ID);
            jSONObject9.put(CacheEntity.DATA, jSONObject5.toString());
            LogUtils.d("PhoneNode", "postDetailPhoneInfoResult, napadata: " + jSONObject9);
            SpeechClient.instance().getAgent().sendEvent(ContextEvent.NAPA_WIDGET_LIST, jSONObject9.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void syncContactList(List<Contact.PhoneInfo> list) {
        try {
            List list2 = (List) list.stream().map(new Function() { // from class: com.xiaopeng.speech.protocol.node.phone.-$$Lambda$PhoneNode$L_Rpx4x45_s7oSm2RX9z9Gf9RA0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return PhoneNode.lambda$syncContactList$0((Contact.PhoneInfo) obj);
                }
            }).collect(Collectors.toList());
            XpListWidget xpListWidget = new XpListWidget();
            xpListWidget.setListType(XpListTypeEnum.CONTACT_LIST);
            xpListWidget.setContents(list2);
            xpListWidget.setTotalSize(list2.size());
            String json = new Gson().toJson(xpListWidget);
            LogUtils.i("PhoneNode", "syncContactList:" + json);
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_UPLOAD, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ PhoneBean lambda$syncContactList$0(Contact.PhoneInfo phoneInfo) {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setName(phoneInfo.getName());
        phoneBean.setId(phoneInfo.getId());
        return phoneBean;
    }

    public List<Contact.PhoneInfo> removeDuplicate(List<Contact.PhoneInfo> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int size = list.size() - 1; size > i; size--) {
                if (!TextUtils.isEmpty(list.get(size).getNumber()) && list.get(size).getNumber().equals(list.get(i).getNumber())) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004a  */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.PhoneEvent.OUT)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onOut(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            r7 = 0
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L3a
            r0.<init>(r8)     // Catch: org.json.JSONException -> L3a
            java.lang.String r8 = "phone"
            java.lang.String r8 = r0.optString(r8)     // Catch: org.json.JSONException -> L3a
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch: org.json.JSONException -> L3a
            if (r1 != 0) goto L17
            com.xiaopeng.speech.protocol.node.phone.bean.PhoneBean r7 = com.xiaopeng.speech.protocol.node.phone.bean.PhoneBean.fromJson(r8)     // Catch: org.json.JSONException -> L3a
            goto L42
        L17:
            com.xiaopeng.speech.protocol.node.phone.bean.PhoneBean r8 = new com.xiaopeng.speech.protocol.node.phone.bean.PhoneBean     // Catch: org.json.JSONException -> L3a
            r8.<init>()     // Catch: org.json.JSONException -> L3a
            java.lang.String r7 = "name"
            java.lang.String r7 = r0.optString(r7)     // Catch: org.json.JSONException -> L38
            r8.setName(r7)     // Catch: org.json.JSONException -> L38
            java.lang.String r7 = "number"
            java.lang.String r7 = r0.optString(r7)     // Catch: org.json.JSONException -> L38
            r8.setNumber(r7)     // Catch: org.json.JSONException -> L38
            java.lang.String r7 = "id"
            java.lang.String r7 = r0.optString(r7)     // Catch: org.json.JSONException -> L38
            r8.setId(r7)     // Catch: org.json.JSONException -> L38
            goto L41
        L38:
            r7 = move-exception
            goto L3e
        L3a:
            r8 = move-exception
            r5 = r8
            r8 = r7
            r7 = r5
        L3e:
            r7.printStackTrace()
        L41:
            r7 = r8
        L42:
            if (r7 != 0) goto L4a
            java.lang.String r7 = "phoneBean == null"
            com.xiaopeng.speech.common.util.LogUtils.e(r6, r7)
            return
        L4a:
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r8 = r6.mListenerList
            java.lang.Object[] r8 = r8.collectCallbacks()
            if (r8 == 0) goto L6c
            r0 = 0
        L53:
            int r1 = r8.length
            if (r0 >= r1) goto L6c
            r1 = r8[r0]
            com.xiaopeng.speech.protocol.node.phone.PhoneListener r1 = (com.xiaopeng.speech.protocol.node.phone.PhoneListener) r1
            java.lang.String r2 = r7.getName()
            java.lang.String r3 = r7.getNumber()
            java.lang.String r4 = r7.getId()
            r1.onOut(r2, r3, r4)
            int r0 = r0 + 1
            goto L53
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.phone.PhoneNode.onOut(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = PhoneEvent.SELECT)
    public void onPhoneSelectOut(String str, String str2) {
        PhoneBean phoneBean;
        int optInt;
        try {
            optInt = new JSONObject(str2).optInt("select_num");
        } catch (JSONException e) {
            e.printStackTrace();
            phoneBean = null;
        }
        if (this.phoneBeanList != null && this.phoneBeanList.size() > 0) {
            if (optInt > 0 && optInt <= this.phoneBeanList.size()) {
                phoneBean = this.phoneBeanList.get(optInt - 1);
                if (phoneBean == null) {
                    LogUtils.e(this, "phoneBean == null");
                    return;
                }
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((PhoneListener) obj).onOut(phoneBean.getName(), phoneBean.getNumber(), phoneBean.getId());
                        SpeechClient.instance().getTTSEngine().speak("好的，正在呼叫 " + phoneBean.getName());
                    }
                    return;
                }
                return;
            }
            SpeechClient.instance().getTTSEngine().speak("您的选择已经超出当前列表范围了哦");
            LogUtils.e(this, "select_num is  == " + optInt);
            return;
        }
        LogUtils.e(this, "phoneBeanList == null");
    }

    @SpeechAnnotation(event = PhoneEvent.IN_ACCEPT)
    public void onInAccept(String str, String str2) {
        SpeechClient.instance().getSpeechState().setCanExitFlag(true);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onInAccept();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.IN_REJECT)
    public void onInReject(String str, String str2) {
        SpeechClient.instance().getSpeechState().setCanExitFlag(true);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onInReject();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.REDIAL_SUPPORT)
    public void onRedialSupport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onRedialSupport();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.REDIAL)
    public void onRedial(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onRedial();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.CALLBACK_SUPPORT)
    public void onCallbackSupport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onCallbackSupport();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.CALLBACK)
    public void onCallback(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onCallback();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.OUT_CUSTOMERSERVICE)
    public void onOutCustomerservice(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("number");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "";
        }
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onOutCustomerservice(str3);
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.OUT_HELP)
    public void onOutHelp(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("number");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "";
        }
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onOutHelp(str3);
            }
        }
    }

    public void onSettingOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onSettingOpen();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.QUERY_BLUETOOTH)
    public void onQueryBluetooth(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onQueryBluetooth();
            }
        }
    }

    @SpeechAnnotation(event = PhoneEvent.SYNC_CONTACT_RESULT)
    public void onSyncContactResult(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        this.syncResultCode = Integer.valueOf(str2).intValue();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((PhoneListener) obj).onSyncContactResult(this.syncResultCode);
            }
        }
    }

    public void onListDisappear(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("listType", XpListTypeEnum.CONTACT_LIST.toString());
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put(SpeechWidget.WIDGET_EXTRA, str);
            }
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.LIST_DISAPPEAR, jSONObject.toString());
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        }
    }

    public void subscribeMultiTask(String str) {
        SpeechClient.instance().getWakeupEngine().subscribeMultiTask(str, this.mBinder);
    }
}
