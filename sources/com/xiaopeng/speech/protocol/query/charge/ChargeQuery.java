package com.xiaopeng.speech.protocol.query.charge;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryChargeEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ChargeQuery extends SpeechQuery<IChargeCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.IS_SUPPORT_OPEN_PORT)
    public boolean isSupportOpenPort(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).isSupportOpenPort();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_START_STATUS)
    public int getStartStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getStartStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_STOP_STATUS)
    public int getStopStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getStopStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_RESTART_STATUS)
    public int getRestartStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getRestartStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.IS_SUPPORT_LIMITS_ADJUST)
    public boolean isSupportLimitsAdjust(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).isSupportLimitsAdjust();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_LIMITS_ADJUST_MIN)
    public int getLimitsAdjustMin(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getLimitsAdjustMin();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_LIMITS_ADJUST_MAX)
    public int getLimitsAdjustMax(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getLimitsAdjustMax();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.IS_SUPPORT_SMART_MODE)
    public boolean isSupportSmartMode(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).isSupportSmartMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.IS_HAS_CHARGING_ORDER)
    public boolean isHasChargingOrder(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).isHasChargingOrder();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.IS_CHARGE_READY_PAGE)
    public boolean isChargeReadyPage(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).isChargeReadyPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.POWER_CHARGE_STATUS)
    public int getChargeStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getChargeStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_PAGE_OPEN_STATUS)
    public int getPageOpenStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getGuiPageOpenState(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_HAS_CAR_REFRIGERATOR)
    public boolean hasCarRefrigerator(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).hasCarRefrigerator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_HAS_SUN_ROOF)
    public boolean hasSunRoof(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).hasSunRoof();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_GET_TRUNK_POWER_STATUS)
    public int getTrunkPowerStatus(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getTrunkPowerStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_DISCHARGE_LIMIT_MIN_VALUE)
    public int getMinDischargeLimit(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getMinDischargeLimit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_DISCHARGE_LIMIT_MAX_VALUE)
    public int getMaxDischargeLimit(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getMaxDischargeLimit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.CHARGE_TRUNK_POWER_STATUS_OPEN)
    public int getTrunkPowerStatusForOpen(String str, String str2) {
        return ((IChargeCaller) this.mQueryCaller).getTrunkPowerStatusForOpen();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryChargeEvent.GET_ENDURANCE_MODE_STATUS)
    public boolean isSupportEnduranceMode(String str, String str2) {
        int i = 1;
        try {
            i = new JSONObject(str2).optInt("mode", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((IChargeCaller) this.mQueryCaller).isSupportEnduranceMode(i);
    }
}
