package org.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Esta clase representa los datos de pronóstico del tiempo obtenidos de la API de OpenWeatherMap.
 */
public class WeatherForecastData {
    // Utilizamos la anotación @SerializedName para mapear el campo JSON "list" a la propiedad "list" de esta clase.
    @SerializedName("list")
    private List<ForecastItem> list;

    /**
     * Obtiene la lista de elementos de pronóstico.
     *
     * @return La lista de elementos de pronóstico.
     */
    public List<ForecastItem> getList() {
        return list;
    }

    /**
     * Establece la lista de elementos de pronóstico.
     *
     * @param list La lista de elementos de pronóstico.
     */
    public void setList(List<ForecastItem> list) {
        this.list = list;
    }

    /**
     * Clase interna que representa un elemento de pronóstico.
     */
    public static class ForecastItem {
        // Utilizamos la anotación @SerializedName para mapear el campo JSON "main" a la propiedad "main" de esta clase.
        @SerializedName("main")
        private Main main;
        // Utilizamos la anotación @SerializedName para mapear el campo JSON "dt_txt" a la propiedad "dtTxt" de esta clase.
        @SerializedName("dt_txt")
        private String dtTxt;  // Campo que contiene la fecha y hora

        /**
         * Obtiene los datos principales del pronóstico.
         *
         * @return Los datos principales del pronóstico.
         */
        public Main getMain() {
            return main;
        }

        /**
         * Establece los datos principales del pronóstico.
         *
         * @param main Los datos principales del pronóstico.
         */
        public void setMain(Main main) {
            this.main = main;
        }

        /**
         * Obtiene la fecha y hora del pronóstico.
         *
         * @return La fecha y hora del pronóstico.
         */
        public String getDtTxt() {
            return dtTxt;
        }

        /**
         * Establece la fecha y hora del pronóstico.
         *
         * @param dtTxt La fecha y hora del pronóstico.
         */
        public void setDtTxt(String dtTxt) {
            this.dtTxt = dtTxt;
        }
    }

    /**
     * Clase interna que representa los datos principales del pronóstico.
     */
    public static class Main {
        // Utilizamos la anotación @SerializedName para mapear el campo JSON "temp" a la propiedad "temp" de esta clase.
        @SerializedName("temp")
        private double temp;

        /**
         * Obtiene la temperatura del pronóstico en grados Kelvin.
         *
         * @return La temperatura del pronóstico en grados Kelvin.
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Establece la temperatura del pronóstico en grados Kelvin.
         *
         * @param temp La temperatura del pronóstico en grados Kelvin.
         */
        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}