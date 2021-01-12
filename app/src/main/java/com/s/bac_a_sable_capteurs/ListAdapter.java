package com.s.bac_a_sable_capteurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Capteur> {

    //attribut
    Context context;

    public ListAdapter(Context context, List<Capteur> listeCapteur) {
        super(context, -1, listeCapteur);
        this.context = context;
    }


    // initialise les boutons ainsi que l'action de clic des boutons
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.liste_ligne, parent, false);
        } else {
            view = convertView;
        }

        final Capteur capteur = getItem(position);
        final Button btnListe = view.findViewById(R.id.btn_liste);
        btnListe.setText(capteur.getSensor().getName());
        btnListe.setContentDescription(""+capteur.getSensor().getType());

        btnListe.setOnClickListener(new TextView.OnClickListener() {
            public void onClick(View v) {
                capteur.activity.findViewById(R.id.btnStopSensor).performClick();
                capteur.initCapteur();
            }
        });


        return view;
    }

}
