package com.xiaopeng.oobe.utils;

import android.os.SystemClock;
/* loaded from: classes.dex */
public class ClickUtils {
    private static final long CLICK_INTERVAL = 500;
    private long mLastClickTime;

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        static ClickUtils sInstance = new ClickUtils();

        private SingletonHolder() {
        }
    }

    private ClickUtils() {
    }

    public static ClickUtils getInstance() {
        return SingletonHolder.sInstance;
    }

    public boolean isTooQuickClick() {
        return isQuickClick(CLICK_INTERVAL);
    }

    public boolean isQuickClick(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.mLastClickTime < j) {
            return true;
        }
        this.mLastClickTime = elapsedRealtime;
        return false;
    }

    public void resetTooQuickClick() {
        this.mLastClickTime = SystemClock.elapsedRealtime();
    }
}
