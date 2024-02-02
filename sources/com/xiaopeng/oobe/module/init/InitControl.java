package com.xiaopeng.oobe.module.init;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.text.SpannableStringBuilder;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ForegroundColorSpan;
import androidx.lifecycle.Observer;
import com.lzy.okgo.OkGo;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.NetUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.base.R;
import com.xiaopeng.oobe.common.BaseTimer;
import com.xiaopeng.oobe.module.IQuit;
import com.xiaopeng.oobe.module.init.InitContract;
import com.xiaopeng.oobe.module.init.Items2InitEnum;
import com.xiaopeng.oobe.utils.BIHelper;
import com.xiaopeng.oobe.viewmodel.IBaseViewControl;
import com.xiaopeng.oobe.viewmodel.IView;
import com.xiaopeng.oobe.viewmodel.IViewControl;
import java.util.HashMap;
/* loaded from: classes.dex */
public class InitControl extends IBaseViewControl implements IViewControl {
    private static final String TAG = "InitControl";
    private BaseTimer mBaseTimer;
    private boolean mEnableVoice;
    private Runnable mInitTimeOutRunnable;
    private HashMap<Items2InitEnum.InitItem, Boolean> mInitedItemMap;
    private boolean mIsInitSuccess;
    private Observer<? super Boolean> mNetConnectObserve;
    private boolean mPlayInitFinish;
    private int mWelcomeTips;

    public /* synthetic */ void lambda$new$0$InitControl(Boolean bool) {
        LogUtils.i(TAG, "getNetConnect: " + bool);
        if (bool.booleanValue()) {
            onInitSuccess(Items2InitEnum.InitItem.NETWORK_MOBILE);
        }
    }

