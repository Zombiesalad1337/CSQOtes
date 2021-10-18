package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SealActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seal);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"The best of the best!", "Good job everyone.",
                "Nice work people! Smoke 'em if you got 'em.", "That was like shooting fish in a barrel.",
                "Let's go.", "Grab your gear, let's go.", "Lock n load.", "Let's move out.",
                "Move out.", "Let's get this done.", "This day right here is where we end this.",
                "Alright gentlemen, this is it.", "Keep ready, this is a free fire zone.",
                "Let's go earn our pay.", "Time to earn our stories.",
                "Let's do this and get back in time for chow.", "We are Oscar Mike.",
                "Let's go have some fun.", "We are good to go.", "Let's go everyone.", "Stay frosty.",
        };

        Integer[] audioIds = new Integer[] {R.raw.seal_onarollbrag08, R.raw.seal_radiobotendclean01,
                R.raw.seal_radiobotendclean03, R.raw.seal_radiobotendclean04, R.raw.seal_radio_locknload01,
                R.raw.seal_radio_locknload02, R.raw.seal_radio_locknload03, R.raw.seal_radio_locknload04,
                R.raw.seal_radio_locknload05, R.raw.seal_radio_locknload06, R.raw.seal_radio_locknload07,
                R.raw.seal_radio_locknload08, R.raw.seal_radio_locknload09, R.raw.seal_radio_locknload10,
                R.raw.seal_radio_locknload11, R.raw.seal_radio_locknload12, R.raw.seal_radio_locknload13,
                R.raw.seal_radio_locknload14, R.raw.seal_radio_locknload15, R.raw.seal_radio_locknload16,
                R.raw.seal_radio_locknload17,
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.seal_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(SealActivity.this, audioIds[i]);
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