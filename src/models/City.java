package models;

import com.google.gson.annotations.SerializedName;

public class City extends CityInfo{
    @SerializedName("temperature_2m")
    private String temperature;
    @SerializedName("relative_humidity_2m")
    private String humidity;
    @SerializedName("wind_speed_10m")
    private String windSpeed;
    @SerializedName("weather_code")
    private String weatherCode;

    public String getWeatherCode() {
        return weatherCode;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }
}
