package com.airbnb.lottie.parser;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
/* loaded from: classes.dex */
public class DropShadowEffectParser {
    private static final JsonReader.Options DROP_SHADOW_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_EFFECT_NAMES = JsonReader.Options.of("nm", "v");
    private AnimatableColorValue color;
    private AnimatableFloatValue direction;
    private AnimatableFloatValue distance;
    private AnimatableFloatValue opacity;
    private AnimatableFloatValue radius;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public DropShadowEffect parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableFloatValue animatableFloatValue3;
        AnimatableFloatValue animatableFloatValue4;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(DROP_SHADOW_EFFECT_NAMES) == 0) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    maybeParseInnerEffect(jsonReader, lottieComposition);
                }
                jsonReader.endArray();
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        AnimatableColorValue animatableColorValue = this.color;
        if (animatableColorValue == null || (animatableFloatValue = this.opacity) == null || (animatableFloatValue2 = this.direction) == null || (animatableFloatValue3 = this.distance) == null || (animatableFloatValue4 = this.radius) == null) {
            return null;
        }
        return new DropShadowEffect(animatableColorValue, animatableFloatValue, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4);
    }

    private void maybeParseInnerEffect(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        String str = "";
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(INNER_EFFECT_NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName != 1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                char c = 65535;
                switch (str.hashCode()) {
                    case 353103893:
                        if (str.equals("Distance")) {
                            c = 3;
                            break;
                        }
                        break;
                    case 397447147:
                        if (str.equals("Opacity")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1041377119:
                        if (str.equals("Direction")) {
                            c = 2;
                            break;
                        }
                        break;
                    case 1379387491:
                        if (str.equals("Shadow Color")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 1383710113:
                        if (str.equals("Softness")) {
                            c = 4;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    this.color = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
                } else if (c == 1) {
                    this.opacity = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
                } else if (c == 2) {
                    this.direction = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
                } else if (c == 3) {
                    this.distance = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                } else if (c == 4) {
                    this.radius = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                } else {
                    jsonReader.skipValue();
                }
            }
        }
        jsonReader.endObject();
    }
}
