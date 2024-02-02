package com.xiaopeng.speech.protocol.node.video;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryAppAndAppletEvent;
import com.xiaopeng.speech.protocol.event.query.QueryVideoEvent;
/* loaded from: classes2.dex */
public class VideoNode_Processor implements ICommandProcessor {
    private VideoNode mTarget;

    public VideoNode_Processor(VideoNode videoNode) {
        this.mTarget = videoNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1592578466:
                if (str.equals(QueryAppAndAppletEvent.GUI_VIDEO_APP_DEMAND)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -1348076748:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_END)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1348063557:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_SET)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1240049296:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_SPEED_DOWN)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -1081395201:
                if (str.equals(QueryVideoEvent.VIDEO_SKIP_END)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1046937853:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_COLLECT)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -322718654:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_AUDIO_MODE)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -317082156:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_SPEED_SET)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -10441720:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_SETTIME)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 158265037:
                if (str.equals(QueryVideoEvent.VIDEO_SKIP_BEGIN)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 270519299:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_CLARITY_DOWN)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 363653418:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_BACKWARD)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 405413609:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_SPEED_UP)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1023744572:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_CLARITY_UP)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1159553978:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_NEXT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1159625466:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_PREV)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1588160015:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_PAUSE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1621438622:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_FORWARD)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1671308513:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_CLARITY_SET)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1989573573:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_COLLECT_CANCEL)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 2049215284:
                if (str.equals(QueryVideoEvent.VIDEO_PLAY_RESUME)) {
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
                this.mTarget.onVideoPrev(str, str2);
                return;
            case 1:
                this.mTarget.onVideoNext(str, str2);
                return;
            case 2:
                this.mTarget.onVideoSet(str, str2);
                return;
            case 3:
                this.mTarget.onVideoForward(str, str2);
                return;
            case 4:
                this.mTarget.onVideoBackward(str, str2);
                return;
            case 5:
                this.mTarget.onVideoSettime(str, str2);
                return;
            case 6:
                this.mTarget.onVideoPause(str, str2);
                return;
            case 7:
                this.mTarget.onVideoResume(str, str2);
                return;
            case '\b':
                this.mTarget.onVideoSpeedUp(str, str2);
                return;
            case '\t':
                this.mTarget.onVideoSpeedDown(str, str2);
                return;
            case '\n':
                this.mTarget.onVideoSpeedSet(str, str2);
                return;
            case 11:
                this.mTarget.onVideoEnd(str, str2);
                return;
            case '\f':
                this.mTarget.onVideoClarityUP(str, str2);
                return;
            case '\r':
                this.mTarget.onVideoClarityDown(str, str2);
                return;
            case 14:
                this.mTarget.onVideoClaritySet(str, str2);
                return;
            case 15:
                this.mTarget.onVideoCollect(str, str2);
                return;
            case 16:
                this.mTarget.onVideoCollectCancel(str, str2);
                return;
            case 17:
                this.mTarget.onVideoSkipBegin(str, str2);
                return;
            case 18:
                this.mTarget.onVideoSkipEnd(str, str2);
                return;
            case 19:
                this.mTarget.onVideoAudioMode(str, str2);
                return;
            case 20:
                this.mTarget.onVideoDemand(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{QueryVideoEvent.VIDEO_PLAY_PREV, QueryVideoEvent.VIDEO_PLAY_NEXT, QueryVideoEvent.VIDEO_PLAY_SET, QueryVideoEvent.VIDEO_PLAY_FORWARD, QueryVideoEvent.VIDEO_PLAY_BACKWARD, QueryVideoEvent.VIDEO_PLAY_SETTIME, QueryVideoEvent.VIDEO_PLAY_PAUSE, QueryVideoEvent.VIDEO_PLAY_RESUME, QueryVideoEvent.VIDEO_PLAY_SPEED_UP, QueryVideoEvent.VIDEO_PLAY_SPEED_DOWN, QueryVideoEvent.VIDEO_PLAY_SPEED_SET, QueryVideoEvent.VIDEO_PLAY_END, QueryVideoEvent.VIDEO_PLAY_CLARITY_UP, QueryVideoEvent.VIDEO_PLAY_CLARITY_DOWN, QueryVideoEvent.VIDEO_PLAY_CLARITY_SET, QueryVideoEvent.VIDEO_PLAY_COLLECT, QueryVideoEvent.VIDEO_PLAY_COLLECT_CANCEL, QueryVideoEvent.VIDEO_SKIP_BEGIN, QueryVideoEvent.VIDEO_SKIP_END, QueryVideoEvent.VIDEO_PLAY_AUDIO_MODE, QueryAppAndAppletEvent.GUI_VIDEO_APP_DEMAND};
    }
}
