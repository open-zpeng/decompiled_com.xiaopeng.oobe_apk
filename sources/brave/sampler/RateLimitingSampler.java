package brave.sampler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public class RateLimitingSampler extends Sampler {
    final MaxFunction maxFunction;
    final AtomicLong nextReset;
    final AtomicInteger usage = new AtomicInteger(0);
    static final long NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    static final int NANOS_PER_DECISECOND = (int) (NANOS_PER_SECOND / 10);

    public static Sampler create(int i) {
        if (i >= 0) {
            if (i == 0) {
                return Sampler.NEVER_SAMPLE;
            }
            return new RateLimitingSampler(i);
        }
        throw new IllegalArgumentException("tracesPerSecond < 0");
    }

    RateLimitingSampler(int i) {
        this.maxFunction = i < 10 ? new LessThan10(i) : new AtLeast10(i);
        this.nextReset = new AtomicLong(System.nanoTime() + NANOS_PER_SECOND);
    }

    @Override // brave.sampler.Sampler
    public boolean isSampled(long j) {
        int i;
        int i2;
        long nanoTime = System.nanoTime();
        long j2 = this.nextReset.get();
        long j3 = -(nanoTime - j2);
        boolean z = j3 <= 0;
        if (z && this.nextReset.compareAndSet(j2, NANOS_PER_SECOND + j2)) {
            this.usage.set(0);
        }
        MaxFunction maxFunction = this.maxFunction;
        if (z) {
            j3 = 0;
        }
        int max = maxFunction.max(j3);
        do {
            i = this.usage.get();
            i2 = i + 1;
            if (i2 > max) {
                return false;
            }
        } while (!this.usage.compareAndSet(i, i2));
        return true;
    }

    /* loaded from: classes.dex */
    static abstract class MaxFunction {
        abstract int max(long j);

        MaxFunction() {
        }
    }

    /* loaded from: classes.dex */
    static final class LessThan10 extends MaxFunction {
        final int tracesPerSecond;

        LessThan10(int i) {
            this.tracesPerSecond = i;
        }

        @Override // brave.sampler.RateLimitingSampler.MaxFunction
        int max(long j) {
            return this.tracesPerSecond;
        }
    }

    /* loaded from: classes.dex */
    static final class AtLeast10 extends MaxFunction {
        final int[] max = new int[10];

        AtLeast10(int i) {
            int i2 = i / 10;
            this.max[0] = (i % 10) + i2;
            for (int i3 = 1; i3 < 10; i3++) {
                int[] iArr = this.max;
                iArr[i3] = iArr[i3 - 1] + i2;
            }
        }

        @Override // brave.sampler.RateLimitingSampler.MaxFunction
        int max(long j) {
            int i = ((int) j) / RateLimitingSampler.NANOS_PER_DECISECOND;
            return this.max[i == 0 ? 0 : 10 - i];
        }
    }
}
