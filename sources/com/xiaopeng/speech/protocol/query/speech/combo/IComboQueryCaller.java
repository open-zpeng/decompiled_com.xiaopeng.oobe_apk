package com.xiaopeng.speech.protocol.query.speech.combo;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IComboQueryCaller extends IQueryCaller {
    default String checkEnterUserMode(String str) {
        return "normal";
    }

    default String enterUserMode(String str) {
        return "normal";
    }

    default String exitUserMode(String str) {
        return "normal";
    }

    default String getCurrentUserMode() {
        return "normal";
    }

    default String getCurrentUserModeWithScreen(int i) {
        return "normal";
    }

    default boolean guiPagecheckForbidden(int i, String str) {
        return false;
    }
}
