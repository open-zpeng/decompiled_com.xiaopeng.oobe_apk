package android.support.rastermill.framesequence.loader;

import android.content.Context;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.ILoader;
import android.support.rastermill.framesequence.FrameSequenceCache;
/* loaded from: classes.dex */
public abstract class AbsLoader implements ILoader {
    protected Context mContext;
    protected String mKey;

    public abstract FrameSequence decode();

    @Override // android.support.rastermill.ILoader
    public boolean isCacheRequired() {
        return false;
    }

    @Override // android.support.rastermill.ILoader
    public void release() {
    }

    public AbsLoader(Context context) {
        this.mContext = context;
    }

    @Override // android.support.rastermill.ILoader
    public String getKey() {
        return this.mKey;
    }

    public FrameSequence getFrameSequence() {
        if (exists()) {
            String key = getKey();
            FrameSequence cachedFrameSequence = key != null ? FrameSequenceCache.getCachedFrameSequence(key) : null;
            if (cachedFrameSequence == null) {
                FrameSequence decode = decode();
                FrameSequenceCache.putCachedFrameSequence(key, decode);
                return decode;
            }
            return cachedFrameSequence;
        }
        return null;
    }
}
