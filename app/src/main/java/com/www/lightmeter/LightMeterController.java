package com.www.lightmeter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by winniewu on 8/19/15.
 */
public class LightMeterController implements SensorEventListener {
    private static final double LIGHTMETER_CONST = 400.0;

    private SensorManager mSensorManager;
    private Sensor lightSensor;
    private LightMeterView view;
    private LightMeterModel model;

    private Variable iso = LightMeterModel.MeterVariable.ISO.variable;
    private Variable aperture = LightMeterModel.MeterVariable.APERTURE.variable;
    private Variable speed = LightMeterModel.MeterVariable.SHUTTER_SPEED.variable;

    public LightMeterController(Context context) {
        model = new LightMeterModel();

        mSensorManager = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void setView(LightMeterView view) {
        this.view = view;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        setNewVariableVal(lux);
        model.setLux(lux);
        view.updateDebug("" + lux);
    }

    public void registerSensor() {
        mSensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregisterSensor() {
        mSensorManager.unregisterListener(this);
    }

    private void setNewVariableVal(float lux) {
        String s = null;
        double N = model.getAperture();
        double t = model.getShutterSpeed();
        double S = model.getIso();
        switch (model.getMeterVariable()) {
            case SHUTTER_SPEED:
                double newSpeed = (LIGHTMETER_CONST * N * N) / (S * lux);
                Log.e("www", "new speed" + newSpeed);
                model.setShutterSpeed(newSpeed);
                s = speed.valToString(model.getShutterSpeed());
                view.setShutterSpeed(s);
                break;
            case APERTURE:
                double newAperture = Math.sqrt((lux * t * S) / LIGHTMETER_CONST);
                Log.e("www", "new aperture" + newAperture);
                model.setAperture(newAperture);
                view.setAperture(model.getAperture());
                s = aperture.valToString(model.getAperture());
                break;
            case ISO:
                double newIso = (LIGHTMETER_CONST * N * N) / (t * lux);
                Log.e("www", "new iso" + newIso);
                model.setIso(newIso);
                view.setIso(newIso);
                s = iso.valToString(model.getIso());
                break;
        }
        view.setVariableView(s);
    }

    void changeVariable(LightMeterModel.MeterVariable variable) {
        model.setMeterVariable(variable);
        view.setActive(variable);
    }

    void initNonvariables() {
        view.setAperture(model.getAperture());
        view.setIso(model.getIso());
        view.setShutterSpeed(LightMeterModel.MeterVariable.SHUTTER_SPEED.variable.valToString(model.getShutterSpeed()));
        view.setActive(model.getMeterVariable());
    }
}
