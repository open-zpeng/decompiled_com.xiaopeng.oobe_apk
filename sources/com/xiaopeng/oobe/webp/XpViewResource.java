package com.xiaopeng.oobe.webp;

import android.content.Context;
import android.support.rastermill.FrameSequence;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.xiaopeng.oobe.App;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class XpViewResource {
    private static final Object[][] STATUS_ASSET_LIST = {new Object[]{XpViewStatus.HOME_DEFAULT, "xp_blink.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_CHECK, "xp_check.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_EXIT, "xp_exit.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_HELPLESS, "xp_helpless.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_FAIL, "xp_fail.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_SUCCESS, "xp_success.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_SMILE, "xp_smile.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_SMILE_LEFT, "xp_smile_left.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_SPEEK, "xp_speek_center.webp", Boolean.FALSE}, new Object[]{XpViewStatus.HOME_LISTEN_CENTER, "xp_listen_center.webp", Boolean.FALSE}};
    private static final Object[][] STATUS_IN_ASSET_LIST = new Object[0];
    private static final Object[][] STATUS_OUT_ASSET_LIST = new Object[0];
    private static SparseArray<Pair<Boolean, String>> sStatusAssetMap = new SparseArray<>();
    private static SparseArray<Pair<Boolean, String>> sStatusInAssetMap = new SparseArray<>();
    private static SparseArray<Pair<Boolean, String>> sStatusOutAssetMap = new SparseArray<>();
    private static SparseArray<Reference<FrameSequence>> sStatusFrameSequenceMap = new SparseArray<>();
    private static SparseArray<Reference<FrameSequence>> sStatusInFrameSequenceMap = new SparseArray<>();
    private static SparseArray<Reference<FrameSequence>> sStatusOutFrameSequenceMap = new SparseArray<>();
    private static boolean sLoaded = false;

    public static void load(Context context) {
        if (sLoaded) {
            return;
        }
        XpViewResourceHelper.put(sStatusAssetMap, STATUS_ASSET_LIST);
        XpViewResourceHelper.put(sStatusInAssetMap, STATUS_IN_ASSET_LIST);
        XpViewResourceHelper.put(sStatusOutAssetMap, STATUS_OUT_ASSET_LIST);
        load(context, sStatusAssetMap, sStatusFrameSequenceMap);
        load(context, sStatusInAssetMap, sStatusInFrameSequenceMap);
        load(context, sStatusOutAssetMap, sStatusOutFrameSequenceMap);
        sLoaded = true;
    }

    public static void load(Context context, SparseArray<Pair<Boolean, String>> sparseArray, SparseArray<Reference<FrameSequence>> sparseArray2) {
        for (int i = 0; i < sparseArray.size(); i++) {
            Pair<Boolean, String> valueAt = sparseArray.valueAt(i);
            if (!((Boolean) valueAt.first).booleanValue()) {
                sparseArray2.put(sparseArray.keyAt(i), new SoftReference(getFrameSequence(context, (String) valueAt.second)));
            }
        }
    }

    public static FrameSequence getFrameSequence(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return FrameSequence.decodeStream(context.createDeviceProtectedStorageContext().getAssets().open(str));
        } catch (Exception e) {
            Log.e(XpViewResource.class.toString(), e.toString());
            return null;
        }
    }

    private static FrameSequence getStatus(SparseArray<Reference<FrameSequence>> sparseArray, SparseArray<Pair<Boolean, String>> sparseArray2, XpViewStatus xpViewStatus) {
        if (xpViewStatus == null) {
            return null;
        }
        Reference<FrameSequence> reference = sparseArray.get(xpViewStatus.value);
        FrameSequence frameSequence = reference != null ? reference.get() : null;
        if (frameSequence == null) {
            Pair<Boolean, String> pair = sparseArray2.get(xpViewStatus.value);
            frameSequence = getFrameSequence(App.getInstance(), pair != null ? (String) pair.second : null);
            if (frameSequence != null) {
                sparseArray.put(xpViewStatus.value, new WeakReference(frameSequence));
            }
        }
        return frameSequence;
    }

    public static FrameSequence getStatus(XpViewStatus xpViewStatus) {
        return getStatus(sStatusFrameSequenceMap, sStatusAssetMap, xpViewStatus);
    }

    public static FrameSequence getStatusIn(XpViewStatus xpViewStatus) {
        return getStatus(sStatusInFrameSequenceMap, sStatusInAssetMap, xpViewStatus);
    }

    public static FrameSequence getStatusOut(XpViewStatus xpViewStatus) {
        return getStatus(sStatusOutFrameSequenceMap, sStatusOutAssetMap, xpViewStatus);
    }
}
