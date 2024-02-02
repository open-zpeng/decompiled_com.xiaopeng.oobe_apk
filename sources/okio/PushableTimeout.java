package okio;

import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
final class PushableTimeout extends Timeout {
    private long originalDeadlineNanoTime;
    private boolean originalHasDeadline;
    private long originalTimeoutNanos;
    private Timeout pushed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void push(Timeout timeout) {
        this.pushed = timeout;
        this.originalHasDeadline = timeout.hasDeadline();
        this.originalDeadlineNanoTime = this.originalHasDeadline ? timeout.deadlineNanoTime() : -1L;
        this.originalTimeoutNanos = timeout.timeoutNanos();
        timeout.timeout(minTimeout(this.originalTimeoutNanos, timeoutNanos()), TimeUnit.NANOSECONDS);
        if (this.originalHasDeadline && hasDeadline()) {
            timeout.deadlineNanoTime(Math.min(deadlineNanoTime(), this.originalDeadlineNanoTime));
        } else if (hasDeadline()) {
            timeout.deadlineNanoTime(deadlineNanoTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pop() {
        this.pushed.timeout(this.originalTimeoutNanos, TimeUnit.NANOSECONDS);
        if (this.originalHasDeadline) {
            this.pushed.deadlineNanoTime(this.originalDeadlineNanoTime);
        } else {
            this.pushed.clearDeadline();
        }
    }
}
