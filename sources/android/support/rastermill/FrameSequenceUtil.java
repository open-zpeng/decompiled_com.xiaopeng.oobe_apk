package android.support.rastermill;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.rastermill.cache.DiskCache;
import android.support.rastermill.cache.InternalCacheDiskCacheFactory;
import android.support.rastermill.framesequence.FrameSequencePlugin;
import android.support.rastermill.prugin.IPlugin;
import android.widget.ImageView;
import androidx.annotation.MainThread;
/* loaded from: classes.dex */
public class FrameSequenceUtil {
    public static IPlugin sPlugin = new FrameSequencePlugin();

    public static void init(Context context) {
        init(context, null);
    }

    public static void init(Context context, FrameSequenceParams frameSequenceParams) {
        IPlugin iPlugin;
        DiskCache.Factory factory = null;
        if (frameSequenceParams != null) {
            factory = frameSequenceParams.getDiskCacheFactory();
            iPlugin = frameSequenceParams.getPlugin();
        } else {
            iPlugin = null;
        }
        if (factory == null) {
            factory = new InternalCacheDiskCacheFactory(context);
        }
        if (iPlugin != null) {
            sPlugin = iPlugin;
        }
        if (frameSequenceParams != null) {
            sPlugin.init(frameSequenceParams);
        }
        CacheEngine.init(context, factory);
    }

    @MainThread
    public static FrameSequenceController with(ImageView imageView) {
        if (imageView == null) {
            throw new NullPointerException("imageView is null");
        }
        return new FrameSequenceController(imageView, sPlugin);
    }

    @MainThread
    public static void destroy(ImageView imageView) {
        destroy(imageView, true);
    }

    @MainThread
    public static void destroy(ImageView imageView, boolean z) {
        FrameSequenceController.clearOnFinished(imageView);
        FrameSequenceController.cancelTask(imageView);
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            destroy(FrameSequenceController.getKey(imageView), drawable, z);
        }
        FrameSequenceController.setImageView(imageView, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @MainThread
    public static void destroy(String str, Drawable drawable, boolean z) {
        if (drawable == null) {
            return;
        }
        sPlugin.destroy(str, drawable, z);
    }

    @MainThread
    public static void stop(ImageView imageView) {
        FrameSequenceController.clearOnFinished(imageView);
        FrameSequenceController.cancelTask(imageView);
        stop(imageView.getDrawable());
    }

    @MainThread
    private static void stop(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        sPlugin.stop(drawable);
    }

    @MainThread
    public static void start(ImageView imageView) {
        start(imageView.getDrawable());
    }

    @MainThread
    private static void start(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        sPlugin.start(drawable);
    }
}
