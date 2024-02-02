package com.xiaopeng.oobe.webp;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceDrawable;
import android.support.rastermill.FrameSequenceUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.oobe.utils.ViewUtil;
import com.xiaopeng.oobe.webp.XpView;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes.dex */
public class XpView extends FrameLayout {
    private static final String TAG = "XPImageView";
    private LinkedList<AnimNode> mAnimNodeQueue;
    private boolean mAutoResetLayout;
    private AnimNode mCurrentAnimNode;
    private XpViewStatus mCurrentStatus;
    private ValueAnimator mFadeInAnimator;
    private AppCompatImageView mInOutImageView;
    private AppCompatImageView mNormalImageView;
    private XpViewHomeHelper mXPImageXpViewHomeHelper;

    public XpView(Context context) {
        this(context, null, 0);
    }

    public XpView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XpView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimNodeQueue = new LinkedList<>();
        this.mAutoResetLayout = true;
        XpViewResource.load(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mNormalImageView = new AppCompatImageView(context);
        this.mInOutImageView = new AppCompatImageView(context);
        this.mInOutImageView.setVisibility(4);
        addView(this.mNormalImageView, layoutParams);
        addView(this.mInOutImageView, layoutParams);
        this.mXPImageXpViewHomeHelper = new XpViewHomeHelper(this);
    }

    public void show() {
        this.mXPImageXpViewHomeHelper.showForHome();
    }

    public void dismiss() {
        clear();
        removeAllViews();
    }

    public void clear() {
        this.mAnimNodeQueue.clear();
        this.mNormalImageView.setVisibility(0);
        this.mInOutImageView.setVisibility(4);
        this.mNormalImageView.setImageDrawable(null);
        this.mInOutImageView.setImageDrawable(null);
        this.mCurrentAnimNode = null;
        this.mCurrentStatus = null;
    }

    public boolean isQueue(XpViewStatus xpViewStatus) {
        if (this.mCurrentStatus == xpViewStatus) {
            return true;
        }
        Iterator<AnimNode> it = this.mAnimNodeQueue.iterator();
        while (it.hasNext()) {
            if (it.next().to == xpViewStatus) {
                return true;
            }
        }
        return false;
    }

