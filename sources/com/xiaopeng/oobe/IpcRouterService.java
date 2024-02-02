package com.xiaopeng.oobe;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaopeng.lib.apirouter.server.ApplicationId;
import com.xiaopeng.lib.apirouter.server.IServicePublisher;
import com.xiaopeng.lib.apirouter.server.Publish;
import com.xiaopeng.lib.framework.moduleinterface.ipcmodule.IIpcService;
import com.xiaopeng.lib.utils.LogUtils;
import java.util.Iterator;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
@ApplicationId(BuildConfig.APPLICATION_ID)
/* loaded from: classes.dex */
public class IpcRouterService implements IServicePublisher {
    private static final String TAG = "oobe-IpcRouterService";

    @Publish
    public void onReceiverData(int i, String str) {
        LogUtils.d(TAG, "onReceiverData id:" + i + ",bundle:" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            Bundle bundle = new Bundle();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                bundle.putString(next, jSONObject.getString(next));
            }
            postIPCEvent(i, bundle, jSONObject.optString("senderPackageName"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void postIPCEvent(int i, Bundle bundle, String str) {
        LogUtils.d(TAG, "onReceiverData: id=" + i + ";senderPackageName=" + str);
        if (!TextUtils.isEmpty(str) && i > 0) {
            IIpcService.IpcMessageEvent ipcMessageEvent = new IIpcService.IpcMessageEvent();
            ipcMessageEvent.setMsgID(i);
            ipcMessageEvent.setPayloadData(bundle);
            ipcMessageEvent.setSenderPackageName(str);
            if (EventBus.getDefault().hasSubscriberForEvent(IIpcService.IpcMessageEvent.class)) {
                EventBus.getDefault().post(ipcMessageEvent);
                return;
            }
            LogUtils.d(TAG, "onReceiverData: IIpcService.IpcMessageEvent has not subscriber.");
            EventBus.getDefault().postSticky(ipcMessageEvent);
            return;
        }
        LogUtils.d(TAG, "onReceiverData: fatal from sender. ");
    }
}
