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

/**
 * Esta clase muestra la temperatura actual y el pronóstico para los próximos 5 días
 * de una ciudad ingresada por el usuario utilizando la API de OpenWeatherMap.
 */
public class Main {

    private static final String URL = "https://api.openweathermap.org/data/2.5/";

    /**
     * El método principal del programa.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Se pide nombre de la ciudad por consola
        System.out.println("Introduce el nombre de la ciudad que quieras:");
        String ciudad = sc.nextLine();
        try {
            HttpClient httpClient = HttpClients.createDefault();

            // Realiza una solicitud para obtener la temperatura actual
            HttpGet openWheather = new HttpGet(URL + "weather?q=" + ciudad + "&appid=" + Utils.API_KEY);
            String respuestaTiempo = EntityUtils.toString(httpClient.execute(openWheather).getEntity());

            // Procesa la respuesta JSON para obtener la temperatura actual
            Gson gson = new Gson();
            WeatherCurrentData tiempoActuall = gson.fromJson(respuestaTiempo, WeatherCurrentData.class);

            // Convierte la temperatura de Kelvin a Celsius
            double tiempoEnKelvin = tiempoActuall.getMain().getTemp();
            double tiempoEnGrados = tiempoEnKelvin - 273.15;

            // Muestra la temperatura actual
            System.out.println("Temperatura actual en " + ciudad + ": " + decimalFormat.format(tiempoEnGrados) + "°C");

            // Realiza una solicitud para obtener el pronóstico de 5 días
            HttpGet pronostico = new HttpGet(URL + "forecast?q=" + ciudad + "&appid=" + Utils.API_KEY);
            String respuestaPronostico = EntityUtils.toString(httpClient.execute(pronostico).getEntity());

            // Procesa la respuesta JSON para obtener el pronóstico de 5 días
            WeatherForecastData pronosticoJson = gson.fromJson(respuestaPronostico, WeatherForecastData.class);

            // Muestra el pronóstico para los siguientes 5 días naturales
            System.out.println("Pronóstico para los siguientes 5 días naturales:");
            List<String> lista = new ArrayList<>();

            for (WeatherForecastData.ForecastItem forecastItem : pronosticoJson.getList()) {
                String fecha = forecastItem.getDtTxt().split(" ")[0];
                double temperaturaKelvin = forecastItem.getMain().getTemp();
                double temperaturaEnGrados = temperaturaKelvin - 273.15;

                // Verifica si la fecha ya se ha mostrado
                if (!lista.contains(fecha)) {
                    System.out.println(fecha + ": " + decimalFormat.format(temperaturaEnGrados) + "°C");
                    lista.add(fecha);
                }

                // Si se han mostrado 5 fechas, sal del bucle
                if (lista.size() >= 5) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
}