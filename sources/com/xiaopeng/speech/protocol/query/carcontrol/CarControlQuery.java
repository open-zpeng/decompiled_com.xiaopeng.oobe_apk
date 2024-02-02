package com.xiaopeng.speech.protocol.query.carcontrol;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryCarControlEvent;
/* loaded from: classes2.dex */
public class CarControlQuery extends SpeechQuery<ICarControlCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_CLOSE_MIRROR)
    public boolean isSupportCloseMirror(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportCloseMirror();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_MIRROR_STATUS)
    public int getMirrorStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getMirrorStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_SUPPORT_OPEN_TRUNK)
    public int getSupportOpenTrunk(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getSupportOpenTrunk();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_SUPPORT_CLOSE_TRUNK)
    public int getSupportCloseTrunk(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getSupportCloseTrunk();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_WINDOW_STATUS)
    public int getWindowStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getWindowStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_ENERGY_RECYCLE)
    public boolean isSupportEngryRecycle(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportEnergyRecycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_SUPPORT_SEAT)
    public int getSupportSeat(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getSupportSeat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_DRIVING_MODE)
    public boolean isSupportDrivingMode(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportDrivingMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_LITE_ATMOSPHERE)
    public boolean isSupportAtmosphere(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportAtmosphere();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_TAIRPRESSURE_NORMAL)
    public boolean isTirePressureNormal(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isTirePressureNormal();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_UNLOCK_TRUNK)
    public boolean isSupportUnlockTrunk(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportUnlockTrunk();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_CLOSE_L_CHARGE_PORT)
    public boolean isSupportCloseLeftChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportControlChargePort(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_CLOSE_R_CHARGE_PORT)
    public boolean isSupportCloseRightChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportControlChargePort(1, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_OPEN_L_CHARGE_PORT)
    public boolean isSupportOpenLeftChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportControlChargePort(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_OPEN_R_CHARGE_PORT)
    public boolean isSupportOpenRightChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportControlChargePort(1, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_SUPPORT_CONTROL_MIRROR)
    public boolean isSupportControlMirror(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSupportControlMirror();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_LEG_HEIGHT_GET)
    public int getLegHeight(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getLegHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_CLOSE_L_CHARGE_PORT)
    public int getStatusCloseLeftChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getStatusChargePortControl(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_CLOSE_R_CHARGE_PORT)
    public int getStatusCloseRightChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getStatusChargePortControl(1, 1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_OPEN_L_CHARGE_PORT)
    public int getStatusOpenLeftChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getStatusChargePortControl(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_OPEN_R_CHARGE_PORT)
    public int getStatusOpenRightChargePort(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getStatusChargePortControl(1, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_LIGHT_ATMOSPHERE_BRIGHTNESS)
    public int getAtmosphereBrightnessStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getAtmosphereBrightnessStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_STATUS_LIGHT_ATMOSPHERE_COLOR)
    public int getAtmosphereColorStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getAtmosphereColorStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.IS_STEERING_MODE_ADJUSTABLE)
    public int isSteeringModeAdjustable(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).isSteeringModeAdjustable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_PAGE_OPEN_STATUS)
    public int getGuiPageOpenState(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getGuiPageOpenState(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_CURR_WIPER_LEVEL)
    public int getWiperInterval(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getWiperInterval();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_PSN_SUPPORT_SEAT)
    public int getSupportPsnSeat(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getSupportPsnSeat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_EXTRA_TRUNK_STATUS)
    public int getExtraTrunkStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getExtraTrunkStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_TRUNK_STATUS)
    public int getTrunkStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getTrunkStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.GET_SET_SPEECH_WAKEUP_STATUS)
    public int getDoorKeyValue(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getDoorKeyValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_LOW_SPEED_ANALOG_SOUND_SUPPORT)
    public int getControlLowSpeedAnalogSoundSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlLowSpeedAnalogSoundSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_XPEDAL_SUPPORT)
    public int getControlXpedalSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlXpedalSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SUPPORT_ENERGY_RECYCLE_REASON)
    public int getControlSupportEnergyRecycleReason(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlSupportEnergyRecycleReason();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_LEFT_OPEN_SUPPORT)
    public int getControlScissorDoorLeftOpenSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorLeftOpenSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_RIGHT_OPEN_SUPPORT)
    public int getControlScissorDoorCloseSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorRightOpenSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_LEFT_CLOSE_SUPPORT)
    public int getControlScissorDoorLeftCloseSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorLeftCloseSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_RIGHT_CLOSE_SUPPORT)
    public int getControlScissorDoorRightCloseSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorRightCloseSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_LEFT_RUNNING_SUPPORT)
    public int getControlScissorDoorLeftRunningSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorLeftRunningSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_SCISSOR_DOOR_RIGHT_RUNNING_SUPPORT)
    public int getControlScissorDoorRightRunningSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlScissorDoorRightRunningSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_ELECTRIC_CURTAIN_SUPPORT)
    public int getControlElectricCurtainSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlElectricCurtainSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_GET_WINDOWS_STATE_SUPPORT)
    public float[] getControlWindowsStateSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlWindowsStateSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_COMFORTABLE_DRIVING_MODE_SUPPORT)
    public int getControlComfortableDrivingModeSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlComfortableDrivingModeSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_LAMP_SIGNAL_SUPPORT)
    public int getControlLampSignalSupport(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getControlLampSignalSupport();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.XPU_NGP_STATUS)
    public int getNgpStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getNgpStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_VIP_CHAIR_STATUS)
    public int getVipChairStatus(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getVipChairStatus();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryCarControlEvent.CONTROL_CAPSULE_MODE)
    public int getCapsuleCurrentMode(String str, String str2) {
        return ((ICarControlCaller) this.mQueryCaller).getCapsuleCurrentMode();
    }
}
