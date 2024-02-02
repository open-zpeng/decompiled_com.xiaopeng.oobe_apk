package com.xiaopeng.speech.protocol.query.speech.radio;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechRadioEvent;
/* loaded from: classes2.dex */
public class SpeechRadioQuery_Processor implements IQueryProcessor {
    private SpeechRadioQuery mTarget;

    public SpeechRadioQuery_Processor(SpeechRadioQuery speechRadioQuery) {
        this.mTarget = speechRadioQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1465934626:
                if (str.equals(SpeechRadioEvent.RADIO_STATE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -971371186:
                if (str.equals(SpeechRadioEvent.RADIO_DSP)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -47471690:
                if (str.equals(SpeechRadioEvent.RADIO_MODE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 928420887:
                if (str.equals(SpeechRadioEvent.RADIO_VOLUME_FOCUS)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1543073577:
                if (str.equals(SpeechRadioEvent.RADIO_FREQUENCY)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            return null;
                        }
                        return this.mTarget.getAudioMode(str, str2);
                    }
                    return this.mTarget.getRadioFrequency(str, str2);
                }
                return Integer.valueOf(this.mTarget.getRadioVolumeAutoFocus(str, str2));
            }
            return this.mTarget.getRadioStatus(str, str2);
        }
        return this.mTarget.getAudioDspStatus(str, str2);
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{SpeechRadioEvent.RADIO_DSP, SpeechRadioEvent.RADIO_STATE, SpeechRadioEvent.RADIO_VOLUME_FOCUS, SpeechRadioEvent.RADIO_FREQUENCY, SpeechRadioEvent.RADIO_MODE};
    }
}
