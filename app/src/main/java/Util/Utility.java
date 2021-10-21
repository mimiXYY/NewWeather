package Util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Bean.Temperature;
import Bean.Skycon;
import Bean.weather;
import Bean.weatherFrom;

public class Utility {

    private static final String TAG = "Util";

    /**
     * 处理百度API返回的城市经纬度
     */
    public static String handlBaiDuResponse(String response) {
        String lng = null;
        String lat = null;
        if (!TextUtils.isEmpty(response)) {
            try {
                Log.d(TAG, response);
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("location");
                for (int i = 0; i < jsonObject2.length(); i++) {
                    lng = jsonObject2.getString("lng");
                    lat = jsonObject2.getString("lat");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return lng + "," + lat;
    }

    /**
     * 处理城市实时温度
     */
    public static String[] handlWeatherResponse(String response) {
        String weather = null;
        String temperature = null;
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("realtime");
                for (int i = 0; i < jsonObject2.length(); i++) {
                    temperature = jsonObject2.getString("temperature");
                    weather = jsonObject2.getString("skycon");
                    weather = weatherFrom.weather(weather);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new String[]{weather, temperature};
    }

    /**
     * 处理城市预报温度
     */
    public static List handlForecastWeatherResponse(String response) {
        List<weather> list = new ArrayList<>();
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("daily");
                JSONArray temperatureArray = jsonObject2.getJSONArray("temperature");
                JSONArray skyconArray = jsonObject2.getJSONArray("skycon");
                Log.d(TAG, "handlForecastWeatherResponse:" + skyconArray.toString());
                Gson gson = new Gson();
                List<Temperature> temperatureList =
                        gson.fromJson(temperatureArray.toString(), new TypeToken<List<Temperature>>() {
                        }.getType());
                List<Skycon> skyconList = gson.fromJson(skyconArray.toString(), new TypeToken<List<Skycon>>() {
                }.getType());
                list = ListUtil.ArraysUtility(temperatureList, skyconList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
