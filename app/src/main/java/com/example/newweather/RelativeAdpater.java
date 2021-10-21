package com.example.newweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Bean.weather;

public class RelativeAdpater extends RecyclerView.Adapter<RelativeAdpater.ViewHolder> {
    private Context mContext;
    private List<weather> weatherList;

    public RelativeAdpater(List<weather> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public RelativeAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelativeAdpater.ViewHolder holder, int position) {
        weather weather = weatherList.get(position);
        holder.dateView.setText(weather.getDate());
        holder.maxView.setText(weather.getMax());
        holder.mixView.setText(weather.getMix());
        holder.weatherView.setText(weather.getWeather());
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateView, maxView, mixView, weatherView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            dateView = itemView.findViewById(R.id.dateView);
            maxView = itemView.findViewById(R.id.maxView);
            mixView = itemView.findViewById(R.id.mixView);
            weatherView = itemView.findViewById(R.id.weatherView);
        }
    }
}
