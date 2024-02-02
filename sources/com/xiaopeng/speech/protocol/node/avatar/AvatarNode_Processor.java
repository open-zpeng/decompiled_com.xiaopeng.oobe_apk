package com.xiaopeng.speech.protocol.node.avatar;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.jarvisproto.AvatarExpressionEvent;
import com.xiaopeng.speech.jarvisproto.AvatarStatus;
import com.xiaopeng.speech.jarvisproto.AvatarWakeupDisable;
import com.xiaopeng.speech.jarvisproto.AvatarWakeupEnable;
import com.xiaopeng.speech.jarvisproto.DMListening;
import com.xiaopeng.speech.jarvisproto.DMPersonalRun;
import com.xiaopeng.speech.jarvisproto.DMPersonalRunExit;
import com.xiaopeng.speech.jarvisproto.DMRecognized;
import com.xiaopeng.speech.jarvisproto.DMSpeaking;
import com.xiaopeng.speech.jarvisproto.DMUnderstanding;
import com.xiaopeng.speech.protocol.event.ContextEvent;
/* loaded from: classes2.dex */
public class AvatarNode_Processor implements ICommandProcessor {
    private AvatarNode mTarget;

    public AvatarNode_Processor(AvatarNode avatarNode) {
        this.mTarget = avatarNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1543075995:
                if (str.equals(AvatarWakeupDisable.EVENT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1187871553:
                if (str.equals(DMListening.EVENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -671951780:
                if (str.equals(ContextEvent.WIDGET_CUSTOM)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -511944871:
                if (str.equals(DMPersonalRun.EVENT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -229200237:
                if (str.equals(ContextEvent.WIDGET_SEARCH)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -206519100:
                if (str.equals(AvatarExpressionEvent.EVENT)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 260029798:
                if (str.equals(AvatarWakeupEnable.EVENT)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 337158212:
                if (str.equals(AvatarStatus.EVENT)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 402709913:
                if (str.equals(ContextEvent.WIDGET_MEDIA)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 843973307:
                if (str.equals(ContextEvent.WIDGET_CARD)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 844249161:
                if (str.equals(ContextEvent.WIDGET_LIST)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 844483800:
                if (str.equals(ContextEvent.WIDGET_TEXT)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1309193744:
                if (str.equals(DMPersonalRunExit.EVENT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1330018892:
                if (str.equals(DMSpeaking.EVENT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1386014226:
                if (str.equals(DMRecognized.EVENT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1566189864:
                if (str.equals(DMUnderstanding.EVENT)) {
                    c = 3;
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
                this.mTarget.onSilence(str, str2);
                return;
            case 1:
                this.mTarget.onListening(str, str2);
                return;
            case 2:
                this.mTarget.onSpeaking(str, str2);
                return;
            case 3:
                this.mTarget.onUnderstanding(str, str2);
                return;
            case 4:
                this.mTarget.onPersonalRunning(str, str2);
                return;
            case 5:
                this.mTarget.onPersonalExit(str, str2);
                return;
            case 6:
                this.mTarget.onAvatarExpression(str, str2);
                return;
            case 7:
                this.mTarget.onWidgetCustom(str, str2);
                return;
            case '\b':
                this.mTarget.onWidgetText(str, str2);
                return;
            case '\t':
                this.mTarget.onWidgetList(str, str2);
                return;
            case '\n':
                this.mTarget.onWidgetMedia(str, str2);
                return;
            case 11:
                this.mTarget.onWidgetCard(str, str2);
                return;
            case '\f':
                this.mTarget.onWidgetSearch(str, str2);
                return;
            case '\r':
                this.mTarget.onAvatarWakerupEnable(str, str2);
                return;
            case 14:
                this.mTarget.onAvatarWakerupDisable(str, str2);
                return;
            case 15:
                this.mTarget.onAvatarStatus(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{DMRecognized.EVENT, DMListening.EVENT, DMSpeaking.EVENT, DMUnderstanding.EVENT, DMPersonalRun.EVENT, DMPersonalRunExit.EVENT, AvatarExpressionEvent.EVENT, ContextEvent.WIDGET_CUSTOM, ContextEvent.WIDGET_TEXT, ContextEvent.WIDGET_LIST, ContextEvent.WIDGET_MEDIA, ContextEvent.WIDGET_CARD, ContextEvent.WIDGET_SEARCH, AvatarWakeupEnable.EVENT, AvatarWakeupDisable.EVENT, AvatarStatus.EVENT};
    }
}
