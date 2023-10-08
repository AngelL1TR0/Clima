package org.example;

import com.google.gson.annotations.SerializedName;

/**
 * Esta clase representa los datos de temperatura actual obtenidos de la API de OpenWeatherMap.
 */
public class WeatherCurrentData {
    // Utilizamos la anotación @SerializedName para mapear el campo JSON "main" a la propiedad "main" de esta clase.
    @SerializedName("main")
    private Main main;

    /**
     * Obtiene los datos principales de temperatura actual.
     *
     * @return Los datos principales de temperatura actual.
     */
    public Main getMain() {
        return main;
    }

    /**
     * Establece los datos principales de temperatura actual.
     *
     * @param main Los datos principales de temperatura actual.
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Clase interna que representa los datos principales de temperatura actual.
     */
    public static class Main {
        // Utilizamos la anotación @SerializedName para mapear el campo JSON "temp" a la propiedad "temp" de esta clase.
        @SerializedName("temp")
        private double temp;

        /**
         * Obtiene la temperatura actual en grados Kelvin.
         *
         * @return La temperatura actual en grados Kelvin.
         */
        public double getTemp() {
            return temp;
        }

        /**
         * Establece la temperatura actual en grados Kelvin.
         *
         * @param temp La temperatura actual en grados Kelvin.
         */
        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}
