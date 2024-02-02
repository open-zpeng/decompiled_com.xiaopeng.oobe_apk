package com.xiaopeng.oobe.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* loaded from: classes.dex */
public class BaseTimer {
    private Runnable keyRunnable;
    private Handler timerHandler;
    private TimerCallBack timerCallBack = null;
    private boolean timerRun = false;
    private boolean bInterval = false;
    private long curMsecond = 0;

    /* loaded from: classes.dex */
    public interface TimerCallBack {
        void callback();
    }

    public BaseTimer() {
        this.timerHandler = new Handler(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper()) { // from class: com.xiaopeng.oobe.common.BaseTimer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (BaseTimer.this.timerCallBack != null) {
                    BaseTimer.this.timerCallBack.callback();
                }
                BaseTimer.this.timerRun = false;
                if (BaseTimer.this.bInterval) {
                    BaseTimer baseTimer = BaseTimer.this;
                    baseTimer.startTimer(baseTimer.curMsecond, BaseTimer.this.timerCallBack);
                    BaseTimer.this.bInterval = true;
                }
                super.handleMessage(message);
            }
        };
        this.keyRunnable = new Runnable() { // from class: com.xiaopeng.oobe.common.-$$Lambda$BaseTimer$LAJaWvVmc-WGA1VtVMs7wZ90vpM
            @Override // java.lang.Runnable
            public final void run() {
                BaseTimer.this.lambda$new$0$BaseTimer();
            }
        };
    }

    public /* synthetic */ void lambda$new$0$BaseTimer() {
        this.timerHandler.sendEmptyMessage(0);
    }

    public void killTimer() {
        this.bInterval = false;
        this.timerRun = false;
        this.timerHandler.removeCallbacks(this.keyRunnable);
    }

    public void startTimer(int i, TimerCallBack timerCallBack) {
        killTimer();
        this.curMsecond = i;
        this.timerRun = true;
        this.timerCallBack = timerCallBack;
        this.timerHandler.postDelayed(this.keyRunnable, this.curMsecond);
    }

    public void startTimer(long j, TimerCallBack timerCallBack) {
        killTimer();
        this.timerRun = true;
        this.timerCallBack = timerCallBack;
        this.timerHandler.postDelayed(this.keyRunnable, j);
    }

    public void startInterval(int i, TimerCallBack timerCallBack) {
        startTimer(i, timerCallBack);
        this.bInterval = true;
    }

    public boolean isRunning() {
        return this.timerRun;
    }
}
