package com.xiaopeng.oobe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.xiaopeng.lib.bughunter.StartPerfUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.Constants;
import com.xiaopeng.oobe.audio.SoundPlayHelper;
import com.xiaopeng.oobe.common.BaseTimer;
import com.xiaopeng.oobe.speech.SpeechHelper;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.viewmodel.ViewModelManager;
import com.xiaopeng.oobe.viewmodel.main.IMainViewModel;
import com.xiaopeng.oobe.viewmodel.main.MainViewModel;
/* loaded from: classes.dex */
public class OOBEActivity extends BaseActivity {
    private static final String TAG = "OOBEActivity";
    private Button mBtnSkip;
    private BaseTimer mTimerLauncher = new BaseTimer();
    private Runnable mRunnAddWebp = new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$OOBEActivity$WPDT8a1zNTNLjcHRmdV4rY4jP_w
        @Override // java.lang.Runnable
        public final void run() {
            OOBEActivity.this.lambda$new$0$OOBEActivity();
        }
    };

    public /* synthetic */ void lambda$new$0$OOBEActivity() {
        addWebpView();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oobe);
        StartPerfUtils.onCreateEnd();
        LogUtils.i(TAG, "onCreate");
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtils.i(TAG, "onResume" + hashCode());
        if (this.mAnimView == null) {
            this.mTimerLauncher.startInterval(100, new BaseTimer.TimerCallBack() { // from class: com.xiaopeng.oobe.-$$Lambda$OOBEActivity$PHUgr-j_AeMDtjpUNedISqLBHkM
                @Override // com.xiaopeng.oobe.common.BaseTimer.TimerCallBack
                public final void callback() {
                    OOBEActivity.this.lambda$onResume$1$OOBEActivity();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onResume$1$OOBEActivity() {
        if (App.getInstance().isBootCompleted()) {
            SpeechHelper.getInstance().reInitSpeech();
            App.getInstance().updateTts();
            this.mTimerLauncher.killTimer();
            ThreadUtils.postMainThread(this.mRunnAddWebp);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.xiaopeng.oobe.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtils.i(TAG, "onDestroy");
        SpeechHelper.getInstance().release();
        SoundPlayHelper.instance().clear();
        super.onDestroy();
    }

    private void init() {
        initView();
        int oobeFinishStep = SPUtils.getOobeFinishStep();
        LogUtils.i(TAG, "init FinishStep:" + oobeFinishStep);
        SPUtils.setLoginQrcode("");
        if (oobeFinishStep != 6) {
            getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.STATEMENT), 0);
            getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.INIT), 1);
        }
        if (oobeFinishStep < 2) {
            getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.LOGIN), 2);
        }
        if (oobeFinishStep < 3) {
            if (OOBEHelper.isSupportOS5()) {
                getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.VUI_AUTHORIZATION_OS5), 3);
                this.mMainViewModel = (MainViewModel) ViewModelManager.getInstance().getViewModelImpl(IMainViewModel.class);
                return;
            }
            getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.VUI_AUTHORIZATION), 3);
        }
        if (oobeFinishStep < 4) {
            getViewManager().addView(PageViewFactory.createView(Constants.ViewMode.VOICE_SERVICE), 4);
        }
        this.mMainViewModel = (MainViewModel) ViewModelManager.getInstance().getViewModelImpl(IMainViewModel.class);
    }

    @Override // com.xiaopeng.oobe.BaseActivity
    public void initView() {
        super.initView();
        this.mBtnSkip = (Button) findViewById(R.id.activity_oobe_btn_skip);
        this.mBtnSkip.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.-$$Lambda$OOBEActivity$Eq-qn3MhN_OckCbdhnm_JLrvEvM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OOBEActivity.this.lambda$initView$2$OOBEActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initView$2$OOBEActivity(View view) {
        LogUtils.i(TAG, "skip btn onClick: ");
        showNext();
    }

    @Override // com.xiaopeng.oobe.BaseActivity, com.xiaopeng.oobe.window.OOBEContract.View
    public void dismiss() {
        LogUtils.i(TAG, "dismiss: ");
        SPUtils.setOobeFinishStep(6);
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$OOBEActivity$H8ro7zJQgySEJHdVWqFM7pxVie8
            @Override // java.lang.Runnable
            public final void run() {
                OOBEActivity.this.lambda$dismiss$3$OOBEActivity();
            }
        });
    }

    public /* synthetic */ void lambda$dismiss$3$OOBEActivity() {
        getViewManager().finishOOBE();
        showWebp(11);
        finish();
    }

    @Override // com.xiaopeng.oobe.BaseActivity
    public Button getBtnSkip() {
        return this.mBtnSkip;
    }
}
