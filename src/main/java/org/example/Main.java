package org.example;

import com.google.gson.Gson;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase muestra la temperatura actual y el pronóstico para los próximos 5 días
 * de una ciudad ingresada por el usuario utilizando la API de OpenWeatherMap.
 */
public class Main {

    private static final String API_KEY = "ca6fa12599a3c704cde9cf32b1de0199";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    /**
     * El método principal del programa.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el nombre de la ciudad que quieras:");
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

            // Muestra la temperatura actual
            System.out.println("Temperatura actual en " + city + ": " + decimalFormat.format(currentTemperatureInCelsius) + "°C");

            // Realiza una solicitud para obtener el pronóstico de 5 días
            HttpGet forecastRequest = new HttpGet(BASE_URL + "forecast?q=" + city + "&appid=" + API_KEY);
            String forecastResponse = EntityUtils.toString(httpClient.execute(forecastRequest).getEntity());

            // Procesa la respuesta JSON para obtener el pronóstico de 5 días
            WeatherForecastData forecastData = gson.fromJson(forecastResponse, WeatherForecastData.class);

            // Muestra el pronóstico para los siguientes 5 días naturales
            System.out.println("Pronóstico para los siguientes 5 días naturales:");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            List<String> displayedDates = new ArrayList<>();

            for (WeatherForecastData.ForecastItem forecastItem : forecastData.getList()) {
                String date = forecastItem.getDtTxt().split(" ")[0];
                double temperatureInKelvin = forecastItem.getMain().getTemp();
                double temperatureInCelsius = temperatureInKelvin - 273.15;

                // Verifica si la fecha ya se ha mostrado
                if (!displayedDates.contains(date)) {
                    System.out.println(date + ": " + decimalFormat.format(temperatureInCelsius) + "°C");
                    displayedDates.add(date);
                }

                // Si se han mostrado 5 fechas, sal del bucle
                if (displayedDates.size() >= 5) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
}