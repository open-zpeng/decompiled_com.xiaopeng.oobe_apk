package android.support.rastermill;

import android.support.rastermill.cache.DiskCache;
import android.support.rastermill.prugin.IPlugin;
/* loaded from: classes.dex */
public class FrameSequenceParams {
    private DiskCache.Factory mDiskCacheFactory;
    private int mFrameSequenceCacheSize;
    private IPlugin mPlugin;
    private int mRecycleDrawableCacheSize;

    public DiskCache.Factory getDiskCacheFactory() {
        return this.mDiskCacheFactory;
    }

    public int getFrameSequenceCacheSize() {
        return this.mFrameSequenceCacheSize;
    }

    public int getRecycleDrawableCacheSize() {
        return this.mRecycleDrawableCacheSize;
    }

    public IPlugin getPlugin() {
        return this.mPlugin;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        DiskCache.Factory diskCacheFactory;
        int frameSequenceCacheSize;
        IPlugin plugin;
        int recycleDrawableCacheSize;

        public Builder diskCacheFactory(DiskCache.Factory factory) {
            this.diskCacheFactory = factory;
            return this;
        }

        public Builder frameSequenceCacheSize(int i) {
            this.frameSequenceCacheSize = i;
            return this;
        }

        public Builder recycleDrawableCacheSize(int i) {
            this.recycleDrawableCacheSize = i;
            return this;
        }

        public Builder plugin(IPlugin iPlugin) {
            this.plugin = iPlugin;
            return this;
        }

        public FrameSequenceParams build() {
            FrameSequenceParams frameSequenceParams = new FrameSequenceParams();
            frameSequenceParams.mDiskCacheFactory = this.diskCacheFactory;
            frameSequenceParams.mFrameSequenceCacheSize = this.frameSequenceCacheSize;
            frameSequenceParams.mRecycleDrawableCacheSize = this.recycleDrawableCacheSize;
            frameSequenceParams.mPlugin = this.plugin;
            return frameSequenceParams;
        }
    }
}
