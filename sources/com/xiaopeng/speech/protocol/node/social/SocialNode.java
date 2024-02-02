package com.xiaopeng.speech.protocol.node.social;

import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.BackButtonClick;
import com.xiaopeng.speech.jarvisproto.VoiceButtonClick;
import com.xiaopeng.speech.protocol.event.SocialEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SocialNode extends SpeechNode<SocialListener> {
    public static final String GROUP_MESSAGE_INTENT = "播放群内容";
    public static final String JOIN_GROUP_INTENT = "加入鹏窝";
    private static final String LBS_SOCAIL_TASK = "LBS社交";
    private static final String OFFLINE_SKILL = "命令词";
    public static final String QUERY_SEND_MESSAGE = "发送消息";
    public static final String QUERY_SET_VOICE_BUTTON = "设置方向盘按钮";

    @SpeechAnnotation(event = SocialEvent.SOCIAL_MOTORCADE_OPEN)
    public void onSocialMotorcadeOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialMotorcadeOpen();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_MOTORCADE_CLOSE)
    public void onSocialMotorcadeClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialMotorcadeClose();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_GRAB_MIC)
    public void onSocialGrabMic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialGrabMic();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_GRAB_MIC_CANCEL)
    public void onSocialGrabMicCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialGrabMicCancel();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_CREATE_TOPIC)
    public void onSocialCreateTopic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialCreateTopic();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_REPLY_TOPIC)
    public void onSocialReplyTopic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialReplyTopic();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_QUIT_CHAT)
    public void onSocialQuitChat(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialQuitChat();
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_CONFIRM)
    public void onSocialConfirm(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("intent");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialConfirm(str3);
            }
        }
    }

    @SpeechAnnotation(event = SocialEvent.SOCIAL_CANCEL)
    public void onSocialCancel(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("intent");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onSocialCancel(str3);
            }
        }
    }

    @SpeechAnnotation(event = VoiceButtonClick.EVENT)
    public void onVoiceButtonClick(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onVoiceButtonClick();
            }
        }
    }

    @SpeechAnnotation(event = BackButtonClick.EVENT)
    public void onBackButtonClick(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SocialListener) obj).onBackButtonClick();
            }
        }
    }

    public void broadcastGroupMessage(String str) {
        String str2;
        try {
            str2 = new JSONObject().put("tts", str).put("intent", GROUP_MESSAGE_INTENT).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, LBS_SOCAIL_TASK, GROUP_MESSAGE_INTENT, str2);
    }

    public void joinGroup(String str) {
        String str2;
        try {
            str2 = new JSONObject().put("tts", str).put("intent", JOIN_GROUP_INTENT).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, LBS_SOCAIL_TASK, JOIN_GROUP_INTENT, str2);
    }

    public void querySendMessage(String str) {
        String str2;
        try {
            str2 = new JSONObject().put("tts", str).put("intent", QUERY_SEND_MESSAGE).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, LBS_SOCAIL_TASK, QUERY_SEND_MESSAGE, str2);
    }

    public void querySetVoiceButton(String str) {
        String str2;
        try {
            str2 = new JSONObject().put("tts", str).put("intent", QUERY_SET_VOICE_BUTTON).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, LBS_SOCAIL_TASK, QUERY_SET_VOICE_BUTTON, str2);
    }

    public void stopDialog() {
        SpeechClient.instance().getWakeupEngine().stopDialog();
    }
}
