package com.xiaopeng.oobe;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.Choreographer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.xiaopeng.lib.bughunter.StartPerfUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.manager.ViewManager;
import com.xiaopeng.oobe.module.BaseView;
import com.xiaopeng.oobe.module.init.InitView;
import com.xiaopeng.oobe.utils.CheckDataUtils;
import com.xiaopeng.oobe.utils.ComponentUtil;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.oobe.utils.PrivacyHelper;
import com.xiaopeng.oobe.utils.SPUtils;
import com.xiaopeng.oobe.utils.SettingsUtil;
import com.xiaopeng.oobe.utils.ViewUtil;
import com.xiaopeng.oobe.view.BaseAssistantView;
import com.xiaopeng.oobe.view.PAssistantView;
import com.xiaopeng.oobe.viewmodel.main.MainViewModel;
import com.xiaopeng.oobe.webp.XpView;
import com.xiaopeng.oobe.window.OOBEContract;
/* loaded from: classes.dex */
public class BaseActivity extends AppCompatActivity implements OOBEContract.View {
    private static final String TAG = "BaseActivity";
    public static boolean msMustKill;
    protected XpView mAnimView;
    private ImageView mApertureView;
    private PAssistantView mAssistantView;
    private RelativeLayout mLayoutRoot;
    protected MainViewModel mMainViewModel;
    private ViewManager mViewManager;
    private boolean isFirstFrame = true;
    private Choreographer.FrameCallback callback = new Choreographer.FrameCallback() { // from class: com.xiaopeng.oobe.BaseActivity.1
        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            if (BaseActivity.this.isFirstFrame) {
                Choreographer.getInstance().postFrameCallback(BaseActivity.this.callback);
                BaseActivity.this.isFirstFrame = false;
            }
        }
    };

    @Override // com.xiaopeng.oobe.window.OOBEContract.View
    public void dismiss() {
    }

    public Button getBtnSkip() {
        return null;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    public void showUp() {
    }

    @Override // com.xiaopeng.oobe.window.OOBEContract.View
    public void showNext() {
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$BaseActivity$lIhalwIqSp69fzfHj0n9qfBkEiA
            @Override // java.lang.Runnable
            public final void run() {
                BaseActivity.this.lambda$showNext$0$BaseActivity();
            }
        });
    }

    public /* synthetic */ void lambda$showNext$0$BaseActivity() {
        BaseView next = getViewManager().next();
        if (next instanceof InitView) {
            LogUtils.i(TAG, "showNext: enter initview");
            this.mAnimView.setVisibility(0);
        }
        LogUtils.i(TAG, "show next view = " + next);
        if (next == null) {
            XpView webpView = getWebpView();
            if (webpView != null) {
                webpView.setVisibility(8);
            }
            dismiss();
        }
    }

    public ImageView getApertureView() {
        return this.mApertureView;
    }

    public BaseAssistantView getAssistantView() {
        return this.mAssistantView;
    }

    public XpView getWebpView() {
        return this.mAnimView;
    }

    public void addWebpView() {
        LogUtils.i(TAG, "addWebpView ");
        if (this.mAnimView == null) {
            this.mAnimView = new XpView(getBaseContext());
            this.mAnimView.setId(R.id.xpview);
            int dimension = (int) getResources().getDimension(R.dimen.xiaop_wh);
            int dimension2 = (int) getResources().getDimension(R.dimen.xiaop_top);
            int dimension3 = (int) getResources().getDimension(R.dimen.xiaop_right);
            LogUtils.d(TAG, "addWebpView wh:" + dimension + ",top:" + dimension2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimension, dimension);
            if (!OOBEHelper.isLandScreen()) {
                layoutParams.addRule(14);
            }
            if (OOBEHelper.isSupportOS5()) {
                layoutParams.addRule(21);
            }
            layoutParams.setMargins(0, dimension2, dimension3, 0);
            this.mLayoutRoot.addView(this.mAnimView, layoutParams);
            this.mAnimView.show();
            this.mAnimView.setVisibility(4);
            ViewUtil.onPreDraw(this.mAnimView, new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$BaseActivity$HFr4BcBKD7Ql8SssKI629yRHGio
                @Override // java.lang.Runnable
                public final void run() {
                    BaseActivity.this.lambda$addWebpView$1$BaseActivity();
                }
            }, true);
        }
    }

    public /* synthetic */ void lambda$addWebpView$1$BaseActivity() {
        LogUtils.d(TAG, "addWebpView onPreDraw");
        showOOBE();
    }

    public void showWebp(final int i) {
        LogUtils.e(TAG, "showWebp state:" + i);
        if (this.mAnimView == null) {
            return;
        }
        ThreadUtils.postMainThread(new Runnable() { // from class: com.xiaopeng.oobe.-$$Lambda$BaseActivity$sAeBhNKzkkK2tCbDn903SRnseUw
            @Override // java.lang.Runnable
            public final void run() {
                BaseActivity.this.lambda$showWebp$2$BaseActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$showWebp$2$BaseActivity(int i) {
        if (this.mAnimView.getVisibility() != 0) {
            this.mAnimView.setVisibility(0);
        }
        switch (i) {
            case 0:
                this.mAnimView.normal();
                return;
            case 1:
                this.mAnimView.speak();
                return;
            case 2:
                this.mAnimView.speaking();
                return;
            case 3:
                this.mAnimView.failure();
                return;
            case 4:
                this.mAnimView.failing();
                return;
            case 5:
                this.mAnimView.successed();
                return;
            case 6:
                this.mAnimView.listening();
                return;
            case 7:
                this.mAnimView.checking();
                return;
            case 8:
                this.mAnimView.smile();
                return;
            case 9:
                this.mAnimView.smillingLef();
                return;
            case 10:
                this.mAnimView.helplessing();
                return;
            case 11:
                this.mAnimView.exit();
                return;
            default:
                return;
        }
    }

    public ViewManager getViewManager() {
        if (this.mViewManager == null) {
            this.mViewManager = new ViewManager();
        }
        return this.mViewManager;
    }

    @Override // android.app.Activity
    public void finish() {
        PrivacyHelper.getInstance().disConnected();
        SPUtils.setOobeFinishStep(6);
        int oobeFinishStep = SPUtils.getOobeFinishStep();
        LogUtils.i(TAG, "finish: step=" + oobeFinishStep + ",STEP_FINISH:6");
        if (oobeFinishStep == 6) {
            ComponentUtil.disableOOBEActivity();
        }
        MainViewModel mainViewModel = this.mMainViewModel;
        if (mainViewModel != null) {
            mainViewModel.onDestroy();
        }
        super.finish();
        startHome();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CheckDataUtils.shouldCheckDataAfterOta()) {
            LogUtils.i(TAG, "onCreate to finish");
            finish();
            return;
        }
        App.getInstance().setActivity(this);
        Choreographer.getInstance().postFrameCallback(this.callback);
        StartPerfUtils.onCreateBegin();
    }

    public void initView() {
        this.mLayoutRoot = (RelativeLayout) findViewById(R.id.activity_oobe_layout_root);
        this.mAssistantView = (PAssistantView) findViewById(R.id.activity_oobe_assistant);
        this.mApertureView = (ImageView) findViewById(R.id.activity_oobe_img_aperture);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy =");
        App.getInstance().setActivity(null);
        if (getMainLooper() != null) {
            if (getMainLooper().getQueue() != null) {
                LogUtils.i(TAG, "onDestroy: getQueue is null ");
                LogUtils.i(TAG, "onDestroy: isidel=" + getMainLooper().getQueue().isIdle());
            }
        } else {
            LogUtils.w(TAG, "onDestroy: getMainLooper is null ");
        }
        if (SystemProperties.getInt("service.bootanim.exit", 0) == 1) {
            LogUtils.i(TAG, "onDestroy: System exit");
            System.exit(0);
        } else {
            LogUtils.i(TAG, "onDestroy: but service.bootanim.exit not over");
            msMustKill = true;
        }
        XpView xpView = this.mAnimView;
        if (xpView != null) {
            xpView.dismiss();
        }
    }

    public void showOOBE() {
        SettingsUtil.setSetupComplete();
        showNext();
    }

    public void startHome() {
        LogUtils.i(TAG, "startHome ");
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.addFlags(270532608);
            startActivity(intent);
        } catch (Exception e) {
            LogUtils.i(TAG, "startHome e=" + e);
        }
    }

    @Override // android.app.Activity
    public void recreate() {
        super.recreate();
        LogUtils.i(TAG, "recreate");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        LogUtils.i(TAG, "onStart");
        StartPerfUtils.onStartBegin();
        super.onStart();
        StartPerfUtils.onStartEnd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        StartPerfUtils.onResumeBegin();
        super.onResume();
        StartPerfUtils.onResumeEnd();
        LogUtils.i(TAG, "onResume" + hashCode());
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        LogUtils.i(TAG, "onWindowFocusChanged " + z);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        LogUtils.i(TAG, "onRestart");
        StartPerfUtils.onReStartBegin();
        super.onRestart();
        StartPerfUtils.onReStartEnd();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        LogUtils.i(TAG, "onPause" + hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        LogUtils.i(TAG, "onStop end");
    }
}
