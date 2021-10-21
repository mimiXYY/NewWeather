package weatherHelp;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.newweather.IWeather;
import com.example.newweather.weatherApplication;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import Bean.Skycon;
import Bean.Temperature;
import Bean.weather;
import Util.HttpUtil;
import Util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class weatherHelper {
    private static final String TAG = "weatherHelper";
    private weatherHandler mHandler;
    private TextView temperatureTextView, weatherTextView;
    private static final int MSG_CODE = 1;
    public Map map;

    /**
     * 处理城市经纬度
     *
     * @param address
     */
    public void cityLngLat(String address) {
        HttpUtil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String responseJson = response.body().string();
                        String lngLat = Utility.handlBaiDuResponse(responseJson);
                        RealWeather(lngLat);
                        ForecastWeather(lngLat);
                    }
                }
        );
    }

    /**
     * 处理实时天气
     */
    public void RealWeather(String lngLat) {

        if (lngLat != null) {
            String s = weatherApplication.WeatherTOKEN(lngLat);
            HttpUtil.sendOkHttpRequest(s, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String responseJson = response.body().string();
                    String[] arrays = Utility.handlWeatherResponse(responseJson);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = arrays;
                    mHandler.sendMessage(message);
                }
            });
        }
    }

    /**
     * 处理预报天气
     */
    public void ForecastWeather(String lngLat) {
        if (lngLat != null) {
            String s = weatherApplication.ForecastWeather(lngLat);
            Log.d(TAG, "ForecastWeather: " + s);
            HttpUtil.sendOkHttpRequest(s, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    List<weather> list = Utility.handlForecastWeatherResponse(response.body().string());
                    iWeather.setWeather(list);
                }
            });
        }
    }

    public weatherHelper(TextView temperatureTextView, TextView weatherTextView) {
        mHandler = new weatherHandler(this);
        this.temperatureTextView = temperatureTextView;
        this.weatherTextView = weatherTextView;
    }

    static class weatherHandler extends Handler {
        private WeakReference<weatherHelper> weakReference;

        public weatherHandler(weatherHelper weatherHelper) {
            super(Looper.getMainLooper());
            weakReference = new WeakReference<>(weatherHelper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String[] obj = (String[]) msg.obj;
                    if (obj.length == 2) {
                        weakReference.get().weatherTextView.setText(obj[0]);
                        weakReference.get().temperatureTextView.setText(obj[1]);
                    }
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }

    private IWeather iWeather;
    public void setIWeather(IWeather iWeather) {
        this.iWeather = iWeather;
    }
}


