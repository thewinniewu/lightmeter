package com.www.lightmeter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class HomeActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor lightSensor;

    private View isoView;
    private View apertureView;
    private View speedView;
    private View variableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        isoView = findViewById(R.id.iso_button);
        apertureView = findViewById(R.id.aperture_button);
        speedView = findViewById(R.id.speed_button);
        variableView = findViewById(R.id.variable_button);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

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
        Log.e("www", "" + event.values[0]);
    }
}
