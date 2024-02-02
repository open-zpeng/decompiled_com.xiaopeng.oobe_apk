package com.xiaopeng.speech.protocol.node.media;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.MediaEvent;
/* loaded from: classes2.dex */
public class MediaNode_Processor implements ICommandProcessor {
    private MediaNode mTarget;

    public MediaNode_Processor(MediaNode mediaNode) {
        this.mTarget = mediaNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1657429332:
                if (str.equals(MediaEvent.FORWARD)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1563074367:
                if (str.equals(MediaEvent.PLAY_LOOP_ALL)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1524827811:
                if (str.equals(MediaEvent.NEXT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -1524762210:
                if (str.equals(MediaEvent.PLAY)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1524756323:
                if (str.equals(MediaEvent.PREV)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1524664724:
                if (str.equals(MediaEvent.STOP)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -665165651:
                if (str.equals(MediaEvent.PLAY_LOOP_RANDOM)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -661306345:
                if (str.equals(MediaEvent.RESUME)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -23296820:
                if (str.equals(MediaEvent.PAUSE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 226337192:
                if (str.equals(MediaEvent.CANCEL_COLLECT)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 826237568:
                if (str.equals(MediaEvent.CONTROL_COLLECT)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 999672104:
                if (str.equals(MediaEvent.PLAY_LOOP_SINGLE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1005657622:
                if (str.equals(MediaEvent.SETTIME)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1797961948:
                if (str.equals(MediaEvent.BACKWARD)) {
                    c = '\f';
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
                this.mTarget.onPlay(str, str2);
                return;
            case 1:
                this.mTarget.onPlayLoopSingle(str, str2);
                return;
            case 2:
                this.mTarget.onPlayLoopAll(str, str2);
                return;
            case 3:
                this.mTarget.onPlayLoopRandom(str, str2);
                return;
            case 4:
                this.mTarget.onPause(str, str2);
                return;
            case 5:
                this.mTarget.onResume(str, str2);
                return;
            case 6:
                this.mTarget.onPrev(str, str2);
                return;
            case 7:
                this.mTarget.onNext(str, str2);
                return;
            case '\b':
                this.mTarget.onStop(str, str2);
                return;
            case '\t':
                this.mTarget.onControlCollect(str, str2);
                return;
            case '\n':
                this.mTarget.onCancelCollect(str, str2);
                return;
            case 11:
                this.mTarget.onForward(str, str2);
                return;
            case '\f':
                this.mTarget.onBackward(str, str2);
                return;
            case '\r':
                this.mTarget.onSettime(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{MediaEvent.PLAY, MediaEvent.PLAY_LOOP_SINGLE, MediaEvent.PLAY_LOOP_ALL, MediaEvent.PLAY_LOOP_RANDOM, MediaEvent.PAUSE, MediaEvent.RESUME, MediaEvent.PREV, MediaEvent.NEXT, MediaEvent.STOP, MediaEvent.CONTROL_COLLECT, MediaEvent.CANCEL_COLLECT, MediaEvent.FORWARD, MediaEvent.BACKWARD, MediaEvent.SETTIME};
    }
}
