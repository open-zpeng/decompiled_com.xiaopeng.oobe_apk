package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ShapeStrokeParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "c", "w", "o", "lc", "lj", "ml", "hd", "d");
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.of("n", "v");

    private ShapeStrokeParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ShapeStroke parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        char c;
        int i;
        ArrayList arrayList = new ArrayList();
        float f = 0.0f;
        AnimatableIntegerValue animatableIntegerValue = null;
        String str = null;
        AnimatableFloatValue animatableFloatValue = null;
        AnimatableColorValue animatableColorValue = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        boolean z = false;
        while (true) {
            int i2 = 100;
            if (jsonReader.hasNext()) {
                int i3 = 1;
                switch (jsonReader.selectName(NAMES)) {
                    case 0:
                        str = jsonReader.nextString();
                        break;
                    case 1:
                        animatableColorValue = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
                        break;
                    case 2:
                        animatableFloatValue2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                        break;
                    case 3:
                        animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                        break;
                    case 4:
                        lineCapType = ShapeStroke.LineCapType.values()[jsonReader.nextInt() - 1];
                        break;
                    case 5:
                        lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.nextInt() - 1];
                        break;
                    case 6:
                        f = (float) jsonReader.nextDouble();
                        break;
                    case 7:
                        z = jsonReader.nextBoolean();
                        break;
                    case 8:
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            AnimatableFloatValue animatableFloatValue3 = null;
                            String str2 = null;
                            while (jsonReader.hasNext()) {
                                int selectName = jsonReader.selectName(DASH_PATTERN_NAMES);
                                if (selectName == 0) {
                                    str2 = jsonReader.nextString();
                                } else if (selectName == i3) {
                                    animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                                } else {
                                    jsonReader.skipName();
                                    jsonReader.skipValue();
                                }
                            }
                            jsonReader.endObject();
                            int hashCode = str2.hashCode();
                            if (hashCode == i2) {
                                if (str2.equals("d")) {
                                    c = 1;
                                }
                                c = 65535;
                            } else if (hashCode != 103) {
                                if (hashCode == 111 && str2.equals("o")) {
                                    c = 0;
                                }
                                c = 65535;
                            } else {
                                if (str2.equals("g")) {
                                    c = 2;
                                }
                                c = 65535;
                            }
                            if (c != 0) {
                                i = 1;
                                if (c == 1 || c == 2) {
                                    lottieComposition.setHasDashPattern(true);
                                    arrayList.add(animatableFloatValue3);
                                }
                            } else {
                                i = 1;
                                animatableFloatValue = animatableFloatValue3;
                            }
                            i3 = i;
                            i2 = 100;
                        }
                        int i4 = i3;
                        jsonReader.endArray();
                        if (arrayList.size() != i4) {
                            break;
                        } else {
                            arrayList.add((AnimatableFloatValue) arrayList.get(0));
                            break;
                        }
                    default:
                        jsonReader.skipValue();
                        break;
                }
            } else {
                return new ShapeStroke(str, animatableFloatValue, arrayList, animatableColorValue, animatableIntegerValue == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : animatableIntegerValue, animatableFloatValue2, lineCapType, lineJoinType, f, z);
            }
        }
    }
}
