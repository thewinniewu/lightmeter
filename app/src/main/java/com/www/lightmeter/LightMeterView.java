package com.www.lightmeter;

import android.app.Activity;
import android.view.DragEvent;
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
    private TextView variableView;


    public LightMeterView(Activity activity, LightMeterController controller) {
        super(activity);
        this.activity = activity;
        this.controller = controller;
        controller.setView(this);

        inflate(activity, R.layout.activity_home, this);

        isoView = (TextView) findViewById(R.id.iso_button);
        apertureView = (TextView) findViewById(R.id.aperture_button);
        speedView = (TextView) findViewById(R.id.speed_button);
        variableView = (TextView) findViewById(R.id.variable_button);

        controller.initNonvariables();

        initializeListeners();
    }

    private void initializeListeners() {
        isoView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.changeVariable(LightMeterModel.MeterVariable.ISO);

            }
        });
        apertureView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.changeVariable(LightMeterModel.MeterVariable.APERTURE);

            }
        });
        speedView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.changeVariable(LightMeterModel.MeterVariable.SHUTTER_SPEED);

            }
        });
    }

    public void updateDebug(String debugString) {
        ((TextView) findViewById(R.id.debug_data)).setText(debugString);
    }

    public void setVariableView(String s) {
        variableView.setText(s);
    }

    public void setActive(LightMeterModel.MeterVariable v) {
        isoView.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        speedView.setBackgroundColor(getResources().getColor(R.color.dark_grey));
        apertureView.setBackgroundColor(getResources().getColor(R.color.dark_grey));

        switch (v) {
            case ISO:
                isoView.setBackgroundColor(getResources().getColor(R.color.light_grey));
                break;
            case APERTURE:
                apertureView.setBackgroundColor(getResources().getColor(R.color.light_grey));
                break;
            case SHUTTER_SPEED:
                speedView.setBackgroundColor(getResources().getColor(R.color.light_grey));
                break;
        }
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
