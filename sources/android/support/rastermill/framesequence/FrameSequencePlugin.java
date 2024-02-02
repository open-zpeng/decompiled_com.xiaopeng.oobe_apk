package android.support.rastermill.framesequence;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceController;
import android.support.rastermill.FrameSequenceDrawable;
import android.support.rastermill.FrameSequenceParams;
import android.support.rastermill.ILoader;
import android.support.rastermill.LoadListener;
import android.support.rastermill.framesequence.loader.AbsLoader;
import android.support.rastermill.framesequence.loader.AssetLoader;
import android.support.rastermill.framesequence.loader.FileLoader;
import android.support.rastermill.framesequence.loader.HttpLoader;
import android.support.rastermill.framesequence.loader.InputStreamLoader;
import android.support.rastermill.framesequence.loader.NoneLoader;
import android.support.rastermill.framesequence.loader.ResourceLoader;
import android.support.rastermill.prugin.IPlugin;
import android.text.TextUtils;
import android.widget.ImageView;
import androidx.annotation.MainThread;
/* loaded from: classes.dex */
public class FrameSequencePlugin implements IPlugin {
    @Override // android.support.rastermill.prugin.IPlugin
    public void init(FrameSequenceParams frameSequenceParams) {
        FrameSequenceCache.setFrameSequenceCacheSize(frameSequenceParams.getFrameSequenceCacheSize());
        FrameSequenceCache.setRecycleDrawableCacheSize(frameSequenceParams.getRecycleDrawableCacheSize());
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public void stop(Drawable drawable) {
        if (drawable instanceof FrameSequenceDrawable) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
            if (frameSequenceDrawable.isDestroyed() || !frameSequenceDrawable.isRunning()) {
                return;
            }
            frameSequenceDrawable.stop();
        }
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public void start(Drawable drawable) {
        if (drawable instanceof FrameSequenceDrawable) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
            if (frameSequenceDrawable.isDestroyed() || frameSequenceDrawable.isRunning()) {
                return;
            }
            frameSequenceDrawable.start();
        }
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public void destroy(String str, Drawable drawable, boolean z) {
        if (drawable instanceof FrameSequenceDrawable) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
            frameSequenceDrawable.setOnFinishedListener(null);
            if (frameSequenceDrawable.isDestroyed()) {
                return;
            }
            frameSequenceDrawable.stop();
            if (z && FrameSequenceCache.isRecycleDrawableCacheEnable() && !TextUtils.isEmpty(str)) {
                FrameSequenceCache.putCachedFrameSequenceDrawable(str, frameSequenceDrawable);
            } else {
                destroy(str, frameSequenceDrawable);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MainThread
    public static void destroy(String str, FrameSequenceDrawable frameSequenceDrawable) {
        if (frameSequenceDrawable == null || frameSequenceDrawable.isDestroyed()) {
            return;
        }
        frameSequenceDrawable.setOnFinishedListener(null);
        frameSequenceDrawable.destroy();
        FrameSequenceRefHelper.decrease(frameSequenceDrawable.getFrameSequence(), str);
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public ILoader getLoader(FrameSequenceController frameSequenceController) {
        Context context = frameSequenceController.getContext();
        if (frameSequenceController.getInputStream() != null) {
            return new InputStreamLoader(context, frameSequenceController.getInputStreamKey(), frameSequenceController.getInputStream());
        }
        if (context != null && frameSequenceController.getResourceId() != null) {
            return new ResourceLoader(context, frameSequenceController.getResourceId().intValue());
        }
        if (context != null && !TextUtils.isEmpty(frameSequenceController.getAsset())) {
            return new AssetLoader(context, frameSequenceController.getAsset());
        }
        if (frameSequenceController.getFile() != null && frameSequenceController.getFile().exists()) {
            return new FileLoader(context, frameSequenceController.getFile());
        }
        if (!TextUtils.isEmpty(frameSequenceController.getUrl())) {
            return new HttpLoader(context, frameSequenceController.getUrl());
        }
        return new NoneLoader(context);
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public Drawable getDrawable(FrameSequenceController frameSequenceController, ILoader iLoader) {
        FrameSequence frameSequence;
        if (iLoader == null || !iLoader.exists() || (frameSequence = ((AbsLoader) iLoader).getFrameSequence()) == null) {
            return null;
        }
        FrameSequenceDrawable frameSequenceDrawable = getFrameSequenceDrawable(frameSequenceController, frameSequence);
        FrameSequenceRefHelper.increase(frameSequenceDrawable.getFrameSequence());
        return frameSequenceDrawable;
    }

    private FrameSequenceDrawable getFrameSequenceDrawable(FrameSequenceController frameSequenceController, FrameSequence frameSequence) {
        if (frameSequenceController.getBitmapProvider() != null) {
            return new FrameSequenceDrawable(frameSequence, frameSequenceController.getBitmapProvider(), frameSequenceController.getDecodingThreadId());
        }
        return new FrameSequenceDrawable(frameSequence, frameSequenceController.getDecodingThreadId());
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public void apply(final FrameSequenceController frameSequenceController, ILoader iLoader, Drawable drawable) {
        Drawable placeholderDrawable;
        FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) drawable;
        String key = iLoader.getKey();
        String str = null;
        if (frameSequenceDrawable != null) {
            frameSequenceDrawable.setLoopCount(frameSequenceController.getLoopCount());
            frameSequenceDrawable.setLoopBehavior(frameSequenceController.getLoopBehavior());
            FrameSequenceController.OnFinishedListener onFinishedListener = frameSequenceController.getOnFinishedListener();
            frameSequenceDrawable.setOnFinishedListener(null);
            if (frameSequenceController.hasFinish() || onFinishedListener != null) {
                frameSequenceDrawable.setOnFinishedListener(new FrameSequenceDrawable.OnFinishedListener() { // from class: android.support.rastermill.framesequence.FrameSequencePlugin.1
                    @Override // android.support.rastermill.FrameSequenceDrawable.OnFinishedListener
                    public void onFinished(FrameSequenceDrawable frameSequenceDrawable2) {
                        frameSequenceController.onFinished(frameSequenceDrawable2);
                    }
                });
            }
            drawable.setColorFilter(frameSequenceController.getColorFilter());
            if (frameSequenceController.getAlpha() != null) {
                frameSequenceDrawable.setAlpha(frameSequenceController.getAlpha().intValue());
            }
            if (frameSequenceController.getCircleMaskEnabled() != null) {
                frameSequenceDrawable.setCircleMaskEnabled(frameSequenceController.getCircleMaskEnabled().booleanValue());
            }
            if (frameSequenceController.getFilterBitmap() != null) {
                frameSequenceDrawable.setFilterBitmap(frameSequenceController.getFilterBitmap().booleanValue());
            }
            placeholderDrawable = drawable;
            str = key;
        } else {
            placeholderDrawable = frameSequenceController.getPlaceholderDrawable(true);
        }
        FrameSequenceController.setImageView(frameSequenceController.getImageView(), placeholderDrawable, str);
        LoadListener loadListener = frameSequenceController.getLoadListener();
        if (loadListener != null) {
            if (drawable == null) {
                loadListener.onFail(iLoader.getType(), key);
            } else {
                loadListener.onReady(iLoader.getType(), key, frameSequenceDrawable);
            }
        }
    }

    @Override // android.support.rastermill.prugin.IPlugin
    public boolean checkApplySameOrCache(FrameSequenceController frameSequenceController, ILoader iLoader) {
        return checkApplySame(frameSequenceController, iLoader) || checkApplyCache(frameSequenceController, iLoader);
    }

    private boolean checkApplyCache(FrameSequenceController frameSequenceController, ILoader iLoader) {
        FrameSequenceDrawable cachedFrameSequenceDrawable = FrameSequenceCache.getCachedFrameSequenceDrawable(iLoader.getKey());
        if (cachedFrameSequenceDrawable != null) {
            frameSequenceController.applyInternal(iLoader, cachedFrameSequenceDrawable, true);
            return true;
        }
        return false;
    }

    private boolean checkApplySame(FrameSequenceController frameSequenceController, ILoader iLoader) {
        ImageView imageView = frameSequenceController.getImageView();
        String key = FrameSequenceController.getKey(imageView);
        if (!TextUtils.isEmpty(key) && key.equals(iLoader.getKey()) && (imageView.getDrawable() instanceof FrameSequenceDrawable)) {
            FrameSequenceDrawable frameSequenceDrawable = (FrameSequenceDrawable) imageView.getDrawable();
            frameSequenceDrawable.stop();
            FrameSequenceController.setImageView(imageView, null, null);
            frameSequenceController.applyInternal(iLoader, frameSequenceDrawable, false);
            return true;
        }
        return false;
    }
}
