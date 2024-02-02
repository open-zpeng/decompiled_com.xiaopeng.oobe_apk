package android.support.rastermill;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Pair;
/* loaded from: classes.dex */
class LoaderAsyncTask extends AsyncTask<Object, Void, Pair<ILoader, Drawable>> {
    private FrameSequenceController mFrameSequenceController;

    public LoaderAsyncTask(FrameSequenceController frameSequenceController) {
        this.mFrameSequenceController = frameSequenceController;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    public Pair<ILoader, Drawable> doInBackground(Object... objArr) {
        ILoader iLoader;
        Drawable drawable;
        FrameSequenceController frameSequenceController = this.mFrameSequenceController;
        if (frameSequenceController != null) {
            iLoader = frameSequenceController.getLoader();
            drawable = this.mFrameSequenceController.getDrawable(iLoader);
        } else {
            iLoader = null;
            drawable = null;
        }
        if (LogUtil.isLogEnable()) {
            LogUtil.e("doInBackground : " + (iLoader != null ? iLoader.getKey() : null));
        }
        return new Pair<>(iLoader, drawable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onCancelled(Pair<ILoader, Drawable> pair) {
        super.onCancelled((LoaderAsyncTask) pair);
        if (pair == null) {
            this.mFrameSequenceController.getLoader().release();
            return;
        }
        ILoader iLoader = (ILoader) pair.first;
        if (LogUtil.isLogEnable()) {
            String key = iLoader != null ? iLoader.getKey() : null;
            LogUtil.e("onCancelled : " + key);
        }
        FrameSequenceController.removeTask(this.mFrameSequenceController.getImageView());
        Drawable drawable = (Drawable) pair.second;
        if (drawable != null) {
            FrameSequenceUtil.destroy(iLoader.getKey(), drawable, true);
        }
        iLoader.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Pair<ILoader, Drawable> pair) {
        super.onPostExecute((LoaderAsyncTask) pair);
        ILoader iLoader = (ILoader) pair.first;
        if (LogUtil.isLogEnable()) {
            String key = iLoader != null ? iLoader.getKey() : null;
            LogUtil.e("onPostExecute : " + key);
        }
        FrameSequenceController.removeTask(this.mFrameSequenceController.getImageView());
        this.mFrameSequenceController.applyInternal(iLoader, (Drawable) pair.second, true);
    }
}
