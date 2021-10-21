package Util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Bean.Skycon;
import Bean.Temperature;
import Bean.weather;
import Bean.weatherFrom;

public class ListUtil {
    public static List ArraysUtility(List<Temperature> temperature, List<Skycon> skycons) {
        List<weather> weatherList = new ArrayList<>();
        for (int i = 0; i < temperature.size(); i++) {
            weather weather = new weather(StirngUtil.DateUtils(temperature.get(i).getDate()),
                    StirngUtil.WeatherUtils(temperature.get(i).getMax()), StirngUtil.WeatherUtils(temperature.get(i).getMin()),
                    weatherFrom.weather(skycons.get(i).getWeather()));
            weatherList.add(weather);
        }
        return weatherList;
    }
}
