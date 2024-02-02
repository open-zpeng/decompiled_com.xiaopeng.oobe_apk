package com.xiaopeng.speech.protocol.node.system;

import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.bean.AdjustValue;
import com.xiaopeng.speech.protocol.bean.VolumeValue;
import com.xiaopeng.speech.protocol.bean.stats.SceneSwitchStatisticsBean;
import com.xiaopeng.speech.protocol.event.SystemEvent;
import com.xiaopeng.speech.protocol.utils.StringUtil;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SystemNode extends SpeechNode<SystemListener> {
    public static final String NAVI_VOICE_DATA = "{\"stream_type\":9}";
    private static final String STREAM_MUSIC = "3";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_UP)
    public void onScreenBrightnessUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_MAX)
    public void onScreenBrightnessMax(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessMax();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_DOWN)
    public void onScreenBrightnessDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_MIN)
    public void onScreenBrightnessMin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessMin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.THEME_MODE_AUTO)
    public void onThemeModeAuto(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onThemeModeAuto();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.THEME_MODE_DAY)
    public void onThemeModeDay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onThemeModeDay();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.THEME_MODE_NIGHT)
    public void onThemeModeNight(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onThemeModeNight();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_MODE_CLEAN)
    public void onScreenModeClean(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenModeClean();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.WIFI_OFF)
    public void onWifiOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onWifiOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.WIFI_ON)
    public void onWifiOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onWifiOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.BLUETOOTH_OFF)
    public void onBluetoothOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBluetoothOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.BLUETOOTH_ON)
    public void onBluetoothOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBluetoothOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_UP)
    public void onVolumeUp(String str, String str2) {
        VolumeValue fromJson = VolumeValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeUp(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_SET)
    public void onVolumeSet(String str, String str2) {
        VolumeValue fromJson = VolumeValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeSet(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_DOWN)
    public void onVolumeDown(String str, String str2) {
        VolumeValue fromJson = VolumeValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeDown(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_MAX)
    public void onVolumeMax(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (Exception e) {
            e.printStackTrace();
            str3 = "3";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeMax(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_MIN)
    public void onVolumeMin(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (Exception e) {
            e.printStackTrace();
            str3 = "3";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeMin(Integer.valueOf(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue()).intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_MUTE)
    public void onVolumeMute(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (Exception e) {
            e.printStackTrace();
            str3 = "3";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeMute(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_UNMUTE)
    public void onVolumeUnmute(String str, String str2) {
        String str3;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (Exception e) {
            e.printStackTrace();
            str3 = "3";
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeUnmute(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_RESUME)
    public void onVolumeResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.BRIGHTNESS_UP_PERCENT)
    public void onBrightnessUpPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBrightnessUpPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.BRIGHTNESS_SET_PERCENT)
    public void onScreenBrightnessSetPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBrightnessSetPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_SET)
    public void onBrightnessSet(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBrightnessSet(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_SET)
    public void onIcmBrightnessSet(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessSet(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_UP)
    public void onIcmBrightnessUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_MAX)
    public void onIcmBrightnessMax(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessMax();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_DOWN)
    public void onIcmBrightnessDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_MIN)
    public void onIcmBrightnessMin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessMin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SETTING_PAGE_OPEN)
    public void onOpenSettingPage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onOpenSettingPage();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.WIFI_SETTING_OPEN)
    public void onOpenWifiPage(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onOpenWifiPage();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.BRIGHTNESS_DOWN_PERCENT)
    public void onBrightnessDownPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onBrightnessDownPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_UP_PERCENT)
    public void onIcmBrightnessUpPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessUpPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_SET_PERCENT)
    public void onIcmBrightnessSetPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessSetPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.ICM_BRIGHTNESS_DOWN_PERCENT)
    public void onIcmBrightnessDownPercent(String str, String str2) {
        AdjustValue fromJson = AdjustValue.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onIcmBrightnessDownPercent(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_OFF)
    public void onScreenOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_ON)
    public void onScreenOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_STOP)
    public void onScreenBrightnessStop(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessStop();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_AUTO_ON)
    public void onScreenBrightnessAutoOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessAutoOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SCREEN_BRIGHTNESS_AUTO_OFF)
    public void onScreenBrightnessAutoOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onScreenBrightnessAutoOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_NOTIFICATION_MAX)
    public void onVolumeNotificationMax(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeNotificationMax();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_NOTIFICATION_MIN)
    public void onVolumeNotificationMin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeNotificationMin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_NOTIFICATION_MEDIUM)
    public void onVolumeNotificationMedium(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeNotificationMedium();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_NOTIFICATION_UP)
    public void onVolumeNotificationUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeNotificationUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.VOLUME_NOTIFICATION_DOWN)
    public void onVolumeNotificationDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onVolumeNotificationDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SOUND_EFFECT_DIRECTION_SET)
    public void onSoundEffectField(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt("direction");
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onSoundEffectField(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SOUND_EFFECT_MODE_SET)
    public void onSoundEffectMode(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt("mode");
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onSoundEffectMode(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SOUND_EFFECT_SCENE_SET)
    public void onSoundEffectScene(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt(SceneSwitchStatisticsBean.NAME_SCENE);
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onSoundEffectScene(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.SOUND_EFFECT_STYlE_SET)
    public void onSoundEffectStyle(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt(ThemeManager.AttributeSet.STYLE);
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onSoundEffectStyle(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SystemEvent.HEADREST_MODE_SET)
    public void onHeadsetMode(String str, String str2) {
        int i;
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        try {
            i = new JSONObject(str2).optInt("mode");
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SystemListener) obj).onHeadsetMode(i);
            }
        }
    }
}
