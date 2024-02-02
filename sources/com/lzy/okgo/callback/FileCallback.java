package com.lzy.okgo.callback;

import com.lzy.okgo.convert.FileConvert;
import java.io.File;
import okhttp3.Response;
/* loaded from: classes.dex */
public abstract class FileCallback extends AbsCallback<File> {
    private FileConvert convert;

    public FileCallback() {
        this(null);
    }

    public FileCallback(String str) {
        this(null, str);
    }

    public FileCallback(String str, String str2) {
        this.convert = new FileConvert(str, str2);
        this.convert.setCallback(this);
    }

    @Override // com.lzy.okgo.convert.Converter
    public File convertResponse(Response response) throws Throwable {
        File convertResponse = this.convert.convertResponse(response);
        response.close();
        return convertResponse;
    }
}
