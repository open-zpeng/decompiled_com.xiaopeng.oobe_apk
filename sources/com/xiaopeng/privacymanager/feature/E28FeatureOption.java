package com.xiaopeng.privacymanager.feature;

import com.xiaopeng.libconfig.ipc.IpcConfig;
/* loaded from: classes.dex */
public class E28FeatureOption extends BaseFeatureOption {
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
        return IpcConfig.App.APP_PRIVACY_SERVICE;
    }

    @Override // com.xiaopeng.privacymanager.feature.BaseFeatureOption
    public String getPrivacySettingServer() {
        return "com.xiaopeng.privacyservice.PrivacySettingsServer";
    }
}
