package com.xiaopeng.speech.protocol.bean;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
public class XpListWidget<T extends Serializable> implements Serializable {
    private JsonObject dialogueInfo;
    private JsonObject extra;
    private XpListTypeEnum listType;
    private String triggerApi;
    private List<T> contents = new ArrayList();
    private int totalSize = 0;
    private int pageSize = 0;
    private int totalPage = 0;

    public JsonObject getExtra() {
        return this.extra;
    }

    public void setExtra(JsonObject jsonObject) {
        this.extra = jsonObject;
    }

    public void setTriggerApi(String str) {
        this.triggerApi = str;
    }

    public void setDialogueInfo(JsonObject jsonObject) {
        this.dialogueInfo = jsonObject;
    }

    public void setListType(XpListTypeEnum xpListTypeEnum) {
        this.listType = xpListTypeEnum;
    }

    public void setContents(List<T> list) {
        this.contents = list;
    }

    public void setTotalSize(int i) {
        this.totalSize = i;
    }

    public void setPageSize(int i) {
        this.pageSize = i;
    }

    public void setTotalPage(int i) {
        this.totalPage = i;
    }

    public String getTriggerApi() {
        return this.triggerApi;
    }

    public JsonObject getDialogueInfo() {
        return this.dialogueInfo;
    }
}
