package org.example;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecastData {
    @SerializedName("list")
    private List<ForecastItem> list;

    public List<ForecastItem> getList() {
        return list;
    }

    public void setList(List<ForecastItem> list) {
        this.list = list;
    }

    public static class ForecastItem {
        @SerializedName("main")
        private Main main;
        @SerializedName("dt_txt")
        private String dtTxt;  // Campo que contiene la fecha y hora

        public Main getMain() {
            return main;
        }

        public void setMain(Main main) {
            this.main = main;
        }

        public String getDtTxt() {
            return dtTxt;
        }

        public void setDtTxt(String dtTxt) {
            this.dtTxt = dtTxt;
        }
    }

    public static class Main {
        @SerializedName("temp")
        private double temp;

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }
}