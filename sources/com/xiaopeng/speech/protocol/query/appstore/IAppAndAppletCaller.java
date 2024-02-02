package com.xiaopeng.speech.protocol.query.appstore;

import com.xiaopeng.speech.IQueryCaller;
/* loaded from: classes2.dex */
public interface IAppAndAppletCaller extends IQueryCaller {
    int getAppCloseStatus(String str);

    default int getAppDownStatus(String str) {
        return -1;
    }

    int getAppOpenStatus(String str);

    int getAppletCloseStatus(String str);

    int getAppletOpenStatus(String str);

    default int getCurrentBackStatus(String str) {
        return -1;
    }

    int getCurrentCloseStatus(String str);

    boolean hasDialogCloseByHand();

    int onCloseAppStoreMainPage();

    int onCloseAppletMainPage();

    int onCloseAppshopPage();

    int onOpenAppStoreMainPage(String str);

    int onOpenAppletMainPage(String str);

    int onOpenAppshopPage(String str);
}
