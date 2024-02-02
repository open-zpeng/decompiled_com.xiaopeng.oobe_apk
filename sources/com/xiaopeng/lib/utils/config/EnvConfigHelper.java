package com.xiaopeng.lib.utils.config;

import com.xiaopeng.lib.utils.ThreadUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
/* loaded from: classes.dex */
public class EnvConfigHelper {
    static final int DEF_EXPIRE_INTERVAL_MILLIS = 43200000;
    static final String DEF_MAIN_HOST = "https://xmart.deploy-test.xiaopeng.com";
    static final String KEY_MAIN_HOST = "main_host";
    private static final String TAG = "EnvConfig";

    private static String getNextExpireTime() {
        return new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date(System.currentTimeMillis() + 43200000));
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    static void saveToPath(java.util.Properties r3, java.lang.String r4) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L7
            return
        L7:
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2b
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L2b
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L2b
            r4.<init>(r1)     // Catch: java.lang.Throwable -> L2b
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L26
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L26
            java.lang.String r0 = "pre env file"
            r3.store(r1, r0)     // Catch: java.lang.Throwable -> L23
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(r1)
            goto L3a
        L20:
            r3 = move-exception
            r0 = r1
            goto L3f
        L23:
            r3 = move-exception
            r0 = r1
            goto L2d
        L26:
            r3 = move-exception
            goto L2d
        L28:
            r3 = move-exception
            r4 = r0
            goto L3f
        L2b:
            r3 = move-exception
            r4 = r0
        L2d:
            java.lang.String r1 = "EnvConfig"
            java.lang.String r2 = "EnvConfigHelper.saveToPath error!"
            android.util.Log.e(r1, r2)     // Catch: java.lang.Throwable -> L3e
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(r0)
        L3a:
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(r4)
            return
        L3e:
            r3 = move-exception
        L3f:
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(r0)
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.lib.utils.config.EnvConfigHelper.saveToPath(java.util.Properties, java.lang.String):void");
    }

    public static void saveFile() {
        final Properties cloneConfig = EnvConfig.cloneConfig();
        ThreadUtils.post(0, new Runnable() { // from class: com.xiaopeng.lib.utils.config.EnvConfigHelper.1
            @Override // java.lang.Runnable
            public void run() {
                EnvConfigHelper.saveToPath(cloneConfig, "/sdcard/pre_env.ini");
            }
        });
    }

    public static void setKey(String str, String str2, boolean z) {
        EnvConfig.setString(str, str2);
        if (z) {
            saveFile();
        }
    }

    public static void removeKey(String str, boolean z) {
        if (EnvConfig.hasKey(str)) {
            EnvConfig.removeKey(str);
            if (z) {
                saveFile();
            }
        }
    }

    public static void checkAndSetMainHost() {
        EnvConfig.setString(KEY_MAIN_HOST, DEF_MAIN_HOST);
        EnvConfig.setString("expired_time", getNextExpireTime());
        saveFile();
    }

    public static void removeMainHost() {
        removeKey(KEY_MAIN_HOST, true);
    }
}
