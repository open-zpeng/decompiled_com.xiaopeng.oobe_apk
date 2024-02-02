package com.alibaba.sdk.android.man.util;

import com.alibaba.sdk.android.man.customperf.MANCustomPerformance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class Aggregation {
    private static String tag = "MAN_Aggregation";
    private final String AGGREGATION_CUSTOM_PERFORMANCE_LABLE;
    private final String AGGREGATION_NETWORK_PERFORMANCE_LABLE;
    private final Map<String, AggregationSend> hashMap;
    private final ArrayList<String> networkDefineKey;
    private Timer timer;
    private AggregationTimerTask timerTask;
    private long totalNum;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface AggregationSend {
        void send();
    }

    private Aggregation() {
        this.AGGREGATION_NETWORK_PERFORMANCE_LABLE = "AGGREGATION_3002";
        this.AGGREGATION_CUSTOM_PERFORMANCE_LABLE = "AGGREGATION_66602";
        this.totalNum = 0L;
        this.hashMap = new LinkedHashMap<String, AggregationSend>() { // from class: com.alibaba.sdk.android.man.util.Aggregation.1
            private static final long serialVersionUID = 201503121136L;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, AggregationSend> entry) {
                return size() > 200;
            }
        };
        this.networkDefineKey = new ArrayList<>();
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY);
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY);
        this.networkDefineKey.add("Host");
        this.networkDefineKey.add(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY);
        this.timerTask = new AggregationTimerTask();
        this.timer = new Timer();
        this.timer.schedule(this.timerTask, 30000L, 30000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Singleton {
        static Aggregation instance = new Aggregation();

        private Singleton() {
        }
    }

    public static Aggregation getInstance() {
        return Singleton.instance;
    }

    public boolean addCustomPerfToAggregation(MANCustomPerformance mANCustomPerformance) {
        if (mANCustomPerformance.getProperties() == null || mANCustomPerformance.getProperties().size() == 0) {
            String str = "AGGREGATION_66602" + mANCustomPerformance.getEventLabel();
            synchronized (this.hashMap) {
                AggregationCustomPerf aggregationCustomPerf = (AggregationCustomPerf) this.hashMap.get(str);
                if (aggregationCustomPerf == null) {
                    aggregationCustomPerf = new AggregationCustomPerf(mANCustomPerformance.getEventLabel());
                    this.hashMap.put(str, aggregationCustomPerf);
                }
                aggregationCustomPerf.addCustomPerf(mANCustomPerformance.getDuration());
                this.totalNum++;
                if (this.totalNum >= 100) {
                    submitAggregation();
                }
            }
            return true;
        }
        return false;
    }

    public boolean addToNetPerfAggregation(Map<String, String> map) {
        long j;
        if (isOnlyContainsDefineKey(map)) {
            long convertTimeStr2Long = convertTimeStr2Long(map.get(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY));
            long convertTimeStr2Long2 = convertTimeStr2Long(map.get(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY));
            long convertTimeStr2Long3 = convertTimeStr2Long(map.get(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY));
            try {
                j = Long.valueOf(map.get(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY)).longValue();
            } catch (NumberFormatException unused) {
                j = 0;
            }
            long j2 = j;
            String str = "AGGREGATION_3002" + map.get("Host") + map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY);
            synchronized (this.hashMap) {
                AggregationNetworkPerformance aggregationNetworkPerformance = (AggregationNetworkPerformance) this.hashMap.get(str);
                if (aggregationNetworkPerformance != null) {
                    aggregationNetworkPerformance.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, j2, map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY), map.get("Host"));
                } else {
                    AggregationNetworkPerformance aggregationNetworkPerformance2 = new AggregationNetworkPerformance();
                    aggregationNetworkPerformance2.addNetworkPerformance(convertTimeStr2Long3, convertTimeStr2Long, convertTimeStr2Long2, j2, map.get(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY), map.get("Host"));
                    this.hashMap.put(str, aggregationNetworkPerformance2);
                }
                this.totalNum++;
                if (this.totalNum >= 100) {
                    submitAggregation();
                }
            }
            return true;
        }
        return false;
    }

    private long convertTimeStr2Long(String str) {
        try {
            return Long.valueOf(str).longValue();
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private boolean isOnlyContainsDefineKey(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        for (String str : map.keySet()) {
            if (!this.networkDefineKey.contains(str)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitAggregation() {
        synchronized (this.hashMap) {
            this.totalNum = 0L;
            Iterator<String> it = this.hashMap.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (this.hashMap.get(next) != null) {
                    this.hashMap.get(next).send();
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AggregationNetworkPerformance implements AggregationSend {
        private long connectTimeCount;
        private long connectTimeSum;
        private long count;
        private long firstByteCount;
        private long firstByteSum;
        private String requestHost;
        private String requestMethod;
        private long requestRTSum;
        private long resourceBytesSum;

        private AggregationNetworkPerformance() {
            this.requestRTSum = 0L;
            this.connectTimeSum = 0L;
            this.firstByteSum = 0L;
            this.resourceBytesSum = 0L;
            this.connectTimeCount = 0L;
            this.firstByteCount = 0L;
            this.count = 0L;
            this.requestMethod = null;
            this.requestHost = null;
        }

        public void addNetworkPerformance(long j, long j2, long j3, long j4, String str, String str2) {
            if (j2 != -1) {
                this.connectTimeSum += j2;
                this.connectTimeCount++;
            }
            if (j3 != -1) {
                this.firstByteSum += j3;
                this.firstByteCount++;
            }
            this.resourceBytesSum += j4;
            this.requestRTSum += j;
            this.count++;
            this.requestHost = str2;
            this.requestMethod = str;
        }

        @Override // com.alibaba.sdk.android.man.util.Aggregation.AggregationSend
        public void send() {
            if (this.count == 0) {
                return;
            }
            HashMap hashMap = new HashMap();
            long j = this.connectTimeCount;
            if (j != 0) {
                hashMap.put(MANConfig.NETWORK_SINGLE_CONNECT_TIME_KEY, String.valueOf(this.connectTimeSum / j));
                hashMap.put(MANConfig.NETWORK_AGGREGATION_CONNECTION_TIME_NUMBER_KEY, String.valueOf(this.connectTimeCount));
            }
            long j2 = this.firstByteCount;
            if (j2 != 0) {
                hashMap.put(MANConfig.NETWORK_SINGLE_FIRST_PACKAGE_RT_KEY, String.valueOf(this.firstByteSum / j2));
                hashMap.put(MANConfig.NETWORK_AGGREGATION_FIST_PACKAGE_NUMBER_KEY, String.valueOf(this.firstByteCount));
            }
            long j3 = this.count;
            if (j3 != 0) {
                hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_RT_KEY, String.valueOf(this.requestRTSum / j3));
                hashMap.put(MANConfig.NETWORK_AGGREGATION_PERFORMANCE_NUMBER_KEY, String.valueOf(this.count));
                hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_SIZE_KEY, String.valueOf(this.resourceBytesSum / this.count));
            }
            String str = this.requestHost;
            if (str != null && !str.equals("")) {
                hashMap.put("Host", this.requestHost);
            }
            String str2 = this.requestMethod;
            if (str2 != null && !str2.equals("")) {
                hashMap.put(MANConfig.NETWORK_SINGLE_REQUEST_METHOD_KEY, this.requestMethod);
            }
            EventCommitTool.commitEventDirectly(3002, MANConfig.NETWORK_SIG_REQUEST_EVENT_LABEL, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class AggregationCustomPerf implements AggregationSend {
        private String eventLabel;
        private long duration = 0;
        private int count = 0;

        public AggregationCustomPerf(String str) {
            this.eventLabel = "";
            this.eventLabel = str;
        }

        public void addCustomPerf(long j) {
            this.duration += j;
            this.count++;
        }

        @Override // com.alibaba.sdk.android.man.util.Aggregation.AggregationSend
        public void send() {
            int i = this.count;
            if (i != 0) {
                EventCommitTool.commitEventToUT("UT", MANConfig.CUSTOM_PERFORMANCE_EVENT_ID, this.eventLabel, String.valueOf(i), String.valueOf(this.duration / i), new HashMap());
            }
        }
    }

    /* loaded from: classes.dex */
    private class AggregationTimerTask extends TimerTask {
        private AggregationTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MANLog.Logi(Aggregation.tag, "timer alive.");
            Aggregation.getInstance().submitAggregation();
        }
    }
}
