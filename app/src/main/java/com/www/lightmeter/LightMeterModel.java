package com.www.lightmeter;

/**
 * Created by winniewu on 8/19/15.
 */
public class LightMeterModel {
    private static double[] isoBrackets = {100, 200, 320, 400, 500, 640, 800, 1000, 1250, 1600, 2500, 3200, 6400};
    private static double[] apertureBrackets = {1.4, 1.7, 1.8, 2.0, 2.2, 2.5, 2.8, 3.5, 4.0, 5.0, 5.6, 6.3, 7.0, 8.0, 9.0, 11.0, 16.0, 22.0};
    private static double[] shutterBrackets = {
            1.0 / 4000.0,
            1.0 / 2000.0,
            1.0 / 1000.0,
            1.0 / 500.0,
            1.0 / 250.0,
            1.0 / 125.0,
            1.0 / 60.0,
            1.0 / 30.0,
            1.0 / 15.0,
            1.0 / 8.0,
            1.0 / 4.0,
            1.0 / 2.0,
    };
    private static String[] shutterBracketStrings = {
            "1 / 4000",
            "1 / 2000",
            "1 / 1000",
            "1 / 500",
            "1 / 250",
            "1 / 125",
            "1 / 60",
            "1 / 30",
            "1 / 15",
            "1 / 8",
            "1 / 4",
            "1 / 2",
    };


    public enum MeterVariable {
        ISO(new Variable(isoBrackets, null)),
        APERTURE(new Variable(apertureBrackets, null)),
        SHUTTER_SPEED(new Variable(shutterBrackets, shutterBracketStrings));

        Variable variable;

        MeterVariable(Variable variable) {
            this.variable = variable;
        }
    }

    private MeterVariable meterVariable = MeterVariable.SHUTTER_SPEED;
    private float lux;
    private double aperture = 2.2;
    private double shutterSpeed =  1/50.0;
    private double iso = 400.0;

    public MeterVariable getMeterVariable() {
        return meterVariable;
    }

    public void setMeterVariable(MeterVariable meterVariable) {
        this.meterVariable = meterVariable;
    }

    public float getLux() {
        return lux;
    }

    public void setLux(float lux) {
        this.lux = lux;
    }

    public double getAperture() {
        return aperture;
    }

    public void setAperture(double aperture) {
        this.aperture = aperture;
    }

    public double getShutterSpeed() {
        return shutterSpeed;
    }

    public void setShutterSpeed(double shutterSpeed) {
        this.shutterSpeed = shutterSpeed;
    }

    public double getIso() {
        return iso;
    }

    public void setIso(double iso) {
        this.iso = iso;
    }
}
