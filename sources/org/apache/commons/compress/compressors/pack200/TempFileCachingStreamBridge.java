package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
class TempFileCachingStreamBridge extends StreamBridge {
    private final File f = File.createTempFile("commons-compress", "packtemp");

    /* JADX INFO: Access modifiers changed from: package-private */
    public TempFileCachingStreamBridge() throws IOException {
        this.f.deleteOnExit();
        this.out = new FileOutputStream(this.f);
    }

    @Override // org.apache.commons.compress.compressors.pack200.StreamBridge
    InputStream getInputView() throws IOException {
        this.out.close();
        return new FileInputStream(this.f) { // from class: org.apache.commons.compress.compressors.pack200.TempFileCachingStreamBridge.1
            @Override // java.io.FileInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                super.close();
                TempFileCachingStreamBridge.this.f.delete();
            }
        };
    }
}
