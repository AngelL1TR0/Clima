package org.example;
import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String API_KEY = "ca6fa12599a3c704cde9cf32b1de0199";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre de la ciudad que quieras");
        String city = sc.nextLine();


        try {
            HttpClient httpClient = HttpClients.createDefault();

            // Realiza una solicitud para obtener la temperatura actual
            HttpGet currentWeatherRequest = new HttpGet(BASE_URL + "weather?q=" + city + "&appid=" + API_KEY);
            String currentWeatherResponse = EntityUtils.toString(httpClient.execute(currentWeatherRequest).getEntity());

            // Procesa la respuesta JSON para obtener la temperatura actual
            Gson gson = new Gson();
            WeatherCurrentData currentData = gson.fromJson(currentWeatherResponse, WeatherCurrentData.class);

            // Convierte la temperatura de Kelvin a Celsius
            double currentTemperatureInKelvin = currentData.getMain().getTemp();
            double currentTemperatureInCelsius = currentTemperatureInKelvin - 273.15;

            // Realiza una solicitud para obtener el pronóstico de 5 días
            HttpGet forecastRequest = new HttpGet(BASE_URL + "forecast?q=" + city + "&appid=" + API_KEY);
            String forecastResponse = EntityUtils.toString(httpClient.execute(forecastRequest).getEntity());

            // Procesa la respuesta JSON para obtener el pronóstico de 5 días
            WeatherForecastData forecastData = gson.fromJson(forecastResponse, WeatherForecastData.class);

            // Convierte la temperatura de Kelvin a Celsius para el pronóstico de los próximos 5 días
            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            // Muestra la temperatura actual
            System.out.println("Temperatura actual en " + city + ": " + decimalFormat.format(currentTemperatureInCelsius) + "°C");

            // Filtra y muestra el pronóstico para los próximos 5 días
            System.out.println("Pronóstico para los próximos 5 días:");
            List<WeatherForecastData.ForecastItem> dailyForecasts = filterDailyForecasts(forecastData.getList());
            for (WeatherForecastData.ForecastItem forecastItem : dailyForecasts) {
                String date = forecastItem.getDtTxt();
                double temperatureInKelvin = forecastItem.getMain().getTemp();
                double temperatureInCelsius = temperatureInKelvin - 273.15;
                System.out.println(date + ": " + decimalFormat.format(temperatureInCelsius) + "°C");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para filtrar pronósticos diarios
    private static List<WeatherForecastData.ForecastItem> filterDailyForecasts(List<WeatherForecastData.ForecastItem> forecasts) {
        List<WeatherForecastData.ForecastItem> dailyForecasts = new ArrayList<>();
        String currentDate = null;

        for (WeatherForecastData.ForecastItem forecastItem : forecasts) {
            String date = forecastItem.getDtTxt().split(" ")[0]; // Obtiene la fecha sin la hora

            if (!date.equals(currentDate)) {
                dailyForecasts.add(forecastItem);
                currentDate = date;
            }
        }

        return dailyForecasts;
    }
}