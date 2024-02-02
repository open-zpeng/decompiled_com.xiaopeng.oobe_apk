package com.xiaopeng.speech.common.bean;

import android.os.IBinder;
/* loaded from: classes.dex */
public class SuspendInfoBean extends BaseBean {
    private IBinder binder;
    private String byWho;
    private String info;
    private boolean needMic;
    private int notifyType;

    public boolean isNeedMic() {
        return this.needMic;
    }

    public void setNeedMic(boolean z) {
        this.needMic = z;
    }

    public SuspendInfoBean(IBinder iBinder, String str, String str2, int i, boolean z) {
        this.binder = iBinder;
        this.byWho = str;
        this.info = str2;
        this.notifyType = i;
        this.needMic = z;
    }

    public IBinder getBinder() {
        return this.binder;
    }

    public void setBinder(IBinder iBinder) {
        this.binder = iBinder;
    }

    public String getByWho() {
        return this.byWho;
    }

    public void setByWho(String str) {
        this.byWho = str;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public void setNotifyType(int i) {
        this.notifyType = i;
    }

    public String toString() {
        return "SuspendInfoBean{binder=" + this.binder + ", byWho='" + this.byWho + "', info='" + this.info + "', notifyType=" + this.notifyType + ", needMic=" + this.needMic + '}';
    }
}
