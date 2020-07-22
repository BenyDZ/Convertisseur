package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.convertisseur.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    // text view utilisé pour afficher le resultat de la conversion de la température
    private TextView resultat,resultat1;

    private EditText tempEdit,distEdit;

    private Boolean convertirEnFahrenheit, convertirEnCelsius, convertirEnKm, convertirEnMiles = false;

    private int[] radioButton = {R.id.rbC_F, R.id.rbF_C, R.id.rbKm_Miles, R.id.rbMiles_Km};

    private int[] boutonQuitter = {R.id.bQuitter, R.id.bQuitter1};

    private int[] boutonConvertir = {R.id.bConvertir, R.id.bConvertir1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec tab = tabs.newTabSpec("tag1");
        tab.setContent(R.id.tab1);
        tab.setIndicator("Temperature");
        tabs.addTab(tab);
        tab = tabs.newTabSpec("tag2");
        tab.setContent(R.id.tab2);
        tab.setIndicator("Distance");
        tabs.addTab(tab);

        //cherche le text view
        resultat = (TextView) findViewById(R.id.lResultat_Final);
        //cherche le text view
        resultat1 = (TextView) findViewById(R.id.lResultat_Final1);

        tempEdit = (EditText) findViewById(R.id.tempEdit);
        distEdit = (EditText) findViewById(R.id.distEdit);

        convertirEnCelsius = false;
        convertirEnFahrenheit = false;
        convertirEnKm = false;
        convertirEnMiles = false;

        setRadioButtonOnClickListener();
        setQuitButtonOnClickListener();
        setBoutonConvertirOnClickListener();
    }

    private void setRadioButtonOnClickListener() {
        // Create a common OnClickListener
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                RadioButton button = (RadioButton) v;
                if (button.isChecked()) {
                    if (button.getId() == R.id.rbC_F){
                        convertirEnCelsius = false;
                        convertirEnFahrenheit = true;
                    }

                    if (button.getId() == R.id.rbF_C){
                        convertirEnCelsius = true;
                        convertirEnFahrenheit = false;
                    }

                    if (button.getId() == R.id.rbKm_Miles){
                        convertirEnMiles = true;
                        convertirEnKm = false;
                    }

                    if (button.getId() == R.id.rbMiles_Km){
                        convertirEnKm = true;
                        convertirEnMiles = false;
                    }
                }
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : radioButton) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setQuitButtonOnClickListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                QuitAlertDialog alert = new QuitAlertDialog();
                alert.show(getSupportFragmentManager(), "quit");
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : boutonQuitter) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setBoutonConvertirOnClickListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                if (button.getId() == R.id.bConvertir){
                    if (convertirEnFahrenheit){
                        Convertisseur convertisseur = new Convertisseur();
                        String tempCel = tempEdit.getText().toString();
                        double tempCelsius = Double.valueOf(tempCel);
                        double tempFahr = convertisseur.enFahrenheit(tempCelsius);
                        resultat.setText(Double.toString(tempFahr));
                    }

                    if (convertirEnCelsius){
                        Convertisseur convertisseur = new Convertisseur();
                        String tempFahr = tempEdit.getText().toString();
                        double tempFahrenheit = Double.valueOf(tempFahr);
                        double tempCelsius = convertisseur.enCelsius(tempFahrenheit);
                        resultat.setText(Double.toString(tempCelsius));
                    }

                    if (convertirEnCelsius == false & convertirEnFahrenheit == false){
                        resultat.setText("Veuillez choisir un type de conversion");
                    }
                }

                if (button.getId() == R.id.bConvertir1){
                    if (convertirEnMiles){
                        Convertisseur convertisseur = new Convertisseur();
                        String km = distEdit.getText().toString();
                        double kilometre = Double.valueOf(km);
                        double miles = convertisseur.enMiles(kilometre);
                        resultat1.setText(Double.toString(miles));
                    }

                    if (convertirEnKm){
                        Convertisseur convertisseur = new Convertisseur();
                        String mil = distEdit.getText().toString();
                        double miles = Double.valueOf(mil);
                        double km = convertisseur.enKm(miles);
                        resultat1.setText(Double.toString(km));
                    }

                    if (convertirEnKm == false & convertirEnMiles == false){
                        resultat1.setText("Veuillez choisir un type de conversion");
                    }
                }
            }
        };
        // Assign the listener to all the numeric buttons
        for (int id : boutonConvertir) {
            findViewById(id).setOnClickListener(listener);
        }
    }
}