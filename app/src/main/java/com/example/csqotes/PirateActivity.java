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

        Integer[] audioIds = new Integer[] {R.raw.anarchist_radiobotendclean02, R.raw.anarchist_radio_locknload01,
                R.raw.anarchist_radio_locknload02, R.raw.anarchist_radio_locknload03, R.raw.anarchist_radio_locknload04,
                R.raw.anarchist_radio_locknload05, R.raw.anarchist_radio_locknload06, R.raw.anarchist_radio_locknload07,
                R.raw.anarchist_radio_locknload08, R.raw.anarchist_radio_locknload09};

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