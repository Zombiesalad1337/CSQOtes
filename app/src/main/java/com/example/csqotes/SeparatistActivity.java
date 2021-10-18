package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SeparatistActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separatist);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"They can't stop the bomb now!", "Yes! Yes!", "The world is ours.",
                "Our enemies tremble!", "Our guns shall bring us victory!", "Follow me my friend.",
                "Let's go, my friends.", "So that we may be free.", "To victory.", "Be safe my friends.",
                "Let us be quick.", "Let us go.", "Let's teach these dogs a lesson.", "May we be safe from evil.",
                "They shall regret this day.", "The pigs are shooting at me.", "They think they can kill me.",
                "With much thanks!", };

        Integer[] audioIds = new Integer[] {R.raw.separatist_bombtickingdown03, R.raw.separatist_onarollbrag01,
                R.raw.separatist_onarollbrag03, R.raw.separatist_onarollbrag04, R.raw.separatist_onarollbrag05,
                R.raw.separatist_radio_followme04, R.raw.separatist_radio_letsgo05, R.raw.separatist_radio_locknload01,
                R.raw.separatist_radio_locknload02, R.raw.separatist_radio_locknload03,
                R.raw.separatist_radio_locknload04, R.raw.separatist_radio_locknload05,
                R.raw.separatist_radio_locknload06, R.raw.separatist_radio_locknload07,
                R.raw.separatist_radio_locknload10, R.raw.separatist_radio_takingfire05,
                R.raw.separatist_radio_takingfire07, R.raw.separatist_thanks02

        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.separatist_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(SeparatistActivity.this, audioIds[i]);
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