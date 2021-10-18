package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PirateActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pirate);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"This island's not big enough for all of us.", "Run now while you have the chance.",
                "Show your face so I can cut it.", "So that we may be free.", "Now, let's get them off our island.",
                "Now let's destroy the mill.", "The truck is crashed, let's go.", "Now is the time.",
                "Talk is over, we have tried. Now is the time for action.", "It's time to free us from our bonds.",
                "We must push them from our lands.", "It is time to do our part.", "Grab your stuff and let's go.",
                "Don't forget the plan.", "Let's go blow that truck.", "We are ready, let's go.", "Time to go to work in the mill.", };

        Integer[] audioIds = new Integer[] {R.raw.pirate_onarollbrag03, R.raw.pirate_onarollbrag12, R.raw.pirate_onarollbrag13,
                R.raw.pirate_radio_locknload01, R.raw.pirate_radio_locknload02, R.raw.pirate_radio_locknload03,
                R.raw.pirate_radio_locknload04, R.raw.pirate_radio_locknload05, R.raw.pirate_radio_locknload06,
                R.raw.pirate_radio_locknload07, R.raw.pirate_radio_locknload08, R.raw.pirate_radio_locknload09,
                R.raw.pirate_radio_locknload10, R.raw.pirate_radio_locknload11, R.raw.pirate_radio_locknload12,
                R.raw.pirate_radio_locknload13, R.raw.pirate_radio_locknload14
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.pirate_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(PirateActivity.this, audioIds[i]);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });

        //better to set color here since it covers whole screen
        listView.setBackgroundColor(bgColor);

        //TODO: on scrolling to bottom memory usage still spikes even after setting caching to null
        listView.setScrollingCacheEnabled(false);

    }
    public void releaseMediaPlayer(){
        if (mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null)
            releaseMediaPlayer();
    }
}