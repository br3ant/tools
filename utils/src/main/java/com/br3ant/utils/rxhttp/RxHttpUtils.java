package com.br3ant.utils.rxhttp;

import com.br3ant.utils.GsonUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttp;
import rxhttp.wrapper.converter.GsonConverter;

/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/8/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RxHttpUtils {
    public static void init() {

        RxHttp.init(new OkHttpClient.Builder()
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS)
                .connectTimeout(20000, TimeUnit.MILLISECONDS).build());

        RxHttp.setConverter(GsonConverter.create(GsonUtil.INSTANCE.getGson()));
    }
}
