package com.xiaopeng.speech.protocol.query.speech.combo;

import com.xiaopeng.speech.annotation.IQueryProcessor;
import com.xiaopeng.speech.protocol.event.query.speech.ComboQueryEvent;
/* loaded from: classes2.dex */
public class ComboQuery_Processor implements IQueryProcessor {
    private ComboQuery mTarget;

    public ComboQuery_Processor(ComboQuery comboQuery) {
        this.mTarget = comboQuery;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public Object querySensor(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -2089284112:
                if (str.equals(ComboQueryEvent.COMBO_ENTER_USERMODE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1755970659:
                if (str.equals(ComboQueryEvent.COMBO_GUI_PAGE_CHECKFORBIDDEN)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1068777799:
                if (str.equals(ComboQueryEvent.COMBO_GET_CURRENT_USERMODE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -949369482:
                if (str.equals(ComboQueryEvent.COMBO_EXIT_USERMODE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1690526583:
                if (str.equals(ComboQueryEvent.COMBO_GET_CURRENT_USERMODE_SCREEN)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1765601312:
                if (str.equals(ComboQueryEvent.COMBO_CHECK_ENTER_USERMODE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c != 5) {
                                return null;
                            }
                            return Boolean.valueOf(this.mTarget.guiPagecheckForbidden(str, str2));
                        }
                        return this.mTarget.getCurrentUserModeWithScreen(str, str2);
                    }
                    return this.mTarget.getCurrentUserMode(str, str2);
                }
                return this.mTarget.checkEnterUserMode(str, str2);
            }
            return this.mTarget.exitMode(str, str2);
        }
        return this.mTarget.enterMode(str, str2);
    }

    @Override // com.xiaopeng.speech.annotation.IQueryProcessor
    public String[] getQueryEvents() {
        return new String[]{ComboQueryEvent.COMBO_ENTER_USERMODE, ComboQueryEvent.COMBO_EXIT_USERMODE, ComboQueryEvent.COMBO_CHECK_ENTER_USERMODE, ComboQueryEvent.COMBO_GET_CURRENT_USERMODE, ComboQueryEvent.COMBO_GET_CURRENT_USERMODE_SCREEN, ComboQueryEvent.COMBO_GUI_PAGE_CHECKFORBIDDEN};
    }
}