    public InitControl(IView iView) {
        super(iView);
        this.mBaseTimer = new BaseTimer();
        this.mNetConnectObserve = new Observer() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$xUgOPcfJwMIHFEkAQvDG1qr3qdA
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                InitControl.this.lambda$new$0$InitControl((Boolean) obj);
            }
        };
        this.mEnableVoice = true;
        this.mWelcomeTips = R.string.init_text_1;
        this.mInitTimeOutRunnable = new Runnable() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$c4UOBrvRwR_mpXoQYYhmYeJceXo
            @Override // java.lang.Runnable
            public final void run() {
                InitControl.this.lambda$new$9$InitControl();
            }
        };
        initAllItems2Init();
    }

    public void setEnableVoice(boolean z) {
        this.mEnableVoice = z;
    }

    public void setWelcomeTips(int i) {
        this.mWelcomeTips = i;
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onStart() {
        super.onStart();
        LogUtils.i(TAG, "onStart mRootView:" + this.mRootView);
        if (this.mRootView != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(App.getInstance().getString(this.mWelcomeTips));
            spannableStringBuilder.setSpan(new ForegroundColorSpan(App.getInstance().getColor(R.color.x_color_primary)), 5, 7, 33);
            this.mRootView.updateTips(spannableStringBuilder, App.getInstance().getString(R.string.init_text_sub_1));
        }
        init();
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl
    public void observeData() {
        super.observeData();
        LogUtils.i(TAG, "observeData " + this.mMainViewModel);
        this.mMainViewModel.getNetConnect().observe(this, this.mNetConnectObserve);
        this.mMainViewModel.getMcuIgOff().observe(this, new Observer() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$GA7AoqgxG1lTANPP28joJJCOSE8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                InitControl.this.lambda$observeData$2$InitControl((Boolean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$1$InitControl(Boolean bool) {
        onMcuIgOff(bool.booleanValue());
    }

    public /* synthetic */ void lambda$observeData$2$InitControl(final Boolean bool) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$DJmDQFa8jDi8xkED8es08bobWy8
            @Override // java.lang.Runnable
            public final void run() {
                InitControl.this.lambda$null$1$InitControl(bool);
            }
        }, 1000L);
    }

    private void onMcuIgOff(boolean z) {
        if (z) {
            cancelLoading();
        } else if (this.mIsInitSuccess) {
            speakSuccess();
        } else {
            lambda$waitForInit$4$InitControl();
        }
    }

    private void init() {
        LogUtils.i(TAG, Constants.Unity.INIT);
        ThreadUtils.postBackground(this.mInitTimeOutRunnable, OkGo.DEFAULT_MILLISECONDS);
        if (NetUtils.isNetworkAvailable(App.getInstance())) {
            this.mInitedItemMap.put(Items2InitEnum.InitItem.NETWORK_MOBILE, Boolean.TRUE);
        }
        this.mMainViewModel.checkConnection();
        waitForInit();
        if (isInitSuccess()) {
            onInitFinish();
        }
    }

    private void waitForInit() {
        LogUtils.i(TAG, "waitForInit");
        onWebp(2);
        if (this.mEnableVoice) {
            SoundPlayHelper.instance().play(SoundPlayHelper.instance().getInitStartRes(), new MediaPlayer.OnCompletionListener() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$efn5-b4un6ZfAqz_7BFkaR_xQxg
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    InitControl.this.lambda$waitForInit$3$InitControl(mediaPlayer);
                }
            });
        } else {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$nBLQLJH_B7xk_qPtIOxV0EVZ_kw
                @Override // java.lang.Runnable
                public final void run() {
                    InitControl.this.lambda$waitForInit$4$InitControl();
                }
            }, 3000L);
        }
    }

    public /* synthetic */ void lambda$waitForInit$3$InitControl(MediaPlayer mediaPlayer) {
        lambda$waitForInit$4$InitControl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initStartCompletion */
    public void lambda$waitForInit$4$InitControl() {
        this.mPlayInitFinish = true;
        setTips(App.getInstance().getString(R.string.init_text_3));
        onWebp(7);
        this.mBaseTimer.startInterval(500, new BaseTimer.TimerCallBack() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$VFjsD29af7N6KLIClQKcu7Pk3Mc
            @Override // com.xiaopeng.oobe.common.BaseTimer.TimerCallBack
            public final void callback() {
                InitControl.this.lambda$initStartCompletion$5$InitControl();
            }
        });
        LogUtils.i(TAG, "waitForInit SoundPlay onCompletion mIsInitSuccess:" + this.mIsInitSuccess);
        if (this.mIsInitSuccess) {
            speakSuccess();
        }
    }

    public /* synthetic */ void lambda$initStartCompletion$5$InitControl() {
        if (this.mRootView != null) {
            ((InitContract.View) this.mRootView).setLoading();
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    private void speakSuccess() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(App.getInstance().getString(R.string.init_text_success));
        spannableStringBuilder.setSpan(new DynamicDrawableSpan() { // from class: com.xiaopeng.oobe.module.init.InitControl.1
            @Override // android.text.style.DynamicDrawableSpan
            public Drawable getDrawable() {
                Drawable drawable = App.getInstance().getDrawable(R.drawable.ic_finish);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                return drawable;
            }

            @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
            public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
                Drawable drawable = getDrawable();
                Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
                canvas.save();
                canvas.translate(f, ((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2));
                drawable.draw(canvas);
                canvas.restore();
            }
        }, 0, 1, 33);
        setTips(spannableStringBuilder);
        cancelLoading();
        onWebp(5);
        if (this.mEnableVoice) {
            SoundPlayHelper.instance().play(SoundPlayHelper.instance().getInitSuccessRes(), new MediaPlayer.OnCompletionListener() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$22VCLe5lBVDfGSXtJM4GeeESWKc
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    InitControl.this.lambda$speakSuccess$6$InitControl(mediaPlayer);
                }
            });
        } else {
            ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$0HwwdlcIWKWaYlMiNPb_t8y54SM
                @Override // java.lang.Runnable
                public final void run() {
                    InitControl.this.lambda$speakSuccess$7$InitControl();
                }
            }, 3000L);
        }
    }

    public /* synthetic */ void lambda$speakSuccess$6$InitControl(MediaPlayer mediaPlayer) {
        setTips("");
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
        }
    }

    public /* synthetic */ void lambda$speakSuccess$7$InitControl() {
        setTips("");
        if (this.mRootView != null) {
            this.mRootView.onShowNext();
        }
    }

    private void setTips(CharSequence charSequence) {
        if (this.mRootView != null) {
            this.mRootView.updateTips(charSequence, "");
            if (this.mBaseTimer.isRunning()) {
                this.mBaseTimer.killTimer();
                cancelLoading();
            }
        }
    }

    private void cancelLoading() {
        if (this.mRootView != null) {
            ((InitContract.View) this.mRootView).cancelLoading();
        }
    }

    private void onWebp(int i) {
        if (this.mRootView != null) {
            this.mRootView.onWebp(i);
        }
    }

    @Override // com.xiaopeng.oobe.viewmodel.IBaseViewControl, com.xiaopeng.oobe.viewmodel.IViewControl
    public void onDestroy() {
        setTips("");
        cancelLoading();
        super.onDestroy();
        SoundPlayHelper.instance().clear();
        if (this.mBaseTimer.isRunning()) {
            this.mBaseTimer.killTimer();
        }
        Runnable runnable = this.mInitTimeOutRunnable;
        if (runnable != null) {
            ThreadUtils.removeRunnable(runnable);
            this.mInitTimeOutRunnable = null;
        }
    }

    private void onInitFinish() {
        LogUtils.i(TAG, "onInitFinish");
        this.mIsInitSuccess = true;
        if (this.mPlayInitFinish) {
            speakSuccess();
        } else {
            LogUtils.i(TAG, "onInitFinish 等待语音播放结束");
        }
    }

    private void initAllItems2Init() {
        this.mInitedItemMap = new HashMap<>();
        for (Items2InitEnum.InitItem initItem : Items2InitEnum.InitItem.values()) {
            this.mInitedItemMap.put(initItem, Boolean.FALSE);
        }
    }

    private boolean isInitSuccess() {
        LogUtils.i(TAG, "isInitSuccess mInitedItemMap=" + this.mInitedItemMap);
        for (Boolean bool : this.mInitedItemMap.values()) {
            if (!bool.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    private void onInitSuccess(Items2InitEnum.InitItem initItem) {
        if (initItem != null) {
            LogUtils.i(TAG, "onInitSuccess initEnum=" + initItem.name());
            if (this.mInitedItemMap.get(initItem).booleanValue()) {
                LogUtils.e(TAG, "onInitSuccess state is ready true=" + this.mInitedItemMap.get(initItem));
                return;
            }
            this.mInitedItemMap.put(initItem, Boolean.TRUE);
            checkoutInitState();
        }
    }

    private void checkoutInitState() {
        if (isInitSuccess()) {
            onInitFinish();
        }
    }

    public /* synthetic */ void lambda$new$9$InitControl() {
        LogUtils.i(TAG, "init timeout in 1 minute");
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.module.init.-$$Lambda$InitControl$wMhLm-0rMnAibd-FZ9eMU3H96b0
            @Override // java.lang.Runnable
            public final void run() {
                InitControl.this.lambda$null$8$InitControl();
            }
        }, 3000L);
        String string = App.getInstance().getString(R.string.connection_tips_failed);
        if (this.mEnableVoice) {
            SoundPlayHelper.instance().play(SoundPlayHelper.instance().getTimeOutExitRes());
        }
        setTips(string);
        onWebp(3);
    }

    public /* synthetic */ void lambda$null$8$InitControl() {
        LogUtils.i(TAG, "onTimeOut: delay 3000 dismiss");
        BIHelper.getInstance().sendData(BIHelper.PageId.JUMPER_NOT_NET_WORK, "B009");
        if (this.mRootView != null) {
            ((IQuit) this.mRootView).dismiss();
        }
    }
}
