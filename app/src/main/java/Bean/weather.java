package Bean;

public class weather {
    private String date;
    private String max;
    private String mix;
    private String weather;

    public weather(String date, String max, String mix, String weather) {
        this.date = date;
        this.max = max;
        this.mix = mix;
        this.weather = weather;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMix() {
        return mix;
    }

    public void setMix(String mix) {
        this.mix = mix;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
