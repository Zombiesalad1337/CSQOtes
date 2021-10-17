package com.example.csqotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        String[] factionNames = new String[]{"Anarchist", "Balkan", "FBI", "GIGN", "IDF", "Elite", "Phoenix", "Pirate", "SAS", "Seal", "Separatist", "Swat"};
        Integer[] factionImages = new Integer[]{R.drawable.anarchist, R.drawable.balkan, R.drawable.fbi, R.drawable.gign, R.drawable.idf, R.drawable.leet, R.drawable.phoenix, R.drawable.pirate, R.drawable.sas, R.drawable.seal, R.drawable.separatist, R.drawable.swat};
//        Integer[] factionColor = new Integer[]{R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500};

        ArrayList<Faction> factions = new ArrayList<>();

        for (int i = 0; i != factionNames.length; ++i) {
            factions.add(new Faction(factionImages[i], factionNames[i]));
        }

        FactionAdapter factionAdapter = new FactionAdapter(this, factions, outMetrics.widthPixels, outMetrics.heightPixels);
        GridView gridView = findViewById(R.id.main_grid);
        gridView.setAdapter(factionAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent anarchistIntent = new Intent(MainActivity.this, AnarchistActivity.class);
                        startActivity(anarchistIntent);
                        break;

                    case 1:
                        Intent balkanIntent = new Intent(MainActivity.this, BalkanActivity.class);
                        startActivity(balkanIntent);
                        break;

                    case 2:
                        Intent fbiIntent = new Intent(MainActivity.this, FBIActivity.class);
                        startActivity(fbiIntent);
                        break;

                    case 3:
                        Intent gignIntent = new Intent(MainActivity.this, GIGNActivity.class);
                        startActivity(gignIntent);
                        break;

                    case 4:
                        Intent idfIntent = new Intent(MainActivity.this, IDFActivity.class);
                        startActivity(idfIntent);
                        break;

                    case 5:
                        Intent eliteIntent = new Intent(MainActivity.this, EliteActivity.class);
                        startActivity(eliteIntent);
                        break;

                    case 6:
                        Intent phoenixIntent = new Intent(MainActivity.this, PhoenixActivity.class);
                        startActivity(phoenixIntent);
                        break;

                    case 7:
                        Intent pirateIntent = new Intent(MainActivity.this, PirateActivity.class);
                        startActivity(pirateIntent);
                        break;

                    case 8:
                        Intent sasIntent = new Intent(MainActivity.this, SASActivity.class);
                        startActivity(sasIntent);
                        break;

                    case 9:
                        Intent sealIntent = new Intent(MainActivity.this, SealActivity.class);
                        startActivity(sealIntent);
                        break;

                    case 10:
                        Intent separatistIntent = new Intent(MainActivity.this, SeparatistActivity.class);
                        startActivity(separatistIntent);
                        break;

                    case 11:
                        Intent swatIntent = new Intent(MainActivity.this, SWATActivity.class);
                        startActivity(swatIntent);
                        break;

                    default: break;
                }
            }
        });
    }
}