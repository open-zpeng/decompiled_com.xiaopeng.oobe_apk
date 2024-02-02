package zipkin2.internal;

import zipkin2.Endpoint;
import zipkin2.internal.Buffer;
import zipkin2.v1.V1Annotation;
import zipkin2.v1.V1BinaryAnnotation;
import zipkin2.v1.V1Span;
/* loaded from: classes2.dex */
public final class V1SpanWriter implements Buffer.Writer<V1Span> {
    public String toString() {
        return "Span";
    }

    @Override // zipkin2.internal.Buffer.Writer
    public int sizeInBytes(V1Span v1Span) {
        int i;
        int endpointSizeInBytes;
        int endpointSizeInBytes2;
        int i2 = v1Span.traceIdHigh() != 0 ? 45 : 29;
        if (v1Span.parentId() != 0) {
            i2 += 30;
        }
        int i3 = i2 + 24 + 10;
        if (v1Span.name() != null) {
            i3 += JsonEscaper.jsonEscapedSizeInBytes(v1Span.name());
        }
        if (v1Span.timestamp() != 0) {
            i3 = i3 + 13 + Buffer.asciiSizeInBytes(v1Span.timestamp());
        }
        if (v1Span.duration() != 0) {
            i3 = i3 + 12 + Buffer.asciiSizeInBytes(v1Span.duration());
        }
        int size = v1Span.annotations().size();
        Endpoint endpoint = null;
        if (size > 0) {
            int i4 = i3 + 17;
            if (size > 1) {
                i4 += size - 1;
            }
            Endpoint endpoint2 = null;
            i = 0;
            int i5 = i4;
            int i6 = 0;
            while (i6 < size) {
                V1Annotation v1Annotation = v1Span.annotations().get(i6);
                Endpoint endpoint3 = v1Annotation.endpoint();
                if (endpoint3 == null) {
                    endpoint3 = endpoint2;
                    endpointSizeInBytes2 = 0;
                } else if (endpoint3.equals(endpoint2)) {
                    endpoint3 = endpoint2;
                    endpointSizeInBytes2 = i;
                } else {
                    endpointSizeInBytes2 = V2SpanWriter.endpointSizeInBytes(endpoint3, true);
                    i = endpointSizeInBytes2;
                }
                i5 += V2SpanWriter.annotationSizeInBytes(v1Annotation.timestamp(), v1Annotation.value(), endpointSizeInBytes2);
                i6++;
                endpoint2 = endpoint3;
            }
            i3 = i5;
            endpoint = endpoint2;
        } else {
            i = 0;
        }
        int size2 = v1Span.binaryAnnotations().size();
        if (size2 > 0) {
            int i7 = i3 + 23;
            if (size2 > 1) {
                i7 += size2 - 1;
            }
            Endpoint endpoint4 = endpoint;
            int i8 = i7;
            int i9 = 0;
            while (i9 < size2) {
                int i10 = i9 + 1;
                V1BinaryAnnotation v1BinaryAnnotation = v1Span.binaryAnnotations().get(i9);
                Endpoint endpoint5 = v1BinaryAnnotation.endpoint();
                if (endpoint5 == null) {
                    endpoint5 = endpoint4;
                    endpointSizeInBytes = 0;
                } else if (endpoint5.equals(endpoint4)) {
                    endpoint5 = endpoint4;
                    endpointSizeInBytes = i;
                } else {
                    endpointSizeInBytes = V2SpanWriter.endpointSizeInBytes(endpoint5, true);
                    i = endpointSizeInBytes;
                }
                i8 = v1BinaryAnnotation.stringValue() != null ? i8 + binaryAnnotationSizeInBytes(v1BinaryAnnotation.key(), v1BinaryAnnotation.stringValue(), endpointSizeInBytes) : i8 + 37 + endpointSizeInBytes;
                endpoint4 = endpoint5;
                i9 = i10;
            }
            i3 = i8;
        }
        if (Boolean.TRUE.equals(v1Span.debug())) {
            i3 += 13;
        }
        return i3 + 1;
    }

