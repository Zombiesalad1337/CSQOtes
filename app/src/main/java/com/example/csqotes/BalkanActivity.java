package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class BalkanActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balkan);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"Justice will be ours.",
                "Pay attention to what you are doing.", "Let us defend our land.", "We must defeat them.",
                "Let us be safe, and swift.", "Take care of yourselves.", "We will make them regret this day.",
                "Who here is not ready?", "Now is the time.", "They will remember us after today.",
        };

        Integer[] audioIds = new Integer[] {R.raw.balkan_bombtickingdown02, R.raw.balkan_friendlyfire02, R.raw.balkan_radio_locknload01,
                R.raw.balkan_radio_locknload02, R.raw.balkan_radio_locknload03, R.raw.balkan_radio_locknload04,
                R.raw.balkan_radio_locknload06, R.raw.balkan_radio_locknload07, R.raw.balkan_radio_locknload08, R.raw.balkan_radio_locknload10
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.balkan_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(BalkanActivity.this, audioIds[i]);
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