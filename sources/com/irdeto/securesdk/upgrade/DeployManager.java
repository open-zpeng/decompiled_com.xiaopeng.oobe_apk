package com.irdeto.securesdk.upgrade;

import android.content.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class DeployManager {
    private static final int ERROR = 1;
    private static final int SUCCESS = 0;
    private static final String bak_dir = ".irmsdk_bak";
    private static final String flag_file = "IRDETO";
    private static final String tmp_dir = ".irmsdk_tmp";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v18, types: [java.lang.String] */
    private void createFlagFile(Context context) {
        BufferedWriter bufferedWriter;
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(new File(String.valueOf(context.getFilesDir().getPath()) + MqttTopic.TOPIC_LEVEL_SEPARATOR + flag_file), false));
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
            try {
                ?? r5 = "load success !";
                bufferedWriter.write("load success !");
                bufferedWriter.flush();
                bufferedWriter.close();
                bufferedWriter2 = r5;
            } catch (IOException e2) {
                e = e2;
                bufferedWriter2 = bufferedWriter;
                e.printStackTrace();
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                    bufferedWriter2 = bufferedWriter2;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedWriter2 = bufferedWriter;
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    private void deleteFlagFile(Context context) {
        File file = new File(String.valueOf(context.getFilesDir().getPath()) + MqttTopic.TOPIC_LEVEL_SEPARATOR + flag_file);
        if (file.exists()) {
            file.delete();
        }
    }

    private int init(Context context) {
        String path = context.getFilesDir().getPath();
        if (new File(String.valueOf(path) + MqttTopic.TOPIC_LEVEL_SEPARATOR + flag_file).exists()) {
            return 0;
        }
        O00000Oo.O00000Oo(path);
        File file = new File(String.valueOf(path) + MqttTopic.TOPIC_LEVEL_SEPARATOR + bak_dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        O000000o O00000o0 = O00000Oo.O00000o0(file.getPath());
        if (O00000o0 == null) {
            O00000Oo.O000000o(path, context);
            O00000Oo.O00000Oo(file.getPath());
            O00000Oo.O000000o(file.getPath(), context);
        } else {
            O00000Oo.O000000o(O00000o0, path);
            O00000Oo.O00000Oo(file.getPath());
        }
        if (irmsdk_verify(path) != 0) {
            System.exit(0);
        }
        return 0;
    }

    private int upgrade(Context context, O000000o o000000o) {
        int i;
        String path = context.getFilesDir().getPath();
        String str = String.valueOf(path) + MqttTopic.TOPIC_LEVEL_SEPARATOR + tmp_dir;
        String str2 = String.valueOf(path) + MqttTopic.TOPIC_LEVEL_SEPARATOR + bak_dir;
        String O000000o = O00000Oo.O000000o(o000000o.O000000o.getPath());
        O000000o O00000o0 = O00000Oo.O00000o0(path);
        String O000000o2 = O00000Oo.O000000o(O00000o0.O000000o.getPath());
        boolean z = false;
        if (O00000o0 == null || O00000Oo.O00000Oo(O000000o2, O000000o) <= 0) {
            i = 0;
            z = true;
        } else {
            i = 1;
        }
        if (z && irmsdk_verify(str) != 0) {
            i = 1;
        }
        O00000Oo.O00000Oo(str2);
        if (O00000o0 != null) {
            O00000Oo.O000000o(O00000o0, str2);
        }
        O00000Oo.O00000Oo(path);
        O00000Oo.O000000o(o000000o, path);
        O00000Oo.O00000Oo(str);
        return i;
    }

    public int irmsdk_upgradeInit(Context context) {
        int i;
        boolean z = false;
        if (context == null) {
            i = 1;
        } else {
            i = 0;
            z = true;
        }
        if (z) {
            String str = "/data/data/" + context.getApplicationInfo().packageName;
            String str2 = String.valueOf(str) + "/files";
            String str3 = String.valueOf(str) + "/files_tmp";
            synchronized (this) {
                if (!new File(str3).exists()) {
                    try {
                        Runtime.getRuntime().exec("ln -s " + str2 + " " + str3).waitFor();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            O000000o O00000o0 = O00000Oo.O00000o0(String.valueOf(str2) + MqttTopic.TOPIC_LEVEL_SEPARATOR + tmp_dir);
            if (O00000o0 == null) {
                init(context);
            } else {
                upgrade(context, O00000o0);
            }
            deleteFlagFile(context);
            try {
                System.load(O00000Oo.O00000o0(str3).O000000o.getPath());
                createFlagFile(context);
            } catch (Throwable th) {
                th.printStackTrace();
                return 1;
            }
        }
        return i;
    }

    public int irmsdk_verify(String str) {
        O000000o O00000o0;
        if (str == null || (O00000o0 = O00000Oo.O00000o0(str)) == null) {
            return 1;
        }
        if (O00000Oo.O000000o(O00000o0.O000000o.getPath(), O00000o0.O00000Oo.getPath()) && O00000Oo.O000000o(O00000o0.O00000o0.getPath(), O00000o0.O00000o.getPath())) {
            return 0;
        }
        O00000Oo.O00000Oo(str);
        return 1;
    }
}
