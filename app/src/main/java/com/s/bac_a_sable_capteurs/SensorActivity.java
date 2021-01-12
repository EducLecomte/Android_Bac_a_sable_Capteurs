package com.s.bac_a_sable_capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        init();
    }

    private void init() {
        TextView tvtexte = findViewById(R.id.tv_liste);
        SensorManager mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mesSensors =  mySensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor unSensor: mesSensors) {
            tvtexte.setText(tvtexte.getText()+unSensor.toString()+"\n\n");
        }
    }

}
