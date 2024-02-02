package com.xiaopeng.speech.protocol.query.context;

import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.speech.QueryContextEvent;
/* loaded from: classes2.dex */
public class ContextQuery extends SpeechQuery<IContextCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_LIST_SIZE)
    public int getWidgetListSize(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetListSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_PAGE_SIZE)
    public int getWidgetPageSize(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetPageSize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_CURR_LOCATION)
    public int getWidgetCurrLocation(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetCurrLocation();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_ID)
    public String getWidgetId(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetId();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_TYPE)
    public String getWidgetType(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetType();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_WIDGET_INFO)
    public String getWidgetInfo(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getWidgetInfo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_INFO_LIST_TOP)
    public int getInfoFlowScrollToTop(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getInfoFlowScrollToTop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_INFO_LIST_BOTTOM)
    public int getInfoFlowScrollToBottom(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getInfoFlowScrollToBottom();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = QueryContextEvent.CONTEXT_INFO_LIST_ONEPAGE)
    public int getInfoFlowOnePage(String str, String str2) {
        return ((IContextCaller) this.mQueryCaller).getInfoFlowOnePage();
    }
}
