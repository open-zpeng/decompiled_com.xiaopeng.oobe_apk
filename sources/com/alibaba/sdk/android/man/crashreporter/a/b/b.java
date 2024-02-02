package com.alibaba.sdk.android.man.crashreporter.a.b;

import com.alibaba.sdk.android.man.crashreporter.MotuCrashReporter;
import com.alibaba.sdk.android.man.crashreporter.ReporterConfigure;
import com.xiaopeng.speech.protocol.query.speech.hardware.bean.StreamType;
import java.lang.Thread;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes.dex */
public class b {
    private static Lock a = new ReentrantLock();

    public String a(Map<Thread, StackTraceElement[]> map) {
        int i;
        Iterator<Map.Entry<Thread, StackTraceElement[]>> it;
        Thread key;
        String str;
        a.lock();
        if (map != null) {
            try {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    ReporterConfigure configure = MotuCrashReporter.getInstance().getConfigure();
                    int i2 = 15;
                    if (configure != null) {
                        i2 = configure.enableMaxThreadNumber;
                        i = configure.enableMaxThreadStackTraceNumber;
                        if (i2 == 0) {
                            return "";
                        }
                    } else {
                        i = 15;
                    }
                    Iterator<Map.Entry<Thread, StackTraceElement[]>> it2 = map.entrySet().iterator();
                    int i3 = 0;
                    while (it2.hasNext()) {
                        Map.Entry<Thread, StackTraceElement[]> next = it2.next();
                        if (next == null || (key = next.getKey()) == null) {
                            it = it2;
                        } else {
                            String name = key.getName();
                            if (name == null || !name.equals("ANR-WatchDog")) {
                                int priority = key.getPriority();
                                long id = key.getId();
                                Thread.State state = key.getState();
                                String name2 = state != null ? state.name() : "";
                                ThreadGroup threadGroup = key.getThreadGroup();
                                if (threadGroup != null) {
                                    str = threadGroup.getName();
                                    if (str.equals(StreamType.SYSTEM)) {
                                    }
                                } else {
                                    str = "";
                                }
                                String name3 = key.getClass().getName();
                                ClassLoader contextClassLoader = key.getContextClassLoader();
                                it = it2;
                                stringBuffer.append(String.format("name:%s prio:%d tid:%d \n|state:%s \n|group:%s \n|class:%s \n|classLoader:%s\n", name, Integer.valueOf(priority), Long.valueOf(id), name2, str, name3, contextClassLoader != null ? contextClassLoader.toString() : ""));
                                StackTraceElement[] value = next.getValue();
                                if (value != null && i != 0) {
                                    stringBuffer.append("|stackTrace:\n ");
                                    int i4 = 0;
                                    for (StackTraceElement stackTraceElement : value) {
                                        if (stackTraceElement != null) {
                                            stringBuffer.append(String.format("%s\n", stackTraceElement.toString()));
                                        }
                                        i4++;
                                        if (i4 >= i) {
                                            break;
                                        }
                                    }
                                }
                                stringBuffer.append("\n");
                            }
                        }
                        i3++;
                        if (i3 >= i2) {
                            break;
                        }
                        it2 = it;
                    }
                    return stringBuffer.toString();
                } catch (Exception e) {
                    com.alibaba.sdk.android.man.crashreporter.b.a.d("serialization failed.", e);
                }
            } finally {
                a.unlock();
            }
        }
        return "";
    }
}
