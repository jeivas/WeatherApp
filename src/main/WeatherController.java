package main;

import com.google.gson.Gson;
import models.City;
import models.CityInfo;
import models.DeserializeJson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherController extends JFrame {
    private String searchFieldResult;

    public String getSearchFieldResult() {
        return searchFieldResult;
    }

    public WeatherController() {
        super("Weather app");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(450, 650);

        setLocationRelativeTo(null);

        setLayout(null);

        setResizable(false);

        addGuiComponents();
    }

    private void addGuiComponents() {
        Gson gson = new Gson();

        JTextField searchField = new JTextField(10);
        searchField.setBounds(15, 15, 350, 45);
        searchField.setFont(new Font("Dialog", Font.PLAIN, 24));

        add(searchField);

        if (searchField.getText().isEmpty()) {
            searchField.setText("Type the city:");
            searchField.setForeground(new Color(150, 150, 150));
        }

        searchField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                searchField.setText("");
                searchField.setForeground(new Color(50, 50, 50));
            }

            @Override
            public void focusLost(FocusEvent e) {

                if (searchField.getText().isEmpty()) {
                    searchField.setText("Type the city:");
                    searchField.setForeground(new Color(150, 150, 150));
                }

            }
        });

        JButton searchButton = new JButton(loadImage("src/assets/search.png"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBounds(375, 13, 45, 45);
        add(searchButton);

        JLabel cityText = new JLabel("-");
        cityText.setBounds(-8, 70, 450, 60);
        cityText.setFont(new Font("Description", Font.PLAIN, 28));
        cityText.setHorizontalAlignment(SwingConstants.CENTER);
        add(cityText);

        JLabel weatherImage = new JLabel(loadImage("src/assets/cloudy.png"));
        weatherImage.setBounds(-8, 140, 450, 220);
        add(weatherImage);

        JLabel temperatureText = new JLabel("-");
        temperatureText.setBounds(-8, 375, 450, 60);
        temperatureText.setFont(new Font("Degrees", Font.BOLD, 48));
        temperatureText.setHorizontalAlignment(SwingConstants.CENTER);
        add(temperatureText);

        JLabel weatherDescription = new JLabel("-");
        weatherDescription.setBounds(-8, 415, 450, 60);
        weatherDescription.setFont(new Font("Description", Font.PLAIN, 28));
        weatherDescription.setHorizontalAlignment(SwingConstants.CENTER);
        add(weatherDescription);

        JLabel humidityImage = new JLabel(loadImage("src/assets/humidity.png"));
        humidityImage.setBounds(15, 495, 74, 70);
        add(humidityImage);

        JLabel humidityText = new JLabel("<html><b>humidity</b> -</html>");
        humidityText.setBounds(86, 495, 70, 70);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(humidityText);

        JLabel windImage = new JLabel(loadImage("src/assets/windspeed.png"));
        windImage.setBounds(220, 495, 74, 70);
        add(windImage);

        JLabel windSpeedText = new JLabel("<html><b>wind speed</b> -</html>");
        windSpeedText.setBounds(305, 495, 90, 70);
        windSpeedText.setFont(new Font("Dialog", Font.PLAIN, 16));
        add(windSpeedText);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFieldResult = searchField.getText();
                String searchResultJson = WeatherApp.getLocation(searchFieldResult);
                DeserializeJson cityInfo = gson.fromJson(searchResultJson, DeserializeJson.class);

                if(cityInfo.getResults() == null){
                    System.out.println("City not found");
                    return;
                }

                City cityObject = WeatherApp.getCityObject(cityInfo.getResults().get(0));

                // calls getWeatherInfo, function which returns an City object, then a get method is called to get the info needed
                String cityName = cityObject.getName();
                cityText.setText(cityName);

                String temperature = cityObject.getTemperature();
                temperatureText.setText(temperature + "C");

                String humidity = cityObject.getHumidity();
                humidityText.setText("<html><b>humidity</b> " + humidity + "%</html>");

                String windSpeed = cityObject.getWindSpeed();
                windSpeedText.setText("<html><b>wind speed</b> " + windSpeed + "km/h</html>");

                searchField.setText("Type the city:");
                searchField.setForeground(new Color(150, 150, 150));

                int weatherCode = Integer.parseInt(cityObject.getWeatherCode());

                if (weatherCode >= 0 && weatherCode < 3){
                    weatherImage.setIcon(loadImage("src/assets/clear.png"));
                    weatherDescription.setText("Clear");
                } else if (weatherCode >= 3 && weatherCode < 49){
                    weatherImage.setIcon(loadImage("src/assets/cloudy.png"));
                    weatherDescription.setText("Cloudy");
                } else if (weatherCode >= 51 && weatherCode < 68) {
                    weatherImage.setIcon(loadImage("src/assets/rain.png"));
                    weatherDescription.setText("Rainy");
                } else if (weatherCode >= 71 && weatherCode < 78) {
                    weatherImage.setIcon(loadImage("src/assets/snow.png"));
                    weatherDescription.setText("Snowy");
                } else if (weatherCode >= 80 && weatherCode < 83) {
                    weatherImage.setIcon(loadImage("src/assets/rain.png"));
                    weatherDescription.setText("Rainy");
                } else if (weatherCode >= 85 && weatherCode < 87) {
                    weatherImage.setIcon(loadImage("src/assets/snow.png"));
                    weatherDescription.setText("Snowy");
                } else if (weatherCode >= 95 && weatherCode < 100) {
                    weatherImage.setIcon(loadImage("src/assets/rain.png"));
                    weatherDescription.setText("Rainy");
                }
            }
        });
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not find any resource");
        return null;
    }

    private void removePlaceHolder(JTextField textField) {
        textField.setText("");
    }

    private void addPlaceHolder(JTextField textField) {
        textField.setForeground(new java.awt.Color(86, 86, 86));
        textField.setCaretPosition(0);
        textField.setText("Type your city:");
    }
}
