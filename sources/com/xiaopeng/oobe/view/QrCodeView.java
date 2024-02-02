package com.xiaopeng.oobe.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaopeng.oobe.R;
import com.xiaopeng.oobe.utils.FileUtils;
import com.xiaopeng.oobe.utils.OOBEHelper;
import com.xiaopeng.xui.widget.XButton;
import com.xiaopeng.xui.widget.XLoading;
/* loaded from: classes.dex */
public class QrCodeView extends RelativeLayout {
    private static final String TAG = "QrCodeView";
    private Animation mAnimation;
    private ImageView mBgView;
    private XButton mBtnRefresh;
    private View.OnClickListener mBtnRefreshClick;
    private TextView mExplainTv;
    private ImageView mHeadPortraitBgView;
    private CircleImageView mHeadPortraitView;
    private LoadImageTask mLoadImageTask;
    private XLoading mLoadingView;
    private ImageView mQrCodeView;
    private TextView mSubContent;

    public QrCodeView(Context context) {
        this(context, null);
    }

    public QrCodeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public QrCodeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public QrCodeView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        LayoutInflater.from(getContext()).inflate(R.layout.view_qrcode, (ViewGroup) this, true);
        this.mExplainTv = (TextView) findViewById(R.id.view_qrcode_text_explain);
        this.mSubContent = (TextView) findViewById(R.id.view_qrcode_text_skin);
        this.mLoadingView = (XLoading) findViewById(R.id.view_qrcode_loading);
        this.mBgView = (ImageView) findViewById(R.id.view_qrcode_bg);
        this.mQrCodeView = (ImageView) findViewById(R.id.view_qrcode_img);
        this.mBtnRefresh = (XButton) findViewById(R.id.view_qrcode_btn_refresh);
        if (OOBEHelper.isSupportOS5()) {
            this.mExplainTv.setText("");
            this.mSubContent.setText("");
            this.mBtnRefresh.setText("");
            this.mBtnRefresh.setBackgroundResource(R.drawable.img_qr_btn_refresh);
            Log.d(TAG, "setBackgroundResource completed");
        }
        this.mBtnRefresh.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.oobe.view.-$$Lambda$QrCodeView$YTPhiyxHICYYst1xvHBYW47sTyo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QrCodeView.this.lambda$new$0$QrCodeView(view);
            }
        });
        this.mHeadPortraitView = (CircleImageView) findViewById(R.id.head_portrait_view);
        this.mHeadPortraitBgView = (ImageView) findViewById(R.id.head_portrait_view_bg);
        this.mAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate_assistant_bg_icon);
        this.mAnimation.setDuration(12000L);
        this.mAnimation.setRepeatMode(1);
        this.mAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.xiaopeng.oobe.view.QrCodeView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (QrCodeView.this.mAnimation != null) {
                    QrCodeView.this.mAnimation.start();
                }
            }
        });
    }

    public /* synthetic */ void lambda$new$0$QrCodeView(View view) {
        View.OnClickListener onClickListener = this.mBtnRefreshClick;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Animation animation = this.mAnimation;
        if (animation == null || !animation.hasStarted()) {
            return;
        }
        this.mAnimation.cancel();
        this.mAnimation = null;
    }

    public void clear() {
        Animation animation = this.mAnimation;
        if (animation != null && animation.hasStarted()) {
            this.mAnimation.cancel();
            this.mAnimation = null;
        }
        LoadImageTask loadImageTask = this.mLoadImageTask;
        if (loadImageTask != null) {
            loadImageTask.cancel(true);
        }
        this.mLoadImageTask = null;
        this.mHeadPortraitView.setVisibility(8);
        this.mHeadPortraitBgView.setVisibility(8);
        this.mExplainTv.setText("");
        this.mSubContent.setText("");
    }

    public void setQrCode(Bitmap bitmap) {
        if (bitmap != null) {
            this.mQrCodeView.setImageBitmap(bitmap);
            this.mBtnRefresh.setVisibility(8);
        }
    }

    public int getQrCodeViewSize() {
        return this.mQrCodeView.getHeight();
    }

    public void setExplainTv(String str) {
        TextView textView = this.mExplainTv;
        if (textView != null) {
            textView.setText(str);
        }
    }

    public void setQrCodeRefresh() {
        this.mQrCodeView.setImageResource(R.drawable.img_qr_code_invalid);
        this.mBtnRefresh.setVisibility(0);
    }

    public void showLoginSuccess(String str, String str2) {
        this.mQrCodeView.setVisibility(4);
        this.mBgView.setVisibility(4);
        this.mLoadingView.setVisibility(4);
        this.mBtnRefresh.setVisibility(4);
        if (!OOBEHelper.isSupportOS5()) {
            this.mExplainTv.setText(str);
            this.mSubContent.setText(R.string.login_success_tips);
        }
        if (this.mLoadImageTask == null) {
            this.mLoadImageTask = new LoadImageTask();
        }
        this.mLoadImageTask.execute(str2);
        this.mHeadPortraitBgView.setBackgroundResource(R.drawable.img_pre_landing_bg);
    }

    public void setQrCodeView(int i) {
        if (i == 0) {
            this.mLoadingView.setVisibility(0);
            this.mQrCodeView.setVisibility(4);
            this.mBgView.setImageResource(OOBEHelper.isSupportOS5() ? R.drawable.img_qr_code_invalid : R.drawable.img_qr_bg);
        } else if (i == 1) {
            this.mLoadingView.setVisibility(8);
            this.mBgView.setImageResource(R.drawable.img_qr_code);
            this.mQrCodeView.setVisibility(0);
        } else if (i == 2) {
            this.mLoadingView.setVisibility(8);
            this.mQrCodeView.setVisibility(0);
            setQrCodeRefresh();
            Log.i(TAG, "refresh");
            if (OOBEHelper.isSupportOS5()) {
                return;
            }
            this.mBgView.setImageResource(R.drawable.img_qr_code);
        }
    }

    public void setOnRefreshBthClick(View.OnClickListener onClickListener) {
        this.mBtnRefreshClick = onClickListener;
    }

    /* loaded from: classes.dex */
    private class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        private LoadImageTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            if (isCancelled()) {
                return null;
            }
            return FileUtils.downloadFile(strArr[0]);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                QrCodeView.this.mHeadPortraitView.setImageBitmap(bitmap);
            } else {
                QrCodeView.this.mHeadPortraitView.setImageResource(R.drawable.img_pre_landing);
            }
            if (QrCodeView.this.mHeadPortraitView.getVisibility() != 0) {
                QrCodeView.this.mHeadPortraitView.setVisibility(0);
                if (QrCodeView.this.mHeadPortraitBgView.getVisibility() != 0) {
                    QrCodeView.this.mHeadPortraitBgView.setVisibility(0);
                    QrCodeView.this.mHeadPortraitBgView.setAnimation(QrCodeView.this.mAnimation);
                    if (QrCodeView.this.mAnimation != null) {
                        QrCodeView.this.mAnimation.reset();
                        QrCodeView.this.mAnimation.start();
                    }
                }
            }
        }
    }
}
