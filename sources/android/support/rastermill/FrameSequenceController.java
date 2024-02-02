package android.support.rastermill;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.rastermill.CacheEngine;
import android.support.rastermill.FrameSequenceDrawable;
import android.support.rastermill.cache.DiskCache;
import android.support.rastermill.data.Encoder;
import android.support.rastermill.data.StreamEncoder;
import android.support.rastermill.prugin.IPlugin;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public final class FrameSequenceController implements CacheEngine.CacheCallback {
    public static final int LOOP_DEFAULT = 3;
    public static final int LOOP_FINITE = 1;
    public static final int LOOP_INF = 2;
    private Integer mAlpha;
    private String mAsset;
    private AsyncTask mAsyncTask;
    private FrameSequenceDrawable.BitmapProvider mBitmapProvider;
    private Boolean mCircleMaskEnabled;
    private ColorFilter mColorFilter;
    private Context mContext;
    private Drawable mErrorDrawable;
    private int mErrorId;
    private File mFile;
    private Boolean mFilterBitmap;
    private Drawable mFinishDrawable;
    private int mFinishId;
    private boolean mForcePlaceholder;
    private Future mFuture;
    private ImageView mImageView;
    private InputStream mInputStream;
    private String mInputStreamKey;
    private LoadListener mLoadListener;
    private ILoader mLoader;
    private OnFinishedListener mOnFinishedListener;
    private Drawable mPlaceholderDrawable;
    private int mPlaceholderId;
    private IPlugin mPlugin;
    private PostProcessor mPostProcessor;
    private Integer mResourceId;
    private String mUrl;
    private static final FileOpener DEFAULT_FILE_OPENER = new FileOpener();
    private static final SparseArray<FrameSequenceController> sRunningTaskMap = new SparseArray<>();
    private static Handler sMainThreadHandler = new Handler(Looper.getMainLooper());
    private int mDecodingThreadId = 0;
    private int mLoopCount = 1;
    private int mLoopBehavior = 3;
    private FileOpener mFileOpener = DEFAULT_FILE_OPENER;

    /* loaded from: classes.dex */
    interface DiskCacheProvider {
        DiskCache getDiskCache();
    }

    /* loaded from: classes.dex */
    public interface OnFinishedListener {
        void onFinished(Drawable drawable);
    }

    /* loaded from: classes.dex */
    public interface PostProcessor {
        Rect getCrop(int i, int i2);

        Integer getSampleSize(int i, int i2);

        int onPostProcess(Canvas canvas);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameSequenceController(ImageView imageView, IPlugin iPlugin) {
        this.mImageView = imageView;
        this.mPlugin = iPlugin;
        this.mContext = imageView.getContext();
    }

    public ImageView getImageView() {
        return this.mImageView;
    }

    public IPlugin getPlugin() {
        return this.mPlugin;
    }

    public int getDecodingThreadId() {
        return this.mDecodingThreadId;
    }

    public int getLoopCount() {
        return this.mLoopCount;
    }

    public int getLoopBehavior() {
        return this.mLoopBehavior;
    }

    public OnFinishedListener getOnFinishedListener() {
        return this.mOnFinishedListener;
    }

    public Integer getAlpha() {
        return this.mAlpha;
    }

    public Boolean getCircleMaskEnabled() {
        return this.mCircleMaskEnabled;
    }

    public ColorFilter getColorFilter() {
        return this.mColorFilter;
    }

    public Boolean getFilterBitmap() {
        return this.mFilterBitmap;
    }

    public FrameSequenceDrawable.BitmapProvider getBitmapProvider() {
        return this.mBitmapProvider;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getAsset() {
        return this.mAsset;
    }

    public File getFile() {
        return this.mFile;
    }

    public Integer getResourceId() {
        return this.mResourceId;
    }

    public InputStream getInputStream() {
        return this.mInputStream;
    }

    public String getInputStreamKey() {
        return this.mInputStreamKey;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public LoadListener getLoadListener() {
        return this.mLoadListener;
    }

    public int getPlaceholderId() {
        return this.mPlaceholderId;
    }

    public Drawable getPlaceholderDrawable() {
        return this.mPlaceholderDrawable;
    }

    public int getErrorId() {
        return this.mErrorId;
    }

    public Drawable getErrorDrawable() {
        return this.mErrorDrawable;
    }

    public int getFinishId() {
        return this.mFinishId;
    }

    public boolean isForcePlaceholder() {
        return this.mForcePlaceholder;
    }

    public PostProcessor getPostProcessor() {
        return this.mPostProcessor;
    }

    public FrameSequenceController decodingThreadId(int i) {
        this.mDecodingThreadId = i;
        return this;
    }

    public FrameSequenceController loopCount(int i) {
        this.mLoopCount = i;
        return this;
    }

    public FrameSequenceController loopBehavior(int i) {
        this.mLoopBehavior = i;
        return this;
    }

    public FrameSequenceController onFinishedListener(OnFinishedListener onFinishedListener) {
        this.mOnFinishedListener = onFinishedListener;
        return this;
    }

    public FrameSequenceController asset(String str) {
        this.mAsset = str;
        return this;
    }

    public FrameSequenceController file(File file) {
        this.mFile = file;
        return this;
    }

    public FrameSequenceController resourceId(int i) {
        this.mResourceId = Integer.valueOf(i);
        return this;
    }

    public FrameSequenceController inputStream(InputStream inputStream) {
        this.mInputStream = inputStream;
        return this;
    }

    public FrameSequenceController inputStream(String str, InputStream inputStream) {
        this.mInputStreamKey = str;
        this.mInputStream = inputStream;
        return this;
    }

    public FrameSequenceController url(String str) {
        this.mUrl = str;
        return this;
    }

    public FrameSequenceController alpha(Integer num) {
        this.mAlpha = num;
        return this;
    }

    public FrameSequenceController circleMaskEnabled(Boolean bool) {
        this.mCircleMaskEnabled = bool;
        return this;
    }

    public FrameSequenceController colorFilter(ColorFilter colorFilter) {
        this.mColorFilter = colorFilter;
        return this;
    }

    public FrameSequenceController filterBitmap(Boolean bool) {
        this.mFilterBitmap = bool;
        return this;
    }

    public FrameSequenceController bitmapProvider(FrameSequenceDrawable.BitmapProvider bitmapProvider) {
        this.mBitmapProvider = bitmapProvider;
        return this;
    }

    public FrameSequenceController loadListener(LoadListener loadListener) {
        this.mLoadListener = loadListener;
        return this;
    }

    public FrameSequenceController forcePlaceholder(boolean z) {
        this.mForcePlaceholder = z;
        return this;
    }

    public FrameSequenceController placeholder(int i) {
        this.mPlaceholderId = i;
        return this;
    }

    public FrameSequenceController placeholder(Drawable drawable) {
        this.mPlaceholderDrawable = drawable;
        return this;
    }

    public FrameSequenceController error(int i) {
        this.mErrorId = i;
        return this;
    }

    public FrameSequenceController error(Drawable drawable) {
        this.mErrorDrawable = drawable;
        return this;
    }

    public FrameSequenceController finish(int i) {
        this.mFinishId = i;
        return this;
    }

    public FrameSequenceController finish(Drawable drawable) {
        this.mFinishDrawable = drawable;
        return this;
    }

    public FrameSequenceController postProcessor(PostProcessor postProcessor) {
        this.mPostProcessor = postProcessor;
        return this;
    }

    @MainThread
    public void applyAsync() {
        ILoader loader = getLoader();
        if (this.mPlugin.checkApplySameOrCache(this, loader)) {
            return;
        }
        if (this.mForcePlaceholder) {
            setPlaceholder();
        }
        AsyncTask asyncTask = this.mAsyncTask;
        if (asyncTask != null && !asyncTask.isCancelled()) {
            this.mAsyncTask.cancel(true);
        }
        Future future = this.mFuture;
        if (future != null && !future.isDone() && !this.mFuture.isCancelled()) {
            this.mFuture.cancel(true);
        }
        this.mAsyncTask = null;
        this.mFuture = null;
        putTask(this.mImageView, this);
        if (loader.isCacheRequired()) {
            if (loader.exists()) {
                this.mAsyncTask = new LoaderAsyncTask(this);
            } else {
                this.mFuture = CacheEngine.getInstance().cache(loader.getKey(), this);
            }
        } else {
            this.mAsyncTask = new LoaderAsyncTask(this);
        }
        AsyncTask asyncTask2 = this.mAsyncTask;
        if (asyncTask2 != null) {
            asyncTask2.execute(new Object[0]);
        }
    }

    public Drawable apply() {
        ILoader loader = getLoader();
        if (this.mPlugin.checkApplySameOrCache(this, loader)) {
            return this.mImageView.getDrawable();
        }
        Drawable drawable = getDrawable(loader);
        applyInternal(loader, drawable, true);
        return drawable;
    }

    public void applyInternal(ILoader iLoader, Drawable drawable, boolean z) {
        if (z) {
            FrameSequenceUtil.destroy(this.mImageView);
        }
        this.mPlugin.apply(this, iLoader, drawable);
        iLoader.release();
    }

    private void setPlaceholder() {
        FrameSequenceUtil.destroy(this.mImageView);
        setImageView(this.mImageView, getPlaceholderDrawable(false), null);
    }

    public Drawable getPlaceholderDrawable(boolean z) {
        Drawable drawable;
        if (!z || this.mErrorId == 0) {
            if (!z || (drawable = this.mErrorDrawable) == null) {
                if (this.mPlaceholderId != 0) {
                    return this.mContext.getResources().getDrawable(this.mPlaceholderId);
                }
                Drawable drawable2 = this.mPlaceholderDrawable;
                if (drawable2 != null) {
                    return drawable2;
                }
                return null;
            }
            return drawable;
        }
        return this.mContext.getResources().getDrawable(this.mErrorId);
    }

    public Drawable getFinishDrawable() {
        if (this.mFinishId != 0) {
            return this.mContext.getResources().getDrawable(this.mFinishId);
        }
        Drawable drawable = this.mFinishDrawable;
        if (drawable != null) {
            return drawable;
        }
        return null;
    }

    public boolean hasFinish() {
        return (this.mFinishId == 0 && this.mFinishDrawable == null) ? false : true;
    }

    public ILoader getLoader() {
        ILoader iLoader = this.mLoader;
        if (iLoader != null) {
            return iLoader;
        }
        ILoader loader = this.mPlugin.getLoader(this);
        this.mLoader = loader;
        return loader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Drawable getDrawable(ILoader iLoader) {
        return this.mPlugin.getDrawable(this, iLoader);
    }

    private void cancelAsyncTask() {
        AsyncTask asyncTask = this.mAsyncTask;
        if (asyncTask != null && !asyncTask.isCancelled()) {
            this.mAsyncTask.cancel(true);
        }
        Future future = this.mFuture;
        if (future != null && !future.isDone() && !this.mFuture.isCancelled()) {
            this.mFuture.cancel(true);
        }
        this.mAsyncTask = null;
        this.mFuture = null;
    }

    @Override // android.support.rastermill.CacheEngine.CacheCallback
    public void onCacheFinished(String str) {
        if (LogUtil.isLogEnable()) {
            LogUtil.e("onCacheFinished:" + str);
        }
        this.mFuture = null;
        this.mAsyncTask = new LoaderAsyncTask(this);
        this.mAsyncTask.execute(new Object[0]);
    }

    @Override // android.support.rastermill.CacheEngine.CacheCallback
    public void onCacheFailure(String str) {
        if (LogUtil.isLogEnable()) {
            LogUtil.e("onCacheFailure:" + str);
        }
        this.mFuture = null;
        removeTask(this.mImageView);
    }

    @Override // android.support.rastermill.CacheEngine.CacheCallback
    public void cacheData(String str, InputStream inputStream) {
        CacheEngine.getInstance().getDiskCache().put(str, new SourceWriter(new StreamEncoder(), inputStream));
    }

    public void onFinished(Drawable drawable) {
        this.mImageView.setTag(R.id.tag_on_finished, null);
        if (hasFinish()) {
            FrameSequenceUtil.destroy(getImageView());
            setImageView(this.mImageView, getFinishDrawable(), null);
            OnFinishedListener onFinishedListener = this.mOnFinishedListener;
            if (onFinishedListener != null) {
                onFinishedListener.onFinished(drawable);
                return;
            }
            return;
        }
        OnFinishedListener onFinishedListener2 = this.mOnFinishedListener;
        if (onFinishedListener2 != null) {
            onFinishedListener2.onFinished(drawable);
        }
    }

    public void postOnFinished(final Drawable drawable) {
        Runnable runnable = new Runnable() { // from class: android.support.rastermill.FrameSequenceController.1
            @Override // java.lang.Runnable
            public void run() {
                FrameSequenceController.this.onFinished(drawable);
            }
        };
        this.mImageView.setTag(R.id.tag_on_finished, runnable);
        sMainThreadHandler.post(runnable);
    }

    public static void setImageView(ImageView imageView, Drawable drawable, String str) {
        if (drawable == null) {
            str = null;
        }
        imageView.setTag(R.id.tag_key, str);
        imageView.setImageDrawable(drawable);
    }

    public static String getKey(View view) {
        return (String) getTag(view, R.id.tag_key, String.class);
    }

    private static void putTask(ImageView imageView, FrameSequenceController frameSequenceController) {
        if (imageView == null) {
            return;
        }
        int hashCode = imageView.hashCode();
        sRunningTaskMap.put(hashCode, frameSequenceController);
        if (LogUtil.isLogEnable()) {
            LogUtil.e("putTask : " + hashCode + ", new size : " + sRunningTaskMap.size());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public static void removeTask(ImageView imageView) {
        if (imageView == null) {
            return;
        }
        int hashCode = imageView.hashCode();
        sRunningTaskMap.remove(hashCode);
        if (LogUtil.isLogEnable()) {
            LogUtil.e("removeTask : " + hashCode + ", new size : " + sRunningTaskMap.size());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @MainThread
    public static void cancelTask(ImageView imageView) {
        FrameSequenceController frameSequenceController;
        if (imageView == null || (frameSequenceController = sRunningTaskMap.get(imageView.hashCode())) == null) {
            return;
        }
        frameSequenceController.cancelAsyncTask();
        removeTask(imageView);
    }

    static <T> T getTag(View view, int i, Class<T> cls) {
        T t = (T) view.getTag(R.id.tag_key);
        if (cls.isInstance(t)) {
            return t;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearOnFinished(ImageView imageView) {
        Runnable runnable = (Runnable) getTag(imageView, R.id.tag_on_finished, Runnable.class);
        if (runnable != null) {
            sMainThreadHandler.removeCallbacks(runnable);
        }
        imageView.setTag(R.id.tag_on_finished, null);
    }

    /* loaded from: classes.dex */
    class SourceWriter<DataType> implements DiskCache.Writer {
        private final DataType data;
        private final Encoder<DataType> encoder;

        public SourceWriter(Encoder<DataType> encoder, DataType datatype) {
            this.encoder = encoder;
            this.data = datatype;
        }

        @Override // android.support.rastermill.cache.DiskCache.Writer
        public boolean write(File file) {
            boolean z;
            OutputStream outputStream = null;
            try {
                try {
                    outputStream = FrameSequenceController.this.mFileOpener.open(file);
                    z = this.encoder.encode(this.data, outputStream);
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                LogUtil.e("Failed to find file to write to disk cache", e);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                z = false;
            }
            return z;
        }
    }

    /* loaded from: classes.dex */
    static class FileOpener {
        FileOpener() {
        }

        public OutputStream open(File file) throws FileNotFoundException {
            return new BufferedOutputStream(new FileOutputStream(file));
        }
    }
}
