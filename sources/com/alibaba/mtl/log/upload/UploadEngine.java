package com.alibaba.mtl.log.upload;

import com.alibaba.mtl.log.d.b;
import com.alibaba.mtl.log.d.i;
import com.alibaba.mtl.log.d.s;
import com.xiaopeng.speech.common.SpeechConstant;
import java.util.Random;
/* loaded from: classes.dex */
public class UploadEngine {
    static UploadEngine a = new UploadEngine();
    private int B;
    protected long z = com.alibaba.mtl.log.a.a.a();
    private boolean G = false;

    public static UploadEngine getInstance() {
        return a;
    }

    public synchronized void start() {
        this.G = true;
        if (s.a().b(2)) {
            s.a().f(2);
        }
        c();
        Random random = new Random();
        if (!a.isRunning()) {
            s.a().a(2, new a() { // from class: com.alibaba.mtl.log.upload.UploadEngine.1
                @Override // com.alibaba.mtl.log.upload.a
                public void G() {
                    if (UploadEngine.this.G) {
                        com.alibaba.mtl.log.b.a.C();
                        UploadEngine.this.c();
                        i.a("UploadTask", "mPeriod:", Long.valueOf(UploadEngine.this.z));
                        if (s.a().b(2)) {
                            s.a().f(2);
                        }
                        if (a.isRunning()) {
                            return;
                        }
                        s.a().a(2, this, UploadEngine.this.z);
                    }
                }

                @Override // com.alibaba.mtl.log.upload.a
                public void H() {
                    UploadEngine.this.refreshInterval();
                }
            }, random.nextInt((int) this.z));
        }
    }

    public void refreshInterval() {
        if (this.B == 0) {
            this.B = SpeechConstant.VAD_TIMEOUT;
        } else {
            this.B = 0;
        }
    }

    public synchronized void stop() {
        this.G = false;
        s.a().f(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long c() {
        long a2;
        int i;
        i.a("UploadEngine", "UTDC.bBackground:", Boolean.valueOf(com.alibaba.mtl.log.a.o), "AppInfoUtil.isForeground(UTDC.getContext()) ", Boolean.valueOf(b.b(com.alibaba.mtl.log.a.getContext())));
        com.alibaba.mtl.log.a.o = !b.b(com.alibaba.mtl.log.a.getContext());
        boolean z = com.alibaba.mtl.log.a.o;
        com.alibaba.mtl.log.a.a.a();
        if (z) {
            a2 = com.alibaba.mtl.log.a.a.b();
            i = this.B;
        } else {
            a2 = com.alibaba.mtl.log.a.a.a();
            i = this.B;
        }
        this.z = a2 + i;
        if (com.alibaba.mtl.log.a.a.e()) {
            this.z = 3000L;
        }
        return this.z;
    }
}
