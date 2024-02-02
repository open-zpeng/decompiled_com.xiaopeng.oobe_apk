package com.xiaopeng.xui.utils;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class XInputUtils {
    public static void ignoreHiddenInput(@NonNull View view) {
        view.setTag(ClientDefaults.MAX_MSG_SIZE, 1001);
    }
}
