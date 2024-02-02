package com.xiaopeng.speech.protocol.node.media;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.MediaEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MediaNode extends SpeechNode<MediaListener> {
    private static final String TAG = "MediaNode";

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PLAY)
    public void onPlay(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String optString = new JSONObject(str2).optString("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MediaListener) obj).onPlay(optString);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PLAY_LOOP_SINGLE)
    public void onPlayLoopSingle(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onPlayMode("single");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PLAY_LOOP_ALL)
    public void onPlayLoopAll(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onPlayMode("order");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PLAY_LOOP_RANDOM)
    public void onPlayLoopRandom(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onPlayMode("random");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PAUSE)
    public void onPause(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.RESUME)
    public void onResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.PREV)
    public void onPrev(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onPrev();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.NEXT)
    public void onNext(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onNext();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.STOP)
    public void onStop(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onStop();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.CONTROL_COLLECT)
    public void onControlCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onCollect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MediaEvent.CANCEL_COLLECT)
    public void onCancelCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MediaListener) obj).onCancelCollect();
            }
        }
    }

    @SpeechAnnotation(event = MediaEvent.FORWARD)
    public void onForward(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MediaListener) obj).onForward(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MediaEvent.BACKWARD)
    public void onBackward(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MediaListener) obj).onBackward(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MediaEvent.SETTIME)
    public void onSettime(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MediaListener) obj).onSetTime(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
