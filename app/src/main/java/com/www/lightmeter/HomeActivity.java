package com.www.lightmeter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor lightSensor;

    private View isoView;
    private View apertureView;
    private View speedView;
    private View variableView;

    public HomeActivity() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        isoView = findViewById(R.id.iso_button);
        apertureView = findViewById(R.id.aperture_button);
        speedView = findViewById(R.id.speed_button);
        variableView = findViewById(R.id.variable_button);

        initializeListeners();
    }

    private void initializeListeners() {

    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    }
}
