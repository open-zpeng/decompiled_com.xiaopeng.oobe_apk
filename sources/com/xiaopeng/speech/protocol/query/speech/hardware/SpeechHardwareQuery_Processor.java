package com.xiaopeng.speech.protocol.query.speech.hardware;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechHardwareEvent;
/* loaded from: classes2.dex */
public class SpeechHardwareQuery_Processor implements IQueryProcessor {
    private SpeechHardwareQuery mTarget;

    public SpeechHardwareQuery_Processor(SpeechHardwareQuery speechHardwareQuery) {
        this.mTarget = speechHardwareQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1802748326:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CAR_TYPE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1289226848:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CPU_TEMP)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -147500196:
                if (str.equals(SpeechHardwareEvent.HARDWARE_ROLL_SPEED)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 412745551:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CTRL_CURR)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 413236465:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CTRL_TEMP)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 413305630:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CTRL_VOLT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1437417016:
                if (str.equals(SpeechHardwareEvent.HARDWARE_IPU_FAIL_INFO)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1466238608:
                if (str.equals(SpeechHardwareEvent.HARDWARE_MCU_ID)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1677700304:
                if (str.equals(SpeechHardwareEvent.HARDWARE_TORQUE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1749101026:
                if (str.equals(SpeechHardwareEvent.HARDWARE_STREAM_TYPE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 2019491538:
                if (str.equals(SpeechHardwareEvent.HARDWARE_MOTOR_STATE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2120141088:
                if (str.equals(SpeechHardwareEvent.HARDWARE_CHECKLIST)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 2143370611:
                if (str.equals(SpeechHardwareEvent.HARDWARE_MOTOR_TEMP)) {
                    c = 6;
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
                return this.mTarget.getMcuHardWareId(str, str2);
            case 1:
                return Integer.valueOf(this.mTarget.getCpuTemperature(str, str2));
            case 2:
                return Integer.valueOf(this.mTarget.getIpuFailStInfo(str, str2));
            case 3:
                return Double.valueOf(this.mTarget.getCtrlVolt(str, str2));
            case 4:
                return Double.valueOf(this.mTarget.getCtrlCurr(str, str2));
            case 5:
                return Integer.valueOf(this.mTarget.getCtrlTemp(str, str2));
            case 6:
                return Integer.valueOf(this.mTarget.getMotorTemp(str, str2));
            case 7:
                return Double.valueOf(this.mTarget.getTorque(str, str2));
            case '\b':
                return Integer.valueOf(this.mTarget.getRollSpeed(str, str2));
            case '\t':
                return Integer.valueOf(this.mTarget.getMotorStatus(str, str2));
            case '\n':
                return this.mTarget.getCarType(str, str2);
            case 11:
                return Integer.valueOf(this.mTarget.getStreamType(str, str2));
            case '\f':
                return Integer.valueOf(this.mTarget.getChecklistStatus(str, str2));
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{SpeechHardwareEvent.HARDWARE_MCU_ID, SpeechHardwareEvent.HARDWARE_CPU_TEMP, SpeechHardwareEvent.HARDWARE_IPU_FAIL_INFO, SpeechHardwareEvent.HARDWARE_CTRL_VOLT, SpeechHardwareEvent.HARDWARE_CTRL_CURR, SpeechHardwareEvent.HARDWARE_CTRL_TEMP, SpeechHardwareEvent.HARDWARE_MOTOR_TEMP, SpeechHardwareEvent.HARDWARE_TORQUE, SpeechHardwareEvent.HARDWARE_ROLL_SPEED, SpeechHardwareEvent.HARDWARE_MOTOR_STATE, SpeechHardwareEvent.HARDWARE_CAR_TYPE, SpeechHardwareEvent.HARDWARE_STREAM_TYPE, SpeechHardwareEvent.HARDWARE_CHECKLIST};
    }
}
