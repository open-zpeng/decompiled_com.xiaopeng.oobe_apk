package com.xiaopeng.speech.protocol.node.navi;

import com.xiaopeng.speech.INodeListener;
import com.xiaopeng.speech.protocol.node.navi.bean.AddressBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NaviPreferenceBean;
import com.xiaopeng.speech.protocol.node.navi.bean.NearbySearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PathBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiBean;
import com.xiaopeng.speech.protocol.node.navi.bean.PoiSearchBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectParkingBean;
import com.xiaopeng.speech.protocol.node.navi.bean.SelectRouteBean;
import com.xiaopeng.speech.protocol.node.navi.bean.StartNaviBean;
import com.xiaopeng.speech.protocol.node.navi.bean.WaypointSearchBean;
/* loaded from: classes2.dex */
public interface NaviListener extends INodeListener {
    void onAddressGet(AddressBean addressBean);

    void onAddressSet(AddressBean addressBean, PoiBean poiBean);

    default void onAlertsPreferenceSet(NaviPreferenceBean naviPreferenceBean) {
    }

    default void onAutoRerouteAskFirst() {
    }

    default void onAutoRerouteBetterRoute() {
    }

    default void onAutoRerouteNever() {
    }

    default void onAvoidRouteSet(NaviPreferenceBean naviPreferenceBean) {
    }

    void onCloseTraffic();

    void onConfirmCancel();

    void onConfirmOk();

    default void onControlBridge3DMap() {
    }

    default void onControlBridge3DMapOff() {
    }

    void onControlChargeClose();

    void onControlChargeOpen();

    default void onControlChargeServiceOpen(String str) {
    }

    void onControlClose();

    void onControlCloseRibbonMap();

    void onControlCloseSmallMap();

    default void onControlCommuterRouteShow() {
    }

    default void onControlCommuterRouteShowOff() {
    }

    default void onControlDashboardDisplay2DHeadUp() {
    }

    default void onControlDashboardDisplay3DHeadUp() {
    }

    void onControlDisPlayCar();

    void onControlDisPlayNorth();

    default void onControlDisplay2DHeadUp() {
    }

    void onControlDisplay3D();

    default void onControlDisplay3DHeadUp() {
    }

    default void onControlDriveChargeLess() {
    }

    default void onControlDriveChargeLessOff() {
    }

    void onControlFavoriteClose();

    void onControlFavoriteOpen();

    default void onControlGaodeAccountPageOpen(int i) {
    }

    default void onControlHighroadFirst() {
    }

    default void onControlHighroadFirstOff() {
    }

    void onControlHistory();

    default void onControlHistoryClose() {
    }

    void onControlOpenRibbonMap();

    void onControlOpenSmallMap();

    void onControlOverviewClose();

    void onControlOverviewOpen();

    default void onControlParkRecommendOff() {
    }

    default void onControlParkRecommendOn() {
    }

    default void onControlRealtimeTraffic() {
    }

    default void onControlRealtimeTrafficOff() {
    }

    void onControlRoadAhead();

    void onControlRoadAheadOff();

    void onControlSecurityRemind();

    void onControlSecurityRemindOff();

    default void onControlSettingsClose() {
    }

    void onControlSettingsOpen();

    default void onControlSmartChargingRoute() {
    }

    default void onControlSmartChargingRouteOff() {
    }

    default void onControlSmartRecommend() {
    }

    default void onControlSmartRecommendOff() {
    }

    void onControlSmartScale();

    void onControlSmartScaleOff();

    default void onControlSpeechCruise() {
    }

    default void onControlSpeechCruiseOff() {
    }

    void onControlSpeechDetail();

    void onControlSpeechEye();

    void onControlSpeechEyeOff();

    void onControlSpeechGeneral();

    void onControlSpeechMute();

    void onControlSpeechSimple();

    default void onControlSpeechStandard() {
    }

    void onControlSpeechSuperSimple();

    void onControlStart(StartNaviBean startNaviBean);

    default void onControlTimeFirst() {
    }

    default void onControlTimeFirstOff() {
    }

    default void onControlTrafficIncident() {
    }

    default void onControlTrafficIncidentOff() {
    }

    void onControlVolOff(boolean z, int i);

    void onControlVolOn(boolean z, int i);

    void onControlWaypointStart(PathBean pathBean);

    void onDataControlDisplay3dTts();

    void onDataControlDisplayCarTts();

    void onDataControlDisplayNorthTts();

    void onDriveAdvoidTrafficControl();

    void onDriveAvoidCharge();

    void onDriveAvoidChargeOff();

    void onDriveAvoidCongestion();

    void onDriveAvoidCongestionOff();

    void onDriveAvoidControls();

    void onDriveAvoidControlsOff();

    void onDriveHighwayFirst();

    void onDriveHighwayFirstOff();

    void onDriveHighwayNo();

    void onDriveHighwayNoOff();

    void onDriveRadarRoute();

    void onDriveRadarRouteOff();

    void onGetSettingsInfo();

    default void onListItemSelected(int i) {
    }

    void onMainRoad();

    void onMapEnterFindPath();

    void onMapExitFindPath();

    void onMapOverview();

    default void onMapShowSet(NaviPreferenceBean naviPreferenceBean) {
    }

    void onMapZoomIn();

    void onMapZoomOut();

    void onMapZoominMax();

    void onMapZoomoutMin();

    default void onNaviSearch(String str, String str2) {
    }

    default void onNaviSearchFilter(String[] strArr, String str) {
    }

    default void onNaviSearchOnWay(String str, String str2) {
    }

    boolean onNavigatingGet();

    void onNearbySearch(NearbySearchBean nearbySearchBean);

    void onOpenTraffic();

    void onParkingSelect(SelectParkingBean selectParkingBean);

    default void onPoiDetailsFavoriteAdd() {
    }

    default void onPoiDetailsFavoriteDel() {
    }

    void onPoiSearch(PoiSearchBean poiSearchBean);

    void onRouteNearbySearch(NearbySearchBean nearbySearchBean);

    void onRouteSelect(SelectRouteBean selectRouteBean);

    void onSearchClose();

    void onSelectParkingCount(SelectParkingBean selectParkingBean);

    void onSelectRouteCount(SelectRouteBean selectRouteBean);

    default void onSetOritentionMetre(double d, int i) {
    }

    default void onSetScaleLevel(int i) {
    }

    void onSideRoad();

    void onWaypointSearch(WaypointSearchBean waypointSearchBean);
}
