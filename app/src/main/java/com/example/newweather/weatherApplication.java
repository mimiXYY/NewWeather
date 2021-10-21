package com.example.newweather;

import android.app.Application;
import android.content.Context;

public class weatherApplication extends Application {
    private static Context context;
    //天气
    public static String WeatherTOKEN = "oCbSYWlM4xZqU4Rw/";
    //经纬度
    public static String AK = "GSaopeuFeujxdKtTgLMHZ7TNiEPm0loj";
    //
    public static String bingYing = "http://guolin.tech/api/bing_pic";

    /**
     * 百度获取经纬度TOKEN
     */
    public static String BaiDuTOKEN(String city) {
        return "https://api.map.baidu.com/geocoding/v3/?address=" + city + "&output=json&ak="
                + "GSaopeuFeujxdKtTgLMHZ7TNiEPm0loj&callback=showLocation //GET请求";
    }

    /**
     * 彩云实时天气TOKEN
     */
    public static String WeatherTOKEN(String lngLat) {
        return "https://api.caiyunapp.com/v2.5/oCbSYWlM4xZqU4Rw/" + lngLat + "/realtime.json";
    }

    /**
     * 彩云预报天气TOKEN
     */
    public static String ForecastWeather(String lngLat) {
        return "https://api.caiyunapp.com/v2.5/" + WeatherTOKEN + lngLat + "/daily.json";
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
