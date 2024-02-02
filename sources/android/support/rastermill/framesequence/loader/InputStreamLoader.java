package android.support.rastermill.framesequence.loader;

import android.content.Context;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceUtil;
import android.support.rastermill.LogUtil;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class InputStreamLoader extends AbsLoader {
    private InputStream mInputStream;

    @Override // android.support.rastermill.ILoader
    public int getType() {
        return 4;
    }

    public InputStreamLoader(Context context, String str, InputStream inputStream) {
        super(context);
        this.mInputStream = inputStream;
        this.mKey = str;
    }

    @Override // android.support.rastermill.ILoader
    public boolean exists() {
        try {
            if (this.mInputStream != null) {
                return this.mInputStream.available() > 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // android.support.rastermill.framesequence.loader.AbsLoader
    public FrameSequence decode() {
        InputStream inputStream = this.mInputStream;
        try {
            FrameSequence decodeStream = FrameSequence.decodeStream(inputStream);
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return decodeStream;
                } catch (IOException unused) {
                    return decodeStream;
                }
            }
            return decodeStream;
        } catch (Throwable th) {
            try {
                Log.e(FrameSequenceUtil.class.toString(), "decodeFile", th);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused2) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th2;
            }
        }
    }

    @Override // android.support.rastermill.framesequence.loader.AbsLoader, android.support.rastermill.ILoader
    public void release() {
        if (LogUtil.isLogEnable()) {
            LogUtil.e("InputStreamLoader release : " + this.mKey);
        }
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        this.mInputStream = null;
    }
}
