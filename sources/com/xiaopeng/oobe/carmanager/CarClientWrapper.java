package com.xiaopeng.oobe.carmanager;

import android.car.Car;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaopeng.lib.utils.LogUtils;
import com.xiaopeng.lib.utils.ThreadUtils;
import com.xiaopeng.oobe.App;
import com.xiaopeng.oobe.carmanager.impl.McuController;
import com.xiaopeng.oobe.carmanager.impl.ScuController;
import java.util.Hashtable;
/* loaded from: classes.dex */
public class CarClientWrapper {
    private static final String TAG = "CarClientWrapper";
    private Car mCarClient;
    public static final String XP_MCU_SERVICE = "xp_mcu";
    public static final String XP_SCU_SERVICE = "xp_scu";
    private static final String[] CAR_SVC_ARRAY = {XP_MCU_SERVICE, XP_SCU_SERVICE};
    private static CarClientWrapper sInstance = null;
    private final Object mCarClientReady = new Object();
    private boolean mIsCarSvcConnected = false;
    private final Object mControllerLock = new Object();
    private final Hashtable<String, BaseCarController> mControllers = new Hashtable<>();
    private final ServiceConnection mCarConnectionCb = new ServiceConnection() { // from class: com.xiaopeng.oobe.carmanager.CarClientWrapper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogUtils.d(CarClientWrapper.TAG, "onCarServiceConnected", false);
            CarClientWrapper.this.initCarControllers();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtils.d(CarClientWrapper.TAG, "onCarServiceDisconnected", false);
            CarClientWrapper.this.mIsCarSvcConnected = false;
        }
    };

    public static CarClientWrapper getInstance() {
        if (sInstance == null) {
            synchronized (CarClientWrapper.class) {
                if (sInstance == null) {
                    sInstance = new CarClientWrapper();
                }
            }
        }
        return sInstance;
    }

    private CarClientWrapper() {
    }

    public void connectToCar() {
        if (this.mIsCarSvcConnected) {
            return;
        }
        this.mCarClient = Car.createCar(App.getInstance(), this.mCarConnectionCb);
        this.mCarClient.connect();
        LogUtils.d(TAG, "Start to connect Car service", false);
    }

    private void reconnectToCar() {
        ThreadUtils.postBackground(new Runnable() { // from class: com.xiaopeng.oobe.carmanager.-$$Lambda$CarClientWrapper$ndRvdHl9tkgjzAFB-pvTaoh2FM4
            @Override // java.lang.Runnable
            public final void run() {
                CarClientWrapper.this.lambda$reconnectToCar$0$CarClientWrapper();
            }
        }, 200L);
    }

    public /* synthetic */ void lambda$reconnectToCar$0$CarClientWrapper() {
        LogUtils.d(TAG, "reconnect to car service", false);
        connectToCar();
    }

    public void disconnect() {
        Car car;
        if (!this.mIsCarSvcConnected || (car = this.mCarClient) == null) {
            return;
        }
        car.disconnect();
    }

    public boolean isCarServiceConnected() {
        synchronized (this.mCarClientReady) {
            while (!this.mIsCarSvcConnected) {
                try {
                    LogUtils.d(TAG, "Waiting car service connected", false);
                    this.mCarClientReady.wait();
                } catch (InterruptedException e) {
                    LogUtils.e(TAG, e.getMessage());
                }
            }
        }
        return true;
    }

    public BaseCarController getController(String str) {
        BaseCarController baseCarController;
        Car carClient = getCarClient();
        synchronized (this.mControllerLock) {
            baseCarController = this.mControllers.get(str);
            if (baseCarController == null) {
                baseCarController = createCarController(str, carClient);
                this.mControllers.put(str, baseCarController);
            }
        }
        return baseCarController;
    }

    private Car getCarClient() {
        Car car;
        synchronized (this.mCarClientReady) {
            while (!this.mIsCarSvcConnected) {
                try {
                    LogUtils.d(TAG, "Waiting car service connecting", false);
                    this.mCarClientReady.wait();
                } catch (InterruptedException e) {
                    LogUtils.e(TAG, e.getMessage());
                }
            }
            car = this.mCarClient;
        }
        return car;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCarControllers() {
        ThreadUtils.execute(new Runnable() { // from class: com.xiaopeng.oobe.carmanager.-$$Lambda$CarClientWrapper$Mb-MbTNCNAMTSjyAenYoE6BwqZc
            @Override // java.lang.Runnable
            public final void run() {
                CarClientWrapper.this.lambda$initCarControllers$1$CarClientWrapper();
            }
        });
    }

    public /* synthetic */ void lambda$initCarControllers$1$CarClientWrapper() {
        String[] strArr;
        LogUtils.d(TAG, "initCarControllers start");
        synchronized (this.mControllerLock) {
            for (String str : CAR_SVC_ARRAY) {
                BaseCarController baseCarController = this.mControllers.get(str);
                if (baseCarController == null) {
                    this.mControllers.put(str, createCarController(str, this.mCarClient));
                } else {
                    LogUtils.d(TAG, "re-initCarControllers", false);
                    baseCarController.disconnect();
                    baseCarController.initCarManager(this.mCarClient);
                }
            }
        }
        LogUtils.d(TAG, "initCarControllers end");
        synchronized (this.mCarClientReady) {
            this.mIsCarSvcConnected = true;
            this.mCarClientReady.notifyAll();
        }
    }

    private BaseCarController createCarController(String str, Car car) {
        char c;
        BaseCarController mcuController;
        int hashCode = str.hashCode();
        if (hashCode != -753096744) {
            if (hashCode == -753090978 && str.equals(XP_SCU_SERVICE)) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals(XP_MCU_SERVICE)) {
                c = 0;
            }
            c = 65535;
        }
        if (c == 0) {
            mcuController = new McuController();
        } else if (c == 1) {
            mcuController = new ScuController();
        } else {
            throw new IllegalArgumentException("Can not create controller for " + str);
        }
        mcuController.initCarManager(car);
        return mcuController;
    }
}
