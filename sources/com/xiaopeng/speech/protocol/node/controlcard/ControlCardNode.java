package com.xiaopeng.speech.protocol.node.controlcard;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.bean.CardValue;
import com.xiaopeng.speech.protocol.event.ControlCardEvent;
/* loaded from: classes2.dex */
public class ControlCardNode extends SpeechNode<ControlCardListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_TEMP)
    public void showAcTempCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcTemp = CardValue.fromJsonForAcTemp(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcTempCard(fromJsonForAcTemp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_DRIVER_TEMP)
    public void showAcDriverTempCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcTemp = CardValue.fromJsonForAcTemp(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcDriverTempCard(fromJsonForAcTemp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_PASSENGER_TEMP)
    public void showAcPassengerTempCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcTemp = CardValue.fromJsonForAcTemp(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcPassengerTempCard(fromJsonForAcTemp);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_WIND)
    public void showAcWindCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcWindLv = CardValue.fromJsonForAcWindLv(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcWindCard(fromJsonForAcWindLv);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_ATMOSPWHERE_BRIGHTNESS)
    public void showAtmosphereBrightnessCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAtmosphereBrightness = CardValue.fromJsonForAtmosphereBrightness(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAtmosphereBrightnessCard(fromJsonForAtmosphereBrightness);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_ATMOSPWHERE_COLOR)
    public void showAtmosphereBrightnessColorCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAtmosphereColor = CardValue.fromJsonForAtmosphereColor(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAtmosphereBrightnessColorCard(fromJsonForAtmosphereColor);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_SEAT_HEATING_DRIVER)
    public void showAcSeatHeatingDriverCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcSeatHeating = CardValue.fromJsonForAcSeatHeating(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcSeatHeatingDriverCard(fromJsonForAcSeatHeating);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_SEAT_HEATING_PASSENGER)
    public void showAcSeatHeatingPassengerCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcSeatHeating = CardValue.fromJsonForAcSeatHeating(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcSeatHeatingPassengerCard(fromJsonForAcSeatHeating);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_AC_SEAT_VENTILATE_DRIVER)
    public void showAcSeatVentilateDriverCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForAcSeatVentilate = CardValue.fromJsonForAcSeatVentilate(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showAcSeatVentilateDriverCard(fromJsonForAcSeatVentilate);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_SYSTEM_SCREEN_BRIGHTNESS)
    public void showSystemScreenBrightnessCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForScreenBrightness = CardValue.fromJsonForScreenBrightness(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showSystemScreenBrightnessCard(fromJsonForScreenBrightness);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ControlCardEvent.SHOW_CARD_SYSTEM_ICM_BRIGHTNESS)
    public void showSystemIcmBrightnessCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            CardValue fromJsonForIcmBrightness = CardValue.fromJsonForIcmBrightness(str2);
            for (Object obj : collectCallbacks) {
                ((ControlCardListener) obj).showSystemIcmBrightnessCard(fromJsonForIcmBrightness);
            }
        }
    }
}
