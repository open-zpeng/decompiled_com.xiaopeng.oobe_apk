package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MaskParser {
    private MaskParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Mask parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        boolean z;
        jsonReader.beginObject();
        Mask.MaskMode maskMode = null;
        boolean z2 = false;
        AnimatableShapeValue animatableShapeValue = null;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            int hashCode = nextName.hashCode();
            char c = 65535;
            if (hashCode == 111) {
                if (nextName.equals("o")) {
                    z = true;
                }
                z = true;
            } else if (hashCode == 3588) {
                if (nextName.equals("pt")) {
                    z = true;
                }
                z = true;
            } else if (hashCode != 104433) {
                if (hashCode == 3357091 && nextName.equals("mode")) {
                    z = false;
                }
                z = true;
            } else {
                if (nextName.equals("inv")) {
                    z = true;
                }
                z = true;
            }
            if (!z) {
                String nextString = jsonReader.nextString();
                int hashCode2 = nextString.hashCode();
                if (hashCode2 != 97) {
                    if (hashCode2 != 105) {
                        if (hashCode2 != 110) {
                            if (hashCode2 == 115 && nextString.equals("s")) {
                                c = 1;
                            }
                        } else if (nextString.equals("n")) {
                            c = 2;
                        }
                    } else if (nextString.equals("i")) {
                        c = 3;
                    }
                } else if (nextString.equals("a")) {
                    c = 0;
                }
                if (c == 0) {
                    maskMode = Mask.MaskMode.MASK_MODE_ADD;
                } else if (c == 1) {
                    maskMode = Mask.MaskMode.MASK_MODE_SUBTRACT;
                } else if (c == 2) {
                    maskMode = Mask.MaskMode.MASK_MODE_NONE;
                } else if (c == 3) {
                    lottieComposition.addWarning("Animation contains intersect masks. They are not supported but will be treated like add masks.");
                    maskMode = Mask.MaskMode.MASK_MODE_INTERSECT;
                } else {
                    Logger.warning("Unknown mask mode " + nextName + ". Defaulting to Add.");
                    maskMode = Mask.MaskMode.MASK_MODE_ADD;
                }
            } else if (z) {
                animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
            } else if (z) {
                animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
            } else if (z) {
                z2 = jsonReader.nextBoolean();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return new Mask(maskMode, animatableShapeValue, animatableIntegerValue, z2);
    }
}
