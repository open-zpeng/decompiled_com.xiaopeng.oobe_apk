package com.airbnb.lottie.parser;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class BlurEffectParser {
    private static final JsonReader.Options BLUR_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_BLUR_EFFECT_NAMES = JsonReader.Options.of("ty", "v");

    BlurEffectParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static BlurEffect parse(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        BlurEffect blurEffect = null;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(BLUR_EFFECT_NAMES) == 0) {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    BlurEffect maybeParseInnerEffect = maybeParseInnerEffect(jsonReader, lottieComposition);
                    if (maybeParseInnerEffect != null) {
                        blurEffect = maybeParseInnerEffect;
                    }
                }
                jsonReader.endArray();
            } else {
                jsonReader.skipName();
                jsonReader.skipValue();
            }
        }
        return blurEffect;
    }

    @Nullable
    private static BlurEffect maybeParseInnerEffect(JsonReader jsonReader, LottieComposition lottieComposition) throws IOException {
        jsonReader.beginObject();
        BlurEffect blurEffect = null;
        while (true) {
            boolean z = false;
            while (jsonReader.hasNext()) {
                int selectName = jsonReader.selectName(INNER_BLUR_EFFECT_NAMES);
                if (selectName != 0) {
                    if (selectName != 1) {
                        jsonReader.skipName();
                        jsonReader.skipValue();
                    } else if (z) {
                        blurEffect = new BlurEffect(AnimatableValueParser.parseFloat(jsonReader, lottieComposition));
                    } else {
                        jsonReader.skipValue();
                    }
                } else if (jsonReader.nextInt() == 0) {
                    z = true;
                }
            }
            jsonReader.endObject();
            return blurEffect;
        }
    }
}
