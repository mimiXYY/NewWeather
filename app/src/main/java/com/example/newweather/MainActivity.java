package com.example.newweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Bean.Skycon;
import Bean.Temperature;
import Util.HttpUtil;
import Util.Utility;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import weatherHelp.weatherHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private TextView temperatureTextView, addressTextView, weatherTextView, degree;
    private weatherHelper weatherHelper;
    private static final String TAG = "Main";
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private GridLayoutManager LayoutManager;
    private RelativeAdpater adpater;
    private ImageView imageView;
    private EditText editText;
    private Button editButton;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini();
        iniTool();
        ImageGlide();
    }

    @Override
    public void onClick(View view) {
        String string = editText.getText().toString();
        text = string;
        new MyThread().start();
        if (degree.getText().length() == 0) {
            degree.setText("℃");
        }
    }

    /**
     * 监听
     */

    public void iniTool() {
        swipeRefreshLayout.setOnRefreshListener(this);
        editButton.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    public void ini() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        temperatureTextView = findViewById(R.id.temperature);
        addressTextView = findViewById(R.id.address);
        weatherTextView = findViewById(R.id.weather);
        weatherHelper = new weatherHelper(temperatureTextView, weatherTextView);
        swipeRefreshLayout = findViewById(R.id.swiper);
        recyclerView = findViewById(R.id.RecyclerView);
        LayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(LayoutManager);
        degree = findViewById(R.id.degree);
        imageView = findViewById(R.id.image);
        editText = findViewById(R.id.edit);
        editButton = findViewById(R.id.edit_button);
    }

    @Override
    public void onRefresh() {
        new MyThread().start();
        if (degree.getText().length() == 0) {
            degree.setText("℃");
        }
    }

    /**
     * 请求天气数据
     */
    public void first(String text) {
        String address = weatherApplication.BaiDuTOKEN(text);
        addressTextView.setText(text);
        weatherHelper.cityLngLat(address);
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //第一次刷新，为listview创建adpater
                    if (adpater == null) {
                        String string = editText.getText().toString();
                        if (string == null) {
                            text = string;
                        }
                        first(text);
                        weatherHelper.setIWeather(new IWeather() {
                            @Override
                            public void setWeather(List list) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adpater = new RelativeAdpater(list);
                                        recyclerView.setAdapter(adpater);
                                    }
                                });
                            }
                        });
                        swipeRefreshLayout.setRefreshing(false);
                    } else {
                        first(text);
                        adpater.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            });
        }
    }

    public void ImageGlide() {
        SharedPreferences pref = getSharedPreferences("bingYing", MODE_PRIVATE);
        String bing = pref.getString("bing", null);
        if (bing != null) {
            Glide.with(weatherApplication.getContext()).load(bing).into(imageView);
        } else {
            HttpUtil.sendOkHttpRequest(weatherApplication.bingYing, new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    final String ImageString = response.body().string();
                    SharedPreferences userInfo = getSharedPreferences("bingYing", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userInfo.edit();
                    editor.putString("bing", ImageString);
                    editor.apply();
                }
            });
        }
    }
}