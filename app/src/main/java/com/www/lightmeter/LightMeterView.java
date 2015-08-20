package com.www.lightmeter;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.view.View.inflate;

/**
 * Created by winniewu on 8/19/15.
 */
public class LightMeterView extends FrameLayout {
    private final Activity activity;
    private final LightMeterController controller;

    private View isoView;
    private View apertureView;
    private View speedView;
    private View variableView;


    public LightMeterView(Activity activity, LightMeterController controller) {
        super(activity);
        this.activity = activity;
        this.controller = controller;
        controller.setView(this);

        inflate(activity, R.layout.activity_home, this);

        isoView = findViewById(R.id.iso_button);
        apertureView = findViewById(R.id.aperture_button);
        speedView = findViewById(R.id.speed_button);
        variableView = findViewById(R.id.variable_button);

        initializeListeners();
    }

    private void initializeListeners() {

    }

    public void updateDebug(String debugString) {
        ((TextView) findViewById(R.id.debug_data)).setText(debugString);
    }
}
