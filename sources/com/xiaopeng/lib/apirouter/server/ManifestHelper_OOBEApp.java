package com.xiaopeng.lib.apirouter.server;

import android.os.IBinder;
import android.util.Pair;
import androidx.annotation.Keep;
import java.util.HashMap;
@Keep
/* loaded from: classes.dex */
public class ManifestHelper_OOBEApp implements IManifestHelper {
    public HashMap<String, Pair<IBinder, String>> mapping = new HashMap<>();

    @Override // com.xiaopeng.lib.apirouter.server.IManifestHelper
    public HashMap<String, Pair<IBinder, String>> getMapping() {
        Pair<IBinder, String> pair = new Pair<>(new IpcRouterService_Stub(), IpcRouterService_Manifest.toJsonManifest());
        for (String str : IpcRouterService_Manifest.getKey()) {
            this.mapping.put(str, pair);
        }
        return this.mapping;
    }
}
