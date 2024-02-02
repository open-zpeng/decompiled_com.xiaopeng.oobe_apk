package com.xiaopeng.speech.protocol.query.speech.charge;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechChargeEvent;
/* loaded from: classes2.dex */
public class SpeechChargeQuery_Processor implements IQueryProcessor {
    private SpeechChargeQuery mTarget;

    public SpeechChargeQuery_Processor(SpeechChargeQuery speechChargeQuery) {
        this.mTarget = speechChargeQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -2112164824:
                if (str.equals(SpeechChargeEvent.CHARGE_MILEAGE_NUM)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1562235108:
                if (str.equals(SpeechChargeEvent.CHARGE_GET_GUN_LOCK_ST)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -1431793793:
                if (str.equals(SpeechChargeEvent.CHARGE_ADD_ELECTRI)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1056966063:
                if (str.equals(SpeechChargeEvent.CHARGE_DC_CHARGING_CURRENT)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -1033894871:
                if (str.equals(SpeechChargeEvent.CHARGE_DC_CHARGING_VOLT)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -954113537:
                if (str.equals(SpeechChargeEvent.CHARGE_WLTP_MILEAGE_NUM)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -927637931:
                if (str.equals(SpeechChargeEvent.CHARGE_AVER_VEH_CONSUME)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -735246937:
                if (str.equals(SpeechChargeEvent.GET_BATTERY_COOL_STATUS)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -685146427:
                if (str.equals(SpeechChargeEvent.CHARGE_CHARGING_ERROR)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -672173746:
                if (str.equals(SpeechChargeEvent.CHARGE_CHARGING_STATE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -460071101:
                if (str.equals(SpeechChargeEvent.CHARGE_COLD_WARN_TIP)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -370089957:
                if (str.equals(SpeechChargeEvent.CHARGE_AC_INPUT_STATE)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 105277287:
                if (str.equals(SpeechChargeEvent.GET_AVER_CONSUME_100KM)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 185907206:
                if (str.equals(SpeechChargeEvent.CHARGE_CLTC_MILEAGE_NUM)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 299529912:
                if (str.equals(SpeechChargeEvent.CHARGE_HVAC_CONSUME)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 391995010:
                if (str.equals(SpeechChargeEvent.CHARGE_GET_LIMIT_SOC)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 497707660:
                if (str.equals(SpeechChargeEvent.CHARGE_AC_CHARGING_VOLT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 610188398:
                if (str.equals(SpeechChargeEvent.CHARGE_ELECTRIC_PERCENT)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 855595168:
                if (str.equals(SpeechChargeEvent.CHARGE_CHARGING_GUN_STATE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 959650120:
                if (str.equals(SpeechChargeEvent.CHARGE_ENDURANCE_MILEAGE_MODE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 984976355:
                if (str.equals(SpeechChargeEvent.CHARGE_BATTERY_WARM_STATE)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1026255738:
                if (str.equals(SpeechChargeEvent.CHARGE_CHARGING_MAX_SOC)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1416974162:
                if (str.equals(SpeechChargeEvent.CHARGE_SOH)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1476449550:
                if (str.equals(SpeechChargeEvent.CHARGE_AC_CHARGING_CURRENT)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1865250553:
                if (str.equals(SpeechChargeEvent.CHARGE_DYNAMIC_MILEAGE_NUM)) {
                    c = 7;
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
                return Integer.valueOf(this.mTarget.getAddedElectricity(str, str2));
            case 1:
                return Integer.valueOf(this.mTarget.getChargingError(str, str2));
            case 2:
                return Integer.valueOf(this.mTarget.getChargingGunStatus(str, str2));
            case 3:
                return Integer.valueOf(this.mTarget.getChargeStatus(str, str2));
            case 4:
                return Integer.valueOf(this.mTarget.getMileageNumber(str, str2));
            case 5:
                return Integer.valueOf(this.mTarget.getWltpMileageNumber(str, str2));
            case 6:
                return Integer.valueOf(this.mTarget.getCltcMileageNumber(str, str2));
            case 7:
                return Integer.valueOf(this.mTarget.getDynamicMileageNumber(str, str2));
            case '\b':
                return Integer.valueOf(this.mTarget.getEnduranceMileageMode(str, str2));
            case '\t':
                return Double.valueOf(this.mTarget.getElectricityPercent(str, str2));
            case '\n':
                return Boolean.valueOf(this.mTarget.getColdWarningTips(str, str2));
            case 11:
                return Double.valueOf(this.mTarget.getHvacConsume(str, str2));
            case '\f':
                return Boolean.valueOf(this.mTarget.getBatteryWarmingStatus(str, str2));
            case '\r':
                return Double.valueOf(this.mTarget.getACChargingCurrent(str, str2));
            case 14:
                return Double.valueOf(this.mTarget.getACChargingVolt(str, str2));
            case 15:
                return Double.valueOf(this.mTarget.getDCChargingCurrent(str, str2));
            case 16:
                return Double.valueOf(this.mTarget.getDCChargingVolt(str, str2));
            case 17:
                return Boolean.valueOf(this.mTarget.getACInputStatus(str, str2));
            case 18:
                return this.mTarget.getAverageVehConsume(str, str2);
            case 19:
                return this.mTarget.getChargingMaxSoc(str, str2);
            case 20:
                return Double.valueOf(this.mTarget.getSOH(str, str2));
            case 21:
                return Boolean.valueOf(this.mTarget.getChargeGunLockSt(str, str2));
            case 22:
                return Integer.valueOf(this.mTarget.getChargeLimitSoc(str, str2));
            case 23:
                return Boolean.valueOf(this.mTarget.getBatteryCoolStatus(str, str2));
            case 24:
                return this.mTarget.getAverageVehConsume100km(str, str2);
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{SpeechChargeEvent.CHARGE_ADD_ELECTRI, SpeechChargeEvent.CHARGE_CHARGING_ERROR, SpeechChargeEvent.CHARGE_CHARGING_GUN_STATE, SpeechChargeEvent.CHARGE_CHARGING_STATE, SpeechChargeEvent.CHARGE_MILEAGE_NUM, SpeechChargeEvent.CHARGE_WLTP_MILEAGE_NUM, SpeechChargeEvent.CHARGE_CLTC_MILEAGE_NUM, SpeechChargeEvent.CHARGE_DYNAMIC_MILEAGE_NUM, SpeechChargeEvent.CHARGE_ENDURANCE_MILEAGE_MODE, SpeechChargeEvent.CHARGE_ELECTRIC_PERCENT, SpeechChargeEvent.CHARGE_COLD_WARN_TIP, SpeechChargeEvent.CHARGE_HVAC_CONSUME, SpeechChargeEvent.CHARGE_BATTERY_WARM_STATE, SpeechChargeEvent.CHARGE_AC_CHARGING_CURRENT, SpeechChargeEvent.CHARGE_AC_CHARGING_VOLT, SpeechChargeEvent.CHARGE_DC_CHARGING_CURRENT, SpeechChargeEvent.CHARGE_DC_CHARGING_VOLT, SpeechChargeEvent.CHARGE_AC_INPUT_STATE, SpeechChargeEvent.CHARGE_AVER_VEH_CONSUME, SpeechChargeEvent.CHARGE_CHARGING_MAX_SOC, SpeechChargeEvent.CHARGE_SOH, SpeechChargeEvent.CHARGE_GET_GUN_LOCK_ST, SpeechChargeEvent.CHARGE_GET_LIMIT_SOC, SpeechChargeEvent.GET_BATTERY_COOL_STATUS, SpeechChargeEvent.GET_AVER_CONSUME_100KM};
    }
}
