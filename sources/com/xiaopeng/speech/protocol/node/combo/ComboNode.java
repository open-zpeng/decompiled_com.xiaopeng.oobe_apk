package com.xiaopeng.speech.protocol.node.combo;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.ComboEvent;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechCarStatusEvent;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ComboNode extends SpeechNode<ComboListener> {
    private static final String CLOSE_MEDITATION_INTENT = "关闭冥想模式";
    private static final String CLOSE_WAIT_INTENT = "关闭等人模式";
    private static final String COMBO_SKILL = "离线系统组合命令词";
    private static final String COMBO_TASK = "组合命令词";
    private static final String OPEN_MEDITATION_INTENT = "冥想模式";
    private static final String OPEN_WAIT_INTENT = "等人模式";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_BIO)
    public void onModeBio(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeBio();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_VENTILATE)
    public void onModeVentilate(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeVentilate();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_INVISIBLE)
    public void onModeInvisible(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeInvisible();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_INVISIBLE_OFF)
    public void onModeInvisibleOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeInvisibleOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_FRIDGE)
    public void onModeFridge(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeFridge();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_INVISIBLE_TTS)
    public void onDataModeInvisibleTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeInvisibleTts();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_MEDITATION_TTS)
    public void onDataModeMeditationTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeMeditationTts();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.FAST_CLOSE_MODE_INVISIBLE)
    public void onFastCloseModeInvisible(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onFastCloseModeInvisible();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_BIO_TTS)
    public void onDataModeBioTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeBioTts();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_VENTILATE_TTS)
    public void onDataModeVentilateTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeVentilateTts();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_FRIDGE_TTS)
    public void onDataModeFridgeTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeFridgeTts();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.MODE_BIO_OFF)
    public void onModeBioOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeBioOff();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.MODE_VENTILATE_OFF)
    public void onModeVentilateOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeVentilateOff();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.MODE_FRIDGE_OFF)
    public void onModeFridgeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeFridgeOff();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.DATA_MODE_WAIT_TTS)
    public void onDataModeWaitTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onDataModeWaitTts();
            }
        }
    }

    @SpeechAnnotation(event = ComboEvent.MODE_WAIT)
    public void onModeWait(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeWait();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.MODE_WAIT_OFF)
    public void onModeWaitOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).onModeWaitOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.ENTER_USER_MODE)
    public void enterUserMode(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (int i = 0; i < collectCallbacks.length; i++) {
                String extraFromJson = getExtraFromJson(str2);
                if (TextUtils.isEmpty(extraFromJson)) {
                    ((ComboListener) collectCallbacks[i]).enterUserMode(getModeFromJson(str2));
                } else {
                    ((ComboListener) collectCallbacks[i]).enterUserModeWithExtra(getModeFromJson(str2), extraFromJson);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ComboEvent.EXIT_USER_MODE)
    public void exitUserModel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ComboListener) obj).exitUserModel(getModeFromJson(str2));
            }
        }
    }

    private String getExtraFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has(SpeechWidget.WIDGET_EXTRA) ? jSONObject.optJSONObject(SpeechWidget.WIDGET_EXTRA).toString() : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getModeFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("mode") ? jSONObject.optString("mode") : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void openMeditationMode(String str) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isAsrModeOffline", true).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(COMBO_SKILL, COMBO_TASK, OPEN_MEDITATION_INTENT, str2);
    }

    public void closeMeditationMode(String str) {
        closeMeditationMode(str, false);
    }

    public void closeMeditationMode(String str, boolean z) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isAsrModeOffline", true).put("needTTS", z ? OOBEEvent.STRING_TRUE : OOBEEvent.STRING_FALSE).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(COMBO_SKILL, COMBO_TASK, CLOSE_MEDITATION_INTENT, str2);
    }

    public void openWaitMode(boolean z) {
        String str;
        try {
            str = new JSONObject().put("isAsrModeOffline", true).put("needTTS", z ? OOBEEvent.STRING_TRUE : OOBEEvent.STRING_FALSE).toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(COMBO_SKILL, COMBO_TASK, OPEN_WAIT_INTENT, str);
    }

    public void closeWaitMode(boolean z) {
        SpeechClient.instance().getAgent().sendEvent(ComboEvent.MODE_WAIT_OFF, "");
    }

    public int getCurrentMode() {
        return SpeechClient.instance().getQueryInjector().queryData(SpeechCarStatusEvent.STATUS_CUR_MODE, "").getInteger().intValue();
    }
}
