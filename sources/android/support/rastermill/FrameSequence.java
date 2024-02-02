package android.support.rastermill;

import android.graphics.Bitmap;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public class FrameSequence {
    private final AtomicInteger isDestroyed = new AtomicInteger(0);
    private final int mDefaultLoopCount;
    private final int mFrameCount;
    private final int mHeight;
    private final long mNativeFrameSequence;
    private final boolean mOpaque;
    private final int mWidth;

    private static native long nativeCreateState(long j);

    private static native FrameSequence nativeDecodeByteArray(byte[] bArr, int i, int i2);

    private static native FrameSequence nativeDecodeByteBuffer(ByteBuffer byteBuffer, int i, int i2);

    private static native FrameSequence nativeDecodeStream(InputStream inputStream, byte[] bArr);

    private static native void nativeDestroyFrameSequence(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeDestroyState(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nativeGetFrame(long j, int i, Bitmap bitmap, int i2);

    private static native boolean nativeIsSupport(InputStream inputStream, byte[] bArr);

    static {
        System.loadLibrary("framesequence");
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public boolean isOpaque() {
        return this.mOpaque;
    }

    public int getFrameCount() {
        return this.mFrameCount;
    }

    public int getDefaultLoopCount() {
        return this.mDefaultLoopCount;
    }

    private FrameSequence(long j, int i, int i2, boolean z, int i3, int i4) {
        this.mNativeFrameSequence = j;
        this.mWidth = i;
        this.mHeight = i2;
        this.mOpaque = z;
        this.mFrameCount = i3;
        this.mDefaultLoopCount = i4;
    }

    public static FrameSequence decodeByteArray(byte[] bArr) {
        return decodeByteArray(bArr, 0, bArr.length);
    }

    public static FrameSequence decodeByteArray(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("invalid offset/length parameters");
        }
        return nativeDecodeByteArray(bArr, i, i2);
    }

    public static FrameSequence decodeByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new IllegalArgumentException();
        }
        if (!byteBuffer.isDirect()) {
            if (byteBuffer.hasArray()) {
                return decodeByteArray(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
            }
            throw new IllegalArgumentException("Cannot have non-direct ByteBuffer with no byte array");
        }
        return nativeDecodeByteBuffer(byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public static FrameSequence decodeStream(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalArgumentException();
        }
        return nativeDecodeStream(inputStream, new byte[16384]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public State createState() {
        long j = this.mNativeFrameSequence;
        if (j == 0) {
            throw new IllegalStateException("attempted to use incorrectly built FrameSequence");
        }
        long nativeCreateState = nativeCreateState(j);
        if (nativeCreateState == 0) {
            return null;
        }
        return new State(nativeCreateState);
    }

    public void destroy() {
        if (this.mNativeFrameSequence != 0 && this.isDestroyed.getAndSet(1) == 0) {
            nativeDestroyFrameSequence(this.mNativeFrameSequence);
        }
        if (LogUtil.isLogEnable()) {
            LogUtil.e("FrameSequence.destroy : mFrameSequence = " + hashCode());
        }
    }

    public boolean isDestroyed() {
        return this.isDestroyed.get() > 0;
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mNativeFrameSequence != 0 && this.isDestroyed.get() == 0) {
                nativeDestroyFrameSequence(this.mNativeFrameSequence);
            }
        } finally {
            super.finalize();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class State {
        private AtomicLong mNativeState;

        public State(long j) {
            this.mNativeState = new AtomicLong(j);
        }

        public synchronized void destroy() {
            long andSet = this.mNativeState.getAndSet(0L);
            if (andSet != 0) {
                if (LogUtil.isLogEnable()) {
                    LogUtil.e("FrameSequence.State.destroy");
                }
                FrameSequence.nativeDestroyState(andSet);
            }
        }

        public synchronized long getFrame(int i, Bitmap bitmap, int i2) {
            long j;
            if (bitmap != null) {
                if (bitmap.getConfig() == Bitmap.Config.ARGB_8888) {
                    j = this.mNativeState.get();
                    if (j != 0) {
                    } else {
                        throw new IllegalStateException("attempted to draw destroyed FrameSequenceState");
                    }
                }
            }
            throw new IllegalArgumentException("Bitmap passed must be non-null and ARGB_8888");
            return FrameSequence.nativeGetFrame(j, i, bitmap, i2);
        }
    }
}
