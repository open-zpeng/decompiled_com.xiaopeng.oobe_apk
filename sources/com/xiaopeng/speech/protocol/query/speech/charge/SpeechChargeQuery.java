package com.xiaopeng.speech.protocol.query.speech.charge;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.speech.SpeechChargeEvent;
/* loaded from: classes2.dex */
public class SpeechChargeQuery extends SpeechQuery<ISpeechChargeQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_ADD_ELECTRI)
    public int getAddedElectricity(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getAddedElectricity();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_CHARGING_ERROR)
    public int getChargingError(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargingError();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_CHARGING_GUN_STATE)
    public int getChargingGunStatus(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargingGunStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_CHARGING_STATE)
    public int getChargeStatus(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargeStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_MILEAGE_NUM)
    public int getMileageNumber(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getMileageNumber();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_WLTP_MILEAGE_NUM)
    public int getWltpMileageNumber(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getWltpMileageNumber();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_CLTC_MILEAGE_NUM)
    public int getCltcMileageNumber(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getCltcMieageNumber();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_DYNAMIC_MILEAGE_NUM)
    public int getDynamicMileageNumber(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getDynamicMileageNumber();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_ENDURANCE_MILEAGE_MODE)
    public int getEnduranceMileageMode(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getEnduranceMileageMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_ELECTRIC_PERCENT)
    public double getElectricityPercent(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getElectricityPercent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_COLD_WARN_TIP)
    public boolean getColdWarningTips(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getColdWarningTips();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_HVAC_CONSUME)
    public double getHvacConsume(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getHvacConsume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_BATTERY_WARM_STATE)
    public boolean getBatteryWarmingStatus(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getBatteryWarmingStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_AC_CHARGING_CURRENT)
    public double getACChargingCurrent(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getACChargingCurrent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_AC_CHARGING_VOLT)
    public double getACChargingVolt(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getACChargingVolt();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_DC_CHARGING_CURRENT)
    public double getDCChargingCurrent(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getDCChargingCurrent();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_DC_CHARGING_VOLT)
    public double getDCChargingVolt(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getDCChargingVolt();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_AC_INPUT_STATE)
    public boolean getACInputStatus(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getACInputStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_AVER_VEH_CONSUME)
    public String getAverageVehConsume(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getAverageVehConsume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_CHARGING_MAX_SOC)
    public int[] getChargingMaxSoc(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargingMaxSoc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_SOH)
    public double getSOH(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getSOH();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_GET_GUN_LOCK_ST)
    public boolean getChargeGunLockSt(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargeGunLockSt();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.CHARGE_GET_LIMIT_SOC)
    public int getChargeLimitSoc(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getChargeLimitSoc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.GET_BATTERY_COOL_STATUS)
    public boolean getBatteryCoolStatus(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getBatteryCoolStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = SpeechChargeEvent.GET_AVER_CONSUME_100KM)
    public String getAverageVehConsume100km(String str, String str2) {
        return ((ISpeechChargeQueryCaller) this.mQueryCaller).getAverageVehConsume100km();
    }
}
