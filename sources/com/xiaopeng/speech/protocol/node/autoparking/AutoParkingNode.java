package com.xiaopeng.speech.protocol.node.autoparking;

import android.os.Binder;
import android.os.IBinder;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.DMEnd;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.SpeechUtils;
import com.xiaopeng.speech.protocol.event.AutoParkingEvent;
import com.xiaopeng.speech.protocol.node.autoparking.bean.ParkingPositionBean;
import com.xiaopeng.speech.protocol.node.dialog.AbsDialogListener;
import com.xiaopeng.speech.protocol.node.dialog.DialogNode;
import com.xiaopeng.speech.protocol.node.dialog.bean.DialogEndReason;
import com.xiaopeng.speech.protocol.utils.CarTypeUtils;
import com.xiaopeng.speech.proxy.HotwordEngineProxy;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AutoParkingNode extends SpeechNode<AutoParkingListener> {
    private static final String COMMAND_SPLIT = "#";
    private static final String FIND_SKILL_NAME = "沿途找车位";
    private static final String FIND_TASK_PARK = "沿途泊车";
    private static final String INTENT_FIND_POSITION_PARK = "找到车位";
    private static final long SET_EXIT_PARKING_TIMEOUT = 150;
    private static final String SKILL_NAME = "离线自动泊车";
    private static final String TAG = "AutoParkingNode";
    private static final String TASK_PARK = "自动泊车";
    private static final int TRIGGER_ID_FINDING = 10003;
    private static final int TRIGGER_ID_PARKING = 10002;
    private static int curTriggerID = -1;
    private IBinder mWakeupBinder = new Binder();
    private volatile boolean isShouldExitParking = false;

    private void removeSetExitParkingRunnable() {
    }

    public AutoParkingNode() {
        SpeechClient.instance().getNodeManager().subscribe(DialogNode.class, new AbsDialogListener() { // from class: com.xiaopeng.speech.protocol.node.autoparking.AutoParkingNode.1
            @Override // com.xiaopeng.speech.protocol.node.dialog.AbsDialogListener, com.xiaopeng.speech.protocol.node.dialog.DialogListener
            public void onDialogEnd(DialogEndReason dialogEndReason) {
                super.onDialogEnd(dialogEndReason);
                if (DMEnd.REASON_VOICE.equals(dialogEndReason.reason) || DMEnd.REASON_WHEEL.equals(dialogEndReason.reason)) {
                    AutoParkingNode.this.onExit("", "");
                }
            }
        });
    }

    public void findParkingPosition(String str) {
        findParkingPosition(str, false);
    }

    public void findParkingPosition(String str, boolean z) {
        String str2;
        String str3;
        String str4;
        if (CarTypeUtils.is3DCarType()) {
            if (z) {
                triggerDialog(10003, str, null);
                return;
            } else {
                triggerDialog(10002, str, null);
                return;
            }
        }
        String str5 = "";
        if (z) {
            try {
                str5 = new JSONObject().put("tts", str).put("isLocalSkill", true).put(WakeupResult.REASON_COMMAND, "command://autoparking.park.start#native://autoparking.park.carport.count#command://autoparking.find.parking.space.continue#command://autoparking.find.parking.space.exit").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            str2 = str5;
            str3 = FIND_SKILL_NAME;
            str4 = FIND_TASK_PARK;
        } else {
            try {
                str5 = new JSONObject().put("tts", str).put("isLocalSkill", true).put(WakeupResult.REASON_COMMAND, "command://autoparking.park.start#native://autoparking.park.carport.count#command://autoparking.exit").toString();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            str2 = str5;
            str3 = SKILL_NAME;
            str4 = TASK_PARK;
        }
        stopCarSpeech();
        SpeechClient.instance().getAgent().triggerIntentWithBinder(this.mWakeupBinder, str3, str4, INTENT_FIND_POSITION_PARK, str2);
    }

    public void triggerDialog(int i, String str, String str2) {
        JSONObject jSONObject;
        LogUtils.i(TAG, "trigger dialog, id: %s, tts: %s, data: %s", Integer.valueOf(i), str, str2);
        if (curTriggerID != -1) {
            LogUtils.i(TAG, "already in trigger, already disable wakeup.");
        } else {
            disableWakeup();
        }
        curTriggerID = i;
        try {
            if (!SpeechUtils.isJson(str2)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(str2);
            }
            jSONObject.putOpt("tts", str);
            SpeechClient.instance().getAgent().triggerDialog(i, new int[]{0}, jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leaveTrigger() {
        LogUtils.i(TAG, "leave trigger, curTriggerID: %s", Integer.valueOf(curTriggerID));
        if (curTriggerID == -1) {
            LogUtils.i(TAG, "not in trigger, can not leave trigger.");
            return;
        }
        try {
            SpeechClient.instance().getAgent().leaveTriggerWithID(curTriggerID);
            enableWakeup();
            SpeechClient.instance().getWakeupEngine().stopDialogReason(TAG);
            curTriggerID = -1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void suspendXP() {
        SpeechClient.instance().getWakeupEngine().suspendSpeechWithInfo(this.mWakeupBinder, "泊车", "为了你的驾驶安全，语音暂不可用哦", 2, false);
    }

    public void resumeXP() {
        SpeechClient.instance().getWakeupEngine().resumeSpeechWithTypeInfo(this.mWakeupBinder, "泊车", 2, false);
    }

    public boolean isShouldExitParking() {
        return this.isShouldExitParking;
    }

    public void setShouldExitParking(boolean z) {
        removeSetExitParkingRunnable();
        this.isShouldExitParking = z;
    }

    public void setWheelWakeupEnabled(boolean z) {
        SpeechClient.instance().getWakeupEngine().setWheelWakeupEnabled(this.mWakeupBinder, z);
    }

    public void enableWakeup() {
        if (!CarTypeUtils.is3DCarType()) {
            SpeechClient.instance().getWakeupEngine().enableMainWakeupWord(this.mWakeupBinder);
            SpeechClient.instance().getWakeupEngine().enableFastWakeEnhance(this.mWakeupBinder);
            SpeechClient.instance().getHotwordEngine().removeHotWords(HotwordEngineProxy.BY_AUTO_PARKING);
            SpeechClient.instance().getHotwordEngine().removeHotWords(HotwordEngineProxy.BY_PARKING_ALONG_THE_WAY);
            return;
        }
        SpeechClient.instance().getWakeupEngine().enableWakeupWithInfo(this.mWakeupBinder, 1, "泊车", 2);
    }

    public void disableWakeup() {
        if (!CarTypeUtils.is3DCarType()) {
            SpeechClient.instance().getWakeupEngine().disableMainWakeupWord(this.mWakeupBinder);
            SpeechClient.instance().getWakeupEngine().disableFastWakeEnhance(this.mWakeupBinder);
        } else if (CarTypeUtils.isOverseasCarType()) {
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mWakeupBinder, 1, "泊车", "Voice control is unavailable when Parking app is on", 2);
        } else {
            SpeechClient.instance().getWakeupEngine().disableWakeupWithInfo(this.mWakeupBinder, 1, "泊车", "请告诉我你要泊入的车位哦", 2);
        }
    }

    public void stopCarSpeech() {
        removeSetExitParkingRunnable();
        this.isShouldExitParking = false;
        SpeechClient.instance().getWakeupEngine().stopDialogReason(DMEnd.REASON_AUTO_PARK);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AutoParkingEvent.ACTIVATE)
    public void onActivate(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AutoParkingListener) obj).onActivate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AutoParkingEvent.EXIT)
    public void onExit(String str, String str2) {
        removeSetExitParkingRunnable();
        this.isShouldExitParking = false;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AutoParkingListener) obj).onExit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AutoParkingEvent.PARK_START)
    public void onParkStart(String str, String str2) {
        removeSetExitParkingRunnable();
        this.isShouldExitParking = false;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AutoParkingListener) obj).onParkStart(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AutoParkingEvent.PARK_CARPORT_COUNT)
    public void onParkCarportCount(String str, String str2) {
        ParkingPositionBean fromJson = ParkingPositionBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AutoParkingListener) obj).onParkCarportCount(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = AutoParkingEvent.MEMORY_PARKING_START)
    public void onMemoryParkingStart(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((AutoParkingListener) obj).onMemoryParkingStart();
            }
        }
    }
}
