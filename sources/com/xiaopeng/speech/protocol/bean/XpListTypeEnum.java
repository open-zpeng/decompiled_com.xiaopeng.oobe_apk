package com.xiaopeng.speech.protocol.bean;
/* loaded from: classes2.dex */
public enum XpListTypeEnum {
    POI_LIST("poiList", "常规POI列表"),
    CHARGING_LIST("chargingList", "充电站列表"),
    ROUTE_LIST("routeList", "路线列表"),
    CONTACT_LIST("contactList", "联系人列表");
    
    String description;
    String name;

    XpListTypeEnum(String str, String str2) {
        this.name = str;
        this.description = str2;
    }

    public String getName() {
        return this.name;
    }
}