    public void setAutoResetLayout(boolean z) {
        this.mAutoResetLayout = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleImageAnim() {
        if (this.mAnimNodeQueue.isEmpty()) {
            return;
        }
        AnimNode poll = this.mAnimNodeQueue.poll();
        ImageView imageView = getImageView(poll);
        if (this.mCurrentAnimNode != null && poll.type == this.mCurrentAnimNode.type && poll.to == this.mCurrentAnimNode.to) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) imageView.getDrawable();
            frameSequenceDrawable.stop();
            frameSequenceDrawable.start();
            return;
        }
        this.mCurrentAnimNode = poll;
        this.mCurrentStatus = poll.to;
        FrameSequenceUtil.destroy(imageView);
        FrameSequenceDrawable frameSequenceDrawable2 = new FrameSequenceDrawable(poll.frameSequence, ViewUtil.sBitmapProvider);
        poll.setDrawable(frameSequenceDrawable2);
        imageView.setImageDrawable(frameSequenceDrawable2);
        FrameSequence frameSequence = poll.frameSequence;
        int width = frameSequence.getWidth();
        int height = frameSequence.getHeight();
        if (this.mAutoResetLayout) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
            LogUtils.i(TAG, "handleImageAnim: width=" + width + " height=" + height + "layoutParams.width= " + layoutParams.width + "layoutParams.height= " + layoutParams.height);
            if (width > 0 && height > 0) {
                layoutParams.width = (width * layoutParams.height) / height;
            } else {
                layoutParams.width = layoutParams.height;
            }
            LogUtils.i(TAG, "handleImageAnim: width=" + layoutParams.width + "  height=" + layoutParams.height + " type =" + poll.type);
            layoutParams.addRule(14);
            setLayoutParams(layoutParams);
        }
        int i = poll.type;
        if (i == 0) {
            FrameSequenceUtil.destroy(this.mInOutImageView);
            this.mInOutImageView.setImageDrawable(null);
            this.mInOutImageView.setVisibility(4);
            this.mNormalImageView.setVisibility(0);
            this.mNormalImageView.setImageAlpha(255);
        } else if (i != 1) {
            if (i == 2 || i == 3) {
                this.mInOutImageView.setVisibility(0);
                this.mNormalImageView.setImageAlpha(4);
            }
        } else {
            this.mInOutImageView.setVisibility(0);
            ValueAnimator valueAnimator = this.mFadeInAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mFadeInAnimator.cancel();
            } else if (this.mNormalImageView.getVisibility() == 0 && this.mNormalImageView.getImageAlpha() >= 255) {
                this.mInOutImageView.setImageAlpha(0);
            }
            this.mFadeInAnimator = ValueAnimator.ofInt(0, 255);
            this.mFadeInAnimator.setDuration(400L);
            this.mFadeInAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.xiaopeng.oobe.webp.-$$Lambda$XpView$BQRQQqPjHmBuOwEVqXkTQeZBkk0
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    XpView.this.lambda$handleImageAnim$0$XpView(valueAnimator2);
                }
            });
            this.mFadeInAnimator.start();
        }
    }

    public /* synthetic */ void lambda$handleImageAnim$0$XpView(ValueAnimator valueAnimator) {
        int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.mInOutImageView.setImageAlpha(intValue);
        this.mNormalImageView.setImageAlpha(255 - intValue);
    }

    private ImageView getImageView(AnimNode animNode) {
        if (animNode.type == 1 || animNode.type == 2 || animNode.type == 3) {
            return this.mInOutImageView;
        }
        return this.mNormalImageView;
    }

    private void keepFirstOutNode() {
        AnimNode animNode = null;
        for (int i = 0; i < this.mAnimNodeQueue.size(); i++) {
            AnimNode animNode2 = this.mAnimNodeQueue.get(i);
            if (animNode2.type == 2) {
                animNode = animNode2;
            } else {
                animNode2.destroy();
            }
        }
        this.mAnimNodeQueue.clear();
        if (animNode != null) {
            this.mAnimNodeQueue.add(animNode);
        }
    }

    public void switchTo(XpViewStatus xpViewStatus) {
        FrameSequence status;
        if (xpViewStatus == this.mCurrentStatus || (status = XpViewResource.getStatus(xpViewStatus)) == null) {
            return;
        }
        keepFirstOutNode();
        FrameSequence statusOut = XpViewResource.getStatusOut(this.mCurrentStatus);
        FrameSequence statusIn = XpViewResource.getStatusIn(xpViewStatus);
        AnimNode animNode = this.mCurrentAnimNode;
        XpViewStatus xpViewStatus2 = animNode != null ? animNode.to : null;
        if (statusOut != null) {
            AnimNode animNode2 = new AnimNode(2, statusOut, xpViewStatus2, this.mCurrentStatus);
            this.mAnimNodeQueue.add(animNode2);
            LogUtils.i(TAG, "switchTo(out):" + animNode2.toString());
        }
        XpViewStatus xpViewStatus3 = this.mCurrentStatus;
        if (statusIn != null) {
            AnimNode animNode3 = new AnimNode(1, statusIn, xpViewStatus3, xpViewStatus);
            this.mAnimNodeQueue.add(animNode3);
            LogUtils.i(TAG, "switchTo(in):" + animNode3.toString());
            xpViewStatus3 = xpViewStatus;
        }
        AnimNode animNode4 = new AnimNode(0, status, xpViewStatus3, xpViewStatus);
        this.mAnimNodeQueue.add(animNode4);
        LogUtils.i(TAG, "switchTo(normal):" + animNode4.toString());
        handleImageAnim();
    }

    public void advanceSequence(XpViewStatus xpViewStatus, boolean z, XpViewStatus... xpViewStatusArr) {
        FrameSequence status;
        if (xpViewStatusArr == null || xpViewStatusArr.length == 0 || (status = XpViewResource.getStatus(xpViewStatus)) == null) {
            return;
        }
        keepFirstOutNode();
        for (XpViewStatus xpViewStatus2 : xpViewStatusArr) {
            FrameSequence status2 = XpViewResource.getStatus(xpViewStatus2);
            if (status2 != null) {
                if (!z && XpViewResource.getStatusIn(xpViewStatus2) != null) {
                    this.mAnimNodeQueue.add(new AnimNode(3, status2, xpViewStatus2, xpViewStatus2));
                }
                this.mAnimNodeQueue.add(new AnimNode(3, status2, xpViewStatus2, xpViewStatus2));
                if (!z && XpViewResource.getStatusOut(xpViewStatus2) != null) {
                    this.mAnimNodeQueue.add(new AnimNode(3, status2, xpViewStatus2, xpViewStatus2));
                }
            }
        }
        this.mAnimNodeQueue.add(new AnimNode(0, status, xpViewStatus, xpViewStatus));
        handleImageAnim();
    }

    public void normalSequence(XpViewStatus xpViewStatus, XpViewStatus... xpViewStatusArr) {
        LogUtils.i(TAG, "normalSequence: " + xpViewStatus.name());
        advanceSequence(xpViewStatus, true, xpViewStatusArr);
    }

    public XpViewStatus getXpViewStatus(int i) {
        XpViewStatus xpViewStatus = XpViewStatus.HOME_DEFAULT;
        switch (i) {
            case 1:
            case 2:
                xpViewStatus = XpViewStatus.HOME_SPEEK;
                break;
            case 3:
            case 4:
                xpViewStatus = XpViewStatus.HOME_FAIL;
                break;
            case 5:
                xpViewStatus = XpViewStatus.HOME_SUCCESS;
                break;
            case 6:
                xpViewStatus = XpViewStatus.HOME_LISTEN_CENTER;
                break;
            case 7:
                xpViewStatus = XpViewStatus.HOME_CHECK;
                break;
            case 8:
                xpViewStatus = XpViewStatus.HOME_SMILE;
                break;
            case 9:
                xpViewStatus = XpViewStatus.HOME_SMILE_LEFT;
                break;
            case 10:
                xpViewStatus = XpViewStatus.HOME_HELPLESS;
                break;
            case 11:
                xpViewStatus = XpViewStatus.HOME_EXIT;
                break;
        }
        Log.e(TAG, "getXpViewStatus: " + i);
        return xpViewStatus;
    }

    public void performStatus(XpViewStatus xpViewStatus, XpViewStatus xpViewStatus2) {
        normalSequence(xpViewStatus, xpViewStatus2);
    }

    public void performStatus(XpViewStatus xpViewStatus) {
        normalSequence(XpViewStatus.HOME_DEFAULT, xpViewStatus);
    }

    public void normal() {
        performStatus(XpViewStatus.HOME_DEFAULT);
    }

    public void speak() {
        performStatus(XpViewStatus.HOME_SPEEK);
    }

    public void speaking() {
        performStatus(XpViewStatus.HOME_SPEEK, XpViewStatus.HOME_SPEEK);
    }

    public void successed() {
        performStatus(XpViewStatus.HOME_SUCCESS);
    }

    public void failing() {
        performStatus(XpViewStatus.HOME_FAIL, XpViewStatus.HOME_FAIL);
    }

    public void failure() {
        performStatus(XpViewStatus.HOME_FAIL);
    }

    public void helplessing() {
        performStatus(XpViewStatus.HOME_HELPLESS, XpViewStatus.HOME_HELPLESS);
    }

    public void exit() {
        performStatus(XpViewStatus.HOME_EXIT);
    }

    public void checking() {
        performStatus(XpViewStatus.HOME_CHECK, XpViewStatus.HOME_CHECK);
    }

    public void smile() {
        performStatus(XpViewStatus.HOME_SMILE);
    }

    public void smillingLef() {
        performStatus(XpViewStatus.HOME_SMILE_LEFT, XpViewStatus.HOME_SMILE_LEFT);
    }

    public void listening() {
        performStatus(XpViewStatus.HOME_LISTEN_CENTER, XpViewStatus.HOME_LISTEN_CENTER);
    }

    /* loaded from: classes.dex */
    public class AnimNode {
        public static final int TYPE_IN = 1;
        public static final int TYPE_NORMAL = 0;
        public static final int TYPE_OUT = 2;
        public static final int TYPE_PLAY_ONE = 3;
        public FrameSequence frameSequence;
        public XpViewStatus from;
        public FrameSequenceDrawable mDrawable = null;
        public XpViewStatus to;
        public int type;

        public AnimNode(int i, FrameSequence frameSequence, XpViewStatus xpViewStatus, XpViewStatus xpViewStatus2) {
            this.type = i;
            this.frameSequence = frameSequence;
            this.from = xpViewStatus;
            this.to = xpViewStatus2;
        }

        public void setDrawable(FrameSequenceDrawable frameSequenceDrawable) {
            if (this.mDrawable != null) {
                freeDrawable();
            }
            LogUtils.i(XpView.TAG, "setDrawable: type=" + this.type);
            int i = this.type;
            if (i == 0) {
                frameSequenceDrawable.setLoopCount(1);
                frameSequenceDrawable.setLoopBehavior(2);
                frameSequenceDrawable.setOnFinishedListener(null);
            } else if (i == 1 || i == 2 || i == 3) {
                frameSequenceDrawable.setLoopCount(1);
                frameSequenceDrawable.setLoopBehavior(1);
                frameSequenceDrawable.setOnFinishedListener(new FrameSequenceDrawable.OnFinishedListener() { // from class: com.xiaopeng.oobe.webp.-$$Lambda$XpView$AnimNode$ZI5Sj_5FtTOP0IrkSR6d58stYrM
                    @Override // android.support.rastermill.FrameSequenceDrawable.OnFinishedListener
                    public final void onFinished(FrameSequenceDrawable frameSequenceDrawable2) {
                        XpView.AnimNode.this.lambda$setDrawable$0$XpView$AnimNode(frameSequenceDrawable2);
                    }
                });
            }
            this.mDrawable = frameSequenceDrawable;
        }

        public /* synthetic */ void lambda$setDrawable$0$XpView$AnimNode(FrameSequenceDrawable frameSequenceDrawable) {
            LogUtils.i(XpView.TAG, "FrameSequenceDrawable OnFinishedListener onFinished: ");
            XpView.this.handleImageAnim();
        }

        public void destroy() {
            freeDrawable();
        }

        private void freeDrawable() {
            FrameSequenceDrawable frameSequenceDrawable = this.mDrawable;
            if (frameSequenceDrawable != null) {
                frameSequenceDrawable.setOnFinishedListener(null);
                this.mDrawable.stop();
                this.mDrawable = null;
            }
        }

        public String toString() {
            return "AnimNode{type=" + this.type + ", from=" + this.from + ", to=" + this.to + '}';
        }
    }
}
