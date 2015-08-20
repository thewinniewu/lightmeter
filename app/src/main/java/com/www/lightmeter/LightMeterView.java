package com.www.lightmeter;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by winniewu on 8/19/15.
 */
public class LightMeterView extends FrameLayout {
    private final Activity activity;
    private final LightMeterController controller;

    private TextView isoView;
    private TextView apertureView;
    private TextView speedView;
    private View variableView;


    public LightMeterView(Activity activity, LightMeterController controller) {
        super(activity);
        this.activity = activity;
        this.controller = controller;
        controller.setView(this);

        inflate(activity, R.layout.activity_home, this);

        isoView = (TextView) findViewById(R.id.iso_button);
        apertureView = (TextView) findViewById(R.id.aperture_button);
        speedView = (TextView) findViewById(R.id.speed_button);
        variableView = findViewById(R.id.variable_button);

        initializeListeners();
    }

    private void initializeListeners() {

    }

    public void updateDebug(String debugString) {
        ((TextView) findViewById(R.id.debug_data)).setText(debugString);
    }

    public void setShutterSpeed(String s) {
        speedView.setText("SHUTTER SPEED: " + s);
    }

    public void setAperture(double n) {
        apertureView.setText("APERTURE: " + n);
    }

    public void setIso(double n) {
        isoView.setText("ISO: " + n);
    }
}
