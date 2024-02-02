package com.xiaopeng.speech.protocol.node.scene;

import android.text.TextUtils;
import com.xiaopeng.libconfig.ipc.IpcConfig;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.jarvisproto.DMEnd;
import com.xiaopeng.speech.jarvisproto.DMStart;
import com.xiaopeng.speech.jarvisproto.VuiDisable;
import com.xiaopeng.speech.jarvisproto.VuiEnable;
import com.xiaopeng.speech.protocol.event.VuiEvent;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SceneNode extends SpeechNode<SceneListener> {
    List<String> supportAppNameList = Arrays.asList(IpcConfig.App.SYSTEM_SYSTEMUI, IpcConfig.App.CAR_CHARGE_CONTROL, "com.xiaopeng.speech.scenedemo", "com.xiaopeng.carspeechservice", IpcConfig.App.CAR_ACCOUNT);

    @SpeechAnnotation(event = VuiEvent.SCENE_CONTROL)
    public void onSceneEvent(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            String optString = new JSONObject(str2).optString("sceneId");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            String str3 = null;
            if (optString.contains("-")) {
                str3 = optString.split("-")[0];
            } else if (optString.contains("_")) {
                str3 = optString.split("_")[0];
            }
            if (!TextUtils.isEmpty(str3) && this.supportAppNameList.contains(str3)) {
                callSceneEvent(str, str2);
            }
        } catch (Exception unused) {
        }
    }

    @SpeechAnnotation(event = VuiEvent.SCENE_DM_START)
    public void onDMStart(String str, String str2) {
        callSceneEvent(DMStart.EVENT, str2);
    }

    @SpeechAnnotation(event = VuiEvent.SCENE_DM_END)
    public void onDMEnd(String str, String str2) {
        callSceneEvent(DMEnd.EVENT, str2);
    }

    @SpeechAnnotation(event = VuiEvent.SCENE_VUI_ENABLE)
    public void onVuiEnable(String str, String str2) {
        callSceneEvent(VuiEnable.EVENT, str2);
    }

    @SpeechAnnotation(event = VuiEvent.SCENE_VUI_DISABLE)
    public void onVuiDisable(String str, String str2) {
        callSceneEvent(VuiDisable.EVENT, str2);
    }

    @SpeechAnnotation(event = VuiEvent.SCENE_REBUILD)
    public void onRebuild(String str, String str2) {
        callSceneEvent(str, str2);
    }

    private void callSceneEvent(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((SceneListener) obj).onSceneEvent(str, str2);
                }
            }
        } catch (Exception unused) {
        }
    }
}
