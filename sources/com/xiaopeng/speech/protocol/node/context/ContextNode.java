package com.xiaopeng.speech.protocol.node.context;

import android.text.TextUtils;
import com.lzy.okgo.cookie.SerializableCookie;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.ContextInput;
import com.xiaopeng.speech.jarvisproto.ContextOutput;
import com.xiaopeng.speech.protocol.bean.FeedListUIValue;
import com.xiaopeng.speech.protocol.event.ContextEvent;
import com.xiaopeng.speech.protocol.node.tts.TtsEcho;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ContextNode extends SpeechNode<ContextListener> {
    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextInput.EVENT)
    public void onInputText(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onInputText(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextOutput.EVENT)
    public void onOutputText(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onOutputText(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.TIPS_TEXT)
    public void onTipsText(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onTipsText(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_CUSTOM)
    public void onWidgetCustom(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetCustom(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_TEXT)
    public void onWidgetText(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetText(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST)
    public void onWidgetList(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetList(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_FOCUS)
    public void onWidgetListFocus(String str, String str2) {
        Object[] collectCallbacks;
        try {
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            if (fromJson == null || (collectCallbacks = this.mListenerList.collectCallbacks()) == null) {
                return;
            }
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetListFocus(fromJson.source, fromJson.index);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_MEDIA)
    public void onWidgetMedia(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetMedia(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_CARD)
    public void onWidgetCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetCard(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_RECOMMEND)
    public void onWidgetRecommend(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetRecommend(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_SEARCH)
    public void onWidgetSearch(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetSearch(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.EXPRESSION)
    public void onExpression(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onExpression(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.SAY_WELCOME)
    public void onSayWelcome(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onSayWelcome(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_SCROLL)
    public void onWidgetScroll(String str, String str2) {
        Object[] collectCallbacks;
        try {
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            if (fromJson == null || (collectCallbacks = this.mListenerList.collectCallbacks()) == null) {
                return;
            }
            for (Object obj : collectCallbacks) {
                LogUtils.i("onWidgetScroll", "onWidgetScroll data:" + str2);
                ((ContextListener) obj).onWidgetScroll(fromJson.source, fromJson.index);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_SELECT)
    public void onWidgetListSelect(String str, String str2) {
        Object[] collectCallbacks;
        try {
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            if (fromJson == null || (collectCallbacks = this.mListenerList.collectCallbacks()) == null) {
                return;
            }
            for (int i = 0; i < collectCallbacks.length; i++) {
                LogUtils.i("onWidgetListSelect", "onWidgetListSelect data:" + str2);
                if (TextUtils.isEmpty(fromJson.type)) {
                    ((ContextListener) collectCallbacks[i]).onWidgetListSelect(fromJson.source, fromJson.index);
                } else {
                    ((ContextListener) collectCallbacks[i]).onWidgetListSelect(fromJson.source, fromJson.index, fromJson.type);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_CANCEL)
    public void onWidgetCancel(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks != null) {
                for (Object obj : collectCallbacks) {
                    String str3 = null;
                    String str4 = "force";
                    if (str2 != null) {
                        JSONObject jSONObject = new JSONObject(str2);
                        String optString = jSONObject.optString("widgetId");
                        str4 = jSONObject.optString("cancel");
                        str3 = optString;
                    }
                    ((ContextListener) obj).onWidgetCancel(str3, str4);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.CONTEXT_WIDGET_NEXT_PAGE)
    public void onPageNext(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onPageNext();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.CONTEXT_WIDGET_PREV_PAGE)
    public void onPagePrev(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onPagePrev();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.CONTEXT_WIDGET_TOPPING_PAGE)
    public void onPageTopping(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onPageTopping();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.CONTEXT_WIDGET_LOW_PAGE)
    public void onPageSetLow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onPageSetLow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_CANCEL_FOCUS)
    public void onWidgetListCancelFocus(String str, String str2) {
        Object[] collectCallbacks;
        try {
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            if (fromJson == null || (collectCallbacks = this.mListenerList.collectCallbacks()) == null) {
                return;
            }
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetListCancelFocus(fromJson.source, fromJson.index);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.SCRIPT_START)
    public void onScript(String str, String str2) {
        String str3;
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            String str4 = "";
            if (str2 != null) {
                JSONObject jSONObject = new JSONObject(str2);
                str3 = jSONObject.optString(SerializableCookie.DOMAIN);
                str4 = jSONObject.optString("scriptId");
            } else {
                str3 = "";
            }
            if (collectCallbacks == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                return;
            }
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onScript(str3, str4);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.SCRIPT_STATUS)
    public void onScriptStatus(String str, String str2) {
        String str3;
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            String str4 = "";
            if (str2 != null) {
                JSONObject jSONObject = new JSONObject(str2);
                str3 = jSONObject.optString("status");
                str4 = jSONObject.optString("scriptId");
            } else {
                str3 = "";
            }
            if (collectCallbacks == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                return;
            }
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onScriptStatus(str4, str3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = "context.exit.recommend.card")
    public void onExitRecommendCard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onExitRecommendCard();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_FOLD)
    public void onWidgetListFold(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks == null || str2 == null) {
                return;
            }
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetListFold(fromJson.source, fromJson.type);
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_EXPEND)
    public void onWidgetListExpend(String str, String str2) {
        try {
            Object[] collectCallbacks = this.mListenerList.collectCallbacks();
            if (collectCallbacks == null || str2 == null) {
                return;
            }
            FeedListUIValue fromJson = FeedListUIValue.fromJson(new JSONObject(str2));
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetListExpend(fromJson.source, fromJson.type);
            }
        } catch (JSONException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.WIDGET_LIST_STOP_COUNTDOWN)
    public void onWidgetListStopCountdown(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onWidgetListStopCountdown();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.TIPS_LISTENING_SHOW)
    public void onTipsListeningShow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onTipsListeningShow(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = ContextEvent.TIPS_LISTENING_STOP)
    public void onTipsListeningStop(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onTipsListeningStop(str2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @SpeechAnnotation(event = TtsEcho.EVENT_TTS_ECHO)
    public void onTtsEcho(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        TtsEcho fromJson = TtsEcho.fromJson(str2);
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((ContextListener) obj).onTtsEcho(fromJson);
            }
        }
    }

    public void onWidgetCancelByUser(String str) {
        LogUtils.i("onWidgetCancelByUser widgetId: " + str);
        SpeechClient.instance().getAgent().sendUIEvent(ContextEvent.WIDGET_CANCEL_BY_USER, str);
    }
}
