package com.xiaopeng.oobe.webp;
/* loaded from: classes.dex */
public enum XpViewStatus {
    HOME_DEFAULT(0),
    HOME_SPEEK(2),
    HOME_FAIL(3),
    HOME_SUCCESS(5),
    HOME_LISTEN_CENTER(6),
    HOME_CHECK(7),
    HOME_SMILE(8),
    HOME_SMILE_LEFT(9),
    HOME_HELPLESS(10),
    HOME_EXIT(11);
    
    public int value;

    XpViewStatus(int i) {
        this.value = i;
    }
}
