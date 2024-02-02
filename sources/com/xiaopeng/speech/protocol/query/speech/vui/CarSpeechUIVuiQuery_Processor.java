package com.xiaopeng.speech.protocol.query.speech.vui;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.CarSpeechUIVuiEvent;
/* loaded from: classes2.dex */
public class CarSpeechUIVuiQuery_Processor implements IQueryProcessor {
    private CarSpeechUIVuiQuery mTarget;

    public CarSpeechUIVuiQuery_Processor(CarSpeechUIVuiQuery carSpeechUIVuiQuery) {
        this.mTarget = carSpeechUIVuiQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1732965939:
                if (str.equals(CarSpeechUIVuiEvent.VUI_XSLIDER_SETVALUE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1581896649:
                if (str.equals(CarSpeechUIVuiEvent.VUI_ELEMENT_CHECKBOX_CHECKED)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1313067648:
                if (str.equals(CarSpeechUIVuiEvent.VUI_STATEFULBUTTON_CHECKED)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -973678603:
                if (str.equals(CarSpeechUIVuiEvent.VUI_ELEMENT_RADIOBUTTON_CHECKED)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -841064778:
                if (str.equals(CarSpeechUIVuiEvent.VUI_XSWITCH_CHECKED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -465782917:
                if (str.equals(CarSpeechUIVuiEvent.VUI_XTABLAYOUT_SELECTED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1309650368:
                if (str.equals(CarSpeechUIVuiEvent.VUI_STATEFULBUTTON_SETVALUE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1786033643:
                if (str.equals(CarSpeechUIVuiEvent.VUI_ELEMENT_SCROLL_STATE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1935252304:
                if (str.equals(CarSpeechUIVuiEvent.VUI_ELEMENT_ENABLED)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return Boolean.valueOf(this.mTarget.isSwitchChecked(str, str2));
            case 1:
                return Boolean.valueOf(this.mTarget.isTableLayoutSelected(str, str2));
            case 2:
                return Integer.valueOf(this.mTarget.getXSliderIndex(str, str2));
            case 3:
                return Boolean.valueOf(this.mTarget.isStatefulButtonChecked(str, str2));
            case 4:
                return this.mTarget.getStatefulButtonValue(str, str2);
            case 5:
                return Boolean.valueOf(this.mTarget.isElementEnabled(str, str2));
            case 6:
                return Boolean.valueOf(this.mTarget.isElementCanScrolled(str, str2));
            case 7:
                return Boolean.valueOf(this.mTarget.isCheckBoxChecked(str, str2));
            case '\b':
                return Boolean.valueOf(this.mTarget.isRadioButtonChecked(str, str2));
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{CarSpeechUIVuiEvent.VUI_XSWITCH_CHECKED, CarSpeechUIVuiEvent.VUI_XTABLAYOUT_SELECTED, CarSpeechUIVuiEvent.VUI_XSLIDER_SETVALUE, CarSpeechUIVuiEvent.VUI_STATEFULBUTTON_CHECKED, CarSpeechUIVuiEvent.VUI_STATEFULBUTTON_SETVALUE, CarSpeechUIVuiEvent.VUI_ELEMENT_ENABLED, CarSpeechUIVuiEvent.VUI_ELEMENT_SCROLL_STATE, CarSpeechUIVuiEvent.VUI_ELEMENT_CHECKBOX_CHECKED, CarSpeechUIVuiEvent.VUI_ELEMENT_RADIOBUTTON_CHECKED};
    }
}
