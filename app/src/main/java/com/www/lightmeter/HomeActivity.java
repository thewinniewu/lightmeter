package com.www.lightmeter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
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
}
