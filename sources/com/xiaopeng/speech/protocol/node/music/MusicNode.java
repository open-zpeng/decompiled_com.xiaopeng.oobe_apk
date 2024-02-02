package com.xiaopeng.speech.protocol.node.music;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.protocol.SpeechUtils;
import com.xiaopeng.speech.protocol.event.MusicEvent;
import com.xiaopeng.speech.protocol.node.context.AbsContextListener;
import com.xiaopeng.speech.protocol.node.context.ContextNode;
import com.xiaopeng.speech.protocol.node.music.bean.CollectHistoryMusic;
import com.xiaopeng.speech.protocol.node.music.bean.SearchMusic;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import java.math.BigDecimal;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class MusicNode extends SpeechNode<MusicListener> {
    private static final String TAG = "MusicNode";

    public MusicNode() {
        SpeechUtils.subscribe(ContextNode.class, new AbsContextListener() { // from class: com.xiaopeng.speech.protocol.node.music.MusicNode.1
            @Override // com.xiaopeng.speech.protocol.node.context.AbsContextListener, com.xiaopeng.speech.protocol.node.context.ContextListener
            public void onWidgetText(String str) {
                JSONObject optJSONObject;
                super.onWidgetText(str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!"music_list_play".equals(jSONObject.optString("function")) || (optJSONObject = jSONObject.optJSONObject(SpeechWidget.WIDGET_EXTRA)) == null) {
                        return;
                    }
                    MusicNode.this.onMusicListPlay(MusicEvent.MUSIC_LIST_PLAY, optJSONObject.optString("param"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY)
    public void onPlay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlay();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_LOOP_SINGLE)
    public void onPlayLoopSingle(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayMode("single");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_LOOP_ALL)
    public void onPlayLoopAll(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayMode("order");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_LOOP_RANDOM)
    public void onPlayLoopRandom(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayMode("random");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.SEARCH)
    public void onSearch(String str, String str2) {
        SearchMusic fromJson = SearchMusic.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSearch(str, fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PAUSE)
    public void onPause(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPause();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.RESUME)
    public void onResume(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onResume();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PREV)
    public void onPrev(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPrev();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.NEXT)
    public void onNext(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onNext();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.STOP)
    public void onStop(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onStop();
            }
        }
    }

    protected void onExit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onExit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_BLUETOOTH)
    public void onPlayBlueTooth(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayBluetooth();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_MODE_SUPPORT)
    public void onSupportPlayModeChange(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSupportPlayModeChange(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.AUDIO_BOOK_PLAY)
    public void onAudioBookPlay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onAudioBookPlay(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.MUSIC_LIST_PLAY)
    public void onMusicListPlay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (SpeechUtils.isJson(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has(TypedValues.TransitionType.S_FROM) && "dui_xp".equals(jSONObject.optString(TypedValues.TransitionType.S_FROM))) {
                    LogUtils.i(TAG, "is from dui_xp");
                    return;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        for (Object obj : collectCallbacks) {
            ((MusicListener) obj).onMusicListPlay(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.TWELVE_NOVEL_PLAY)
    public void onTwelveNovelPlay(String str, String str2) {
        String str3;
        try {
            str3 = new JSONObject(str2).optString("param");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = null;
        }
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onTwelveNovelPlay(str3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.CONTROL_COLLECT)
    public void onControlCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onControlCollect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.AUDIO_BOOK_SUBSCRIBE)
    public void onAudioBookSubscribe(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onAudioBookSubscribe();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.SOUND_EFFECT_STEREO)
    public void onSoundEffectStereo(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSoundEffectStereo();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.SOUND_EFFECT_LIVE)
    public void onSoundEffectLive(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSoundEffectLive();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.SOUND_EFFECT_VOCAL)
    public void onSoundEffectVocal(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSoundEffectVocal();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.SOUND_EFFECT_SUPERBASS)
    public void onSoundEffectSuperbass(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onSoundEffectSuperbass();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.CANCEL_COLLECT)
    public void onDelCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onDelCollect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_COLLECT)
    public void onPlayCollect(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        CollectHistoryMusic fromJson = CollectHistoryMusic.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayCollect(fromJson);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_SIMILAR)
    public void onPlaySimilar(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlaySimilar();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.CANCEL_PLAY_SIMILAR)
    public void onCancelPlaySimilar(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onCancelPlaySimilar();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = MusicEvent.PLAY_HISTORY_LIST)
    public void onPlayHistoryList(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        CollectHistoryMusic fromJson = CollectHistoryMusic.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayHistoryList(fromJson);
            }
        }
    }

    public void startMusicSearch() {
        SpeechClient.instance().getWakeupEngine().startDialogFrom("music");
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_FORWARD)
    public void onMusicForward(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MusicListener) obj).onMusicForward(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_BACKWARD)
    public void onMusicBackward(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MusicListener) obj).onMusicBackward(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_SETTIME)
    public void onMusicSettime(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            int optInt = new JSONObject(str2).optInt("value");
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MusicListener) obj).onMusicSettime(optInt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_SPEED_UP)
    public void onMusicSpeedUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onMusicSpeedUp();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_SPEED_DOWN)
    public void onMusicSpeedDown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onMusicSpeedDown();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_SPEED_SET)
    public void onMusicSpeedSet(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            float floatValue = BigDecimal.valueOf(new JSONObject(str2).optDouble("value")).floatValue();
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    ((MusicListener) obj).onMusicSpeedSet(floatValue);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_NEWS_PLAY)
    public void onMusicNewsPlay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onMusicNewsPlay();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.MUSIC_DAILYREC_PLAY)
    public void onMusicDailyrecPlay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onMusicDailyrecPlay();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.PLAY_USB)
    public void onPlayUsb(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayUsb();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.PLAY_SPOTIFY)
    public void onPlaySpotify(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlaySpotify();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.PLAY_LOOP_CLOSE)
    public void onPlayLoopClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayLoopClose();
            }
        }
    }

    @SpeechAnnotation(event = MusicEvent.PLAY_RANDOM_CLOSE)
    public void onPlayRandomClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((MusicListener) obj).onPlayRandomClose();
            }
        }
    }
}
