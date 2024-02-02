package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.Address;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.platform.Platform;
/* loaded from: classes2.dex */
public final class RealConnectionPool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Executor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    boolean cleanupRunning;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    private final Runnable cleanupRunnable = new Runnable() { // from class: okhttp3.internal.connection.-$$Lambda$RealConnectionPool$Cy61BJKpsrwSB_hQmB_R9MngVNU
        @Override // java.lang.Runnable
        public final void run() {
            RealConnectionPool.this.lambda$new$0$RealConnectionPool();
        }
    };
    private final Deque<RealConnection> connections = new ArrayDeque();
    final RouteDatabase routeDatabase = new RouteDatabase();

    public /* synthetic */ void lambda$new$0$RealConnectionPool() {
        while (true) {
            long cleanup = cleanup(System.nanoTime());
            if (cleanup == -1) {
                return;
            }
            if (cleanup > 0) {
                long j = cleanup / 1000000;
                long j2 = cleanup - (1000000 * j);
                synchronized (this) {
                    try {
                        wait(j, (int) j2);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    public RealConnectionPool(int i, long j, TimeUnit timeUnit) {
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    public synchronized int idleConnectionCount() {
        int i;
        i = 0;
        for (RealConnection realConnection : this.connections) {
            if (realConnection.transmitters.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public synchronized int connectionCount() {
        return this.connections.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean transmitterAcquirePooledConnection(Address address, Transmitter transmitter, @Nullable List<Route> list, boolean z) {
        for (RealConnection realConnection : this.connections) {
            if (!z || realConnection.isMultiplexed()) {
                if (realConnection.isEligible(address, list)) {
                    transmitter.acquireConnectionNoEvents(realConnection);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void put(RealConnection realConnection) {
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean connectionBecameIdle(RealConnection realConnection) {
        if (realConnection.noNewExchanges || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public void evictAll() {
        ArrayList<RealConnection> arrayList = new ArrayList();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.transmitters.isEmpty()) {
                    next.noNewExchanges = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        for (RealConnection realConnection : arrayList) {
            Util.closeQuietly(realConnection.socket());
        }
    }

    long cleanup(long j) {
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            int i = 0;
            RealConnection realConnection = null;
            int i2 = 0;
            for (RealConnection realConnection2 : this.connections) {
                if (pruneAndGetAllocationCount(realConnection2, j) > 0) {
                    i++;
                } else {
                    i2++;
                    long j3 = j - realConnection2.idleAtNanos;
                    if (j3 > j2) {
                        realConnection = realConnection2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.keepAliveDurationNs && i2 <= this.maxIdleConnections) {
                if (i2 > 0) {
                    return this.keepAliveDurationNs - j2;
                } else if (i > 0) {
                    return this.keepAliveDurationNs;
                } else {
                    this.cleanupRunning = false;
                    return -1L;
                }
            }
            this.connections.remove(realConnection);
            Util.closeQuietly(realConnection.socket());
            return 0L;
        }
    }

    private int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<Transmitter>> list = realConnection.transmitters;
        int i = 0;
        while (i < list.size()) {
            Reference<Transmitter> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                Platform.get().logCloseableLeak("A connection to " + realConnection.route().address().url() + " was leaked. Did you forget to close a response body?", ((Transmitter.TransmitterReference) reference).callStackTrace);
                list.remove(i);
                realConnection.noNewExchanges = true;
                if (list.isEmpty()) {
                    realConnection.idleAtNanos = j - this.keepAliveDurationNs;
                    return 0;
                }
            }
        }
        return list.size();
    }

    public void connectFailed(Route route, IOException iOException) {
        if (route.proxy().type() != Proxy.Type.DIRECT) {
            Address address = route.address();
            address.proxySelector().connectFailed(address.url().uri(), route.proxy().address(), iOException);
        }
        this.routeDatabase.failed(route);
    }
}
