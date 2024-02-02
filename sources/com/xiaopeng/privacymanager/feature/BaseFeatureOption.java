package com.xiaopeng.privacymanager.feature;
/* loaded from: classes.dex */
public abstract class BaseFeatureOption {
    private static final String CAR_CDU_TYPE_QB_H93 = "QB";
    public static final String TAG = "BaseFeatureOption";

    public String getContentProviderAuthority() {
        return null;
    }

    public String getPrivacyServiceAction() {
        return null;
    }

    public String getPrivacyServicePackage() {
        return null;
    }

    public String getPrivacySettingServer() {
        return null;
    }

    static /* synthetic */ BaseFeatureOption access$000() {
        return createFeatureOption();
    }

    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static final BaseFeatureOption sInstance = BaseFeatureOption.access$000();

        private SingletonHolder() {
        }
    }

    public static BaseFeatureOption getInstance() {
        return SingletonHolder.sInstance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r0.equals("Q3") != false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getCarCduType() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "ro.xiaopeng.software"
            java.lang.String r1 = android.os.SystemProperties.get(r1, r0)
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lf
            return r1
        Lf:
            r0 = 5
            r2 = 7
            java.lang.String r0 = r1.substring(r0, r2)
            r1 = 0
            java.lang.String r2 = "persist.sys.xiaopeng.extendedCarType"
            int r2 = android.os.SystemProperties.getInt(r2, r1)
            r3 = 1
            if (r2 != r3) goto L4b
            r2 = -1
            int r4 = r0.hashCode()
            r5 = 2562(0xa02, float:3.59E-42)
            if (r4 == r5) goto L37
            r1 = 2566(0xa06, float:3.596E-42)
            if (r4 == r1) goto L2d
            goto L40
        L2d:
            java.lang.String r1 = "Q7"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L40
            r1 = r3
            goto L41
        L37:
            java.lang.String r4 = "Q3"
            boolean r4 = r0.equals(r4)
            if (r4 == 0) goto L40
            goto L41
        L40:
            r1 = r2
        L41:
            if (r1 == 0) goto L49
            if (r1 == r3) goto L46
            goto L4b
        L46:
            java.lang.String r0 = "Q7A"
            return r0
        L49:
            java.lang.String r0 = "Q3A"
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.privacymanager.feature.BaseFeatureOption.getCarCduType():java.lang.String");
    }

    private static BaseFeatureOption createFeatureOption() {
        String carCduType = getCarCduType();
        if (((carCduType.hashCode() == 2577 && carCduType.equals(CAR_CDU_TYPE_QB_H93)) ? (char) 0 : (char) 65535) == 0) {
            return new H93FeatureOption();
        }
        return new E28FeatureOption();
    }
}
