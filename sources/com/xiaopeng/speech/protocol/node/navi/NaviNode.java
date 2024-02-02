package com.xiaopeng.speech.protocol.node.navi;

import android.os.Binder;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.xiaopeng.speech.SpeechClient;
import com.xiaopeng.speech.SpeechNode;
import com.xiaopeng.speech.actorapi.ResultActor;
import com.xiaopeng.speech.annotation.SpeechAnnotation;
import com.xiaopeng.speech.common.util.LogUtils;
import com.xiaopeng.speech.jarvisproto.FeedUIEvent;
import com.xiaopeng.speech.jarvisproto.WakeupResult;
import com.xiaopeng.speech.protocol.bean.FeedListUIValue;
import com.xiaopeng.speech.protocol.bean.XpListTypeEnum;
import com.xiaopeng.speech.protocol.event.ContextEvent;
import com.xiaopeng.speech.protocol.event.NaviEvent;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
import com.xiaopeng.speech.protocol.node.navi.bean.AddressBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NaviPreferenceBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NearbySearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PathBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiSearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.RouteSelectBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectParkingBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectRouteBean;
import com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean;
import com.xiaopeng.speech.protocol.node.navi.bean.WaypointSearchBean;
import com.xiaopeng.speech.speechwidget.ContentWidget;
import com.xiaopeng.speech.speechwidget.ListWidget;
import com.xiaopeng.speech.speechwidget.SpeechWidget;
import com.xiaopeng.speech.speechwidget.TextWidget;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NaviNode extends SpeechNode<NaviListener> {
    private static final String ACTIVE_VOICE_TASK = "主动语音";
    public static final String ALL_ROUTE_WIDGET_ID = "-Route-2";
    public static final String BASE_ROUTE_WIDGET_ID = "-Route-1";
    private static final String COMMAND_SPLIT = "#";
    private static final String KEY_MODE = "mode";
    private static final String KEY_PULLUP_NAVI = "pullUpNavi";
    private static final String OFFLINE_SKILL = "离线命令词";
    private static final String SELECT_PARKING_INTENT = "停车场主动语音";
    private static final String SELECT_ROUTE_INTENT = "路线主动语音";
    private static final int STOP_DIALOG_OPT_FORCE = 0;
    private static final int STOP_DIALOG_OPT_OPTIONAL = 1;
    private boolean mAddressPendingRoute = false;
    private IBinder binder = new Binder();

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE)
    public void onControlClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMIN)
    public void onMapZoomIn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomIn();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMOUT)
    public void onMapZoomOut(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomOut();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROAD_INFO_OPEN)
    public void onOpenTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onOpenTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROAD_INFO_CLOSE)
    public void onCloseTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onCloseTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OVERVIEW_OPEN)
    public void onControlOverviewOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOverviewOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OVERVIEW_CLOSE)
    public void onControlOverviewClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOverviewClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_OVERVIEW)
    public void onMapOverview(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapOverview();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_FAVORITE_OPEN)
    public void onControlFavoriteOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlFavoriteOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SETTINGS_OPEN)
    public void onControlSettingsOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSettingsOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_OPEN)
    public void onControlChargeOpen(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlChargeOpen();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_CLOSE)
    public void onControlChargeClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlChargeClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONGESTION)
    public void onDriveAvoidCongestion(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCongestion();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONGESTION_OFF)
    public void onDriveAvoidCongestionOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCongestionOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CHARGE)
    public void onDriveAvoidCharge(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidCharge();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CHARGE_OFF)
    public void onDriveAvoidChargeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidChargeOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_FIRST_OFF)
    public void onDriveHighwayFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONTROLS)
    public void onDriveAvoidControls(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidControls();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_AVOID_CONTROLS_OFF)
    public void onDriveAvoidControlsOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAvoidControlsOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_RADAR_ROUTE)
    public void onDriveRadarRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveRadarRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_RADAR_ROUTE_OFF)
    public void onDriveRadarRouteOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveRadarRouteOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_MUTE)
    public void onControlSpeechMute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechMute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_SUPER_SIMPLE)
    public void onControlSpeechSuperSimple(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechSuperSimple();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_GENERAL)
    public void onControlSpeechGeneral(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechGeneral();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_EYE)
    public void onControlSpeechEye(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechEye();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_EYE_OFF)
    public void onControlSpeechEyeOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechEyeOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_SCALE)
    public void onControlSmartScale(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartScale();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_SCALE_OFF)
    public void onControlSmartScaleOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartScaleOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SECURITY_REMIND)
    public void onControlSecurityRemind(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSecurityRemind();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_ROAD_AHEAD)
    public void onControlRoadAhead(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRoadAhead();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_NO)
    public void onDriveHighwayNo(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayNo();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_NO_OFF)
    public void onDriveHighwayNoOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayNoOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.DRIVE_HIGHWAY_FIRST)
    public void onDriveHighwayFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveHighwayFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.NAVIGATING_GET)
    public void onNavigatingGet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNavigatingGet();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.POI_SEARCH)
    public void onPoiSearch(String str, String str2) {
        PoiSearchBean fromJson = PoiSearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiSearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SECURITY_REMIND_OFF)
    public void onControlSecurityRemindOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSecurityRemindOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ENTER_FIND_PATH)
    public void onMapEnterFindPath(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapEnterFindPath();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_EXIT_FIND_PATH)
    public void onMapExitFindPath(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapExitFindPath();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SEARCH_CLOSE)
    public void onSearchClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSearchClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAIN_ROAD)
    public void onMainRoad(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMainRoad();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SIDE_ROAD)
    public void onSideRoad(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSideRoad();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_FAVORITE_CLOSE)
    public void onControlFavoriteClose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlFavoriteClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_ROAD_AHEAD_OFF)
    public void onControlRoadAheadOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRoadAheadOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMIN_MAX)
    public void onMapZoominMax(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoominMax();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_ZOOMOUT_MIN)
    public void onMapZoomoutMin(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onMapZoomoutMin();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.NEARBY_SEARCH)
    public void onNearbySearch(String str, String str2) {
        NearbySearchBean fromJson = NearbySearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNearbySearch(fromJson);
            }
        }
    }

    public void postPoiResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.POI_SEARCH, str, list);
    }

    public void postNearbyResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.NEARBY_SEARCH, str, list);
    }

    public void postSearchPoiResult(String str, String str2, List<PoiBean> list) {
        postPoiResult(str, str2, list);
    }

    public void postAddressPosResult(String str, List<PoiBean> list) {
        postPoiResult(NaviEvent.POI_SEARCH, str, list);
    }

    public void postWaypointsFull(String str) {
        postWaypointSearchResult(str, null, true, true);
    }

    public void postWaypointsNotExitRoute(String str) {
        postWaypointSearchResult(str, null, false, false);
    }

    public void postWaypointSearchResult(String str, List<PoiBean> list) {
        postWaypointSearchResult(str, list, true, false);
    }

    private void postWaypointSearchResult(String str, List<PoiBean> list, boolean z, boolean z2) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(str);
        listWidget.setExist(z);
        listWidget.setExtraType("navi");
        listWidget.addContent("isWaypointListFull", String.valueOf(z2));
        if (list != null) {
            for (PoiBean poiBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(poiBean.getName());
                contentWidget.setSubTitle(poiBean.getAddress());
                try {
                    contentWidget.addExtra("navi", poiBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.WAYPOINT_SEARCH).setResult(listWidget));
    }

    private void postPoiResult(String str, String str2, List<PoiBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(str2);
        listWidget.setExtraType("navi");
        if (list != null) {
            for (PoiBean poiBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(poiBean.getName());
                contentWidget.setSubTitle(poiBean.getAddress());
                try {
                    contentWidget.addExtra("navi", poiBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(str).setResult(listWidget));
    }

    @SpeechAnnotation(event = NaviEvent.ADDRESS_GET)
    public void onAddressGet(String str, String str2) {
        AddressBean fromJson = AddressBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAddressGet(fromJson);
            }
        }
        this.mAddressPendingRoute = false;
    }

    public void postAddressGetResult(boolean z, boolean z2, PoiBean poiBean) {
        TextWidget textWidget = new TextWidget();
        textWidget.setText(z ? "success" : "fail");
        textWidget.addContent("hasBigData", z2 ? OOBEEvent.STRING_TRUE : OOBEEvent.STRING_FALSE);
        if (poiBean != null) {
            try {
                textWidget.addContent("address", poiBean.getAddress());
                textWidget.addExtra("navi", poiBean.toJson().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.ADDRESS_GET).setResult(textWidget));
    }

    @SpeechAnnotation(event = NaviEvent.ADDRESS_PENDING_ROUTE)
    public void onAddressPendingRoute(String str, String str2) {
        LogUtils.i(this, "pending route");
        this.mAddressPendingRoute = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0086  */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.ADDRESS_SET)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onAddressSet(java.lang.String r10, java.lang.String r11) {
        /*
            r9 = this;
            java.lang.String r10 = "soundArea"
            com.xiaopeng.speech.protocol.node.navi.bean.AddressBean r0 = new com.xiaopeng.speech.protocol.node.navi.bean.AddressBean
            r0.<init>()
            r1 = 0
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L63
            r3.<init>(r11)     // Catch: org.json.JSONException -> L63
            java.lang.String r11 = "addressType"
            java.lang.String r11 = r3.optString(r11)     // Catch: org.json.JSONException -> L63
            r0.setAddressType(r11)     // Catch: org.json.JSONException -> L63
            java.lang.String r11 = "poi"
            java.lang.String r11 = r3.optString(r11)     // Catch: org.json.JSONException -> L63
            com.xiaopeng.speech.protocol.node.navi.bean.PoiBean r11 = com.xiaopeng.speech.protocol.node.navi.bean.PoiBean.fromJson(r11)     // Catch: org.json.JSONException -> L63
            java.lang.String r4 = "pref"
            java.lang.String r4 = r3.optString(r4)     // Catch: org.json.JSONException -> L5e
            java.lang.String r5 = "type"
            java.lang.String r5 = r3.optString(r5)     // Catch: org.json.JSONException -> L59
            java.lang.String r6 = "naviType"
            int r6 = r3.optInt(r6)     // Catch: org.json.JSONException -> L55
            java.lang.String r7 = "routeSelectRef"
            int r7 = r3.optInt(r7)     // Catch: org.json.JSONException -> L52
            boolean r8 = r3.has(r10)     // Catch: org.json.JSONException -> L50
            if (r8 == 0) goto L6c
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch: org.json.JSONException -> L50
            r8.<init>()     // Catch: org.json.JSONException -> L50
            int r2 = r3.getInt(r10)     // Catch: org.json.JSONException -> L4d
            r8.put(r10, r2)     // Catch: org.json.JSONException -> L4d
            r2 = r8
            goto L6c
        L4d:
            r10 = move-exception
            r2 = r8
            goto L69
        L50:
            r10 = move-exception
            goto L69
        L52:
            r10 = move-exception
            r7 = r1
            goto L69
        L55:
            r10 = move-exception
            r6 = r1
            r7 = r6
            goto L69
        L59:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r5 = r2
            goto L69
        L5e:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r4 = r2
            goto L68
        L63:
            r10 = move-exception
            r6 = r1
            r7 = r6
            r11 = r2
            r4 = r11
        L68:
            r5 = r4
        L69:
            r10.printStackTrace()
        L6c:
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r10 = r9.mListenerList
            java.lang.Object[] r10 = r10.collectCallbacks()
            if (r10 == 0) goto L82
            r3 = r1
        L75:
            int r8 = r10.length
            if (r3 >= r8) goto L82
            r8 = r10[r3]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r8 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r8
            r8.onAddressSet(r0, r11)
            int r3 = r3 + 1
            goto L75
        L82:
            boolean r10 = r9.mAddressPendingRoute
            if (r10 == 0) goto L97
            com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean r10 = new com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean
            r10.<init>(r11, r4, r5)
            r10.setNaviType(r6)
            r10.setRouteSelectRef(r7)
            r10.setSpeechInfo(r2)
            r9.doControlStart(r10)
        L97:
            r9.mAddressPendingRoute = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onAddressSet(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_START)
    public void onControlStart(String str, String str2) {
        boolean z;
        int i;
        int i2;
        PoiBean poiBean;
        String str3;
        String str4;
        boolean z2;
        JSONObject jSONObject = null;
        try {
            JSONObject jSONObject2 = new JSONObject(str2);
            poiBean = PoiBean.fromJson(jSONObject2.optString("poi"));
            try {
                str3 = jSONObject2.optString("pref");
                try {
                    str4 = jSONObject2.optString("type");
                    try {
                        i = jSONObject2.optInt("naviType");
                        try {
                            i2 = jSONObject2.optInt("routeSelectRef");
                            try {
                                z2 = jSONObject2.has("fullSceneSwitch") ? jSONObject2.getBoolean("fullSceneSwitch") : true;
                                try {
                                    if (jSONObject2.has("soundArea")) {
                                        JSONObject jSONObject3 = new JSONObject();
                                        try {
                                            jSONObject3.put("soundArea", jSONObject2.getInt("soundArea"));
                                            jSONObject = jSONObject3;
                                        } catch (JSONException e) {
                                            e = e;
                                            jSONObject = jSONObject3;
                                            JSONException jSONException = e;
                                            z = z2;
                                            e = jSONException;
                                            e.printStackTrace();
                                            z2 = z;
                                            StartNaviBean startNaviBean = new StartNaviBean(poiBean, str3, str4);
                                            startNaviBean.setNaviType(i);
                                            startNaviBean.setRouteSelectRef(i2);
                                            startNaviBean.setFullSceneSwitch(z2);
                                            startNaviBean.setSpeechInfo(jSONObject);
                                            LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean.toString());
                                            doControlStart(startNaviBean);
                                        }
                                    }
                                } catch (JSONException e2) {
                                    e = e2;
                                }
                            } catch (JSONException e3) {
                                e = e3;
                                z = true;
                            }
                        } catch (JSONException e4) {
                            e = e4;
                            z = true;
                            i2 = 0;
                        }
                    } catch (JSONException e5) {
                        e = e5;
                        z = true;
                        i = 0;
                        i2 = 0;
                    }
                } catch (JSONException e6) {
                    e = e6;
                    z = true;
                    i = 0;
                    i2 = 0;
                    str4 = null;
                }
            } catch (JSONException e7) {
                e = e7;
                z = true;
                i = 0;
                i2 = 0;
                str3 = null;
                str4 = str3;
                e.printStackTrace();
                z2 = z;
                StartNaviBean startNaviBean2 = new StartNaviBean(poiBean, str3, str4);
                startNaviBean2.setNaviType(i);
                startNaviBean2.setRouteSelectRef(i2);
                startNaviBean2.setFullSceneSwitch(z2);
                startNaviBean2.setSpeechInfo(jSONObject);
                LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean2.toString());
                doControlStart(startNaviBean2);
            }
        } catch (JSONException e8) {
            e = e8;
            z = true;
            i = 0;
            i2 = 0;
            poiBean = null;
            str3 = null;
        }
        StartNaviBean startNaviBean22 = new StartNaviBean(poiBean, str3, str4);
        startNaviBean22.setNaviType(i);
        startNaviBean22.setRouteSelectRef(i2);
        startNaviBean22.setFullSceneSwitch(z2);
        startNaviBean22.setSpeechInfo(jSONObject);
        LogUtils.d("NaviNode", "StartNaviBean = %s", startNaviBean22.toString());
        doControlStart(startNaviBean22);
    }

    private void doControlStart(StartNaviBean startNaviBean) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlStart(startNaviBean);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_SIMPLE)
    public void onControlSpeechSimple(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechSimple();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_DETAIL)
    public void onControlSpeechDetail(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechDetail();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_NORTH)
    public void onControlDisPlayNorth(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisPlayNorth();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_CAR)
    public void onControlDisPlayCar(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisPlayCar();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_3D)
    public void onControlDisplay3D(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay3D();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[LOOP:0: B:11:0x0027->B:13:0x002a, LOOP_START, PHI: r2 
      PHI: (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:10:0x0025, B:13:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[ORIG_RETURN, RETURN] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.CONTROL_VOL_ON)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onControlVolOn(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r5 = "mode"
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r0 = r4.mListenerList
            java.lang.Object[] r0 = r0.collectCallbacks()
            r1 = 1
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L20
            r3.<init>(r6)     // Catch: java.lang.Exception -> L20
            java.lang.String r6 = "pullUpNavi"
            boolean r1 = r3.optBoolean(r6, r2)     // Catch: java.lang.Exception -> L20
            boolean r6 = r3.has(r5)     // Catch: java.lang.Exception -> L20
            if (r6 == 0) goto L24
            int r5 = r3.optInt(r5)     // Catch: java.lang.Exception -> L20
            goto L25
        L20:
            r5 = move-exception
            r5.printStackTrace()
        L24:
            r5 = r2
        L25:
            if (r0 == 0) goto L34
        L27:
            int r6 = r0.length
            if (r2 >= r6) goto L34
            r6 = r0[r2]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r6 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r6
            r6.onControlVolOn(r1, r5)
            int r2 = r2 + 1
            goto L27
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onControlVolOn(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027 A[LOOP:0: B:11:0x0027->B:13:0x002a, LOOP_START, PHI: r2 
      PHI: (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:10:0x0025, B:13:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0034 A[ORIG_RETURN, RETURN] */
    @com.xiaopeng.speech.annotation.SpeechAnnotation(event = com.xiaopeng.speech.protocol.event.NaviEvent.CONTROL_VOL_OFF)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onControlVolOff(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            java.lang.String r5 = "mode"
            com.xiaopeng.speech.common.util.SimpleCallbackList<T> r0 = r4.mListenerList
            java.lang.Object[] r0 = r0.collectCallbacks()
            r1 = 1
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: java.lang.Exception -> L20
            r3.<init>(r6)     // Catch: java.lang.Exception -> L20
            java.lang.String r6 = "pullUpNavi"
            boolean r1 = r3.optBoolean(r6, r2)     // Catch: java.lang.Exception -> L20
            boolean r6 = r3.has(r5)     // Catch: java.lang.Exception -> L20
            if (r6 == 0) goto L24
            int r5 = r3.optInt(r5)     // Catch: java.lang.Exception -> L20
            goto L25
        L20:
            r5 = move-exception
            r5.printStackTrace()
        L24:
            r5 = r2
        L25:
            if (r0 == 0) goto L34
        L27:
            int r6 = r0.length
            if (r2 >= r6) goto L34
            r6 = r0[r2]
            com.xiaopeng.speech.protocol.node.navi.NaviListener r6 = (com.xiaopeng.speech.protocol.node.navi.NaviListener) r6
            r6.onControlVolOff(r1, r5)
            int r2 = r2 + 1
            goto L27
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.onControlVolOff(java.lang.String, java.lang.String):void");
    }

    @SpeechAnnotation(event = NaviEvent.ROUTE_NEARBY_SEARCH)
    public void onRouteNearbySearch(String str, String str2) {
        NearbySearchBean fromJson = NearbySearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onRouteNearbySearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.PARKING_SELECT)
    public void onParkingSelect(String str, String str2) {
        SelectParkingBean fromJson = SelectParkingBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onParkingSelect(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONFIRM_OK)
    public void onConfirmOk(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onConfirmOk();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONFIRM_CANCEL)
    public void onConfirmCancel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onConfirmCancel();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.ROUTE_SELECT)
    public void onRouteSelect(String str, String str2) {
        SelectRouteBean fromJson = SelectRouteBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onRouteSelect(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SELECT_PARKING_COUNT)
    public void onSelectParkingCount(String str, String str2) {
        SelectParkingBean fromJson = SelectParkingBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSelectParkingCount(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SELECT_ROUTE_COUNT)
    public void onSelectRouteCount(String str, String str2) {
        SelectRouteBean fromJson = SelectRouteBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onSelectRouteCount(fromJson);
            }
        }
    }

    public void onDataControlDisplay3dTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplay3dTts();
            }
        }
    }

    public void onDataControlDisplayCarTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplayCarTts();
            }
        }
    }

    public void onDataControlDisplayNorthTts(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDataControlDisplayNorthTts();
            }
        }
    }

    public void onDriveAdvoidTrafficControl(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onDriveAdvoidTrafficControl();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.WAYPOINT_SEARCH)
    public void onWaypointSearch(String str, String str2) {
        WaypointSearchBean fromJson = WaypointSearchBean.fromJson(str2);
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onWaypointSearch(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_WAYPOINT_START)
    public void onControlWaypointStart(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        PathBean fromJson = PathBean.fromJson(str2);
        LogUtils.d("NaviNode, pathBean =%s", fromJson == null ? "data is null" : fromJson.toString());
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlWaypointStart(fromJson);
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OPEN_SMALL_MAP)
    public void onControlOpenSmallMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOpenSmallMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE_SMALL_MAP)
    public void onControlCloseSmallMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCloseSmallMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_OPEN_RIBBON_MAP)
    public void onControlOpenRibbonMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlOpenRibbonMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CLOSE_RIBBON_MAP)
    public void onControlCloseRibbonMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCloseRibbonMap();
            }
        }
    }

    public void selectParking(String str) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isLocalSkill", true).put("intent", SELECT_PARKING_INTENT).put("isAsrModeOffline", false).put(WakeupResult.REASON_COMMAND, "native://navi.select.parking.count#command://navi.parking.select#command://navi.confirm.cancel").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, ACTIVE_VOICE_TASK, SELECT_PARKING_INTENT, str2);
    }

    @Deprecated
    public void selectRoute(String str) {
        String str2;
        SpeechClient.instance().getWakeupEngine().stopDialog();
        try {
            str2 = new JSONObject().put("tts", str).put("isLocalSkill", true).put("intent", SELECT_ROUTE_INTENT).put("isAsrModeOffline", false).put(WakeupResult.REASON_COMMAND, "native://navi.select.route.count#command://navi.route.select#command://navi.confirm.cancel").toString();
        } catch (JSONException e) {
            e.printStackTrace();
            str2 = "";
        }
        SpeechClient.instance().getAgent().triggerIntent(OFFLINE_SKILL, ACTIVE_VOICE_TASK, SELECT_ROUTE_INTENT, str2);
    }

    public void selectRoute(List<RouteSelectBean> list) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
    }

    public void updateRouteSelect(List<RouteSelectBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        LogUtils.i("updateRouteSelect", "updateRouteSelect:" + listWidget.toString());
        SpeechClient.instance().getAgent().sendEvent(ContextEvent.WIDGET_LIST, listWidget.toString());
    }

    private SpeechWidget getRouteSelectWidget(List<RouteSelectBean> list) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        return listWidget;
    }

    public void stopSpeechDialog() {
        SpeechClient.instance().getWakeupEngine().stopDialog();
    }

    public void stopSpeechDialog(int i) {
        LogUtils.i(this, "stopDialog option: " + i);
        if (i == 0) {
            SpeechClient.instance().getWakeupEngine().stopDialog();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TypedValues.TransitionType.S_FROM, "Navigation");
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startSpeechDialog() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TypedValues.TransitionType.S_FROM, "Navigation");
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void directNavigation() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(TypedValues.TransitionType.S_FROM, "Navigation");
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.SCRIPT_QUIT, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_HISTORY)
    public void onControlHistory(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHistory();
            }
        }
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
            jSONObject.put("poiInfo", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRoute(List<RouteSelectBean> list, String str, boolean z, String str2, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            if (list != null && list.size() > 0) {
                for (RouteSelectBean routeSelectBean : list) {
                    jSONArray.put(routeSelectBean.toJson());
                }
            }
            jSONObject.put("route_list", jSONArray.toString());
            jSONObject.put("localStory", str);
            jSONObject.put("poiInfo", str2);
            jSONObject.put("isChargeRoute", z2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (z) {
            SpeechClient.instance().getAgent().triggerEvent(FeedUIEvent.LIST_ROUT_UPLOAD, jSONObject.toString());
        }
        syncRouteToInfoFlow(list, str, z);
    }

    public void syncRouteToInfoFlow(List<RouteSelectBean> list, String str, boolean z) {
        ListWidget listWidget = new ListWidget();
        listWidget.setTitle(FeedListUIValue.TYPE_ROUTE);
        listWidget.setExtraType(ListWidget.EXTRA_TYPE_NAVI_ROUTE);
        if (z) {
            listWidget.setWidgetId(str + BASE_ROUTE_WIDGET_ID);
        } else {
            listWidget.setWidgetId(str + ALL_ROUTE_WIDGET_ID);
        }
        if (list != null && list.size() > 0) {
            for (RouteSelectBean routeSelectBean : list) {
                ContentWidget contentWidget = new ContentWidget();
                contentWidget.setTitle(routeSelectBean.totalTimeLine1);
                contentWidget.setSubTitle(routeSelectBean.routeTypeName);
                try {
                    contentWidget.addExtra(ListWidget.EXTRA_TYPE_NAVI_ROUTE, routeSelectBean.toJson().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                listWidget.addContentWidget(contentWidget);
            }
        }
        LogUtils.i("NaviNode", "syncRouteToInfoFlow:" + listWidget.toString());
        SpeechClient.instance().getAgent().sendEvent(ContextEvent.WIDGET_LIST, listWidget.toString());
    }

    @SpeechAnnotation(event = NaviEvent.SETTINGS_INFO_GET)
    public void onGetSettingsInfo(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onGetSettingsInfo();
            }
        }
    }

    public void postSettingsInfo(String str) {
        SpeechClient.instance().getActorBridge().send(new ResultActor(NaviEvent.SETTINGS_INFO_GET).setResult(str));
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_PARK_RECOMMEND_ON)
    public void onControlParkRecommendOn(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlParkRecommendOn();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_PARK_RECOMMEND_OFF)
    public void onControlParkRecommendOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlParkRecommendOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.SCALE_LEVEL_SET)
    public void onSetScaleLevel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("level")) {
                int i = jSONObject.getInt("level");
                for (Object obj : collectCallbacks) {
                    ((NaviListener) obj).onSetScaleLevel(i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.MOVE_NAV_METRE_SET)
    public void onSetOritentionMetre(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("metre") && jSONObject.has("oritention")) {
                double d = jSONObject.getDouble("metre");
                int i = jSONObject.getInt("oritention");
                for (Object obj : collectCallbacks) {
                    ((NaviListener) obj).onSetOritentionMetre(d, i);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.ALERTS_PREFERENCE_SET)
    public void onAlertsPreferenceSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onAlertsPreferenceSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.AVOID_ROUTE_SET)
    public void onAvoidRouteSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onAvoidRouteSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_BETTER_ROUTE)
    public void onAutoRerouteBetterRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteBetterRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_ASK_FIRST)
    public void onAutoRerouteAskFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteAskFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.AUTO_REROUTE_NEVER)
    public void onAutoRerouteNever(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onAutoRerouteNever();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.MAP_SHOW_SET)
    public void onMapShowSet(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks == null || TextUtils.isEmpty(str2)) {
            return;
        }
        NaviPreferenceBean fromJson = NaviPreferenceBean.fromJson(str2);
        for (Object obj : collectCallbacks) {
            ((NaviListener) obj).onMapShowSet(fromJson);
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_POI_DETAILS_FAVORITE_ADD)
    public void onPoiDetailsFavoriteAdd(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiDetailsFavoriteAdd();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_POI_DETAILS_FAVORITE_DEL)
    public void onPoiDetailsFavoriteDel(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onPoiDetailsFavoriteDel();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SETTINGS_CLOSE)
    public void onControlSettingsCLose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSettingsClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_HISTORY_CLOSE)
    public void onControlHistoryCLose(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHistoryClose();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.LIST_ITEM_SELECTED)
    public void onListItemSelected(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("index")) {
                int optInt = jSONObject.optInt("index");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onListItemSelected(optInt);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_GAODE_ACCOUNT_BING_PAGE_OPEN)
    public void onControlGaodeAccountPageOpen(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("pageId")) {
                int optInt = jSONObject.optInt("pageId");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onControlGaodeAccountPageOpen(optInt);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_CHARGE_SERVICE_OPEN)
    public void onControlChargeServiceOpen(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("stationId")) {
                String optString = jSONObject.optString("stationId");
                Object[] collectCallbacks = this.mListenerList.collectCallbacks();
                if (collectCallbacks != null) {
                    for (Object obj : collectCallbacks) {
                        ((NaviListener) obj).onControlChargeServiceOpen(optString);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_STANDARD)
    public void onControlSpeechStandard(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechStandard();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_CRUISE)
    public void onControlSpeechCruise(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechCruise();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SPEECH_CRUISE_OFF)
    public void onControlSpeechCruiseOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSpeechCruiseOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_CHARGING_ROUTE)
    public void onControlSmartChargingRoute(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartChargingRoute();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_CHARGING_ROUTE_OFF)
    public void onControlSmartChargingRouteOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartChargingRouteOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_RECOMMEND)
    public void onControlSmartRecommend(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartRecommend();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_SMART_RECOMMEND_OFF)
    public void onControlSmartRecommendOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlSmartRecommendOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_CHARGE_LESS)
    public void onControlDriveChargeLess(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDriveChargeLess();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_CHARGE_LESS_OFF)
    public void onControlDriveChargeLessOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDriveChargeLessOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_HIGHROAD_FIRST)
    public void onControlHighroadFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHighroadFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_HIGHROAD_FIRST_OFF)
    public void onControlHighroadFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlHighroadFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_TIME_FIRST)
    public void onControlTimeFirst(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTimeFirst();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DRIVE_TIME_FIRST_OFF)
    public void onControlTimeFirstOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTimeFirstOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_COMMUTER_ROUTE_SHOW)
    public void onControlCommuterRouteShow(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCommuterRouteShow();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_COMMUTER_ROUTE_SHOW_OFF)
    public void onControlCommuterRouteShowOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlCommuterRouteShowOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_2D_HEAD_UP)
    public void onControlDisplay2DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay2DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_3D_HEAD_UP)
    public void onControlDisplay3DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDisplay3DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_DASHBOARD_2D_HEAD_UP)
    public void onControlDashboardDisplay2DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDashboardDisplay2DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_DISPLAY_DASHBOARD_3D_HEAD_UP)
    public void onControlDashboardDisplay3DHeadUp(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlDashboardDisplay3DHeadUp();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_REALTIME_TRAFFIC_OFF)
    public void onControlRealtimeTrafficOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRealtimeTrafficOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_REALTIME_TRAFFIC)
    public void onControlRealtimeTraffic(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlRealtimeTraffic();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_TRAFFIC_INCIDENT)
    public void onControlTrafficIncident(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTrafficIncident();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_TRAFFIC_INCIDENT_OFF)
    public void onControlTrafficIncidentOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlTrafficIncidentOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_BRIDGE_3D_MAP)
    public void onControlBridge3DMap(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlBridge3DMap();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_BRIDGE_3D_MAP_OFF)
    public void onControlBridge3DMapOff(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        if (collectCallbacks != null) {
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onControlBridge3DMapOff();
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH)
    public void onNaviSearch(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (collectCallbacks != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject3 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject3.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            jSONObject2.put(next, jSONObject3.get(next));
                        }
                        jSONObject.put(next, jSONObject3.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearch:searchInfo:" + jSONObject2 + ",speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearch(jSONObject2.toString(), jSONObject.toString());
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH_ON_WAY)
    public void onNaviSearchOnWay(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (collectCallbacks != null) {
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject3 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject3.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            jSONObject2.put(next, jSONObject3.get(next));
                        }
                        jSONObject.put(next, jSONObject3.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearchOnWay:searchInfo:" + jSONObject2 + ",speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearchOnWay(jSONObject2.toString(), jSONObject.toString());
            }
        }
    }

    @SpeechAnnotation(event = NaviEvent.CONTROL_NAVI_SEARCH_FILTER)
    public void onNaviSearchFilter(String str, String str2) {
        Object[] collectCallbacks = this.mListenerList.collectCallbacks();
        JSONObject jSONObject = new JSONObject();
        if (collectCallbacks != null) {
            String[] strArr = null;
            try {
                if (!TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject2 = new JSONObject(str2);
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        if (!"triggerApi".equals(next) && !"dialogueInfo".equals(next) && !"soundArea".equals(next)) {
                            if ("idList".equals(next)) {
                                JSONArray jSONArray = jSONObject2.getJSONArray("idList");
                                strArr = new String[jSONArray.length()];
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    strArr[i] = (String) jSONArray.get(i);
                                }
                            }
                        }
                        jSONObject.put(next, jSONObject2.get(next));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LogUtils.i("NaviNode", "onNaviSearchFilter:speechInfo:" + jSONObject);
            for (Object obj : collectCallbacks) {
                ((NaviListener) obj).onNaviSearchFilter(strArr, jSONObject.toString());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006d A[Catch: Exception -> 0x011f, TryCatch #0 {Exception -> 0x011f, blocks: (B:3:0x000a, B:6:0x0020, B:8:0x002f, B:10:0x0035, B:12:0x003b, B:13:0x0046, B:15:0x004c, B:16:0x0057, B:18:0x005d, B:20:0x0067, B:22:0x006d, B:24:0x007c, B:26:0x0082, B:28:0x0088, B:30:0x009b, B:32:0x00a1, B:33:0x00ac, B:35:0x00b2, B:37:0x00bf, B:38:0x00c6, B:29:0x0094, B:47:0x00ec, B:48:0x00f3, B:40:0x00cc, B:42:0x00d5, B:44:0x00db, B:45:0x00e0), top: B:51:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ec A[Catch: Exception -> 0x011f, TryCatch #0 {Exception -> 0x011f, blocks: (B:3:0x000a, B:6:0x0020, B:8:0x002f, B:10:0x0035, B:12:0x003b, B:13:0x0046, B:15:0x004c, B:16:0x0057, B:18:0x005d, B:20:0x0067, B:22:0x006d, B:24:0x007c, B:26:0x0082, B:28:0x0088, B:30:0x009b, B:32:0x00a1, B:33:0x00ac, B:35:0x00b2, B:37:0x00bf, B:38:0x00c6, B:29:0x0094, B:47:0x00ec, B:48:0x00f3, B:40:0x00cc, B:42:0x00d5, B:44:0x00db, B:45:0x00e0), top: B:51:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void syncPoiList(java.util.List<com.xiaopeng.speech.protocol.node.navi.bean.XpPoi> r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            r9 = this;
            java.lang.String r0 = "pageSize"
            java.lang.String r1 = "totalPage"
            java.lang.String r2 = "dialogueInfo"
            java.lang.String r3 = "totalSize"
            java.lang.String r4 = "triggerApi"
            com.xiaopeng.speech.protocol.bean.XpListWidget r5 = new com.xiaopeng.speech.protocol.bean.XpListWidget     // Catch: java.lang.Exception -> L11f
            r5.<init>()     // Catch: java.lang.Exception -> L11f
            com.xiaopeng.speech.protocol.bean.XpListTypeEnum r6 = com.xiaopeng.speech.protocol.bean.XpListTypeEnum.POI_LIST     // Catch: java.lang.Exception -> L11f
            r5.setListType(r6)     // Catch: java.lang.Exception -> L11f
            r5.setContents(r10)     // Catch: java.lang.Exception -> L11f
            boolean r6 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> L11f
            r7 = -1
            java.lang.String r8 = "soundArea"
            if (r6 != 0) goto L66
            com.google.gson.JsonParser r6 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L11f
            r6.<init>()     // Catch: java.lang.Exception -> L11f
            com.google.gson.JsonElement r12 = r6.parse(r12)     // Catch: java.lang.Exception -> L11f
            com.google.gson.JsonObject r12 = r12.getAsJsonObject()     // Catch: java.lang.Exception -> L11f
            if (r12 == 0) goto L66
            int r6 = r12.size()     // Catch: java.lang.Exception -> L11f
            if (r6 <= 0) goto L66
            boolean r6 = r12.has(r4)     // Catch: java.lang.Exception -> L11f
            if (r6 == 0) goto L46
            com.google.gson.JsonElement r4 = r12.get(r4)     // Catch: java.lang.Exception -> L11f
            java.lang.String r4 = r4.getAsString()     // Catch: java.lang.Exception -> L11f
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L11f
        L46:
            boolean r4 = r12.has(r2)     // Catch: java.lang.Exception -> L11f
            if (r4 == 0) goto L57
            com.google.gson.JsonElement r2 = r12.get(r2)     // Catch: java.lang.Exception -> L11f
            com.google.gson.JsonObject r2 = r2.getAsJsonObject()     // Catch: java.lang.Exception -> L11f
            r5.setDialogueInfo(r2)     // Catch: java.lang.Exception -> L11f
        L57:
            boolean r2 = r12.has(r8)     // Catch: java.lang.Exception -> L11f
            if (r2 == 0) goto L66
            com.google.gson.JsonElement r12 = r12.get(r8)     // Catch: java.lang.Exception -> L11f
            int r12 = r12.getAsInt()     // Catch: java.lang.Exception -> L11f
            goto L67
        L66:
            r12 = r7
        L67:
            boolean r2 = android.text.TextUtils.isEmpty(r13)     // Catch: java.lang.Exception -> L11f
            if (r2 != 0) goto Lca
            com.google.gson.JsonParser r2 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L11f
            r2.<init>()     // Catch: java.lang.Exception -> L11f
            com.google.gson.JsonElement r13 = r2.parse(r13)     // Catch: java.lang.Exception -> L11f
            com.google.gson.JsonObject r13 = r13.getAsJsonObject()     // Catch: java.lang.Exception -> L11f
            if (r13 == 0) goto Lea
            int r2 = r13.size()     // Catch: java.lang.Exception -> L11f
            if (r2 <= 0) goto Lea
            boolean r2 = r13.has(r3)     // Catch: java.lang.Exception -> L11f
            if (r2 == 0) goto L94
            com.google.gson.JsonElement r10 = r13.get(r3)     // Catch: java.lang.Exception -> L11f
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11f
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11f
            goto L9b
        L94:
            int r10 = r10.size()     // Catch: java.lang.Exception -> L11f
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11f
        L9b:
            boolean r10 = r13.has(r1)     // Catch: java.lang.Exception -> L11f
            if (r10 == 0) goto Lac
            com.google.gson.JsonElement r10 = r13.get(r1)     // Catch: java.lang.Exception -> L11f
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11f
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L11f
        Lac:
            boolean r10 = r13.has(r0)     // Catch: java.lang.Exception -> L11f
            if (r10 == 0) goto Lbd
            com.google.gson.JsonElement r10 = r13.get(r0)     // Catch: java.lang.Exception -> L11f
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11f
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L11f
        Lbd:
            if (r12 == r7) goto Lc6
            java.lang.Integer r10 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Exception -> L11f
            r13.addProperty(r8, r10)     // Catch: java.lang.Exception -> L11f
        Lc6:
            r5.setExtra(r13)     // Catch: java.lang.Exception -> L11f
            goto Lea
        Lca:
            if (r10 == 0) goto Ld3
            int r10 = r10.size()     // Catch: java.lang.Exception -> L11f
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11f
        Ld3:
            if (r12 == r7) goto Lea
            com.google.gson.JsonObject r10 = r5.getExtra()     // Catch: java.lang.Exception -> L11f
            if (r10 != 0) goto Le0
            com.google.gson.JsonObject r10 = new com.google.gson.JsonObject     // Catch: java.lang.Exception -> L11f
            r10.<init>()     // Catch: java.lang.Exception -> L11f
        Le0:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch: java.lang.Exception -> L11f
            r10.addProperty(r8, r12)     // Catch: java.lang.Exception -> L11f
            r5.setExtra(r10)     // Catch: java.lang.Exception -> L11f
        Lea:
            if (r11 == 0) goto Lf3
            java.lang.String r10 = r5.getTriggerApi()     // Catch: java.lang.Exception -> L11f
            android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L11f
        Lf3:
            com.google.gson.Gson r10 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L11f
            r10.<init>()     // Catch: java.lang.Exception -> L11f
            java.lang.String r10 = r10.toJson(r5)     // Catch: java.lang.Exception -> L11f
            java.lang.String r11 = "NaviNode"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L11f
            r12.<init>()     // Catch: java.lang.Exception -> L11f
            java.lang.String r13 = "syncPoiList:"
            r12.append(r13)     // Catch: java.lang.Exception -> L11f
            r12.append(r10)     // Catch: java.lang.Exception -> L11f
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> L11f
            com.xiaopeng.speech.common.util.LogUtils.i(r11, r12)     // Catch: java.lang.Exception -> L11f
            com.xiaopeng.speech.SpeechClient r11 = com.xiaopeng.speech.SpeechClient.instance()     // Catch: java.lang.Exception -> L11f
            com.xiaopeng.speech.proxy.AgentProxy r11 = r11.getAgent()     // Catch: java.lang.Exception -> L11f
            java.lang.String r12 = "list.upload"
            r11.triggerEvent(r12, r10)     // Catch: java.lang.Exception -> L11f
        L11f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.syncPoiList(java.util.List, java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0073 A[Catch: Exception -> 0x011c, TryCatch #0 {Exception -> 0x011c, blocks: (B:3:0x000a, B:6:0x0020, B:8:0x002f, B:10:0x0035, B:12:0x003b, B:14:0x004c, B:16:0x0052, B:17:0x005d, B:19:0x0063, B:21:0x006d, B:23:0x0073, B:25:0x0082, B:27:0x0088, B:29:0x008e, B:31:0x00a1, B:33:0x00a7, B:34:0x00b2, B:36:0x00b8, B:38:0x00c5, B:39:0x00cc, B:30:0x009a, B:47:0x00f0, B:41:0x00d2, B:43:0x00db, B:45:0x00e1, B:46:0x00e6, B:13:0x0047), top: B:50:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void syncRouteList(java.util.List<com.xiaopeng.speech.protocol.node.navi.bean.XpRoute> r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.String r0 = "pageSize"
            java.lang.String r1 = "totalPage"
            java.lang.String r2 = "dialogueInfo"
            java.lang.String r3 = "totalSize"
            java.lang.String r4 = "triggerApi"
            com.xiaopeng.speech.protocol.bean.XpListWidget r5 = new com.xiaopeng.speech.protocol.bean.XpListWidget     // Catch: java.lang.Exception -> L11c
            r5.<init>()     // Catch: java.lang.Exception -> L11c
            com.xiaopeng.speech.protocol.bean.XpListTypeEnum r6 = com.xiaopeng.speech.protocol.bean.XpListTypeEnum.ROUTE_LIST     // Catch: java.lang.Exception -> L11c
            r5.setListType(r6)     // Catch: java.lang.Exception -> L11c
            r5.setContents(r10)     // Catch: java.lang.Exception -> L11c
            boolean r6 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Exception -> L11c
            r7 = -1
            java.lang.String r8 = "soundArea"
            if (r6 != 0) goto L6c
            com.google.gson.JsonParser r6 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L11c
            r6.<init>()     // Catch: java.lang.Exception -> L11c
            com.google.gson.JsonElement r11 = r6.parse(r11)     // Catch: java.lang.Exception -> L11c
            com.google.gson.JsonObject r11 = r11.getAsJsonObject()     // Catch: java.lang.Exception -> L11c
            if (r11 == 0) goto L6c
            int r6 = r11.size()     // Catch: java.lang.Exception -> L11c
            if (r6 <= 0) goto L6c
            boolean r6 = r11.has(r4)     // Catch: java.lang.Exception -> L11c
            if (r6 == 0) goto L47
            com.google.gson.JsonElement r4 = r11.get(r4)     // Catch: java.lang.Exception -> L11c
            java.lang.String r4 = r4.getAsString()     // Catch: java.lang.Exception -> L11c
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L11c
            goto L4c
        L47:
            java.lang.String r4 = "NavigationAutoRoute"
            r5.setTriggerApi(r4)     // Catch: java.lang.Exception -> L11c
        L4c:
            boolean r4 = r11.has(r2)     // Catch: java.lang.Exception -> L11c
            if (r4 == 0) goto L5d
            com.google.gson.JsonElement r2 = r11.get(r2)     // Catch: java.lang.Exception -> L11c
            com.google.gson.JsonObject r2 = r2.getAsJsonObject()     // Catch: java.lang.Exception -> L11c
            r5.setDialogueInfo(r2)     // Catch: java.lang.Exception -> L11c
        L5d:
            boolean r2 = r11.has(r8)     // Catch: java.lang.Exception -> L11c
            if (r2 == 0) goto L6c
            com.google.gson.JsonElement r11 = r11.get(r8)     // Catch: java.lang.Exception -> L11c
            int r11 = r11.getAsInt()     // Catch: java.lang.Exception -> L11c
            goto L6d
        L6c:
            r11 = r7
        L6d:
            boolean r2 = android.text.TextUtils.isEmpty(r12)     // Catch: java.lang.Exception -> L11c
            if (r2 != 0) goto Ld0
            com.google.gson.JsonParser r2 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> L11c
            r2.<init>()     // Catch: java.lang.Exception -> L11c
            com.google.gson.JsonElement r12 = r2.parse(r12)     // Catch: java.lang.Exception -> L11c
            com.google.gson.JsonObject r12 = r12.getAsJsonObject()     // Catch: java.lang.Exception -> L11c
            if (r12 == 0) goto Lf0
            int r2 = r12.size()     // Catch: java.lang.Exception -> L11c
            if (r2 <= 0) goto Lf0
            boolean r2 = r12.has(r3)     // Catch: java.lang.Exception -> L11c
            if (r2 == 0) goto L9a
            com.google.gson.JsonElement r10 = r12.get(r3)     // Catch: java.lang.Exception -> L11c
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11c
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11c
            goto La1
        L9a:
            int r10 = r10.size()     // Catch: java.lang.Exception -> L11c
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11c
        La1:
            boolean r10 = r12.has(r1)     // Catch: java.lang.Exception -> L11c
            if (r10 == 0) goto Lb2
            com.google.gson.JsonElement r10 = r12.get(r1)     // Catch: java.lang.Exception -> L11c
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11c
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L11c
        Lb2:
            boolean r10 = r12.has(r0)     // Catch: java.lang.Exception -> L11c
            if (r10 == 0) goto Lc3
            com.google.gson.JsonElement r10 = r12.get(r0)     // Catch: java.lang.Exception -> L11c
            int r10 = r10.getAsInt()     // Catch: java.lang.Exception -> L11c
            r5.setTotalPage(r10)     // Catch: java.lang.Exception -> L11c
        Lc3:
            if (r11 == r7) goto Lcc
            java.lang.Integer r10 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Exception -> L11c
            r12.addProperty(r8, r10)     // Catch: java.lang.Exception -> L11c
        Lcc:
            r5.setExtra(r12)     // Catch: java.lang.Exception -> L11c
            goto Lf0
        Ld0:
            if (r10 == 0) goto Ld9
            int r10 = r10.size()     // Catch: java.lang.Exception -> L11c
            r5.setTotalSize(r10)     // Catch: java.lang.Exception -> L11c
        Ld9:
            if (r11 == r7) goto Lf0
            com.google.gson.JsonObject r10 = r5.getExtra()     // Catch: java.lang.Exception -> L11c
            if (r10 != 0) goto Le6
            com.google.gson.JsonObject r10 = new com.google.gson.JsonObject     // Catch: java.lang.Exception -> L11c
            r10.<init>()     // Catch: java.lang.Exception -> L11c
        Le6:
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch: java.lang.Exception -> L11c
            r10.addProperty(r8, r11)     // Catch: java.lang.Exception -> L11c
            r5.setExtra(r10)     // Catch: java.lang.Exception -> L11c
        Lf0:
            com.google.gson.Gson r10 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L11c
            r10.<init>()     // Catch: java.lang.Exception -> L11c
            java.lang.String r10 = r10.toJson(r5)     // Catch: java.lang.Exception -> L11c
            java.lang.String r11 = "NaviNode"
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L11c
            r12.<init>()     // Catch: java.lang.Exception -> L11c
            java.lang.String r0 = "syncRouteList:"
            r12.append(r0)     // Catch: java.lang.Exception -> L11c
            r12.append(r10)     // Catch: java.lang.Exception -> L11c
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Exception -> L11c
            com.xiaopeng.speech.common.util.LogUtils.i(r11, r12)     // Catch: java.lang.Exception -> L11c
            com.xiaopeng.speech.SpeechClient r11 = com.xiaopeng.speech.SpeechClient.instance()     // Catch: java.lang.Exception -> L11c
            com.xiaopeng.speech.proxy.AgentProxy r11 = r11.getAgent()     // Catch: java.lang.Exception -> L11c
            java.lang.String r12 = "list.upload"
            r11.triggerEvent(r12, r10)     // Catch: java.lang.Exception -> L11c
        L11c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.speech.protocol.node.navi.NaviNode.syncRouteList(java.util.List, java.lang.String, java.lang.String):void");
    }

    public void onListDisappear(String str, String str2, String str3) {
        String xpListTypeEnum;
        try {
            JSONObject jSONObject = new JSONObject();
            if ("poi".equals(str)) {
                xpListTypeEnum = XpListTypeEnum.POI_LIST.toString();
            } else {
                xpListTypeEnum = XpListTypeEnum.ROUTE_LIST.toString();
            }
            jSONObject.put("listType", xpListTypeEnum);
            jSONObject.put("speechInfo", str2);
            jSONObject.put(SpeechWidget.WIDGET_EXTRA, str3);
            SpeechClient.instance().getAgent().sendUIEvent(FeedUIEvent.LIST_DISAPPEAR, jSONObject.toString());
        } catch (Exception unused) {
        }
    }

    public void subscribeMultiTask(String str) {
        SpeechClient.instance().getWakeupEngine().subscribeMultiTask(str, this.binder);
    }
}
