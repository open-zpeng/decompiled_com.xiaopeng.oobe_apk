package com.xiaopeng.speech.protocol.node.speech;

import com.xiaopeng.speech.INodeListener;
/* loaded from: classes2.dex */
public interface SpeechDialogListener extends INodeListener {
    default void closeFastSpeech(String str) {
    }

    default void closeFullTimeSpeech(String str) {
    }

    default void closeMultiSpeech(String str) {
    }

    default void injectTriggerWords(String str) {
    }

    void onCloseSceneGuideWindow(String str);

    void onCloseSpeechSceneSetting();

    default void onCloseSuperDialogue() {
    }

    void onCloseWindow(String str);

    default void onContinueDialogueClose() {
    }

    default void onContinueDialogueOpen() {
    }

    default void onGlobalSpeechExit() {
    }

    void onOpenSceneGuideWindow(String str);

    void onOpenSpeechSceneSetting();

    default void onOpenSuperDialogue() {
    }

    void onOpenWindow(String str);

    default void onRefreshUi(int i, boolean z) {
    }

    default void onScriptQuit() {
    }

    default void onScriptWidget(String str) {
    }

    default void onSoundAreaClose(String str) {
    }

    default void onSoundAreaOpen(String str) {
    }

    default void openFastSpeech(String str) {
    }

    default void openFullTimeSpeech(String str) {
    }

    default void openMultiSpeech(String str) {
    }

    default void setScreenOn(int i) {
    }

    default void showChildwatchLocation(String str) {
    }
}
