package Util;

import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 网络请求工具类
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

}
