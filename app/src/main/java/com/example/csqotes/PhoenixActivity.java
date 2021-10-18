package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhoenixActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoenix);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"You are stupid to think you will win.", "You think you can kill us!?!?",
                "We cannot be killed!", "I fear no man!", "We will stomp your little skulls.", "Run little girls, run!",
                "Where are the real men?!?", "You will cry like babies before you die.", "We will dance on your graves.",
                "We must win.", "Together we can do this.", "We must walk together .", "Let us go.",
                "We will make them regret this day.", "It is time now my brothers.", "Now is the time.",
                "No more talking. Now we fight!", "This is our fight.", "This is what we've been working for.",
                "Guys I tell you: We kill them all!", "This is easy, we kill them and then we go home.",};

        Integer[] audioIds = new Integer[] {R.raw.phoenix_onarollbrag02, R.raw.phoenix_onarollbrag04,
                R.raw.phoenix_onarollbrag05, R.raw.phoenix_onarollbrag08, R.raw.phoenix_onarollbrag10,
                R.raw.phoenix_onarollbrag11, R.raw.phoenix_onarollbrag13, R.raw.phoenix_onarollbrag14,
                R.raw.phoenix_onarollbrag15, R.raw.phoenix_radio_locknload01, R.raw.phoenix_radio_locknload02,
                R.raw.phoenix_radio_locknload03, R.raw.phoenix_radio_locknload04, R.raw.phoenix_radio_locknload05,
                R.raw.phoenix_radio_locknload06, R.raw.phoenix_radio_locknload07, R.raw.phoenix_radio_locknload08,
                R.raw.phoenix_radio_locknload09, R.raw.phoenix_radio_locknload10, R.raw.phoenix_radio_locknload11,
                R.raw.phoenix_radio_locknload12
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.phoenix_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(PhoenixActivity.this, audioIds[i]);
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