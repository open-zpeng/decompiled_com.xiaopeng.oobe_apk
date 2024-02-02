package com.xiaopeng.oobe.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.xiaopeng.lib.utils.LogUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/* loaded from: classes.dex */
public class FileUtils {
    private static final String TAG = "oobe/FileUtils";

    public static Bitmap downloadFile(String str) {
        InputStream inputStream;
        Closeable closeable;
        FileOutputStream fileOutputStream;
        try {
            try {
                URLConnection openConnection = new URL(str).openConnection();
                openConnection.setConnectTimeout(5000);
                inputStream = openConnection.getInputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
            inputStream = null;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
            closeable = null;
        }
        try {
            File createTempFile = File.createTempFile(Uri.encode(str), "jpg");
            fileOutputStream = new FileOutputStream(createTempFile);
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        LogUtils.d(TAG, "file download complete");
                        Bitmap file2Bitmap = file2Bitmap(createTempFile);
                        com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStream);
                        com.xiaopeng.lib.utils.FileUtils.closeQuietly(fileOutputStream);
                        return file2Bitmap;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                LogUtils.d(TAG, "file download error" + e.getMessage());
                com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStream);
                com.xiaopeng.lib.utils.FileUtils.closeQuietly(fileOutputStream);
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream = null;
        } catch (Throwable th3) {
            th = th3;
            closeable = null;
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStream);
            com.xiaopeng.lib.utils.FileUtils.closeQuietly(closeable);
            throw th;
        }
    }

    public static Bitmap file2Bitmap(File file) {
        if (file == null) {
            LogUtils.i(TAG, "file2Bitmap params file is null");
            return null;
        }
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    public static String readFromAssets(Context context, String str) {
        return readFromAssets(context, str, false);
    }

    public static String readFromAssets(Context context, String str, boolean z) {
        try {
            return inputStream2String(context.getAssets().open(str), z);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String inputStream2String(InputStream inputStream, boolean z) {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2 = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                String str = "";
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            if (z) {
                                str = str + "\r\n";
                            }
                            str = str + readLine;
                        } else {
                            inputStreamReader.close();
                            bufferedReader.close();
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStreamReader);
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(bufferedReader);
                            return str;
                        }
                    } catch (Exception e) {
                        e = e;
                        inputStreamReader2 = inputStreamReader;
                        try {
                            e.printStackTrace();
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStreamReader2);
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(bufferedReader);
                            return "";
                        } catch (Throwable th) {
                            th = th;
                            inputStreamReader = inputStreamReader2;
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStreamReader);
                            com.xiaopeng.lib.utils.FileUtils.closeQuietly(bufferedReader);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        com.xiaopeng.lib.utils.FileUtils.closeQuietly(inputStreamReader);
                        com.xiaopeng.lib.utils.FileUtils.closeQuietly(bufferedReader);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                bufferedReader = null;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        } catch (Exception e3) {
            e = e3;
            bufferedReader = null;
        } catch (Throwable th4) {
            th = th4;
            bufferedReader = null;
            inputStreamReader = null;
        }
    }
}
