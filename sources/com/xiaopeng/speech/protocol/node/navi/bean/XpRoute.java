package com.xiaopeng.speech.protocol.node.navi.bean;
/* loaded from: classes2.dex */
public class XpRoute implements XpBaseContent<XpRoute> {
    private boolean isChargingRoute = false;
    private String routeTypeName;
    private String routeTypeNo;

    @Override // java.lang.Comparable
    public int compareTo(XpRoute xpRoute) {
        return 0;
    }

    public String getRouteTypeNo() {
        return this.routeTypeNo;
    }

    public String getRouteTypeName() {
        return this.routeTypeName;
    }

    public boolean isChargingRoute() {
        return this.isChargingRoute;
    }

    public void setRouteTypeNo(String str) {
        this.routeTypeNo = str;
    }

    public void setRouteTypeName(String str) {
        this.routeTypeName = str;
    }

    public void setChargingRoute(boolean z) {
        this.isChargingRoute = z;
    }
}
