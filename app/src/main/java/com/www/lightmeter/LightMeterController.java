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

    private double[] isoBrackets = {100, 200, 320, 400, 500, 640, 800, 1000, 1250, 1600, 2500, 3200, 6400};
    private double[] apertureBrackets = {1.4, 1.7, 1.8, 2.0, 2.2, 2.5, 2.8, 3.5, 4.0, 5.0, 5.6, 6.3, 7.0, 8.0, 9.0, 11.0, 16.0, 22.0};

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
        double N = model.getAperture();
        double t = model.getShutterSpeed();
        double S = model.getIso();
        switch (model.getMeterVariable()) {
            case SHUTTER_SPEED:
                double newSpeed = (LIGHTMETER_CONST * N * N) / (S * lux);
                Log.e("www", "new speed" + newSpeed);
                model.setShutterSpeed(newSpeed);
                view.setShutterSpeed(toMixedFraction(newSpeed));
                break;
            case APERTURE:
                double newAperture = Math.sqrt((lux * t * S) / LIGHTMETER_CONST);
                Log.e("www", "new aperture" + newAperture);
                model.setAperture(newAperture);
                view.setAperture(newAperture);
                break;
            case ISO:
                double newIso = (LIGHTMETER_CONST * N * N) / (t * lux);
                Log.e("www", "new iso" + newIso);
                model.setIso(newIso);
                view.setIso(newIso);
                break;
        }
    }

    String toMixedFraction(double x) {
        int w = (int) x,
                n = (int) (x * 64) % 64,
                a = n & -n;
        return w + (n == 0 ? "" : " " + n / a + "/" + 64 / a);
    }

    double findQuantizedValue(double actual) {
        double[] quantizedArray = model.getMeterVariable() == LightMeterModel.MeterVariable.ISO ? isoBrackets : apertureBrackets;
        double correctVal = -1;
        for (int i = 0; i < quantizedArray.length; i++) {
            if (actual > quantizedArray[i]) {
                continue;
            } else {
                correctVal = quantizedArray[i];
                break;
            }
        }
        return correctVal;
    }
}
