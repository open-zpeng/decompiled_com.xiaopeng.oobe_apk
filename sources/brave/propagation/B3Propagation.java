package brave.propagation;

import brave.propagation.B3SinglePropagation;
import brave.propagation.Propagation;
import brave.propagation.TraceContext;
import com.xiaopeng.speech.protocol.event.OOBEEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class B3Propagation<K> implements Propagation<K> {
    public static final Propagation.Factory FACTORY = new Propagation.Factory() { // from class: brave.propagation.B3Propagation.1
        @Override // brave.propagation.Propagation.Factory
        public boolean supportsJoin() {
            return true;
        }

        public String toString() {
            return "B3PropagationFactory";
        }

        @Override // brave.propagation.Propagation.Factory
        public <K> Propagation<K> create(Propagation.KeyFactory<K> keyFactory) {
            return new B3Propagation(keyFactory);
        }
    };
    static final String FLAGS_NAME = "X-B3-Flags";
    static final String PARENT_SPAN_ID_NAME = "X-B3-ParentSpanId";
    static final String SAMPLED_NAME = "X-B3-Sampled";
    static final String SPAN_ID_NAME = "X-B3-SpanId";
    static final String TRACE_ID_NAME = "X-B3-TraceId";
    final K b3Key;
    final K debugKey;
    final List<K> fields;
    final K parentSpanIdKey;
    final K sampledKey;
    final K spanIdKey;
    final K traceIdKey;

    B3Propagation(Propagation.KeyFactory<K> keyFactory) {
        this.b3Key = keyFactory.create("b3");
        this.traceIdKey = keyFactory.create(TRACE_ID_NAME);
        this.spanIdKey = keyFactory.create(SPAN_ID_NAME);
        this.parentSpanIdKey = keyFactory.create(PARENT_SPAN_ID_NAME);
        this.sampledKey = keyFactory.create(SAMPLED_NAME);
        this.debugKey = keyFactory.create(FLAGS_NAME);
        this.fields = Collections.unmodifiableList(Arrays.asList(this.b3Key, this.traceIdKey, this.spanIdKey, this.parentSpanIdKey, this.sampledKey, this.debugKey));
    }

    @Override // brave.propagation.Propagation
    public List<K> keys() {
        return this.fields;
    }

    @Override // brave.propagation.Propagation
    public <C> TraceContext.Injector<C> injector(Propagation.Setter<C, K> setter) {
        if (setter == null) {
            throw new NullPointerException("setter == null");
        }
        return new B3Injector(this, setter);
    }

    /* loaded from: classes.dex */
    static final class B3Injector<C, K> implements TraceContext.Injector<C> {
        final B3Propagation<K> propagation;
        final Propagation.Setter<C, K> setter;

        B3Injector(B3Propagation<K> b3Propagation, Propagation.Setter<C, K> setter) {
            this.propagation = b3Propagation;
            this.setter = setter;
        }

        @Override // brave.propagation.TraceContext.Injector
        public void inject(TraceContext traceContext, C c) {
            this.setter.put(c, this.propagation.traceIdKey, traceContext.traceIdString());
            this.setter.put(c, this.propagation.spanIdKey, traceContext.spanIdString());
            String parentIdString = traceContext.parentIdString();
            if (parentIdString != null) {
                this.setter.put(c, this.propagation.parentSpanIdKey, parentIdString);
            }
            if (traceContext.debug()) {
                this.setter.put(c, this.propagation.debugKey, "1");
            } else if (traceContext.sampled() != null) {
                this.setter.put(c, this.propagation.sampledKey, traceContext.sampled().booleanValue() ? "1" : "0");
            }
        }
    }

    @Override // brave.propagation.Propagation
    public <C> TraceContext.Extractor<C> extractor(Propagation.Getter<C, K> getter) {
        if (getter == null) {
            throw new NullPointerException("getter == null");
        }
        return new B3Extractor(this, getter);
    }

    /* loaded from: classes.dex */
    static final class B3Extractor<C, K> implements TraceContext.Extractor<C> {
        final Propagation.Getter<C, K> getter;
        final B3Propagation<K> propagation;
        final B3SinglePropagation.B3SingleExtractor<C, K> singleExtractor;

        B3Extractor(B3Propagation<K> b3Propagation, Propagation.Getter<C, K> getter) {
            this.propagation = b3Propagation;
            this.singleExtractor = new B3SinglePropagation.B3SingleExtractor<>(b3Propagation.b3Key, getter);
            this.getter = getter;
        }

        @Override // brave.propagation.TraceContext.Extractor
        public TraceContextOrSamplingFlags extract(C c) {
            Boolean bool;
            if (c == null) {
                throw new NullPointerException("carrier == null");
            }
            TraceContextOrSamplingFlags extract = this.singleExtractor.extract(c);
            if (extract.equals(TraceContextOrSamplingFlags.EMPTY)) {
                String str = this.getter.get(c, this.propagation.sampledKey);
                if (str != null) {
                    bool = Boolean.valueOf(str.equals("1") || str.equalsIgnoreCase(OOBEEvent.STRING_TRUE));
                } else {
                    bool = null;
                }
                boolean equals = "1".equals(this.getter.get(c, this.propagation.debugKey));
                String str2 = this.getter.get(c, this.propagation.traceIdKey);
                if (str2 == null) {
                    return TraceContextOrSamplingFlags.create(bool, equals);
                }
                TraceContext.Builder newBuilder = TraceContext.newBuilder();
                if (newBuilder.parseTraceId(str2, this.propagation.traceIdKey) && newBuilder.parseSpanId(this.getter, c, this.propagation.spanIdKey) && newBuilder.parseParentId(this.getter, c, this.propagation.parentSpanIdKey)) {
                    if (bool != null) {
                        newBuilder.sampled(bool.booleanValue());
                    }
                    if (equals) {
                        newBuilder.debug(true);
                    }
                    return TraceContextOrSamplingFlags.create(newBuilder.build());
                }
                return TraceContextOrSamplingFlags.EMPTY;
            }
            return extract;
        }
    }
}
