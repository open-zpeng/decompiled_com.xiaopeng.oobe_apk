package com.xiaopeng.speech.protocol.node.carcontrol;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.bean.ChangeValue;
import com.xiaopeng.speech.protocol.bean.SpeechParam;
import com.xiaopeng.speech.protocol.bean.base.CommandValue;
import com.xiaopeng.speech.protocol.event.CarcontrolEvent;
import com.xiaopeng.speech.protocol.node.carcontrol.bean.ControlReason;
import com.xiaopeng.speech.protocol.node.carcontrol.bean.SeatValue;
import com.xiaopeng.speech.protocol.node.carcontrol.bean.UserBookValue;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CarcontrolNode extends SpeechNode<CarcontrolListener> {
    private static final String KEY_PERCENT = "percent";

    private int getPercent(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(KEY_PERCENT)) {
                return jSONObject.optInt(KEY_PERCENT);
            }
            return 100;
        } catch (Exception e) {
            e.printStackTrace();
            return 100;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_HOME_OFF)
    public void onLightHomeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightHomeOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_HOME_ON)
    public void onLightHomeOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightHomeOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_LOW_OFF)
    public void onLightLowOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        ControlReason fromJson = ControlReason.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightLowOff(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_LOW_ON)
    public void onLightLowOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightLowOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_POSITION_ON)
    public void onLightPositionOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightPositionOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_POSITION_OFF)
    public void onLightPositionOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightPositionOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_AUTO_ON)
    public void onLightAutoOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAutoOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_AUTO_OFF)
    public void onLightAutoOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAutoOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MIST_LIGHT_OFF)
    public void onMistLightOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onMistLightOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MIST_LIGHT_ON)
    public void onMistLightOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onMistLightOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MIRROR_REAR_CLOSE)
    public void onMirrorRearClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onMirrorRearClose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MIRROR_REAR_ON)
    public void onMirrorRearOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onMirrorRearOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.TRUNK_OPEN)
    public void onTrunkOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onTrunkOpen();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_DRIVER_CLOSE)
    public void onWindowDriverClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(0, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_DRIVER_OPEN)
    public void onWindowDriverOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(0, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_PASSENGER_CLOSE)
    public void onWindowPassengerClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(1, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_PASSENGER_OPEN)
    public void onWindowPassengerOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(1, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOWS_CLOSE)
    public void onWindowsClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(6, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOWS_OPEN)
    public void onWindowsOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(6, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.TRUNK_CLOSE)
    public void onTrunkClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onTrunkClose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOWS_VENTILATE_ON)
    public void onWindowsVentilateOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsVentilateOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOWS_VENTILATE_OFF)
    public void onWindowsVentilateOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsVentilateOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_DRIVING_SPORT)
    public void onModesDrivingSport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesDrivingSport();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_DRIVING_CONSERVATION)
    public void onModesDrivingConservation(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesDrivingConservation();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_DRIVING_NORMAL)
    public void onModesDrivingNormal(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesDrivingNormal();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_STEERING_SOFT)
    public void onModesSteeringSoft(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesSteeringSoft();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_STEERING_NORMAL)
    public void onModesSteeringNormal(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesSteeringNormal();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MODES_STEERING_SPORT)
    public void onModesSteeringSport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesSteeringSport();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ENERGY_RECYCLE_HIGH)
    public void onEnergyRecycleHigh(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onEnergyRecycleHigh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ENERGY_RECYCLE_LOW)
    public void onEnergyRecycleLow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onEnergyRecycleLow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ENERGY_RECYCLE_MEDIA)
    public void onEnergyRecycleMedia(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onEnergyRecycleMedia();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ENERGY_RECYCLE_UP)
    public void onEnergyRecycleUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onEnergyRecycleUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ENERGY_RECYCLE_DOWN)
    public void onEnergyRecycleDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onEnergyRecycleDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_RIGHT_REAR_OPEN)
    public void onWindowRightRearOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(3, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_RIGHT_REAR_CLOSE)
    public void onWindowRightRearClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(3, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_LEFT_REAR_OPEN)
    public void onWindowLeftRearOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(2, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_LEFT_REAR_CLOSE)
    public void onWindowLeftRearClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(2, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_FRONT_OPEN)
    public void onWindowFrontOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(7, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_FRONT_CLOSE)
    public void onWindowFrontClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(7, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_REAR_OPEN)
    public void onWindowRearOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(8, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_REAR_CLOSE)
    public void onWindowRearClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(8, 1, getPercent(str2));
            }
        }
    }

    protected void onModesSportSupport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesSportSupport();
            }
        }
    }

    protected void onModesConservationSupport(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onModesConservationSupport();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_UP)
    public void onSeatMoveUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_DOWN)
    public void onSeatMoveDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_HIGHEST)
    public void onSeatMoveHighest(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveHighest();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_LOWEST)
    public void onSeatMoveLowest(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveLowest();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_BACK)
    public void onSeatMoveBack(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        ChangeValue fromJson = ChangeValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveBack(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_FORWARD)
    public void onSeatMoveForward(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        ChangeValue fromJson = ChangeValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveForward(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_REAR)
    public void onSeatMoveRear(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SpeechParam fromJson = SpeechParam.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveRear(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_MOVE_FOREMOST)
    public void onSeatMoveForemost(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatMoveForemost();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_BACKREST_BACK)
    public void onSeatBackrestBack(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SpeechParam fromJson = SpeechParam.fromJson(str2);
        ChangeValue fromJson2 = ChangeValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatBackrestBack(fromJson2, fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_BACKREST_FORWARD)
    public void onSeatBackrestForward(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        ChangeValue fromJson = ChangeValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatBackrestForward(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_BACKREST_REAR)
    public void onSeatBackrestRear(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SpeechParam fromJson = SpeechParam.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatBackrestRear(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_BACKREST_FOREMOST)
    public void onSeatBackrestForemost(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatBackrestForemost();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_ADJUST)
    public void onSeatAdjust(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        SeatValue fromJson = SeatValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatAdjust(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_LIGHT_RESUME)
    public void onControlLightResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlLightResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SEAT_RESUME)
    public void onControlSeatResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlSeatResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LOW_VOLUME_ON)
    public void onLowVolumeOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLowVolumeOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LOW_VOLUME_OFF)
    public void onLowVolumeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLowVolumeOff();
            }
        }
    }

    protected void onCheckUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        UserBookValue fromJson = UserBookValue.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onCheckUserBook(fromJson);
            }
        }
    }

    protected void onOpenUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onOpenUserBook();
            }
        }
    }

    protected void onCloseUserBook(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onCloseUserBook();
            }
        }
    }

    protected void onLightTopAutoOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightTopAutoOn();
            }
        }
    }

    protected void onLightTopAutoOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightTopAutoOff();
            }
        }
    }

    protected void onLightTopOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightTopOn();
            }
        }
    }

    protected void onLightTopOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightTopOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_ON)
    public void onLightAtmosphereOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_OFF)
    public void onLightAtmosphereOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereOff(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.MIRROR_REAR_SET)
    public void onMirrorRearSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onMirrorRearSet(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_SPEED_UP)
    public void onWiperSpeedUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperSpeedUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_SPEED_DOWN)
    public void onWiperSpeedDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperSpeedDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_SLOW)
    public void onWiperSlow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperSlow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_HIGH)
    public void onWiperHigh(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperHigh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_MEDIUM)
    public void onWiperMedium(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperMedium();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WIPER_SUPERHIGH)
    public void onWiperSuperhigh(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWiperSuperhigh();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.TRUNK_UNLOCK)
    public void onTrunkUnlock(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onTrunkUnlock();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_LEFT_CLOSE)
    public void onWindowsLeftClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(4, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_LEFT_OPEN)
    public void onWindowsLeftOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(4, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_RIGHT_CLOSE)
    public void onWindowsRightClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(5, 1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.WINDOW_RIGHT_OPEN)
    public void onWindowsRightOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onWindowsControl(5, 0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LEG_UP)
    public void onLegUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLegUp(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LEG_DOWN)
    public void onLegDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLegDown(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LEG_HIGHEST)
    public void onLegHighest(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLegHighest(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LEG_LOWEST)
    public void onLegLowest(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLegLowest(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.SEAT_RESTORE)
    public void onSeatRestore(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onSeatRestore();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.DIRECT_PORT_ON)
    public void onRightChargePortOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onChargePortControl(1, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.DIRECT_PORT_OFF)
    public void onRightChargePortClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onChargePortControl(1, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ALTERNATING_PORT_OFF)
    public void onLeftChargePortClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onChargePortControl(0, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.ALTERNATING_PORT_ON)
    public void onLeftChargePortOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onChargePortControl(0, 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.TIRE_PRESSURE_SHOW)
    public void onTirePressureShow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onTirePressureShow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_COLOR)
    public void onLightAtmosphereColor(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereColor(new CommandValue(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_BRIGHTNESS_SET)
    public void onLightAtmosphereBrightnessSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereBrightnessSet(new CommandValue(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_BRIGHTNESS_UP)
    public void onLightAtmosphereBrightnessUp() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereBrightnessUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_BRIGHTNESS_DOWN)
    public void onLightAtmosphereBrightnessDown() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereBrightnessDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_BRIGHTNESS_MAX)
    public void onLightAtmosphereBrightnessMax() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereBrightnessMax();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.LIGHT_ATMOSPHERE_BRIGHTNESS_MIN)
    public void onLightAtmosphereBrightnessMin() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onLightAtmosphereBrightnessMin();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_MOVE_UP)
    public void onPsnSeatMoveUp() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatMoveUp();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_MOVE_DOWN)
    public void onPsnSeatMoveDown() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatMoveDown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_BACKREST_BACK)
    public void onPsnSeatBackrestBack() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatBackrestBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_BACKREST_FORWARD)
    public void onPsnSeatBackrestForward() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatBackrestForward();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_MOVE_BACK)
    public void onPsnSeatMoveBack() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatMoveBack();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.PSN_SEAT_MOVE_FORWARD)
    public void onPsnSeatMoveForward() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onPsnSeatMoveForward();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_XPEDAL_ON)
    public void onControlXpedalOn() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlXpedalOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_XPEDAL_OFF)
    public void onControlXpedalOff() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlXpedalOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_LEFT_DOOR_ON)
    public void onControlScissorLeftDoorOn() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorLeftDoorOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_RIGHT_DOOR_ON)
    public void onControlScissorRightDoorOn() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorRightDoorOn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_LEFT_DOOR_OFF)
    public void onControlScissorLeftDoorOff() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorLeftDoorOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_RIGHT_DOOR_OFF)
    public void onControlScissorRightDoorOff() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorRightDoorOff();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_LEFT_DOOR_PAUSE)
    public void onControlScissorLeftDoorPause() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorLeftDoorPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_SCISSOR_RIGHT_DOOR_PAUSE)
    public void onControlScissorRightDoorPause() {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlScissorRightDoorPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_ELECTRIC_CURTAIN_OPEN)
    public void onControlElectricCurtainOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlSunShade(0, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_ELECTRIC_CURTAIN_CLOSE)
    public void onControlElectricCurtainClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlSunShade(1, getPercent(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_COMFORTABLE_DRIVING_MODE_OPEN)
    public void onControlComfortableDrivingModeOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlComfortableDrivingModeOpen();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_COMFORTABLE_DRIVING_MODE_CLOSE)
    public void onControlComfortableDrivingModeClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).onControlComfortableDrivingModeClose();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_LIGHT_LANGUAGE_ON)
    public void onControlLightLanguageOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).setLluSw(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_LIGHT_LANGUAGE_OFF)
    public void onControlLightLanguageOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).setLluSw(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = CarcontrolEvent.CONTROL_CAPSULE_UNIVERSAL_SET)
    public void setCapsuleUniversal(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((CarcontrolListener) obj).setCapsuleUniversal(getModeFromJson(str2));
            }
        }
    }

    private String getModeFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("mode") ? jSONObject.optString("mode") : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
