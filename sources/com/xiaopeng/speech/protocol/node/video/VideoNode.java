package com.xiaopeng.speech.protocol.node.video;

import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryAppAndAppletEvent;
import com.xiaopeng.speech.protocol.event.query.QueryVideoEvent;
/* loaded from: classes2.dex */
public class VideoNode extends SpeechNode<VideoListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_PREV)
    public void onVideoPrev(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoPrev(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_NEXT)
    public void onVideoNext(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoNext(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_SET)
    public void onVideoSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSet(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_FORWARD)
    public void onVideoForward(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoForward(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_BACKWARD)
    public void onVideoBackward(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoBackward(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_SETTIME)
    public void onVideoSettime(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSettime(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_PAUSE)
    public void onVideoPause(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoPause(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_RESUME)
    public void onVideoResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoResume(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_SPEED_UP)
    public void onVideoSpeedUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSpeedUp(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_SPEED_DOWN)
    public void onVideoSpeedDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSpeedDown(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_SPEED_SET)
    public void onVideoSpeedSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSpeedSet(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_END)
    public void onVideoEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoEnd(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_CLARITY_UP)
    public void onVideoClarityUP(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoClarityUP(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_CLARITY_DOWN)
    public void onVideoClarityDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoClarityDown(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_CLARITY_SET)
    public void onVideoClaritySet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoClaritySet(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_COLLECT)
    public void onVideoCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoCollect(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_COLLECT_CANCEL)
    public void onVideoCollectCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoCollectCancel(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_SKIP_BEGIN)
    public void onVideoSkipBegin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSkipBegin(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_SKIP_END)
    public void onVideoSkipEnd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoSkipEnd(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryVideoEvent.VIDEO_PLAY_AUDIO_MODE)
    public void onVideoAudioMode(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoAudioMode(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = QueryAppAndAppletEvent.GUI_VIDEO_APP_DEMAND)
    public void onVideoDemand(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((VideoListener) obj).onVideoDemand(str2);
            }
        }
    }
}
