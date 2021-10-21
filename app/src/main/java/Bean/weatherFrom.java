package Bean;

public class weatherFrom {
    private static final String CLEAR_DAY = "CLEAR_DAY";
    private static final String CLEAR_NIGHT = "CLEAR_NIGHT";
    private static final String PARTLY_CLOUDY_DAY = "PARTLY_CLOUDY_DAY";
    private static final String PARTLY_CLOUDY_NIGHT = "PARTLY_CLOUDY_NIGHT";
    private static final String CLOUDY = "CLOUDY";
    private static final String LIGHT_HAZE = "LIGHT_HAZE";
    private static final String MODERATE_HAZE = "MODERATE_HAZE";
    private static final String HEAVY_HAZE = "HEAVY_HAZE";
    private static final String LIGHT_RAIN = "LIGHT_RAIN";
    private static final String MODERATE_RAIN = "MODERATE_RAIN";
    private static final String HEAVY_RAIN = "HEAVY_RAIN";
    private static final String STORM_RAIN = "STORM_RAIN";
    private static final String FOG = "FOG";
    private static final String LIGHT_SNOW = "LIGHT_SNOW";
    private static final String MODERATE_SNOW = "MODERATE_SNOW";
    private static final String HEAVY_SNOW = "HEAVY_SNOW";
    private static final String STORM_SNOW = "STORM_SNOW";
    private static final String WIND = "WIND";

    public static String weather(String weahter) {
        switch (weahter) {
            case CLEAR_DAY:
                return "晴天";
            case CLEAR_NIGHT:
                return "晴天";
            case PARTLY_CLOUDY_DAY:
                return "多云";
            case PARTLY_CLOUDY_NIGHT:
                return "多云";
            case CLOUDY:
                return "阴天";
            case LIGHT_HAZE:
                return "轻度雾霾";
            case MODERATE_HAZE:
                return "中度雾霾";
            case HEAVY_HAZE:
                return "重度雾霾";
            case LIGHT_RAIN:
                return "小雨";
            case MODERATE_RAIN:
                return "中雨";
            case HEAVY_RAIN:
                return "大雨";
            case STORM_RAIN:
                return "暴雨";
            case FOG:
                return "雾天";
            case LIGHT_SNOW:
                return "小雪";
            case MODERATE_SNOW:
                return "中雪";
            case HEAVY_SNOW:
                return "大雪";
            case STORM_SNOW:
                return "暴雪";
            case WIND:
                return "大风";
            default:
                return "";
        }
    }
}
