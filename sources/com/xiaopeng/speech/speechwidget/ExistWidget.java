package com.xiaopeng.speech.speechwidget;
/* loaded from: classes2.dex */
public class ExistWidget extends SpeechWidget {
    public static final String KEY_STATUS = "status";
    public static final String STATUS_EXISTS = "1";
    public static final String STATUS_NOT_FOUND = "0";

    public ExistWidget() {
        super("custom");
    }

    public ExistWidget setExist(boolean z) {
        super.addContent("text", z ? "success" : "fail");
        return (ExistWidget) super.addContent("status", z ? "1" : "0");
    }
}
