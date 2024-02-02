package com.xiaopeng.oobe.utils;

import android.content.Context;
import com.xiaopeng.datalog.DataLogModuleEntry;
import com.xiaopeng.lib.bughunter.BugHunter;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IMoleEventBuilder;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import java.util.Map;
/* loaded from: classes.dex */
public class BIHelper {
    private static final String EVENT_NAME = "OOBE";
    private static final String TAG = "BIHelper";
    private IDataLog mIDataLog;

    /* loaded from: classes.dex */
    public static class ButtonID {
        public static final String AWAKE_OK_CLICK = "B003";
        public static final String AWAKE_SKIP_CLICK = "B004";
        public static final String JUMPER_NOT_NET_WORK = "B009";
        public static final String MICRO_PHONE_MUTE_CLICK = "B008";
        public static final String VOICE_AUTHORIZATION_EVERY_MORE_CLICK = "B006";
        public static final String VOICE_AUTHORIZATION_ONCE_CLICK = "B007";
        public static final String VOICE_NO_USER_CLICK = "B005";
        public static final String VUI_JUMPER_NOT_NET_WORK = "B010";
        public static final String VUI_OK_CLICK = "B001";
        public static final String VUI_SKIP_CLICK = "B002";

        /* loaded from: classes.dex */
        public static class OS5 {
            public static final String CAMERA_DIALOG_NO_CLICK = "B007";
            public static final String CAMERA_DIALOG_YES_CLICK = "B006";
            public static final String VOICE_AUTHORIZATION_DIALOG_EVERY_MORE_CLICK = "B004";
            public static final String VOICE_AUTHORIZATION_DIALOG_ONCE_CLICK = "B005";
            public static final String VOICE_NO_USER_CLICK = "B009";
            public static final String VUI_AUTHORIZED_SUCCESS = "B008";
            public static final String VUI_FULL_TIME_DIALOG_NO_CLICK = "B003";
            public static final String VUI_FULL_TIME_DIALOG_YES_CLICK = "B002";
            public static final String VUI_JUMPER_NOT_NET_WORK = "B010";
            public static final String VUI_YES_CLICK = "B001";
        }
    }

    /* loaded from: classes.dex */
    public static class PageId {
        public static final String AWAKE_DIALOG_PAGE = "P30002";
        public static final String JUMPER_NOT_NET_WORK = "P20010";
        public static final String MICRO_PHONE_MUTE_PAGE = "P30005";
        public static final String VOICE_AUTHORIZATION_PAGE = "P30004";
        public static final String VOICE_NO_USER_PAGE = "P30003";
        public static final String VUI_DIALOG_PAGE = "P30001";
        public static final String VUI_JUMPER_NOT_NET_WORK = "P30001";

        /* loaded from: classes.dex */
        public static class OS5 {
            public static final String CAMERA_DIALOG_PAGE = "P30004";
            public static final String VOICE_AUTHORIZATION_DIALOG_PAGE = "P30003";
            public static final String VOICE_NO_USER_PAGE = "P30006";
            public static final String VUI_AUTHORIZATION_PAGE = "P30001";
            public static final String VUI_AUTHORIZED_SUCCESS = "P30005";
            public static final String VUI_FULL_TIME_DIALOG_PAGE = "P30002";
            public static final String VUI_JUMPER_NOT_NET_WORK = "P30010";
        }
    }

    public static BIHelper getInstance() {
        return SingletonHolder.sInstance;
    }

    public void init(Context context) {
        BugHunter.init(context);
        this.mIDataLog = getDataLog();
    }

    public void sendAwakeDialogPage(boolean z) {
        sendData("P30002", z ? "B003" : "B004");
    }

    public void sendVoiceNotUserPage() {
        sendData("P30003", "B005");
    }

    public void sendData(String str, String str2) {
        LogUtils.d(TAG, "sendData, scene : " + str + ", eventId: " + str2, false);
        sendMqttDataLog(str, str2, null);
    }

    private void sendMqttDataLog(final String str, final String str2, final Map<String, Number> map) {
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.utils.-$$Lambda$BIHelper$I1vHWjxUTNE-yQJODWz-jpqAQgQ
            @Override // java.lang.Runnable
            public final void run() {
                BIHelper.this.lambda$sendMqttDataLog$0$BIHelper(str, str2, map);
            }
        });
    }

    public /* synthetic */ void lambda$sendMqttDataLog$0$BIHelper(String str, String str2, Map map) {
        if (this.mIDataLog == null) {
            this.mIDataLog = getDataLog();
        }
        IMoleEventBuilder buttonId = this.mIDataLog.buildMoleEvent().setEvent(EVENT_NAME).setModule(EVENT_NAME).setPageId(str).setButtonId(str2);
        StringBuilder sb = new StringBuilder();
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                buttonId.setProperty((String) entry.getKey(), (Number) entry.getValue());
                sb.append((String) entry.getKey());
                sb.append(":");
                sb.append(entry.getValue());
                sb.append(";");
            }
        }
        this.mIDataLog.sendStatData(buttonId.build());
        LogUtils.d(TAG, "sendMqttDataLog, pageId : " + str + ", buttonId: " + str2 + ", params: " + sb.toString(), false);
    }

    private IDataLog getDataLog() {
        return (IDataLog) Module.get(DataLogModuleEntry.class).get(IDataLog.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SingletonHolder {
        static BIHelper sInstance = new BIHelper();

        private SingletonHolder() {
        }
    }
}
