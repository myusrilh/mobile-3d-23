package com.aplas.basicappx;

public class Temperature {
    private double celcius;

    public Temperature() {
        celcius = 0.0;
    }

    public void setCelcius(double temp) {
        this.celcius = temp;
    }

    public void setFahrenheit(double temp) {
        this.celcius = (temp - 32) / 9 * 5;
    }

    public void setKelvins(double temp) {
        this.celcius = temp - 273.15;
    }

    public double getCelcius() {
        return this.celcius;
    }

    public double getFahrenheit() {
        return this.celcius * 9 / 5 + 32;
    }

    public double getKelvins() {
        return this.celcius + 273.15;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        double hasil = 0.0;
        if ("°C".equalsIgnoreCase(oriUnit)) {
            setCelcius(value);
            if ("°C".equalsIgnoreCase(convUnit)) {
                hasil = getCelcius();
            } else if ("°F".equalsIgnoreCase(convUnit)) {
                hasil = getFahrenheit();
            } else if ("K".equalsIgnoreCase(convUnit)) {
                hasil = getKelvins();
            }
        } else if ("°F".equalsIgnoreCase(oriUnit)) {
            setFahrenheit(value);
            if ("°C".equalsIgnoreCase(convUnit)) {
                hasil = getCelcius();
            } else if ("°F".equalsIgnoreCase(convUnit)) {
                hasil = getFahrenheit();
            } else if ("K".equalsIgnoreCase(convUnit)) {
                hasil = getKelvins();
            }
        } else if ("K".equalsIgnoreCase(oriUnit)) {
            setKelvins(value);
            if ("°C".equalsIgnoreCase(convUnit)) {
                hasil = getCelcius();
            } else if ("°F".equalsIgnoreCase(convUnit)) {
                hasil = getFahrenheit();
            } else if ("K".equalsIgnoreCase(convUnit)) {
                hasil = getKelvins();
            }
        }
        return hasil;
    }

}

