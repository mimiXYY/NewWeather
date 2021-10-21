package Util;

import android.text.TextUtils;
import android.util.Log;

public class StirngUtil {
    public static String DateUtils(String date) {
        if (!TextUtils.isEmpty(date)) {
            return date.substring(5, 10);
        }
        return "";
    }

    public static String WeatherUtils(String weather) {
        if (!TextUtils.isEmpty(weather)) {
            boolean status = weather.contains(".");
            if (status) {
                return weather.substring(0, weather.indexOf("."));
            } else {
                return weather;
            }
        }
        return "";
    }
}
