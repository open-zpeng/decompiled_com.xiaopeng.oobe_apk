package com.xiaopeng.lib.framework.moduleinterface.configurationmodule;

import android.app.Application;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public interface IConfiguration {
    String getConfiguration(String str, String str2);

    void init(@NonNull Application application, @NonNull String str);
}
