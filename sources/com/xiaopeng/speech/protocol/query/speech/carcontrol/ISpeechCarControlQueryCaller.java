package com.xiaopeng.speech.protocol.query.speech.carcontrol;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface ISpeechCarControlQueryCaller extends IQueryCaller {
    int getACCStatus();

    int getATWSState();

    default int getAbnormalTirePressureState() {
        return 0;
    }

    int[] getAllTirePressureWarnings();

    int getBCMIgStatus();

    int getBlindAreaDetectionWarning();

    float getCarSpeed();

    int[] getChairDirection();

    int[] getChairLocationValue();

    boolean getChairWelcomeMode();

    int getCruiseActive();

    boolean getDoorLockState();

    int[] getDoorsState();

    boolean getDriveAutoLock();

    float getDriveTotalMileage();

    int getDriverSeatStatus();

    int getDrivingMode();

    boolean getElectricSeatBelt();

    boolean getEmergencyBrakeWarning();

    int getEnergyRecycleLevel();

    boolean getFarLampState();

    int getFrontCollisionSecurity();

    int getHeadLampGroup();

    float getICMDriverTempValue();

    int getICMWindBlowMode();

    int getICMWindLevel();

    int getIcmAlarmVolume();

    boolean getIcmConnectionState();

    int getIntelligentSpeedLimit();

    boolean getInternalLight();

    int getLCCStatus();

    int getLaneChangeAssist();

    int getLaneDepartureWarning();

    float getLastChargeMileage();

    float getLastStartUpMileage();

    int getLightMeHome();

    boolean getLocationLampState();

    int getLowSocStatus();

    float getMeterMileageA();

    float getMeterMileageB();

    boolean getNearLampState();

    int getOled();

    boolean getParkingAutoUnlock();

    default int getPassengerSeatStatus() {
        return -1;
    }

    boolean getRadarWarningVoiceStatus();

    boolean getRearFogLamp();

    boolean getRearSeatBeltWarning();

    int getRiseSpeakerStatus();

    boolean getSeatErrorState();

    int getShiftStatus();

    int getSideReversingWarning();

    boolean getSpeedLimitWarningSwitch();

    int getSpeedLimitWarningValue();

    float getSteerWheelRotationAngle();

    int getSteeringWheelEPS();

    default int getSystemFaultWarnLampStatus() {
        return 0;
    }

    float[] getTirePressureAll();

    int getTrunk();

    default int getTrunkOpennerStatus() {
        return 0;
    }

    int getUnlockResponse();

    boolean getWelcomeModeBackStatus();

    int getWindowLockStatus();

    float[] getWindowsState();

    int getWiperInterval();

    boolean isCarTrip();

    default int[] getPassengerChairLocationValue() {
        return new int[4];
    }
}
