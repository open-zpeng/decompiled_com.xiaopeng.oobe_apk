package com.xiaopeng.speech.protocol.query.system;

import com.xiaopeng.libtheme.ThemeManager;
import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.bean.stats.SceneSwitchStatisticsBean;
import com.xiaopeng.speech.protocol.event.query.QuerySystemEvent;
import com.xiaopeng.speech.protocol.utils.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SystemQuery extends SpeechQuery<ISystemCaller> {
    private static final int SOUND_EFFECT_MODE = 2;
    private static final String STREAM_MUSIC = "3";

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CUR_SCREEN_BRIGHTNESS)
    public int getCurScreenBrightness(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurScreenBrightness();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MAX_SCREEN_BRIGHTNESS_VALUE)
    public int getMaxScreenBrightnessValue(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getMaxScreenBrightnessValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MIN_SCREEN_BRIGHTNESS_VALUE)
    public int getMinScreenBrightnessValue(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getMinScreenBrightnessValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CUR_ICM_BRIGHTNESS)
    public int getCurIcmBrightness(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurIcmBrightness();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MAX_ICM_BRIGHTNESS_VALUE)
    public int getMaxIcmBrightnessValue(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getMaxIcmBrightnessValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MIN_ICM_BRIGHTNESS_VALUE)
    public int getMinIcmBrightnessValue(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getMinIcmBrightnessValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CUR_VOLUME)
    public int getCurVolume(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "3";
        }
        return ((ISystemCaller) this.mQueryCaller).getCurVolume(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MAX_VOLUME_VALUE)
    public int getMaxVolumeValue(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "3";
        }
        return ((ISystemCaller) this.mQueryCaller).getMaxVolumeValue(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_MIN_VOLUME_VALUE)
    public int getMinVolumeValue(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("stream_type", "3");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = "3";
        }
        return ((ISystemCaller) this.mQueryCaller).getMinVolumeValue(Integer.valueOf(StringUtil.isDecimalNumber(str3) ? str3 : "3").intValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_TIPS_VOLUME)
    public int getTipsVolume(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getTipsVolume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_PAGE_OPEN_STATUS)
    public int getGuiPageOpenState(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getGuiPageOpenState(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_SYSTEM_MUSIC_ISPLAYING)
    public int getCurrentMusicActive(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentMusicActive();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_SOUND_EFFECT_MODEL)
    public int getCurrentSoundEffectMode(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentSoundEffectMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.ISSUPPORT_SOUND_EFFECT_DYNAUDIO)
    public int isSupportSoundEffectMode(String str, String str2) {
        int i;
        try {
            i = new JSONObject(str2).optInt("mode");
        } catch (JSONException e) {
            e.printStackTrace();
            i = 2;
        }
        return ((ISystemCaller) this.mQueryCaller).isSupportSoundEffectMode(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.ISSUPPORT_SOUND_EFFECT_STYLE)
    public int isSupportSoundEffectStyle(String str, String str2) {
        int i;
        try {
            i = new JSONObject(str2).optInt(ThemeManager.AttributeSet.STYLE);
        } catch (JSONException e) {
            e.printStackTrace();
            i = 0;
        }
        return ((ISystemCaller) this.mQueryCaller).isSupportSoundEffectStyle(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CURRENT_SOUND_EFFECT_STYLE)
    public int getCurrentSoundEffectStyle(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentSoundEffectStyle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.ISSUPPORT_SOUND_SCENE)
    public int isSupportSoundEffectScene(String str, String str2) {
        int i;
        try {
            i = new JSONObject(str2).optInt(SceneSwitchStatisticsBean.NAME_SCENE);
        } catch (JSONException e) {
            e.printStackTrace();
            i = 1;
        }
        return ((ISystemCaller) this.mQueryCaller).isSupportSoundEffectScene(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CURRENT_SOUND_SCENE)
    public int getCurrentSoundEffectScene(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentSoundEffectScene();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.ISSUPPORT_HEADREST_MODE)
    public int isSupportHeadsetMode(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).isSupportHeadsetMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CURRENT_HEADREST_MODE)
    public int getCurrentHeadsetMode(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentHeadsetMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.ISUPPORT_SOUND_POSTION)
    public int isSupportSoundEffectField(String str, String str2) {
        int i;
        try {
            i = new JSONObject(str2).optInt("direction");
        } catch (JSONException e) {
            e.printStackTrace();
            i = 1;
        }
        return ((ISystemCaller) this.mQueryCaller).isSupportSoundEffectField(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CURRENT_SOUND_POSITON)
    public int getCurrentSoundEffectField(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentSoundEffectField();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_AUTO_SCREEN_STATUS)
    public int getAutoScreenStatus(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getAutoScreenStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_CURRENT_NEDC_STATUS)
    public int getCurrentNedcStatus(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getCurrentNedcStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QuerySystemEvent.GET_INTELLIGENT_DARK_LIGHT_ADAPT_STATUS)
    public int getIntelligentDarkLightAdaptStatus(String str, String str2) {
        return ((ISystemCaller) this.mQueryCaller).getIntelligentDarkLightAdaptStatus();
    }
}
