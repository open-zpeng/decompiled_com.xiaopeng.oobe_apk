package android.support.rastermill.prugin;

import android.graphics.drawable.Drawable;
import android.support.rastermill.FrameSequenceController;
import android.support.rastermill.FrameSequenceParams;
import android.support.rastermill.ILoader;
/* loaded from: classes.dex */
public interface IPlugin {
    void apply(FrameSequenceController frameSequenceController, ILoader iLoader, Drawable drawable);

    boolean checkApplySameOrCache(FrameSequenceController frameSequenceController, ILoader iLoader);

    void destroy(String str, Drawable drawable, boolean z);

    Drawable getDrawable(FrameSequenceController frameSequenceController, ILoader iLoader);

    ILoader getLoader(FrameSequenceController frameSequenceController);

    void init(FrameSequenceParams frameSequenceParams);

    void start(Drawable drawable);

    void stop(Drawable drawable);
}
