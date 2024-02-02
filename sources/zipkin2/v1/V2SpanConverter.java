package zipkin2.v1;

import java.util.Map;
import zipkin2.Annotation;
import zipkin2.Endpoint;
import zipkin2.Span;
import zipkin2.v1.V1Span;
/* loaded from: classes2.dex */
public final class V2SpanConverter {
    final V1Span.Builder result = V1Span.newBuilder();
    final V1SpanMetadata md = new V1SpanMetadata();

    public static V2SpanConverter create() {
        return new V2SpanConverter();
    }

    public V1Span convert(Span span) {
        this.md.parse(span);
        this.result.clear().traceId(span.traceId()).parentId(span.parentId()).id(span.id()).name(span.name()).debug(span.debug());
        if (!Boolean.TRUE.equals(span.shared())) {
            this.result.timestamp(span.timestampAsLong());
            this.result.duration(span.durationAsLong());
        }
        boolean z = false;
        boolean z2 = (this.md.startTs == 0 || this.md.begin == null) ? false : true;
        boolean z3 = (this.md.endTs == 0 || this.md.end == null) ? false : true;
        Endpoint localEndpoint = span.localEndpoint();
        int size = span.annotations().size();
        if (z2) {
            size++;
            this.result.addAnnotation(this.md.startTs, this.md.begin, localEndpoint);
        }
        int size2 = span.annotations().size();
        for (int i = 0; i < size2; i++) {
            Annotation annotation = span.annotations().get(i);
            if ((!z2 || !annotation.value().equals(this.md.begin)) && (!z3 || !annotation.value().equals(this.md.end))) {
                this.result.addAnnotation(annotation.timestamp(), annotation.value(), localEndpoint);
            }
        }
        if (z3) {
            size++;
            this.result.addAnnotation(this.md.endTs, this.md.end, localEndpoint);
        }
        for (Map.Entry<String, String> entry : span.tags().entrySet()) {
            this.result.addBinaryAnnotation(entry.getKey(), entry.getValue(), localEndpoint);
        }
        boolean z4 = size == 0 && localEndpoint != null && span.tags().isEmpty();
        if (this.md.addr != null && span.remoteEndpoint() != null) {
            z = true;
        }
        if (z4) {
            this.result.addBinaryAnnotation("lc", "", localEndpoint);
        }
        if (z) {
            this.result.addBinaryAnnotation(this.md.addr, span.remoteEndpoint());
        }
        return this.result.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class V1SpanMetadata {
        String addr;
        String begin;
        String end;
        long endTs;
        long mrTs;
        long msTs;
        long startTs;
        long wrTs;
        long wsTs;

        V1SpanMetadata() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:65:0x0144, code lost:
            if (r5 < r1) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x015a, code lost:
            if (r5 > r1) goto L87;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0195, code lost:
            if (r5 < r1) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x01ab, code lost:
            if (r5 > r1) goto L103;
         */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0152  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0168  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x01a3  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void parse(zipkin2.Span r17) {
            /*
                Method dump skipped, instructions count: 459
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: zipkin2.v1.V2SpanConverter.V1SpanMetadata.parse(zipkin2.Span):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: zipkin2.v1.V2SpanConverter$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$zipkin2$Span$Kind = new int[Span.Kind.values().length];

        static {
            try {
                $SwitchMap$zipkin2$Span$Kind[Span.Kind.CLIENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$zipkin2$Span$Kind[Span.Kind.SERVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$zipkin2$Span$Kind[Span.Kind.PRODUCER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$zipkin2$Span$Kind[Span.Kind.CONSUMER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    V2SpanConverter() {
    }
}
