package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        String[] factionNames = new String[] {"Anarchist", "Balkan", "FBI", "GIGN", "IDF", "Elite", "Phoenix", "Pirate", "SAS", "Seal", "Separatist", "Swat"};
        Integer[] factionImages = new Integer[] {R.drawable.anarchist, R.drawable.balkan, R.drawable.fbi, R.drawable.gign, R.drawable.idf, R.drawable.leet, R.drawable.phoenix, R.drawable.pirate, R.drawable.sas, R.drawable.seal, R.drawable.separatist, R.drawable.swat};
        Integer[] factionColor = new Integer[] {R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500, R.color.purple_500};

        ArrayList<Faction> factions = new ArrayList<>();

        for (int i = 0; i != factionNames.length; ++i){
            factions.add(new Faction(factionImages[i], factionNames[i], factionColor[i]));
        }

        FactionAdapter factionAdapter = new FactionAdapter(this, factions, outMetrics.widthPixels, outMetrics.heightPixels);
        GridView gridView = findViewById(R.id.main_grid);
        gridView.setAdapter(factionAdapter);
    }
}