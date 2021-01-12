package com.s.bac_a_sable_capteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //attribut
    SensorManager mySensorManager;
    ArrayList<Capteur> mesCapteurs;
    ListView lvListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mySensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mesCapteurs = new ArrayList<>();
        lvListe = findViewById(R.id.lv_liste) ;

        setListeSensor();
        ecouteBtnListe();
        ecouteBtnStopSensor();

    }


    private void ecouteBtnListe() {
        findViewById(R.id.btn_liste).setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SensorActivity.class) ;
                startActivity(intent) ;
            }
        });
    }

    private void ecouteBtnStopSensor() {
        findViewById(R.id.btnStopSensor).setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                for (Capteur capteur : mesCapteurs) {
                    capteur.stopCapteur();
                }
            }
        });
    }

    // permet de générer la liste des capteurs disponible sur le téléphone
    // le listeAdapter génére des boutons qui permette d'ecouter le capteur voulu
    void setListeSensor(){
        List<Sensor> mySensor =  mySensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor unSensor : mySensor ) {
            Capteur unCapteur = new Capteur(mySensorManager, unSensor, MainActivity.this);
            mesCapteurs.add(unCapteur);
        }

        ListAdapter listeAdapter = new ListAdapter(this, mesCapteurs) ;
        lvListe.setAdapter(listeAdapter) ;
    }



    // permet de stopper l'écoute des capteurs quand l'activity n'est plus au premier plan
    @Override
    protected void onPause() {
        super.onPause();
        findViewById(R.id.btnStopSensor).performClick();
    }
}
