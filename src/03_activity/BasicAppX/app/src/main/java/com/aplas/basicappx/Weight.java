package com.aplas.basicappx;

public class Weight {
    private double gram;

    public Weight() {
        this.gram = 0.0;
    }

    public void setGram(double wg) {
        this.gram = wg;
    }

    public void setOunce(double wg) {
        this.gram = wg * 28.3495231;
    }

    public void setPound(double wg) {
        this.gram = wg * 453.59237;
    }

    public double getGram() {
        return gram;
    }

    public double getOunce() {
        return gram / 28.349523;
    }

    public double getPound() {
        return gram / 453.59237;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        double hasil = 0.0;
        if ("Grm".equalsIgnoreCase(oriUnit)) {
            setGram(value);
            if ("Grm".equalsIgnoreCase(convUnit)) {
                hasil = getGram();
            } else if ("Onc".equalsIgnoreCase(convUnit)) {
                hasil = getOunce();
            } else if ("Pnd".equalsIgnoreCase(convUnit)) {
                hasil = getPound();
            }
        } else if ("Onc".equalsIgnoreCase(oriUnit)) {
            setOunce(value);
            if ("Grm".equalsIgnoreCase(convUnit)) {
                hasil = getGram();
            } else if ("Onc".equalsIgnoreCase(convUnit)) {
                hasil = getOunce();
            } else if ("Pnd".equalsIgnoreCase(convUnit)) {
                hasil = getPound();
            }
        } else if ("Pnd".equalsIgnoreCase(oriUnit)) {
            setPound(value);
            if ("Grm".equalsIgnoreCase(convUnit)) {
                hasil = getGram();
            } else if ("Onc".equalsIgnoreCase(convUnit)) {
                hasil = getOunce();
            } else if ("Pnd".equalsIgnoreCase(convUnit)) {
                hasil = getPound();
            }
        } else {
            return hasil;
        }
        return hasil;
    }
}
