package com.www.lightmeter;

/**
 * Created by winniewu on 8/19/15.
 */
public class LightMeterModel {
    enum MeterVariable {
        ISO, APERTURE, SHUTTER_SPEED;
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
