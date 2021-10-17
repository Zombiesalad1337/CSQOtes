package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class AnarchistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anarchist);

        String[] arrQuote = new String[] {"We showed them who's in charge!",
                "Let's go kick some butt!",
                "I know how much you hate 'em so let's do this!",
                "Let's show them we mean business.",
                "Let's teach 'em a lesson.",
                "You guys ready for this?",
                "Ready? This is gonna be gnarly.",
                "The time for talks's over. Let's do this.",
                "Hmm still seems like a good idea. Right guys?",
                "Remember, keep the hostages safe!"};

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], 0));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.anarchist_list);
        listView.setAdapter(quoteAdapter);

        //better to set color here since it covers whole screen
        listView.setBackgroundColor(bgColor);
    }
}