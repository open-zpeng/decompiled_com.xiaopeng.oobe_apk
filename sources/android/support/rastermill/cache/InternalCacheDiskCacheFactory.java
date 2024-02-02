package android.support.rastermill.cache;

import android.content.Context;
import android.support.rastermill.cache.DiskCache;
import android.support.rastermill.cache.DiskLruCacheFactory;
import java.io.File;
/* loaded from: classes.dex */
public final class InternalCacheDiskCacheFactory extends DiskLruCacheFactory {
    public InternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
    }

    public InternalCacheDiskCacheFactory(Context context, int i) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, i);
    }

    public InternalCacheDiskCacheFactory(final Context context, final String str, int i) {
        super(new DiskLruCacheFactory.CacheDirectoryGetter() { // from class: android.support.rastermill.cache.InternalCacheDiskCacheFactory.1
            @Override // android.support.rastermill.cache.DiskLruCacheFactory.CacheDirectoryGetter
            public File getCacheDirectory() {
                File cacheDir = context.getCacheDir();
                if (cacheDir == null) {
                    return null;
                }
                String str2 = str;
                return str2 != null ? new File(cacheDir, str2) : cacheDir;
            }
        }, i);
    }
}
