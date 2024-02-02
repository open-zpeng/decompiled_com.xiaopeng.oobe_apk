package com.xiaopeng.speech.protocol.node.scene;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.VuiEvent;
/* loaded from: classes2.dex */
public class SceneNode_Processor implements ICommandProcessor {
    private SceneNode mTarget;

    public SceneNode_Processor(SceneNode sceneNode) {
        this.mTarget = sceneNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1722601438:
                if (str.equals(VuiEvent.SCENE_VUI_DISABLE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1546876663:
                if (str.equals(VuiEvent.SCENE_VUI_ENABLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 720644575:
                if (str.equals(VuiEvent.SCENE_DM_START)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 828241388:
                if (str.equals(VuiEvent.SCENE_CONTROL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1045003449:
                if (str.equals(VuiEvent.SCENE_REBUILD)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1301293464:
                if (str.equals(VuiEvent.SCENE_DM_END)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            this.mTarget.onSceneEvent(str, str2);
        } else if (c == 1) {
            this.mTarget.onDMStart(str, str2);
        } else if (c == 2) {
            this.mTarget.onDMEnd(str, str2);
        } else if (c == 3) {
            this.mTarget.onVuiEnable(str, str2);
        } else if (c == 4) {
            this.mTarget.onVuiDisable(str, str2);
        } else if (c != 5) {
        } else {
            this.mTarget.onRebuild(str, str2);
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{VuiEvent.SCENE_CONTROL, VuiEvent.SCENE_DM_START, VuiEvent.SCENE_DM_END, VuiEvent.SCENE_VUI_ENABLE, VuiEvent.SCENE_VUI_DISABLE, VuiEvent.SCENE_REBUILD};
    }
}
