package com.xiaopeng.speech.protocol.node.speech;

import android.text.TextUtils;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.protocol.event.SpeechDialogEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SpeechDialogNode extends SpeechNode<SpeechDialogListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_CLOSE_SPEECH_WINDOW)
    public void onCloseWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_OPEN_SPEECH_WINDOW)
    public void onOpenWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_OPEN_SCENE_GUIDE_WINDOW)
    public void onOpenSceneGuideWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSceneGuideWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.ROUTE_CLOSE_SCENE_GUIDE_WINDOW)
    public void onCloseSceneGuideWindow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSceneGuideWindow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_ENABLE)
    public void onOpenSpeechSceneSetting(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSpeechSceneSetting();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SWITCH_SPEECH_CONTINUOUS_DISABLED)
    public void onCloseSpeechSceneSetting(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSpeechSceneSetting();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_OPEN)
    public void onOpenSuperDialogue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onOpenSuperDialogue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SYSTEM_LISTENTIME_ACCOMPANY_CLOSE)
    public void onCloseSuperDialogue(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onCloseSuperDialogue();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0030 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0023 A[LOOP:0: B:9:0x0023->B:11:0x0026, LOOP_START, PHI: r1 
      PHI: (r1v1 int) = (r1v0 int), (r1v2 int) binds: [B:8:0x0021, B:11:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.SpeechDialogEvent.REFRESH_CARSPEECHSERVICE_UI)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onRefreshUi(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r4 = r3.mListenerList
            java.lang.Object[] r4 = r4.collectCallbacks()
            r0 = -1
            r1 = 0
            boolean r2 = com.xiaopeng.speech.protocol.SpeechUtils.isJson(r5)     // Catch: java.lang.Exception -> L20
            if (r2 == 0) goto L20
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> L20
            r2.<init>(r5)     // Catch: java.lang.Exception -> L20
            java.lang.String r5 = "type"
            int r0 = r2.optInt(r5, r0)     // Catch: java.lang.Exception -> L20
            java.lang.String r5 = "state"
            boolean r5 = r2.optBoolean(r5, r1)     // Catch: java.lang.Exception -> L20
            goto L21
        L20:
            r5 = r1
        L21:
            if (r4 == 0) goto L30
        L23:
            int r2 = r4.length
            if (r1 >= r2) goto L30
            r2 = r4[r1]
            com.xiaopeng.speech.protocol.node.speech.SpeechDialogListener r2 = (com.xiaopeng.speech.protocol.node.speech.SpeechDialogListener) r2
            r2.onRefreshUi(r0, r5)
            int r1 = r1 + 1
            goto L23
        L30:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.speech.SpeechDialogNode.onRefreshUi(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.GLOBAL_SPEECH_EXIT)
    public void onGlobalSpeechExit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onGlobalSpeechExit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SET_SCREEN_ON)
    public void setScreenOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            int i = -1;
            try {
                if (!TextUtils.isEmpty(str2)) {
                    i = new JSONObject(str2).optInt("display_location");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).setScreenOn(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SCRIPT_WIDGET)
    public void onScriptWidget(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            String str3 = "";
            try {
                if (!TextUtils.isEmpty(str2)) {
                    str3 = new JSONObject(str2).optString("widget");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onScriptWidget(str3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SCRIPT_QUIT)
    public void onScriptQuit(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onScriptQuit();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SHOW_CHILDWATCH_LOCATION)
    public void showChildwatchLocation(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).showChildwatchLocation(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SOUND_AREA_OPEN)
    public void onSoundAreaOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onSoundAreaOpen(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SOUND_AREA_CLOSE)
    public void onSoundAreaClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onSoundAreaClose(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.FAST_SPEECH_ON)
    public void openFastSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).openFastSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.FAST_SPEECH_OFF)
    public void closeFastSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).closeFastSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.MULTI_SPEECH_ON)
    public void openMultiSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).openMultiSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.MULTI_SPEECH_OFF)
    public void closeMultiSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).closeMultiSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.FULL_TIME_SPEECH_ON)
    public void openFullTimeSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).openFullTimeSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.FULL_TIME_SPEECH_OFF)
    public void closeFullTimeSpeech(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).closeFullTimeSpeech(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.INJECT_TRIGGER_WORDS)
    public void injectTriggerWords(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).injectTriggerWords(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_ON)
    public void onContinueDialogueOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onContinueDialogueOpen();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = SpeechDialogEvent.SPEECH_CONTINUE_DIALOGUE_OFF)
    public void onContinueDialogueClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((SpeechDialogListener) obj).onContinueDialogueClose();
            }
        }
    }
}
