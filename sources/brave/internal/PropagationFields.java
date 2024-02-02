package brave.internal;

import brave.propagation.TraceContext;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class PropagationFields {
    long spanId;
    long traceId;

    public abstract String get(String str);

    public abstract void put(String str, String str2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void putAllIfAbsent(PropagationFields propagationFields);

    public abstract Map<String, String> toMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean tryToClaim(long j, long j2) {
        synchronized (this) {
            boolean z = true;
            if (this.traceId == 0) {
                this.traceId = j;
                this.spanId = j2;
                return true;
            }
            if (this.traceId != j || this.spanId != j2) {
                z = false;
            }
            return z;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + toMap();
    }

    public static String get(TraceContext traceContext, String str, Class<? extends PropagationFields> cls) {
        if (traceContext != null) {
            if (str == null) {
                throw new NullPointerException("name == null");
            }
            PropagationFields propagationFields = (PropagationFields) traceContext.findExtra(cls);
            if (propagationFields != null) {
                return propagationFields.get(str);
            }
            return null;
        }
        throw new NullPointerException("context == null");
    }

    public static void put(TraceContext traceContext, String str, String str2, Class<? extends PropagationFields> cls) {
        if (traceContext == null) {
            throw new NullPointerException("context == null");
        }
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        if (str2 == null) {
            throw new NullPointerException("value == null");
        }
        PropagationFields propagationFields = (PropagationFields) traceContext.findExtra(cls);
        if (propagationFields == null) {
            return;
        }
        propagationFields.put(str, str2);
    }
}
