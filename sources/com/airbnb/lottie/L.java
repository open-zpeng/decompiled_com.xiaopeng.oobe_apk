package com.airbnb.lottie;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.os.TraceCompat;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import java.io.File;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider;
    private static int depthPastMaxDepth;
    private static LottieNetworkFetcher fetcher;
    private static volatile NetworkCache networkCache;
    private static volatile NetworkFetcher networkFetcher;
    private static String[] sections;
    private static long[] startTimeNs;
    private static int traceDepth;
    private static boolean traceEnabled;

    private L() {
    }

    public static void setTraceEnabled(boolean z) {
        if (traceEnabled == z) {
            return;
        }
        traceEnabled = z;
        if (traceEnabled) {
            sections = new String[20];
            startTimeNs = new long[20];
        }
    }

    public static void beginSection(String str) {
        if (traceEnabled) {
            int i = traceDepth;
            if (i == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i] = str;
            startTimeNs[i] = System.nanoTime();
            TraceCompat.beginSection(str);
            traceDepth++;
        }
    }

    public static float endSection(String str) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return 0.0f;
        } else if (traceEnabled) {
            traceDepth--;
            int i2 = traceDepth;
            if (i2 == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            }
            if (!str.equals(sections[i2])) {
                throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + sections[traceDepth] + ".");
            }
            TraceCompat.endSection();
            return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
        } else {
            return 0.0f;
        }
    }

    public static void setFetcher(LottieNetworkFetcher lottieNetworkFetcher) {
        fetcher = lottieNetworkFetcher;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider lottieNetworkCacheProvider) {
        cacheProvider = lottieNetworkCacheProvider;
    }

    @NonNull
    public static NetworkFetcher networkFetcher(@NonNull Context context) {
        NetworkFetcher networkFetcher2 = networkFetcher;
        if (networkFetcher2 == null) {
            synchronized (NetworkFetcher.class) {
                networkFetcher2 = networkFetcher;
                if (networkFetcher2 == null) {
                    networkFetcher2 = new NetworkFetcher(networkCache(context), fetcher != null ? fetcher : new DefaultLottieNetworkFetcher());
                    networkFetcher = networkFetcher2;
                }
            }
        }
        return networkFetcher2;
    }

    @NonNull
    public static NetworkCache networkCache(@NonNull Context context) {
        final Context applicationContext = context.getApplicationContext();
        NetworkCache networkCache2 = networkCache;
        if (networkCache2 == null) {
            synchronized (NetworkCache.class) {
                networkCache2 = networkCache;
                if (networkCache2 == null) {
                    networkCache2 = new NetworkCache(cacheProvider != null ? cacheProvider : new LottieNetworkCacheProvider() { // from class: com.airbnb.lottie.L.1
                        @Override // com.airbnb.lottie.network.LottieNetworkCacheProvider
                        @NonNull
                        public File getCacheDir() {
                            return new File(applicationContext.getCacheDir(), "lottie_network_cache");
                        }
                    });
                    networkCache = networkCache2;
                }
            }
        }
        return networkCache2;
    }
}
