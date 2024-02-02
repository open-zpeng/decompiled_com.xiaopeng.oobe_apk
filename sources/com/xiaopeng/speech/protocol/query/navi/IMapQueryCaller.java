package com.xiaopeng.speech.protocol.query.navi;

import com.xiaopeng.speech.IQueryCaller;
import com.xiaopeng.speech.protocol.node.navi.bean.AddressBean;
/* loaded from: classes2.dex */
public interface IMapQueryCaller extends IQueryCaller {
    String getCommonAddress(AddressBean addressBean);

    default int getCurrentScaleLevel() {
        return -1;
    }

    int getFavoriteOpenStatus();

    default int getListBottom() {
        return -1;
    }

    default int getListPageInfo() {
        return -1;
    }

    default int getListTop() {
        return -1;
    }

    String getNavigationInfo();

    int getOpenControlStatus();

    default int getPoiDetailsFavoriteStatus() {
        return -1;
    }

    int getSwitchMainRoadStatus();

    int getSwitchSideRoadStatus();

    boolean isCalculatePath();

    boolean isCruise();

    boolean isExplorePath();

    default boolean isGaodeLogin() {
        return false;
    }

    default boolean isNaviSearching() {
        return false;
    }

    default boolean isNaviSpeechCruiseOpened() {
        return false;
    }

    boolean isNavigation();

    default boolean isSRMap() {
        return false;
    }

    boolean isZoominMax();

    boolean isZoomoutMin();
}
