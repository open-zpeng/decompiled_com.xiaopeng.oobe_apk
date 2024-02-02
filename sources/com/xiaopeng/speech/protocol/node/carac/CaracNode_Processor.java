package com.xiaopeng.speech.protocol.node.carac;

import com.xiaopeng.speech.annotation.ICommandProcessor;
import com.xiaopeng.speech.protocol.event.CaracEvent;
/* loaded from: classes2.dex */
public class CaracNode_Processor implements ICommandProcessor {
    private CaracNode mTarget;

    public CaracNode_Processor(CaracNode caracNode) {
        this.mTarget = caracNode;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public void performCommand(String str, String str2) {
        char c;
        switch (str.hashCode()) {
            case -2049213332:
                if (str.equals(CaracEvent.TEMP_PASSENGER_UP)) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -2031004713:
                if (str.equals(CaracEvent.MODES_ECO_ON)) {
                    c = 'J';
                    break;
                }
                c = 65535;
                break;
            case -1997714566:
                if (str.equals(CaracEvent.TEMP_DUAL_OFF)) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -1997710125:
                if (str.equals(CaracEvent.TEMP_DUAL_SYN)) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -1982458101:
                if (str.equals(CaracEvent.TEMP_DRIVER_DOWN)) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -1877455440:
                if (str.equals(CaracEvent.OPEN_FAST_FRIDGE)) {
                    c = '=';
                    break;
                }
                c = 65535;
                break;
            case -1754019960:
                if (str.equals(CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_CLOSE)) {
                    c = ']';
                    break;
                }
                c = 65535;
                break;
            case -1659193971:
                if (str.equals(CaracEvent.AC_LEFT_REAR_SEAT_HEAT_SET)) {
                    c = '^';
                    break;
                }
                c = 65535;
                break;
            case -1611128182:
                if (str.equals(CaracEvent.SEAT_HEATING_DOWN)) {
                    c = '1';
                    break;
                }
                c = 65535;
                break;
            case -1610800078:
                if (str.equals(CaracEvent.SEAT_HEATING_OPEN)) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case -1520877577:
                if (str.equals(CaracEvent.WIND_AUTO_SWEEP_OFF)) {
                    c = 'G';
                    break;
                }
                c = 65535;
                break;
            case -1495695801:
                if (str.equals(CaracEvent.OPEN_AC_PANEL)) {
                    c = 'S';
                    break;
                }
                c = 65535;
                break;
            case -1474634240:
                if (str.equals(CaracEvent.HEAD_WINDOW_OFF)) {
                    c = 'f';
                    break;
                }
                c = 65535;
                break;
            case -1402704680:
                if (str.equals(CaracEvent.WIND_UNIDIRECTION)) {
                    c = 'B';
                    break;
                }
                c = 65535;
                break;
            case -1379253628:
                if (str.equals(CaracEvent.EXIT_FAST_FRIDGE)) {
                    c = '?';
                    break;
                }
                c = 65535;
                break;
            case -1179765898:
                if (str.equals(CaracEvent.OPEN_AC_INTELLIGENT_PSN_ON)) {
                    c = 'T';
                    break;
                }
                c = 65535;
                break;
            case -1143304767:
                if (str.equals(CaracEvent.SEAT_HEAT_PASSENGER_UP)) {
                    c = ';';
                    break;
                }
                c = 65535;
                break;
            case -1125717353:
                if (str.equals(CaracEvent.OUTLET_OFF)) {
                    c = 'Y';
                    break;
                }
                c = 65535;
                break;
            case -1082711556:
                if (str.equals(CaracEvent.SEAT_HEAT_PASSENGER_SET)) {
                    c = ':';
                    break;
                }
                c = 65535;
                break;
            case -1067108253:
                if (str.equals(CaracEvent.AC_LEFT_REAR_SEAT_HEAT_CLOSE)) {
                    c = '\\';
                    break;
                }
                c = 65535;
                break;
            case -1064721728:
                if (str.equals(CaracEvent.HVAC_ON)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1054854969:
                if (str.equals(CaracEvent.TEMP_MAX)) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -1054854731:
                if (str.equals(CaracEvent.TEMP_MIN)) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -1054849083:
                if (str.equals(CaracEvent.TEMP_SET)) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1032797908:
                if (str.equals(CaracEvent.DEMIST_FOOT_ON)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -997211612:
                if (str.equals(CaracEvent.WIND_UP)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -970874129:
                if (str.equals(CaracEvent.PURIFIER_OPEN)) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -933998679:
                if (str.equals(CaracEvent.NATURE_ON)) {
                    c = 'N';
                    break;
                }
                c = 65535;
                break;
            case -902370839:
                if (str.equals(CaracEvent.SEAT_PSN_HEAT_CLOSE)) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case -848796933:
                if (str.equals(CaracEvent.WIND_MAX)) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -848796695:
                if (str.equals(CaracEvent.WIND_MIN)) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -848791047:
                if (str.equals(CaracEvent.WIND_SET)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -794660614:
                if (str.equals(CaracEvent.INNER_OFF)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -686815348:
                if (str.equals(CaracEvent.DEMIST_FRONT_ON)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -684339037:
                if (str.equals(CaracEvent.OPEN_FRESH_AIR)) {
                    c = '>';
                    break;
                }
                c = 65535;
                break;
            case -543155733:
                if (str.equals(CaracEvent.WIND_DOWN)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -543093835:
                if (str.equals(CaracEvent.WIND_FREE)) {
                    c = 'E';
                    break;
                }
                c = 65535;
                break;
            case -533834088:
                if (str.equals(CaracEvent.HEATING_ON)) {
                    c = 'L';
                    break;
                }
                c = 65535;
                break;
            case -530897568:
                if (str.equals(CaracEvent.AC_OFF)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -515974873:
                if (str.equals(CaracEvent.AQS_ON)) {
                    c = 'H';
                    break;
                }
                c = 65535;
                break;
            case -363842875:
                if (str.equals(CaracEvent.MIRROR_ON)) {
                    c = 'V';
                    break;
                }
                c = 65535;
                break;
            case -324663506:
                if (str.equals(CaracEvent.HEAD_WINDOW_ON)) {
                    c = 'd';
                    break;
                }
                c = 65535;
                break;
            case -299710995:
                if (str.equals(CaracEvent.DEMIST_REAR_OFF)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -202489381:
                if (str.equals(CaracEvent.TEMP_DRIVE_MAX)) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case -202489143:
                if (str.equals(CaracEvent.TEMP_DRIVE_MIN)) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case -202483495:
                if (str.equals(CaracEvent.TEMP_DRIVER_SET)) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -43518477:
                if (str.equals(CaracEvent.PURIFIER_CLOSE)) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -28747719:
                if (str.equals(CaracEvent.SEAT_PSN_HEAT_OPEN)) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case 54152895:
                if (str.equals(CaracEvent.SEAT_VENTILATE_ON)) {
                    c = '2';
                    break;
                }
                c = 65535;
                break;
            case 54153083:
                if (str.equals(CaracEvent.SEAT_VENTILATE_UP)) {
                    c = '7';
                    break;
                }
                c = 65535;
                break;
            case 67141408:
                if (str.equals(CaracEvent.BLOW_FOOT_ON)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 104157399:
                if (str.equals(CaracEvent.AC_LEFT_REAR_SEAT_HEAT_DOWN)) {
                    c = 'b';
                    break;
                }
                c = 65535;
                break;
            case 104485503:
                if (str.equals(CaracEvent.AC_LEFT_REAR_SEAT_HEAT_OPEN)) {
                    c = 'Z';
                    break;
                }
                c = 65535;
                break;
            case 183560546:
                if (str.equals(CaracEvent.DEMIST_FRONT_OFF)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 339726725:
                if (str.equals(CaracEvent.REAR_HEAT_ON)) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 343152168:
                if (str.equals(CaracEvent.WIND_EVAD)) {
                    c = 'C';
                    break;
                }
                c = 65535;
                break;
            case 343970304:
                if (str.equals(CaracEvent.WIND_FRONT)) {
                    c = 'D';
                    break;
                }
                c = 65535;
                break;
            case 348580471:
                if (str.equals(CaracEvent.EXIT_FRESH_AIR)) {
                    c = '@';
                    break;
                }
                c = 65535;
                break;
            case 363678332:
                if (str.equals(CaracEvent.SEAT_HEATING_MAX)) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case 363678570:
                if (str.equals(CaracEvent.SEAT_HEATING_MIN)) {
                    c = '/';
                    break;
                }
                c = 65535;
                break;
            case 409110340:
                if (str.equals(CaracEvent.TEMP_DRIVER_UP)) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 428424718:
                if (str.equals(CaracEvent.TEMP_PSN_SYN_OFF)) {
                    c = 'R';
                    break;
                }
                c = 65535;
                break;
            case 428429159:
                if (str.equals(CaracEvent.TEMP_PSN_SYN_ON)) {
                    c = 'Q';
                    break;
                }
                c = 65535;
                break;
            case 501001602:
                if (str.equals(CaracEvent.SEAT_VENTILATE_DOWN)) {
                    c = '6';
                    break;
                }
                c = 65535;
                break;
            case 520162008:
                if (str.equals(CaracEvent.TEMP_UP)) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 579107870:
                if (str.equals(CaracEvent.WINDOW_ON)) {
                    c = 'P';
                    break;
                }
                c = 65535;
                break;
            case 607537788:
                if (str.equals(CaracEvent.SEAT_VENTILATE_DRIVER_SET)) {
                    c = '8';
                    break;
                }
                c = 65535;
                break;
            case 631012310:
                if (str.equals(CaracEvent.HEATING_OFF)) {
                    c = 'M';
                    break;
                }
                c = 65535;
                break;
            case 667102452:
                if (str.equals(CaracEvent.INNER_ON)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 795243080:
                if (str.equals(CaracEvent.SEAT_HEAT_PASSENGER_DOWN)) {
                    c = '<';
                    break;
                }
                c = 65535;
                break;
            case 816190956:
                if (str.equals(CaracEvent.SEAT_HEAT_DRIVER_SET)) {
                    c = '9';
                    break;
                }
                c = 65535;
                break;
            case 843015811:
                if (str.equals(CaracEvent.SEAT_HEATING_UP)) {
                    c = '0';
                    break;
                }
                c = 65535;
                break;
            case 875138608:
                if (str.equals(CaracEvent.HEAD_FOOT_ON)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 898888115:
                if (str.equals(CaracEvent.TEMP_PASSENGER_MAX)) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 898888353:
                if (str.equals(CaracEvent.TEMP_PASSENGER_MIN)) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case 898894001:
                if (str.equals(CaracEvent.TEMP_PASSENGER_SET)) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 916309008:
                if (str.equals(CaracEvent.AC_LEFT_REAR_SEAT_HEAT_UP)) {
                    c = '`';
                    break;
                }
                c = 65535;
                break;
            case 960163233:
                if (str.equals(CaracEvent.DEMIST_REAR_ON)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1012713970:
                if (str.equals(CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_SET)) {
                    c = '_';
                    break;
                }
                c = 65535;
                break;
            case 1110811877:
                if (str.equals(CaracEvent.NATURE_OFF)) {
                    c = 'O';
                    break;
                }
                c = 65535;
                break;
            case 1184647975:
                if (str.equals(CaracEvent.AQS_OFF)) {
                    c = 'I';
                    break;
                }
                c = 65535;
                break;
            case 1279594251:
                if (str.equals(CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_UP)) {
                    c = 'a';
                    break;
                }
                c = 65535;
                break;
            case 1301962337:
                if (str.equals(CaracEvent.AUTO_ON)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1328924946:
                if (str.equals(CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_DOWN)) {
                    c = 'c';
                    break;
                }
                c = 65535;
                break;
            case 1329253050:
                if (str.equals(CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_OPEN)) {
                    c = '[';
                    break;
                }
                c = 65535;
                break;
            case 1336412759:
                if (str.equals(CaracEvent.WIND_AUTO_SWEEP_ON)) {
                    c = 'F';
                    break;
                }
                c = 65535;
                break;
            case 1349159863:
                if (str.equals(CaracEvent.OUTLET_ON)) {
                    c = 'X';
                    break;
                }
                c = 65535;
                break;
            case 1353364654:
                if (str.equals(CaracEvent.HVAC_OFF)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1360839242:
                if (str.equals(CaracEvent.WIND_UNIDIRECTION_SET)) {
                    c = 'A';
                    break;
                }
                c = 65535;
                break;
            case 1368347598:
                if (str.equals(CaracEvent.AC_ON)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1463363191:
                if (str.equals(CaracEvent.MODES_ECO_OFF)) {
                    c = 'K';
                    break;
                }
                c = 65535;
                break;
            case 1542451310:
                if (str.equals(CaracEvent.BLOW_HEAD_ON)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1593613584:
                if (str.equals(CaracEvent.SEAT_HEATING_CLOSE)) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case 1605772617:
                if (str.equals(CaracEvent.MIRROR_OFF)) {
                    c = 'W';
                    break;
                }
                c = 65535;
                break;
            case 1658979743:
                if (str.equals(CaracEvent.TEMP_DOWN)) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1678737540:
                if (str.equals(CaracEvent.SEAT_VENTILATE_MAX)) {
                    c = '4';
                    break;
                }
                c = 65535;
                break;
            case 1678737778:
                if (str.equals(CaracEvent.SEAT_VENTILATE_MIN)) {
                    c = '5';
                    break;
                }
                c = 65535;
                break;
            case 1678739599:
                if (str.equals(CaracEvent.SEAT_VENTILATE_OFF)) {
                    c = '3';
                    break;
                }
                c = 65535;
                break;
            case 1706126637:
                if (str.equals(CaracEvent.AUTO_OFF)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1941593737:
                if (str.equals(CaracEvent.REAR_HEAT_OFF)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 2004070256:
                if (str.equals(CaracEvent.HEAD_WINDOW_FOOT_ON)) {
                    c = 'e';
                    break;
                }
                c = 65535;
                break;
            case 2081962680:
                if (str.equals(CaracEvent.CLOSE_AC_INTELLIGENT_PSN_OFF)) {
                    c = 'U';
                    break;
                }
                c = 65535;
                break;
            case 2095473203:
                if (str.equals(CaracEvent.TEMP_PASSENGER_DOWN)) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.mTarget.onHvacOff(str, str2);
                return;
            case 1:
                this.mTarget.onHvacOn(str, str2);
                return;
            case 2:
                this.mTarget.onAcOn(str, str2);
                return;
            case 3:
                this.mTarget.onAcOff(str, str2);
                return;
            case 4:
                this.mTarget.onAutoOn(str, str2);
                return;
            case 5:
                this.mTarget.onAutoOff(str, str2);
                return;
            case 6:
                this.mTarget.onBlowFootOn(str, str2);
                return;
            case 7:
                this.mTarget.onHeadFootOn(str, str2);
                return;
            case '\b':
                this.mTarget.onBlowHeadOn(str, str2);
                return;
            case '\t':
                this.mTarget.onDemistFrontOff(str, str2);
                return;
            case '\n':
                this.mTarget.onDemistFrontOn(str, str2);
                return;
            case 11:
                this.mTarget.onDemistRearOff(str, str2);
                return;
            case '\f':
                this.mTarget.onDemistRearOn(str, str2);
                return;
            case '\r':
                this.mTarget.onDemistFootOn(str, str2);
                return;
            case 14:
                this.mTarget.onInnerOff(str, str2);
                return;
            case 15:
                this.mTarget.onInnerOn(str, str2);
                return;
            case 16:
                this.mTarget.onWindDown(str, str2);
                return;
            case 17:
                this.mTarget.onWindUp(str, str2);
                return;
            case 18:
                this.mTarget.onWindSet(str, str2);
                return;
            case 19:
                this.mTarget.onRearHeatOff(str, str2);
                return;
            case 20:
                this.mTarget.onRearHeatOn(str, str2);
                return;
            case 21:
                this.mTarget.onTempDown(str, str2);
                return;
            case 22:
                this.mTarget.onTempUp(str, str2);
                return;
            case 23:
                this.mTarget.onTempSet(str, str2);
                return;
            case 24:
                this.mTarget.onPurifierOpen(str, str2);
                return;
            case 25:
                this.mTarget.onPurifierClose(str, str2);
                return;
            case 26:
                this.mTarget.onTempDriverUp(str, str2);
                return;
            case 27:
                this.mTarget.onTempDriverDown(str, str2);
                return;
            case 28:
                this.mTarget.onTempDriverSet(str, str2);
                return;
            case 29:
                this.mTarget.onTempPassengerUp(str, str2);
                return;
            case 30:
                this.mTarget.onTempPassengerDown(str, str2);
                return;
            case 31:
                this.mTarget.onTempPassengerSet(str, str2);
                return;
            case ' ':
                this.mTarget.onTempDualSyn(str, str2);
                return;
            case '!':
                this.mTarget.onTempDualOff(str, str2);
                return;
            case '\"':
                this.mTarget.onWindMax(str, str2);
                return;
            case '#':
                this.mTarget.onWindMin(str, str2);
                return;
            case '$':
                this.mTarget.onTempMin(str, str2);
                return;
            case '%':
                this.mTarget.onTempMax(str, str2);
                return;
            case '&':
                this.mTarget.onTempDriveMin(str, str2);
                return;
            case '\'':
                this.mTarget.onTempDriveMax(str, str2);
                return;
            case '(':
                this.mTarget.onTempPassengerMin(str, str2);
                return;
            case ')':
                this.mTarget.onTempPassengerMax(str, str2);
                return;
            case '*':
                this.mTarget.onSeatHeatingOpen(str, str2);
                return;
            case '+':
                this.mTarget.onSeatHeatingClose(str, str2);
                return;
            case ',':
                this.mTarget.onSeatPsnHeatingOpen(str, str2);
                return;
            case '-':
                this.mTarget.onSeatPsnHeatingClose(str, str2);
                return;
            case '.':
                this.mTarget.onSeatHeatingMax(str, str2);
                return;
            case '/':
                this.mTarget.onSeatHeatingMin(str, str2);
                return;
            case '0':
                this.mTarget.onSeatHeatingUp(str, str2);
                return;
            case '1':
                this.mTarget.onSeatHeatingDown(str, str2);
                return;
            case '2':
                this.mTarget.onSeatVentilateOn(str, str2);
                return;
            case '3':
                this.mTarget.onSeatVentilateOff(str, str2);
                return;
            case '4':
                this.mTarget.onSeatVentilateMax(str, str2);
                return;
            case '5':
                this.mTarget.onSeatVentilateMin(str, str2);
                return;
            case '6':
                this.mTarget.onSeatVentilateDown(str, str2);
                return;
            case '7':
                this.mTarget.onSeatVentilateUp(str, str2);
                return;
            case '8':
                this.mTarget.onSeatVentilateDriverSet(str, str2);
                return;
            case '9':
                this.mTarget.onSeatHeatDriverSet(str, str2);
                return;
            case ':':
                this.mTarget.onSeatHeatPassengerSet(str, str2);
                return;
            case ';':
                this.mTarget.onSeatHeatPassengerUp(str, str2);
                return;
            case '<':
                this.mTarget.onSeatHeatPassengerDown(str, str2);
                return;
            case '=':
                this.mTarget.onOpenFastFridge(str, str2);
                return;
            case '>':
                this.mTarget.onOpenFreshAir(str, str2);
                return;
            case '?':
                this.mTarget.onExitFastFridge(str, str2);
                return;
            case '@':
                this.mTarget.onExitFreshAir(str, str2);
                return;
            case 'A':
                this.mTarget.onWindUnidirectionSet(str, str2);
                return;
            case 'B':
                this.mTarget.onWindUnindirection(str, str2);
                return;
            case 'C':
                this.mTarget.onWindEvad(str, str2);
                return;
            case 'D':
                this.mTarget.onWindFront(str, str2);
                return;
            case 'E':
                this.mTarget.onWindFree(str, str2);
                return;
            case 'F':
                this.mTarget.onWindAutoSweepOn(str, str2);
                return;
            case 'G':
                this.mTarget.onWindAutoSweepOff(str, str2);
                return;
            case 'H':
                this.mTarget.onAqsOn(str, str2);
                return;
            case 'I':
                this.mTarget.onAqsOff(str, str2);
                return;
            case 'J':
                this.mTarget.onModesEcoOn(str, str2);
                return;
            case 'K':
                this.mTarget.onModesEcoOff(str, str2);
                return;
            case 'L':
                this.mTarget.onHeatingOn(str, str2);
                return;
            case 'M':
                this.mTarget.onHeatingOff(str, str2);
                return;
            case 'N':
                this.mTarget.onNatureOn(str, str2);
                return;
            case 'O':
                this.mTarget.onNatureOff(str, str2);
                return;
            case 'P':
                this.mTarget.onWindowOn(str, str2);
                return;
            case 'Q':
                this.mTarget.onPsnTempSynOn(str, str2);
                return;
            case 'R':
                this.mTarget.onPsnTempSynOff(str, str2);
                return;
            case 'S':
                this.mTarget.onHvacPanelOn(str, str2);
                return;
            case 'T':
                this.mTarget.openIntelligentPsn(str, str2);
                return;
            case 'U':
                this.mTarget.closeIntelligentPsn(str, str2);
                return;
            case 'V':
                this.mTarget.onMirrorOn(str, str2);
                return;
            case 'W':
                this.mTarget.onMirrorOff(str, str2);
                return;
            case 'X':
                this.mTarget.onWindOutletOn(str, str2);
                return;
            case 'Y':
                this.mTarget.onWindOutletOff(str, str2);
                return;
            case 'Z':
                this.mTarget.onLeftRearSeatHeatingOpen(str, str2);
                return;
            case '[':
                this.mTarget.onRightRearSeatHeatingOpen(str, str2);
                return;
            case '\\':
                this.mTarget.onLeftRearSeatHeatingClose(str, str2);
                return;
            case ']':
                this.mTarget.onRightRearSeatHeatingClose(str, str2);
                return;
            case '^':
                this.mTarget.onLeftRearSeatHeatSet(str, str2);
                return;
            case '_':
                this.mTarget.onRightRearSeatHeatSet(str, str2);
                return;
            case '`':
                this.mTarget.onLeftRearSeatHeatUp(str, str2);
                return;
            case 'a':
                this.mTarget.onRightRearSeatHeatUp(str, str2);
                return;
            case 'b':
                this.mTarget.onLeftRearSeatHeatDown(str, str2);
                return;
            case 'c':
                this.mTarget.onRightRearSeatHeatDown(str, str2);
                return;
            case 'd':
                this.mTarget.onHeadWindowOn(str, str2);
                return;
            case 'e':
                this.mTarget.onHeadWindowFootOn(str, str2);
                return;
            case 'f':
                this.mTarget.onHeadWindowOff(str, str2);
                return;
            default:
                return;
        }
    }

    @Override // com.xiaopeng.speech.annotation.ICommandProcessor
    public String[] getSubscribeEvents() {
        return new String[]{CaracEvent.HVAC_OFF, CaracEvent.HVAC_ON, CaracEvent.AC_ON, CaracEvent.AC_OFF, CaracEvent.AUTO_ON, CaracEvent.AUTO_OFF, CaracEvent.BLOW_FOOT_ON, CaracEvent.HEAD_FOOT_ON, CaracEvent.BLOW_HEAD_ON, CaracEvent.DEMIST_FRONT_OFF, CaracEvent.DEMIST_FRONT_ON, CaracEvent.DEMIST_REAR_OFF, CaracEvent.DEMIST_REAR_ON, CaracEvent.DEMIST_FOOT_ON, CaracEvent.INNER_OFF, CaracEvent.INNER_ON, CaracEvent.WIND_DOWN, CaracEvent.WIND_UP, CaracEvent.WIND_SET, CaracEvent.REAR_HEAT_OFF, CaracEvent.REAR_HEAT_ON, CaracEvent.TEMP_DOWN, CaracEvent.TEMP_UP, CaracEvent.TEMP_SET, CaracEvent.PURIFIER_OPEN, CaracEvent.PURIFIER_CLOSE, CaracEvent.TEMP_DRIVER_UP, CaracEvent.TEMP_DRIVER_DOWN, CaracEvent.TEMP_DRIVER_SET, CaracEvent.TEMP_PASSENGER_UP, CaracEvent.TEMP_PASSENGER_DOWN, CaracEvent.TEMP_PASSENGER_SET, CaracEvent.TEMP_DUAL_SYN, CaracEvent.TEMP_DUAL_OFF, CaracEvent.WIND_MAX, CaracEvent.WIND_MIN, CaracEvent.TEMP_MIN, CaracEvent.TEMP_MAX, CaracEvent.TEMP_DRIVE_MIN, CaracEvent.TEMP_DRIVE_MAX, CaracEvent.TEMP_PASSENGER_MIN, CaracEvent.TEMP_PASSENGER_MAX, CaracEvent.SEAT_HEATING_OPEN, CaracEvent.SEAT_HEATING_CLOSE, CaracEvent.SEAT_PSN_HEAT_OPEN, CaracEvent.SEAT_PSN_HEAT_CLOSE, CaracEvent.SEAT_HEATING_MAX, CaracEvent.SEAT_HEATING_MIN, CaracEvent.SEAT_HEATING_UP, CaracEvent.SEAT_HEATING_DOWN, CaracEvent.SEAT_VENTILATE_ON, CaracEvent.SEAT_VENTILATE_OFF, CaracEvent.SEAT_VENTILATE_MAX, CaracEvent.SEAT_VENTILATE_MIN, CaracEvent.SEAT_VENTILATE_DOWN, CaracEvent.SEAT_VENTILATE_UP, CaracEvent.SEAT_VENTILATE_DRIVER_SET, CaracEvent.SEAT_HEAT_DRIVER_SET, CaracEvent.SEAT_HEAT_PASSENGER_SET, CaracEvent.SEAT_HEAT_PASSENGER_UP, CaracEvent.SEAT_HEAT_PASSENGER_DOWN, CaracEvent.OPEN_FAST_FRIDGE, CaracEvent.OPEN_FRESH_AIR, CaracEvent.EXIT_FAST_FRIDGE, CaracEvent.EXIT_FRESH_AIR, CaracEvent.WIND_UNIDIRECTION_SET, CaracEvent.WIND_UNIDIRECTION, CaracEvent.WIND_EVAD, CaracEvent.WIND_FRONT, CaracEvent.WIND_FREE, CaracEvent.WIND_AUTO_SWEEP_ON, CaracEvent.WIND_AUTO_SWEEP_OFF, CaracEvent.AQS_ON, CaracEvent.AQS_OFF, CaracEvent.MODES_ECO_ON, CaracEvent.MODES_ECO_OFF, CaracEvent.HEATING_ON, CaracEvent.HEATING_OFF, CaracEvent.NATURE_ON, CaracEvent.NATURE_OFF, CaracEvent.WINDOW_ON, CaracEvent.TEMP_PSN_SYN_ON, CaracEvent.TEMP_PSN_SYN_OFF, CaracEvent.OPEN_AC_PANEL, CaracEvent.OPEN_AC_INTELLIGENT_PSN_ON, CaracEvent.CLOSE_AC_INTELLIGENT_PSN_OFF, CaracEvent.MIRROR_ON, CaracEvent.MIRROR_OFF, CaracEvent.OUTLET_ON, CaracEvent.OUTLET_OFF, CaracEvent.AC_LEFT_REAR_SEAT_HEAT_OPEN, CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_OPEN, CaracEvent.AC_LEFT_REAR_SEAT_HEAT_CLOSE, CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_CLOSE, CaracEvent.AC_LEFT_REAR_SEAT_HEAT_SET, CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_SET, CaracEvent.AC_LEFT_REAR_SEAT_HEAT_UP, CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_UP, CaracEvent.AC_LEFT_REAR_SEAT_HEAT_DOWN, CaracEvent.AC_RIGHT_REAR_SEAT_HEAT_DOWN, CaracEvent.HEAD_WINDOW_ON, CaracEvent.HEAD_WINDOW_FOOT_ON, CaracEvent.HEAD_WINDOW_OFF};
    }
}
