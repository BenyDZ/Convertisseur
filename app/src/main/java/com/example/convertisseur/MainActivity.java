package com.example.convertisseur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}