package com.xiaopeng.speech.protocol.node.navi.bean;

import android.text.TextUtils;
import com.xiaopeng.speech.common.util.DontProguardClass;
import com.xiaopeng.speech.common.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;
@DontProguardClass
/* loaded from: classes2.dex */
public class NaviPreferenceBean {
    public static final String ATMS = "atms";
    public static final int ATMS_ID = 300;
    public static final String AVOID_CARPOOL = "avoid carpool";
    public static final String AVOID_COUNTRY_BORDER = "avoid country border";
    public static final String AVOID_FERRIES = "avoid ferries";
    public static final String AVOID_HIGHWAY = "avoid highway";
    public static final String AVOID_TOLL = "avoid toll";
    public static final String AVOID_TUNNEL = "avoid tunnel";
    public static final String AVOID_UNPAVED = "avoid unpaved";
    public static final String CAFE = "coffee";
    public static final int CAFE_ID = 301;
    public static final String CARPOOL = "carpool lanes";
    public static final String CHARGING_STATION = "charging station";
    public static final int CHARGING_STATION_ID = 307;
    public static final String COUNTRY_BORDER = "country borders";
    public static final String ECO_FRIENDLY = "eco";
    public static final String FASTEST = "fastest";
    public static final String FAST_FOOD = "fast food";
    public static final int FAST_FOOD_ID = 304;
    public static final String FERRIES = "ferries";
    public static final String GROCERY = "grocery";
    public static final int GROCERY_ID = 303;
    public static final String HIGHWAY = "highways";
    public static final String HOTEL = "hotel";
    public static final int HOTEL_ID = 302;
    public static final String PARKING_LOT = "parking lot";
    public static final int PARKING_LOT_ID = 306;
    public static final int PATH_PREF_AVOID_CARPOOL = 207;
    public static final int PATH_PREF_AVOID_COUNTRY_BORDER = 213;
    public static final int PATH_PREF_AVOID_FERRIES = 205;
    public static final int PATH_PREF_AVOID_HIGHWAY = 209;
    public static final int PATH_PREF_AVOID_TOLL = 201;
    public static final int PATH_PREF_AVOID_TUNNEL = 203;
    public static final int PATH_PREF_AVOID_UNPAVED = 211;
    public static final int PATH_PREF_CARPOOL = 206;
    public static final int PATH_PREF_COUNTRY_BORDER = 212;
    public static final int PATH_PREF_ECO_FRIENDLY = 101;
    public static final int PATH_PREF_FASTEST = 100;
    public static final int PATH_PREF_FERRIES = 204;
    public static final int PATH_PREF_HIGHWAY = 208;
    public static final int PATH_PREF_SHORTEST = 102;
    public static final int PATH_PREF_TOLL = 200;
    public static final int PATH_PREF_TUNNEL = 202;
    public static final int PATH_PREF_UNPAVED = 210;
    public static final String RESTAURANT = "restaurant";
    public static final int RESTAURANT_ID = 305;
    public static final String SAFETY_ALERT = "safety alert";
    public static final String SHORTEST = "shortest";
    private static final String TAG = "NaviPreferenceBean";
    public static final String TOLL = "toll road";
    public static final String TOLL_GATE_ALERT = "toll gate alert";
    public static final String TRAFFIC_CAMERA_ALERT = "traffic camera alert";
    public static final String TRAFFIC_EVENT_ALERT = "traffic event alert";
    public static final String TUNNEL = "tunnels";
    public static final String UNPAVED = "unpaved road";
    private int mEnable;
    private String mPref;
    private int mPrefId;

    public static NaviPreferenceBean fromJson(String str) {
        NaviPreferenceBean naviPreferenceBean = new NaviPreferenceBean();
        try {
            JSONObject jSONObject = new JSONObject(str);
            naviPreferenceBean.mPref = jSONObject.optString("mode");
            naviPreferenceBean.mPrefId = prefNameToPrefId(naviPreferenceBean.mPref);
            naviPreferenceBean.mEnable = jSONObject.optInt("switch");
            LogUtils.d(TAG, "fromJson, pref:" + naviPreferenceBean.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return naviPreferenceBean;
    }

    public String getPref() {
        return this.mPref;
    }

    public int getPrefId() {
        return this.mPrefId;
    }

    public boolean isEnable() {
        return this.mEnable == 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int prefNameToPrefId(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        switch (str.hashCode()) {
            case -2097684128:
                if (str.equals(AVOID_UNPAVED)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -1772467395:
                if (str.equals(RESTAURANT)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -1749203455:
                if (str.equals(AVOID_COUNTRY_BORDER)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -1722267927:
                if (str.equals(PARKING_LOT)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -1684221946:
                if (str.equals(HIGHWAY)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1355030580:
                if (str.equals(CAFE)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -1262772667:
                if (str.equals(AVOID_CARPOOL)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -1122290598:
                if (str.equals("eco friendly")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1077115990:
                if (str.equals(FASTEST)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -969179893:
                if (str.equals(TUNNEL)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -963579080:
                if (str.equals(FERRIES)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -901578142:
                if (str.equals(AVOID_HIGHWAY)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -862547864:
                if (str.equals("tunnel")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -597819771:
                if (str.equals(CHARGING_STATION)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -342498422:
                if (str.equals(SHORTEST)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -280604405:
                if (str.equals("unpaved")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -104723179:
                if (str.equals(UNPAVED)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -85459550:
                if (str.equals(FAST_FOOD)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 100241:
                if (str.equals(ECO_FRIENDLY)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3004697:
                if (str.equals(ATMS)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 3565883:
                if (str.equals("toll")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 99467700:
                if (str.equals(HOTEL)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 292882701:
                if (str.equals(GROCERY)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 554307056:
                if (str.equals("carpool")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 915501581:
                if (str.equals("highway")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 924752343:
                if (str.equals(CARPOOL)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1157046643:
                if (str.equals(AVOID_TUNNEL)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1158255333:
                if (str.equals(TOLL)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1359855878:
                if (str.equals(AVOID_TOLL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1410404157:
                if (str.equals(COUNTRY_BORDER)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 1514308493:
                if (str.equals(AVOID_FERRIES)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1569517558:
                if (str.equals("country border")) {
                    c = 21;
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
                return 100;
            case 1:
            case 2:
                return 101;
            case 3:
            case 4:
                return 200;
            case 5:
                return 201;
            case 6:
            case 7:
                return 206;
            case '\b':
                return 207;
            case '\t':
            case '\n':
                return 202;
            case 11:
                return 203;
            case '\f':
                return 204;
            case '\r':
                return 205;
            case 14:
            case 15:
                return 208;
            case 16:
                return 209;
            case 17:
            case 18:
                return PATH_PREF_UNPAVED;
            case 19:
                return PATH_PREF_AVOID_UNPAVED;
            case 20:
            case 21:
                return PATH_PREF_COUNTRY_BORDER;
            case 22:
                return PATH_PREF_AVOID_COUNTRY_BORDER;
            case 23:
                return 102;
            case 24:
                return 300;
            case 25:
                return 301;
            case 26:
                return 302;
            case 27:
                return 303;
            case 28:
                return 304;
            case 29:
                return 305;
            case 30:
                return 306;
            case 31:
                return 307;
            default:
                return 0;
        }
    }

    public String toString() {
        return "NaviPreferenceBean{pref='" + this.mPref + "', prefId='" + this.mPrefId + "', enable='" + this.mEnable + "'}";
    }
}
