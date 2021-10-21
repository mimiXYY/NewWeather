package Bean;

import com.google.gson.annotations.SerializedName;

public class Skycon {
    private String date;
    @SerializedName("value")
    private String weather;

    public String getDate() {
        return date;
    }

    public void setDate(String ate) {
        this.date = ate;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
