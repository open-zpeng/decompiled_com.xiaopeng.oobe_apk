package com.xiaopeng.speech.protocol.node.context;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.jarvisproto.ContextInput;
import com.xiaopeng.speech.jarvisproto.ContextOutput;
import com.xiaopeng.speech.protocol.event.ContextEvent;
import com.xiaopeng.speech.protocol.node.tts.TtsEcho;
/* loaded from: classes2.dex */
public class ContextNode_Processor implements ICommandProcessor {
    private ContextNode mTarget;

    public ContextNode_Processor(ContextNode contextNode) {
        this.mTarget = contextNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -1780364159:
                if (str.equals(ContextEvent.CONTEXT_WIDGET_TOPPING_PAGE)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1771180897:
                if (str.equals(ContextEvent.CONTEXT_WIDGET_PREV_PAGE)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1131091804:
                if (str.equals(ContextEvent.TIPS_TEXT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1045691198:
                if (str.equals(ContextInput.EVENT)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -796542743:
                if (str.equals(TtsEcho.EVENT_TTS_ECHO)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -690587803:
                if (str.equals(ContextEvent.WIDGET_CANCEL)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -671951780:
                if (str.equals(ContextEvent.WIDGET_CUSTOM)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -420795744:
                if (str.equals(ContextEvent.SAY_WELCOME)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -397592754:
                if (str.equals(ContextEvent.WIDGET_LIST_FOCUS)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -229200237:
                if (str.equals(ContextEvent.WIDGET_SEARCH)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 50079459:
                if (str.equals(ContextEvent.WIDGET_LIST_EXPEND)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case 104831985:
                if (str.equals(ContextEvent.WIDGET_RECOMMEND)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 287472607:
                if (str.equals(ContextEvent.CONTEXT_WIDGET_NEXT_PAGE)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 319661844:
                if (str.equals(ContextEvent.CONTEXT_WIDGET_LOW_PAGE)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 391447827:
                if (str.equals("context.exit.recommend.card")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 402709913:
                if (str.equals(ContextEvent.WIDGET_MEDIA)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 843973307:
                if (str.equals(ContextEvent.WIDGET_CARD)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 844249161:
                if (str.equals(ContextEvent.WIDGET_LIST)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 844483800:
                if (str.equals(ContextEvent.WIDGET_TEXT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 921064215:
                if (str.equals(ContextEvent.WIDGET_LIST_SCROLL)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 922722630:
                if (str.equals(ContextEvent.WIDGET_LIST_SELECT)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 970800010:
                if (str.equals(ContextEvent.WIDGET_LIST_STOP_COUNTDOWN)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 1541827335:
                if (str.equals(ContextEvent.TIPS_LISTENING_SHOW)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 1541838860:
                if (str.equals(ContextEvent.TIPS_LISTENING_STOP)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 1588160567:
                if (str.equals(ContextEvent.EXPRESSION)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1815394606:
                if (str.equals(ContextEvent.WIDGET_LIST_CANCEL_FOCUS)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1996065910:
                if (str.equals(ContextEvent.SCRIPT_STATUS)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 2004051806:
                if (str.equals(ContextEvent.SCRIPT_START)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 2121665289:
                if (str.equals(ContextOutput.EVENT)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 2140852870:
                if (str.equals(ContextEvent.WIDGET_LIST_FOLD)) {
                    c = 24;
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
                this.mTarget.onInputText(str, str2);
                return;
            case 1:
                this.mTarget.onOutputText(str, str2);
                return;
            case 2:
                this.mTarget.onTipsText(str, str2);
                return;
            case 3:
                this.mTarget.onWidgetCustom(str, str2);
                return;
            case 4:
                this.mTarget.onWidgetText(str, str2);
                return;
            case 5:
                this.mTarget.onWidgetList(str, str2);
                return;
            case 6:
                this.mTarget.onWidgetListFocus(str, str2);
                return;
            case 7:
                this.mTarget.onWidgetMedia(str, str2);
                return;
            case '\b':
                this.mTarget.onWidgetCard(str, str2);
                return;
            case '\t':
                this.mTarget.onWidgetRecommend(str, str2);
                return;
            case '\n':
                this.mTarget.onWidgetSearch(str, str2);
                return;
            case 11:
                this.mTarget.onExpression(str, str2);
                return;
            case '\f':
                this.mTarget.onSayWelcome(str, str2);
                return;
            case '\r':
                this.mTarget.onWidgetScroll(str, str2);
                return;
            case 14:
                this.mTarget.onWidgetListSelect(str, str2);
                return;
            case 15:
                this.mTarget.onWidgetCancel(str, str2);
                return;
            case 16:
                this.mTarget.onPageNext(str, str2);
                return;
            case 17:
                this.mTarget.onPagePrev(str, str2);
                return;
            case 18:
                this.mTarget.onPageTopping(str, str2);
                return;
            case 19:
                this.mTarget.onPageSetLow(str, str2);
                return;
            case 20:
                this.mTarget.onWidgetListCancelFocus(str, str2);
                return;
            case 21:
                this.mTarget.onScript(str, str2);
                return;
            case 22:
                this.mTarget.onScriptStatus(str, str2);
                return;
            case 23:
                this.mTarget.onExitRecommendCard(str, str2);
                return;
            case 24:
                this.mTarget.onWidgetListFold(str, str2);
                return;
            case 25:
                this.mTarget.onWidgetListExpend(str, str2);
                return;
            case 26:
                this.mTarget.onWidgetListStopCountdown(str, str2);
                return;
            case 27:
                this.mTarget.onTipsListeningShow(str, str2);
                return;
            case 28:
                this.mTarget.onTipsListeningStop(str, str2);
                return;
            case 29:
                this.mTarget.onTtsEcho(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{ContextInput.EVENT, ContextOutput.EVENT, ContextEvent.TIPS_TEXT, ContextEvent.WIDGET_CUSTOM, ContextEvent.WIDGET_TEXT, ContextEvent.WIDGET_LIST, ContextEvent.WIDGET_LIST_FOCUS, ContextEvent.WIDGET_MEDIA, ContextEvent.WIDGET_CARD, ContextEvent.WIDGET_RECOMMEND, ContextEvent.WIDGET_SEARCH, ContextEvent.EXPRESSION, ContextEvent.SAY_WELCOME, ContextEvent.WIDGET_LIST_SCROLL, ContextEvent.WIDGET_LIST_SELECT, ContextEvent.WIDGET_CANCEL, ContextEvent.CONTEXT_WIDGET_NEXT_PAGE, ContextEvent.CONTEXT_WIDGET_PREV_PAGE, ContextEvent.CONTEXT_WIDGET_TOPPING_PAGE, ContextEvent.CONTEXT_WIDGET_LOW_PAGE, ContextEvent.WIDGET_LIST_CANCEL_FOCUS, ContextEvent.SCRIPT_START, ContextEvent.SCRIPT_STATUS, "context.exit.recommend.card", ContextEvent.WIDGET_LIST_FOLD, ContextEvent.WIDGET_LIST_EXPEND, ContextEvent.WIDGET_LIST_STOP_COUNTDOWN, ContextEvent.TIPS_LISTENING_SHOW, ContextEvent.TIPS_LISTENING_STOP, TtsEcho.EVENT_TTS_ECHO};
    }
}
