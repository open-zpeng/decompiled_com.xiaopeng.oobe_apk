package com.xiaopeng.speech.protocol.query.speech.combo;

import android.text.TextUtils;
import com.xiaopeng.datalog.MoleEvent;
import com.xiaopeng.speech.SpeechQuery;
import com.xiaopeng.speech.annotation.QueryAnnotation;
import com.xiaopeng.speech.protocol.event.query.speech.ComboQueryEvent;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class ComboQuery extends SpeechQuery<IComboQueryCaller> {
    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_ENTER_USERMODE)
    public String enterMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).enterUserMode(getModeFromJson(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_EXIT_USERMODE)
    public String exitMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).exitUserMode(getModeFromJson(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_CHECK_ENTER_USERMODE)
    public String checkEnterUserMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).checkEnterUserMode(getModeFromJson(str2));
    }

    private String getModeFromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("mode") ? jSONObject.optString("mode") : "";
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_GET_CURRENT_USERMODE)
    public String getCurrentUserMode(String str, String str2) {
        return ((IComboQueryCaller) this.mQueryCaller).getCurrentUserMode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_GET_CURRENT_USERMODE_SCREEN)
    public String getCurrentUserModeWithScreen(String str, String str2) {
        int i = 0;
        try {
            i = new JSONObject(str2).optInt("display_location", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ((IComboQueryCaller) this.mQueryCaller).getCurrentUserModeWithScreen(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @QueryAnnotation(event = ComboQueryEvent.COMBO_GUI_PAGE_CHECKFORBIDDEN)
    public boolean guiPagecheckForbidden(String str, String str2) {
        String str3;
        int i = 0;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            i = jSONObject.optInt("display_location", 0);
            str3 = jSONObject.optString(MoleEvent.KEY_PAGE_ID, "");
        } catch (JSONException e) {
            e.printStackTrace();
            str3 = null;
        }
        return ((IComboQueryCaller) this.mQueryCaller).guiPagecheckForbidden(i, str3);
    }
}
