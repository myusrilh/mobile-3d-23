package com.aplas.basicappx;

public class Distance {
    private double meter;

    public Distance() {
        this.meter = 0;
    }

    public void setMeter(double dist) {
        this.meter = dist;
    }

    public void setInch(double dist) {
        this.meter = dist / 39.3701;
    }

    public void setMile(double dist) {
        this.meter = dist / 0.000621371;
    }

    public void setFoot(double dist) {
        this.meter = dist / 3.28084;
    }

    public double getMeter() {
        return meter;
    }

    public double getInch() {
        return meter * 39.3701;
    }

    public double getMile() {
        return meter * 0.000621371;
    }

    public double getFoot() {
        return meter * 3.28084;
    }

    public double convert(String oriUnit, String convUnit, double value) {
        double hasil = 0.0;
        if ("Mtr".equalsIgnoreCase(oriUnit)) {
            setMeter(value);
            if ("Mtr".equalsIgnoreCase(convUnit)) {
                hasil = getMeter();
            } else if ("Inc".equalsIgnoreCase(convUnit)) {
                hasil = getInch();
            } else if ("Mil".equalsIgnoreCase(convUnit)) {
                hasil = getMile();
            } else if ("Ft".equalsIgnoreCase(convUnit)) {
                hasil = getFoot();
            }
        } else if ("Inc".equalsIgnoreCase(oriUnit)) {
            setInch(value);
            if ("Mtr".equalsIgnoreCase(convUnit)) {
                hasil = getMeter();
            } else if ("Inc".equalsIgnoreCase(convUnit)) {
                hasil = getInch();
            } else if ("Mil".equalsIgnoreCase(convUnit)) {
                hasil = getMile();
            } else if ("Ft".equalsIgnoreCase(convUnit)) {
                hasil = getFoot();
            }
        } else if ("Mil".equalsIgnoreCase(oriUnit)) {
            setMile(value);
            if ("Mtr".equalsIgnoreCase(convUnit)) {
                hasil = getMeter();
            } else if ("Inc".equalsIgnoreCase(convUnit)) {
                hasil = getInch();
            } else if ("Mil".equalsIgnoreCase(convUnit)) {
                hasil = getMile();
            } else if ("Ft".equalsIgnoreCase(convUnit)) {
                hasil = getFoot();
            }
        } else if ("Ft".equalsIgnoreCase(oriUnit)) {
            setFoot(value);
            if ("Mtr".equalsIgnoreCase(convUnit)) {
                hasil = getMeter();
            } else if ("Inc".equalsIgnoreCase(convUnit)) {
                hasil = getInch();
            } else if ("Mil".equalsIgnoreCase(convUnit)) {
                hasil = getMile();
            } else if ("Ft".equalsIgnoreCase(convUnit)) {
                hasil = getFoot();
            }
        } else {
            return hasil;
        }
        return hasil;
    }
}
