package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
class FallbackZipEncoding implements ZipEncoding {
    private final String charsetName;

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public boolean canEncode(String str) {
        return true;
    }

    public FallbackZipEncoding() {
        this.charsetName = null;
    }

    public FallbackZipEncoding(String str) {
        this.charsetName = str;
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public ByteBuffer encode(String str) throws IOException {
        String str2 = this.charsetName;
        if (str2 == null) {
            return ByteBuffer.wrap(str.getBytes());
        }
        return ByteBuffer.wrap(str.getBytes(str2));
    }

    @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
    public String decode(byte[] bArr) throws IOException {
        String str = this.charsetName;
        if (str == null) {
            return new String(bArr);
        }
        return new String(bArr, str);
    }
}
