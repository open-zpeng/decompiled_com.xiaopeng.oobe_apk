package com.xiaopeng.oobe.audio;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import com.xiaopeng.lib.utils.FileUtils;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.base.R;
/* loaded from: classes.dex */
public class SoundPlayHelper implements AudioManager.OnAudioFocusChangeListener, MediaPlayer.OnCompletionListener {
    private static final String TAG = "SoundPlayHelper";
    private boolean isRetryTimeOutPlay;
    private Context mContext;
    private boolean mIsAudioFocus;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private int mRawId;
    private Runnable mRunnTimeout;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Holder {
        private static final SoundPlayHelper Instance = new SoundPlayHelper();

        private Holder() {
        }
    }

    public static final SoundPlayHelper instance() {
        return Holder.Instance;
    }

    private SoundPlayHelper() {
        this.mMediaPlayer = null;
        this.mIsAudioFocus = false;
        this.mRawId = -1;
        this.mRunnTimeout = new Runnable() { // from class: com.xiaopeng.oobe.audio.SoundPlayHelper.1
            @Override // java.lang.Runnable
            public void run() {
                boolean isRetry = SoundPlayHelper.this.isRetry();
                LogUtils.i(SoundPlayHelper.TAG, "mRunnTimeout callback  retry:" + isRetry);
                if (SoundPlayHelper.this.mOnCompletionListener == null || isRetry) {
                    return;
                }
                LogUtils.i(SoundPlayHelper.TAG, "mRunnTimeout callback  onCompletion ");
                SoundPlayHelper.this.mOnCompletionListener.onCompletion(null);
                SoundPlayHelper.this.mOnCompletionListener = null;
            }
        };
        this.mContext = App.getInstance();
        if (this.mContext != null) {
            LogUtils.i(TAG, "Create Audio Manager");
        } else {
            LogUtils.i(TAG, "Create Audio Manager, context = null");
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) {
        LogUtils.i(TAG, "Audio Focus Changed, focusChange = " + i);
        if (i == -3 || i == -2 || i == -1) {
            this.mIsAudioFocus = false;
            stop();
        } else if (i == 1 || i == 2 || i == 3) {
            if (!this.mIsAudioFocus) {
                play(this.mRawId);
            }
            this.mIsAudioFocus = true;
        }
    }

    public void play(int i) {
        play(i, 10, null);
    }

    public void play(int i, int i2) {
        play(i, i2, null);
    }

    public void play(int i, MediaPlayer.OnCompletionListener onCompletionListener) {
        play(i, 10, onCompletionListener);
    }

    public void play(final int i, final int i2, final MediaPlayer.OnCompletionListener onCompletionListener) {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.audio.-$$Lambda$SoundPlayHelper$RfjDr6DOVMvD7vrAkin_64G8Nls
            @Override // java.lang.Runnable
            public final void run() {
                SoundPlayHelper.this.lambda$play$0$SoundPlayHelper(i, i2, onCompletionListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: playBackground */
    public void lambda$play$0$SoundPlayHelper(int i, int i2, MediaPlayer.OnCompletionListener onCompletionListener) {
        LogUtils.i(TAG, "play mRawId = " + this.mRawId + ", rawId = " + i + ", mMediaPlayer = " + this.mMediaPlayer);
        int i3 = this.mRawId;
        if (i3 == i) {
            LogUtils.e(TAG, "playBackground mRawId == rawId ");
            return;
        }
        this.mOnCompletionListener = onCompletionListener;
        AssetFileDescriptor assetFileDescriptor = null;
        if (i3 != i && i != -1) {
            MediaPlayer mediaPlayer = this.mMediaPlayer;
            if (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        this.mMediaPlayer.stop();
                        this.mMediaPlayer.release();
                        if (this.mOnCompletionListener != null) {
                            LogUtils.i(TAG, " onCompletion play callback " + this.mMediaPlayer);
                            this.mOnCompletionListener.onCompletion(this.mMediaPlayer);
                            this.mOnCompletionListener = null;
                        }
                    }
                } catch (Exception unused) {
                }
                LogUtils.i(TAG, "Stop Media Play, mRawId = " + this.mRawId);
            }
            this.mRawId = i;
        }
        LogUtils.i(TAG, "Media Play, rawId = " + this.mRawId);
        try {
            try {
                assetFileDescriptor = this.mContext.getResources().openRawResourceFd(this.mRawId);
                this.mMediaPlayer = new MediaPlayer();
                this.mMediaPlayer.setAudioStreamType(i2);
                this.mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                assetFileDescriptor.close();
                this.mMediaPlayer.setOnCompletionListener(this);
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.xiaopeng.oobe.audio.-$$Lambda$SoundPlayHelper$FBQ36Yf8-x93rIg-gJCVhbpfETo
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer2) {
                        SoundPlayHelper.this.lambda$playBackground$1$SoundPlayHelper(mediaPlayer2);
                    }
                });
                LogUtils.i(TAG, "MediaPlay prepareAsync");
                this.mMediaPlayer.prepareAsync();
                this.isRetryTimeOutPlay = true;
                ThreadUtils.postBackground(this.mRunnTimeout, 10000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            FileUtils.closeQuietly(assetFileDescriptor);
        }
    }

    public /* synthetic */ void lambda$playBackground$1$SoundPlayHelper(MediaPlayer mediaPlayer) {
        try {
            if (this.mMediaPlayer != null) {
                LogUtils.i(TAG, "Start Play");
                this.mMediaPlayer.start();
            } else {
                LogUtils.i(TAG, "Media Play, mMediaPlayer = null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying(@NonNull int i) {
        try {
            if (this.mMediaPlayer == null || this.mRawId != i) {
                return false;
            }
            return this.mMediaPlayer.isPlaying();
        } catch (IllegalStateException e) {
            LogUtils.e(TAG, "isPlaying e:" + e);
            return false;
        }
    }

    public void stop() {
        ThreadUtils.removeRunnable(this.mRunnTimeout);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.audio.-$$Lambda$SoundPlayHelper$KBfg5_KmkT5YDhrSXjFmUrzVRCs
            @Override // java.lang.Runnable
            public final void run() {
                SoundPlayHelper.this.lambda$stop$2$SoundPlayHelper();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stopInternal */
    public void lambda$stop$2$SoundPlayHelper() {
        LogUtils.i(TAG, "mediaStop");
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        this.mRawId = -1;
    }

    public void clear() {
        ThreadUtils.removeRunnable(this.mRunnTimeout);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.audio.-$$Lambda$SoundPlayHelper$ookkrnOs76aGTxnTgpTEDkcikAQ
            @Override // java.lang.Runnable
            public final void run() {
                SoundPlayHelper.this.lambda$clear$3$SoundPlayHelper();
            }
        });
    }

    public /* synthetic */ void lambda$clear$3$SoundPlayHelper() {
        LogUtils.i(TAG, "clear");
        lambda$stop$2$SoundPlayHelper();
        this.mOnCompletionListener = null;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(final MediaPlayer mediaPlayer) {
        ThreadUtils.removeRunnable(this.mRunnTimeout);
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.audio.-$$Lambda$SoundPlayHelper$YLuSJZ0Qy1xtHp-VliTVNyEIkxM
            @Override // java.lang.Runnable
            public final void run() {
                SoundPlayHelper.this.lambda$onCompletion$4$SoundPlayHelper(mediaPlayer);
            }
        });
    }

    public /* synthetic */ void lambda$onCompletion$4$SoundPlayHelper(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            LogUtils.i(TAG, "Media Play Completed, mRawId = " + this.mRawId);
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                LogUtils.e(TAG, "Media Play Completed, error = " + e.getMessage());
            }
        }
        this.mRawId = -1;
        if (this.mOnCompletionListener != null) {
            LogUtils.i(TAG, "onCompletion callback  " + mediaPlayer);
            this.mOnCompletionListener.onCompletion(mediaPlayer);
            this.mOnCompletionListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRetry() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying() && this.isRetryTimeOutPlay) {
            this.isRetryTimeOutPlay = false;
            ThreadUtils.postBackground(this.mRunnTimeout, 10000L);
            return true;
        }
        return false;
    }

    public int getInitStartRes() {
        if (App.getInstance().isMsTtsEngines()) {
            return R.raw.microsoft_oobe_init_start;
        }
        return R.raw.oobe_init_start;
    }

    public int getInitSuccessRes() {
        if (App.getInstance().isMsTtsEngines()) {
            return R.raw.microsoft_oobe_init_success;
        }
        return R.raw.oobe_init_success;
    }

    public int getLoginScanRes() {
        if (App.getInstance().isMsTtsEngines()) {
            return R.raw.microsoft_oobe_login_scan;
        }
        return R.raw.oobe_login_scan;
    }

    public int getTimeOutExitRes() {
        if (App.getInstance().isMsTtsEngines()) {
            return R.raw.microsoft_oobe_timeout_exit;
        }
        return R.raw.oobe_timeout_exit;
    }
}
