package android.support.rastermill.cache;

import android.content.Context;
import android.support.rastermill.cache.DiskCache;
import android.support.rastermill.cache.DiskLruCacheFactory;
import java.io.File;
/* loaded from: classes.dex */
public final class ExternalCacheDiskCacheFactory extends DiskLruCacheFactory {
    public ExternalCacheDiskCacheFactory(Context context) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, DiskCache.Factory.DEFAULT_DISK_CACHE_SIZE);
    }

    public ExternalCacheDiskCacheFactory(Context context, int i) {
        this(context, DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, i);
    }

    public ExternalCacheDiskCacheFactory(final Context context, final String str, int i) {
        super(new DiskLruCacheFactory.CacheDirectoryGetter() { // from class: android.support.rastermill.cache.ExternalCacheDiskCacheFactory.1
            @Override // android.support.rastermill.cache.DiskLruCacheFactory.CacheDirectoryGetter
            public File getCacheDirectory() {
                File externalCacheDir = context.getExternalCacheDir();
                if (externalCacheDir == null) {
                    return null;
                }
                String str2 = str;
                return str2 != null ? new File(externalCacheDir, str2) : externalCacheDir;
            }
        }, i);
    }
}
