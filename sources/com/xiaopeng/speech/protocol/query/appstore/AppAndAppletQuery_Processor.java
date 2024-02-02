package com.xiaopeng.speech.protocol.query.appstore;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.QueryAppAndAppletEvent;
/* loaded from: classes2.dex */
public class AppAndAppletQuery_Processor implements IQueryProcessor {
    private AppAndAppletQuery mTarget;

    public AppAndAppletQuery_Processor(AppAndAppletQuery appAndAppletQuery) {
        this.mTarget = appAndAppletQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1511576655:
                if (str.equals(QueryAppAndAppletEvent.GUI_APP_OPEN)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1488079345:
                if (str.equals(QueryAppAndAppletEvent.GUI_CURRENT_BACK)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -1201339175:
                if (str.equals(QueryAppAndAppletEvent.GUI_APP_PAGE_CLOSE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -649881094:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPLET_PAGE_CLOSE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -176939191:
                if (str.equals(QueryAppAndAppletEvent.GUI_APP_PAGE_OPEN)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 51075875:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_CLOSE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 371669303:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPLET_CLOSE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 374572401:
                if (str.equals(QueryAppAndAppletEvent.GUI_APP_CLOSE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 417650623:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_OPEN)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 533586440:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPLET_PAGE_OPEN)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 896451183:
                if (str.equals(QueryAppAndAppletEvent.GUI_APP_DOWNLOAD)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 982181675:
                if (str.equals(QueryAppAndAppletEvent.GUI_APPLET_OPEN)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1115443664:
                if (str.equals(QueryAppAndAppletEvent.GUI_CURRENT_CLOSE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1121989518:
                if (str.equals(QueryAppAndAppletEvent.HAS_DIALOG_CLOSE_BYHAND)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return Integer.valueOf(this.mTarget.onOpenAppshopPage(str, str2));
            case 1:
                return Integer.valueOf(this.mTarget.onCloseAppshopPage(str, str2));
            case 2:
                return Integer.valueOf(this.mTarget.onOpenAppStoreMainPage(str, str2));
            case 3:
                return Integer.valueOf(this.mTarget.onCloseAppStoreMainPage(str, str2));
            case 4:
                return Integer.valueOf(this.mTarget.onOpenAppletMainPage(str, str2));
            case 5:
                return Integer.valueOf(this.mTarget.onCloseAppletMainPage(str, str2));
            case 6:
                return Integer.valueOf(this.mTarget.getAppOpenStatus(str, str2));
            case 7:
                return Integer.valueOf(this.mTarget.getAppCloseStatus(str, str2));
            case '\b':
                return Integer.valueOf(this.mTarget.getAppletOpenStatus(str, str2));
            case '\t':
                return Integer.valueOf(this.mTarget.getAppletCloseStatus(str, str2));
            case '\n':
                return Integer.valueOf(this.mTarget.getCurrentCloseStatus(str, str2));
            case 11:
                return Boolean.valueOf(this.mTarget.hasDialogCloseByHand(str, str2));
            case '\f':
                return Integer.valueOf(this.mTarget.getAppDownStatus(str, str2));
            case '\r':
                return Integer.valueOf(this.mTarget.getCurrentBackStatus(str, str2));
            default:
                return null;
        }
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_OPEN, QueryAppAndAppletEvent.GUI_APPSHOP_PAGE_CLOSE, QueryAppAndAppletEvent.GUI_APP_PAGE_OPEN, QueryAppAndAppletEvent.GUI_APP_PAGE_CLOSE, QueryAppAndAppletEvent.GUI_APPLET_PAGE_OPEN, QueryAppAndAppletEvent.GUI_APPLET_PAGE_CLOSE, QueryAppAndAppletEvent.GUI_APP_OPEN, QueryAppAndAppletEvent.GUI_APP_CLOSE, QueryAppAndAppletEvent.GUI_APPLET_OPEN, QueryAppAndAppletEvent.GUI_APPLET_CLOSE, QueryAppAndAppletEvent.GUI_CURRENT_CLOSE, QueryAppAndAppletEvent.HAS_DIALOG_CLOSE_BYHAND, QueryAppAndAppletEvent.GUI_APP_DOWNLOAD, QueryAppAndAppletEvent.GUI_CURRENT_BACK};
    }
}
