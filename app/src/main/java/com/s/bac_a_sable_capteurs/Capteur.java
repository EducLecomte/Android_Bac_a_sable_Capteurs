package com.s.bac_a_sable_capteurs;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.TextView;

public class Capteur {

    //attribut
    SensorManager sensorManager;
    Sensor sensor;
    Activity activity;
    SensorEventListener sensorEventListener;

    public Capteur(SensorManager sensorManager, Sensor sensor, Activity activity) {
        this.sensorManager = sensorManager;
        this.sensor = sensor;
        this.activity = activity;
    }

    public Sensor getSensor() {
        return sensor;
    }

    // méthode pour initialiser l'écoute du capteur
    public void initCapteur(){
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.d("onSensorChanged", sensor.getName());
                ((TextView)activity.findViewById(R.id.tv_SensorName)).setText(sensor.getName());
                ((TextView)activity.findViewById(R.id.tv_SensorX)).setText(""+event.values[0]);
                ((TextView)activity.findViewById(R.id.tv_SensorY)).setText(""+event.values[1]);
                ((TextView)activity.findViewById(R.id.tv_SensorZ)).setText(""+event.values[2]);

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Log.d("onAccuracyChanged", ":" + accuracy);
            }
        };

        if (sensor != null) {
            sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    // methode pour stopper l'écoute des capteurs
    public void stopCapteur(){
        sensorManager.unregisterListener(sensorEventListener);
        //activity.findViewById(R.id.btnStopSensor).performClick();
    }




}
