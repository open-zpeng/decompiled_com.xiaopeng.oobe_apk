package com.xiaopeng.speech.protocol.query.appstore;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.QueryAppAndAppletEvent;
/* loaded from: classes2.dex */
public class AppAndAppletQuery extends SpeechQuery<IAppAndAppletCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_OPEN)
    public int onOpenAppshopPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onOpenAppshopPage(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_CLOSE)
    public int onCloseAppshopPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onCloseAppshopPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APP_PAGE_OPEN)
    public int onOpenAppStoreMainPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onOpenAppStoreMainPage(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APP_PAGE_CLOSE)
    public int onCloseAppStoreMainPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onCloseAppStoreMainPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPLET_PAGE_OPEN)
    public int onOpenAppletMainPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onOpenAppletMainPage(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPLET_PAGE_CLOSE)
    public int onCloseAppletMainPage(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).onCloseAppletMainPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APP_OPEN)
    public int getAppOpenStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getAppOpenStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APP_CLOSE)
    public int getAppCloseStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getAppCloseStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPLET_OPEN)
    public int getAppletOpenStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getAppletOpenStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APPLET_CLOSE)
    public int getAppletCloseStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getAppletCloseStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_CURRENT_CLOSE)
    public int getCurrentCloseStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getCurrentCloseStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.HAS_DIALOG_CLOSE_BYHAND)
    public boolean hasDialogCloseByHand(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).hasDialogCloseByHand();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_APP_DOWNLOAD)
    public int getAppDownStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getAppDownStatus(str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryAppAndAppletEvent.GUI_CURRENT_BACK)
    public int getCurrentBackStatus(String str, String str2) {
        return ((IAppAndAppletCaller) this.mQueryCaller).getCurrentBackStatus(str2);
    }
}
