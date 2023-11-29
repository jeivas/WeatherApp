package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
}
