package org.example.model;

public class PriceData {
    private String time_start;
    private String time_end;
    private double SEK_per_kWh;

    public PriceData() {
    }

    public PriceData(String time_start, String time_end, double SEK_per_kWh) {
        this.time_start = time_start;
        this.time_end = time_end;
        this.SEK_per_kWh = SEK_per_kWh;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public double getSEK_per_kWh() {
        return SEK_per_kWh;
    }

    public void setSEK_per_kWh(double SEK_per_kWh) {
        this.SEK_per_kWh = SEK_per_kWh;
    }
}
