# Weather Application in Java

## Overview

Welcome to the Weather Application repository! This Java-based application allows users to retrieve real-time weather information for a specified city. The application leverages the Open-Meteo Geocoding and Open-Meteo Weather Forecast APIs to gather detailed data about the weather conditions, including description, temperature, humidity, and wind speed.

## Features

- **User-Friendly Interface**: The application provides a simple and intuitive interface for users to input the name of a city.
  
- **Accurate Weather Information**: Utilizing the Open-Meteo Geocoding API, the application accurately determines the location of the specified city. The Open-Meteo Weather Forecast API then provides up-to-date weather information.

- **Comprehensive Data**: Users receive a detailed weather report, including a description of the weather conditions, current temperature, humidity level, and wind speed.

- **JSON Deserialization with Gson**: The application uses the Gson library to efficiently deserialize the JSON responses from the Open-Meteo APIs, making it easy to work with the obtained data in a Java-friendly manner.

## How to Use

1. **Clone the Repository**: Begin by cloning this repository to your local machine.

2. **API Key Setup**: Obtain API keys for the Open-Meteo Geocoding and Weather Forecast APIs. Replace the placeholder API keys in the code with your own keys.

3. **Build and Run**: Build the application and run the main class. Enter the name of the city when prompted.

4. **View Weather Information**: The application will fetch and display detailed weather information for the specified city.

## Dependencies

- [Gson](https://github.com/google/gson): A library for JSON serialization and deserialization in Java.

## Contribution

Contributions are welcome! If you find issues or have suggestions for improvements, feel free to create a pull request or open an issue.

Thank you for using the Weather Application. Stay informed about the weather with just a few clicks! 🌦️

---
