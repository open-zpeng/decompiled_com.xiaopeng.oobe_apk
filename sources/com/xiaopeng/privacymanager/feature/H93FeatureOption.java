package com.xiaopeng.privacymanager.feature;
/* loaded from: classes.dex */
public class H93FeatureOption extends BaseFeatureOption {
    @Override // com.xiaopeng.privacymanager.feature.BaseFeatureOption
    public String getContentProviderAuthority() {
        return "com.xiaopeng.privacyservice.provider";
    }

    @Override // com.xiaopeng.privacymanager.feature.BaseFeatureOption
    public String getPrivacyServiceAction() {
        return "com.xiaopeng.privacyservice.PrivacyService";
    }

    @Override // com.xiaopeng.privacymanager.feature.BaseFeatureOption
    public String getPrivacyServicePackage() {
        return "com.xiaopeng.privacycentral";
    }

    @Override // com.xiaopeng.privacymanager.feature.BaseFeatureOption
    public String getPrivacySettingServer() {
        return "com.xiaopeng.privacycentral.PrivacySettingsServer";
    }
}
