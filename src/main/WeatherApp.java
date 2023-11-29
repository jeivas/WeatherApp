package main;

import com.google.gson.Gson;
import models.City;
import models.CityInfo;
import models.DeserializeJson;
import models.JSON;

import java.io.IOException;

public class WeatherApp {

    public static String getLocation(String city) {
        if(city.contains(" ")) city = city.replace(" ", "+");

        String address = "https://geocoding-api.open-meteo.com/v1/search?name=" + city + "&count=10&language=en&format=json";
        try {
            return new JSON(address).getJsonResponse();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage() + " City not found");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getWeatherJson(String lat, String longi){
        String address = "https://api.open-meteo.com/v1/forecast?latitude=" + lat + "&longitude=" + longi +"&current=temperature_2m,relative_humidity_2m,wind_speed_10m,weather_code";
        try {
            String json = new JSON(address).getJsonResponse();
            return json;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static City getCityObject(CityInfo cityInfo){
        Gson gson = new Gson();
        try {
            String searchTemp = WeatherApp.getWeatherJson(cityInfo.getLatitude(), cityInfo.getLongitude());
            DeserializeJson cityWeather = gson.fromJson(searchTemp, DeserializeJson.class);
            return cityWeather.getCurrent();
        } catch (NullPointerException e) {
            System.out.println("Location not found :( Please write a valid location");
            throw new RuntimeException(e);
        }
        //Gets the weather from the api call
    }
}
