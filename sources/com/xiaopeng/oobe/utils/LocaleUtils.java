package com.xiaopeng.oobe.utils;

import java.util.Locale;
/* loaded from: classes.dex */
public class LocaleUtils {
    public static Locale getLanLocale(String str) {
        if (str == null) {
            return null;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1764554162:
                if (str.equals("Norwegian")) {
                    c = 0;
                    break;
                }
                break;
            case -517823520:
                if (str.equals("Italian")) {
                    c = 4;
                    break;
                }
                break;
            case -143377541:
                if (str.equals("Swedish")) {
                    c = 6;
                    break;
                }
                break;
            case 60895824:
                if (str.equals("English")) {
                    c = 7;
                    break;
                }
                break;
            case 66399624:
                if (str.equals("Dutch")) {
                    c = 5;
                    break;
                }
                break;
            case 2039745389:
                if (str.equals("Danish")) {
                    c = 1;
                    break;
                }
                break;
            case 2112439738:
                if (str.equals("French")) {
                    c = 3;
                    break;
                }
                break;
            case 2129449382:
                if (str.equals("German")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new Locale("nb");
            case 1:
                return new Locale("da");
            case 2:
                return new Locale("de");
            case 3:
                return new Locale("fr");
            case 4:
                return new Locale("it");
            case 5:
                return new Locale("nl");
            case 6:
                return new Locale("sv");
            default:
                return new Locale("en");
        }
    }

    public static String getRegion(String str) {
        if (str == null) {
            return "NO";
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1955869026:
                if (str.equals("Norway")) {
                    c = '\n';
                    break;
                }
                break;
            case -1805740532:
                if (str.equals("Sweden")) {
                    c = '\b';
                    break;
                }
                break;
            case -1726992506:
                if (str.equals("Luxembourg")) {
                    c = 6;
                    break;
                }
                break;
            case -1628560509:
                if (str.equals("Switzerland")) {
                    c = '\t';
                    break;
                }
                break;
            case -1077783494:
                if (str.equals("Denmark")) {
                    c = 2;
                    break;
                }
                break;
            case -928898448:
                if (str.equals("Netherlands")) {
                    c = 7;
                    break;
                }
                break;
            case 70969475:
                if (str.equals("Italy")) {
                    c = 5;
                    break;
                }
                break;
            case 1017581365:
                if (str.equals("Austria")) {
                    c = 0;
                    break;
                }
                break;
            case 1440158435:
                if (str.equals("Belgium")) {
                    c = 1;
                    break;
                }
                break;
            case 1588421523:
                if (str.equals("Germany")) {
                    c = 4;
                    break;
                }
                break;
            case 2112320571:
                if (str.equals("France")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "AT";
            case 1:
                return "BE";
            case 2:
                return "DK";
            case 3:
                return "FR";
            case 4:
                return "DE";
            case 5:
                return "IT";
            case 6:
                return "LU";
            case 7:
                return "NL";
            case '\b':
                return "SE";
            case '\t':
                return "CH";
            default:
                return "NO";
        }
    }

    public static String getLanguage(String str) {
        if (str == null) {
            return "en";
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1764554162:
                if (str.equals("Norwegian")) {
                    c = 0;
                    break;
                }
                break;
            case -517823520:
                if (str.equals("Italian")) {
                    c = 5;
                    break;
                }
                break;
            case -143377541:
                if (str.equals("Swedish")) {
                    c = 6;
                    break;
                }
                break;
            case 60895824:
                if (str.equals("English")) {
                    c = 7;
                    break;
                }
                break;
            case 66399624:
                if (str.equals("Dutch")) {
                    c = 2;
                    break;
                }
                break;
            case 2039745389:
                if (str.equals("Danish")) {
                    c = 1;
                    break;
                }
                break;
            case 2112439738:
                if (str.equals("French")) {
                    c = 3;
                    break;
                }
                break;
            case 2129449382:
                if (str.equals("German")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "nb";
            case 1:
                return "da";
            case 2:
                return "nl";
            case 3:
                return "fr";
            case 4:
                return "de";
            case 5:
                return "it";
            case 6:
                return "sv";
            default:
                return "en";
        }
    }
}
