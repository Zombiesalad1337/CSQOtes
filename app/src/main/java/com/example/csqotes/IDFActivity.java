package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class IDFActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idfactivity);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"Is this your first time out?", "We must defeat the enemy, not ourselves.",
                "Our mission is simple, take them out.", "Grab your gear and let's go.", "Lock and load.",
                "Let's move out.", "Let's get this done.", "Do not be afraid, let's go.",
                "This is what we're trained for.", "This is our day.", "Let's get this over with.",
                "This is it my friends.", "Form up. Let's go.", "Let's get this done.", "Let's go.",
        };

        Integer[] audioIds = new Integer[]{R.raw.idf_friendlyfire08, R.raw.idf_killedfriend05, R.raw.idf_radio_locknload01,
                R.raw.idf_radio_locknload02, R.raw.idf_radio_locknload03, R.raw.idf_radio_locknload04,
                R.raw.idf_radio_locknload05, R.raw.idf_radio_locknload06, R.raw.idf_radio_locknload07,
                R.raw.idf_radio_locknload08, R.raw.idf_radio_locknload09, R.raw.idf_radio_locknload10,
                R.raw.idf_radio_locknload11, R.raw.idf_radio_locknload12, R.raw.idf_radio_locknload13
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.idf_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(IDFActivity.this, audioIds[i]);
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

//TODO: refactor activity_()activity.xml