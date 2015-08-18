package com.www.lightmeter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;


public class HomeActivity extends Activity {
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

        initializeListeners();
    }

    private void initializeListeners() {

    }
}
