package com.xiaopeng.oobe;
/* loaded from: classes.dex */
public class Constants {
    public static final String ACCOUNT_TYPE_XP_VEHICLE = "com.xiaopeng.accountservice.ACCOUNT_TYPE_XP_VEHICLE";
    public static final String ACCOUNT_USER_TYPE = "user_type";
    public static final String AUTH_INFO_EXTRA_APP_ID = "app_id";
    public static final String AUTH_INFO_EXTRA_APP_SECRET = "app_secret";
    public static final String AUTH_TYPE_AUTH_CODE = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_CODE";
    @Deprecated
    public static final String AUTH_TYPE_AUTH_CODE_CAR_ACCOUNT = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_CODE_CAR_ACCOUNT";
    @Deprecated
    public static final String AUTH_TYPE_AUTH_CODE_MUSIC = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_CODE_MUSIC";
    @Deprecated
    public static final String AUTH_TYPE_AUTH_CODE_NAVIGATION = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_CODE_NAVIGATION";
    public static final String AUTH_TYPE_AUTH_OTP = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_OTP";
    @Deprecated
    public static final String AUTH_TYPE_AUTH_OTP_DEMO = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_OTP_DEMO";
    @Deprecated
    public static final String AUTH_TYPE_AUTH_OTP_MUSIC = "com.xiaopeng.accountservice.AUTH_TYPE_AUTH_OTP_MUSIC";
    public static final int INTERVAL_INIT_WEBP = 100;
    public static final int STEP_FINISH = 6;
    public static final int STEP_INIT = 1;
    public static final int STEP_LAN = 0;
    public static final int STEP_LOGIN = 2;
    public static final int STEP_MAP = 3;
    public static final int STEP_PRIVACY = 2;
    public static final int STEP_REG = 1;
    public static final int STEP_SPEECH = 4;
    public static final int STEP_STATEMENT = 0;
    public static final int STEP_VOICE_SERVICE = 4;
    public static final int STEP_VUI_AUTHORIZATION = 3;
    public static final int STEP_WELCOME = 6;
    public static final int STEP_XPILOT = 5;
    public static final int TTS_CALLBACK_TIME_OUT = 5000;
    public static final String TTS_ENGINE_AISPEECH = "com.xiaopeng.xpspeechservice";
    public static final String TTS_ENGINE_MS = "com.xiaopeng.xpspeechservice.ms";
    public static final int TTS_INIT_TIME_OUT = 1000;
    public static final int TTS_LOCAL_TIME_OUT = 10000;
    public static final String USER_DATA_EXTRA_AVATAR = "avatar";
    public static final String USER_DATA_EXTRA_UID = "uid";
    public static final String USER_DATA_EXTRA_UPDATE = "update";
    private static final int USER_TYPE_DRIVER = 4;
    private static final int USER_TYPE_TENANT = 3;
    private static final int USER_TYPE_TOWNER = 1;
    private static final int USER_TYPE_USER = 2;
    public static final int VUI_CLOSED = 3;
    public static final int VUI_FULL_TIME = 0;
    public static final int VUI_MULTIPLE = 1;
    public static final int VUI_SINGLE = 2;
    public static final int WAITING_TIMEOUT = 60000;

    /* loaded from: classes.dex */
    public static class Inter {
        public static final int ACCEPT = 1;
        public static final int REFUSE = 0;
        public static final String XP_APP_CONFIG_DIR = "appconfig";
        public static final String XP_PROTOCAL_ACCEPT_FLAG = "xp_protocal_accept_flag";
        public static final String XP_PROTOCAL_MAP_ACCEPT_FLAG = "xp_protocal_map_accept_flag";
        public static final String XP_PROTOCAL_SPEECH_ACCEPT_FLAG = "xp_protocal_speech_accept_flag";
    }

    /* loaded from: classes.dex */
    public static class Unity {
        public static final String APPID_NAME = "oobe";
        public static final String CANCEL_LOADING = "cancelLoading";
        public static final String DISABLE_OOBE = "disableOOBE";
        public static final String DISABLE_STATE = "isDisableState";
        public static final String DISMISS = "dismiss";
        public static final String ENTER_SCENE = "enterScene";
        public static final String EXIT_SCENE = "exitScene";
        public static final String GET_LAN = "getLan";
        public static final String GET_PRIVACY_NAME = "getPrivacyName";
        public static final String GET_PRIVACY_PATH = "getPrivacyPath";
        public static final String GET_REG = "getReg";
        public static final String INIT = "init";
        public static final String MODULE_NAME = "oobe_type";
        public static final String ON_SHOW_NEXT = "onShowNext";
        public static final String ON_VUI_AUTHORIZATION = "onVuiAuthorization";
        public static final String ON_WEBP = "onWebp";
        public static final String REFRESH_QR_CODE = "refreshQrCode";
        public static final String SEND_DATA = "sendData";
        public static final String SET_LAN = "setLan";
        public static final String SET_LISTENER = "set_listener";
        public static final String SET_LOADING = "setLoading";
        public static final String SET_MICROPHONE_MUTE = "setMicrophoneMute";
        public static final String SET_PRIVACY_NAME = "setPrivacyName";
        public static final String SET_QR_CODE_VIEW = "setQrCodeView";
        public static final String SET_REG = "setReg";
        public static final String SET_USER_CHECKED = "setUserChecked";
        public static final String SET_VOICE_AUTHORIZATION_OPEN = "setVoiceAuthorizationOpen";
        public static final String SET_WEBP_VISIBILITY = "setWebpVisibility";
        public static final String SHOW_LOGIN_SUCCESS = "showLoginSuccess";
        public static final String SHOW_NEXT = "showNext";
        public static final String SHOW_OOBE = "showOOBE";
        public static final String SHOW_PREVIOUS = "ShowPrevious";
        public static final String SHOW_SUCCESS = "showSuccess";
        public static final String SIGN_MAP_PROTOCOL = "signMapProtocol";
        public static final String SIGN_PRIVACY_PROTOCOL = "signPrivacyProtocol";
        public static final String SIGN_SPEECH_DATA_PROTOCOL = "signSpeechDataProtocol";
        public static final String SIGN_SPEECH_PLAN_PROTOCOL = "signSpeechPlanProtocol";
        public static final String SPEAK_NEED_SELECT_CHECK = "speakNeedSelectCheck";
        public static final String UPDATE_TIPS = "updateTips";
        public static final String VUI_ALL_TIME_AWAKE = "vuiAllTimeAwake";
        public static final String VUI_AUTHORIZATION = "vuiAuthorization";
        public static final String XPILOT_AGREE = "xpilotAgree";
    }

    /* loaded from: classes.dex */
    public enum ViewMode {
        STATEMENT,
        INIT,
        LOGIN,
        VUI_AUTHORIZATION,
        VUI_AUTHORIZATION_OS5,
        VOICE_SERVICE,
        PROTOCOL_XP,
        PROTOCOL_MAP_SPEECH,
        XPILOT_USER_EXPERIENCE_PLAN,
        REG_SELECT,
        LAN_SELECT
    }
}
