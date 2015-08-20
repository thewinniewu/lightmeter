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
}
