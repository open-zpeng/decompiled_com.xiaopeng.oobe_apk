package org.tukaani.xz;

import java.io.InputStream;
/* loaded from: classes2.dex */
interface FilterDecoder extends FilterCoder {
    InputStream getInputStream(InputStream inputStream);

    int getMemoryUsage();
}
