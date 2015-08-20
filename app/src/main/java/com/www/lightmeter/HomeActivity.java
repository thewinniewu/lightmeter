package com.www.lightmeter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class HomeActivity extends Activity implements SensorEventListener {
    private LightMeterController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = new LightMeterController(this);
        View view = new LightMeterView(this, controller);
        setContentView(view);
    }

    protected void onResume() {
        super.onResume();
        controller.registerSensor();
    }

    protected void onPause() {
        super.onPause();
        controller.unregisterSensor();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        ((TextView) findViewById(R.id.debug_data)).setText("" + event.values[0]);
    }
}