    @Override // zipkin2.internal.Buffer.Writer
    public void write(V1Span v1Span, Buffer buffer) {
        Endpoint endpoint;
        byte[] bArr;
        byte[] legacyEndpointBytes;
        byte[] legacyEndpointBytes2;
        buffer.writeAscii("{\"traceId\":\"");
        if (v1Span.traceIdHigh() != 0) {
            buffer.writeLongHex(v1Span.traceIdHigh());
        }
        buffer.writeLongHex(v1Span.traceId()).writeByte(34);
        if (v1Span.parentId() != 0) {
            buffer.writeAscii(",\"parentId\":\"").writeLongHex(v1Span.parentId()).writeByte(34);
        }
        buffer.writeAscii(",\"id\":\"").writeLongHex(v1Span.id()).writeByte(34);
        buffer.writeAscii(",\"name\":\"");
        if (v1Span.name() != null) {
            buffer.writeUtf8(JsonEscaper.jsonEscape(v1Span.name()));
        }
        buffer.writeByte(34);
        if (v1Span.timestamp() != 0) {
            buffer.writeAscii(",\"timestamp\":").writeAscii(v1Span.timestamp());
        }
        if (v1Span.duration() != 0) {
            buffer.writeAscii(",\"duration\":").writeAscii(v1Span.duration());
        }
        int size = v1Span.annotations().size();
        int i = 0;
        if (size > 0) {
            buffer.writeAscii(",\"annotations\":[");
            int i2 = 0;
            endpoint = null;
            bArr = null;
            while (i2 < size) {
                int i3 = i2 + 1;
                V1Annotation v1Annotation = v1Span.annotations().get(i2);
                Endpoint endpoint2 = v1Annotation.endpoint();
                if (endpoint2 == null) {
                    endpoint2 = endpoint;
                    legacyEndpointBytes2 = null;
                } else if (endpoint2.equals(endpoint)) {
                    endpoint2 = endpoint;
                    legacyEndpointBytes2 = bArr;
                } else {
                    legacyEndpointBytes2 = legacyEndpointBytes(endpoint2);
                    bArr = legacyEndpointBytes2;
                }
                V2SpanWriter.writeAnnotation(v1Annotation.timestamp(), v1Annotation.value(), legacyEndpointBytes2, buffer);
                if (i3 < size) {
                    buffer.writeByte(44);
                }
                endpoint = endpoint2;
                i2 = i3;
            }
            buffer.writeByte(93);
        } else {
            endpoint = null;
            bArr = null;
        }
        int size2 = v1Span.binaryAnnotations().size();
        if (size2 > 0) {
            buffer.writeAscii(",\"binaryAnnotations\":[");
            while (i < size2) {
                int i4 = i + 1;
                V1BinaryAnnotation v1BinaryAnnotation = v1Span.binaryAnnotations().get(i);
                Endpoint endpoint3 = v1BinaryAnnotation.endpoint();
                if (endpoint3 == null) {
                    endpoint3 = endpoint;
                    legacyEndpointBytes = null;
                } else if (endpoint3.equals(endpoint)) {
                    endpoint3 = endpoint;
                    legacyEndpointBytes = bArr;
                } else {
                    legacyEndpointBytes = legacyEndpointBytes(endpoint3);
                    bArr = legacyEndpointBytes;
                }
                if (v1BinaryAnnotation.stringValue() != null) {
                    writeBinaryAnnotation(v1BinaryAnnotation.key(), v1BinaryAnnotation.stringValue(), legacyEndpointBytes, buffer);
                } else {
                    buffer.writeAscii("{\"key\":\"").writeAscii(v1BinaryAnnotation.key());
                    buffer.writeAscii("\",\"value\":true,\"endpoint\":");
                    buffer.write(legacyEndpointBytes);
                    buffer.writeByte(125);
                }
                if (i4 < size2) {
                    buffer.writeByte(44);
                }
                endpoint = endpoint3;
                i = i4;
            }
            buffer.writeByte(93);
        }
        if (Boolean.TRUE.equals(v1Span.debug())) {
            buffer.writeAscii(",\"debug\":true");
        }
        buffer.writeByte(125);
    }

    static byte[] legacyEndpointBytes(@Nullable Endpoint endpoint) {
        if (endpoint == null) {
            return null;
        }
        Buffer buffer = new Buffer(V2SpanWriter.endpointSizeInBytes(endpoint, true));
        V2SpanWriter.writeEndpoint(endpoint, buffer, true);
        return buffer.toByteArray();
    }

    static int binaryAnnotationSizeInBytes(String str, String str2, int i) {
        int jsonEscapedSizeInBytes = JsonEscaper.jsonEscapedSizeInBytes(str) + 21 + JsonEscaper.jsonEscapedSizeInBytes(str2);
        return i != 0 ? jsonEscapedSizeInBytes + 12 + i : jsonEscapedSizeInBytes;
    }

    static void writeBinaryAnnotation(String str, String str2, @Nullable byte[] bArr, Buffer buffer) {
        buffer.writeAscii("{\"key\":\"").writeUtf8(JsonEscaper.jsonEscape(str));
        buffer.writeAscii("\",\"value\":\"").writeUtf8(JsonEscaper.jsonEscape(str2)).writeByte(34);
        if (bArr != null) {
            buffer.writeAscii(",\"endpoint\":").write(bArr);
        }
        buffer.writeAscii("}");
    }
}
