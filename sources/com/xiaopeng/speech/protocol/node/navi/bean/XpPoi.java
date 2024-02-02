package com.xiaopeng.speech.protocol.node.navi.bean;
/* loaded from: classes2.dex */
public class XpPoi implements XpBaseContent<XpPoi> {
    private String address;
    private double distance = 0.0d;
    private String id;
    private double lat;
    private double lon;
    private String name;
    private double naviLat;
    private double naviLon;

    @Override // java.lang.Comparable
    public int compareTo(XpPoi xpPoi) {
        return (int) (this.distance - xpPoi.distance);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public double getLon() {
        return this.lon;
    }

    public double getLat() {
        return this.lat;
    }

    public double getNaviLon() {
        return this.naviLon;
    }

    public double getNaviLat() {
        return this.naviLat;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setLon(double d) {
        this.lon = d;
    }

    public void setLat(double d) {
        this.lat = d;
    }

    public void setNaviLon(double d) {
        this.naviLon = d;
    }

    public void setNaviLat(double d) {
        this.naviLat = d;
    }

    public void setDistance(double d) {
        this.distance = d;
    }
}
