package com.example.csqotes;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SASActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sasactivity);

        //TODO: audio focus
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };

        String[] arrQuote = new String[] {"At least we stopped 'em boys, at least we stopped 'em.",
                "You're champion sonny.", "You're a champion!", "All right!  All right!",
                "Eh You're right on, you're right on!", "We took the piss out of 'em.",
                "Eh we're really laying into them.",
                "We are in the clear lads.", "Easy peasy lemon squeezy.",
                "Ah it's a piece of cake, so it is.", "Eh we gave them a spanking!",
                "Bingo bango bongo bish bash bosh!", "Oh come on, chop chop.",
                "Stay Frosty!", "Come on come on chop chop.", "Let's make this right as rain.",
                "Let's go fellas.", "Let's move out.", "Let's move out.",
                "Remove any doubts in your head, its us or them.",
                "Remember, this isn't the killing house anymore, This is real life.",
                "Watch out, these boys have got a bit of an arsenal and they don't mind using it.",
                "Let's have it lads.", "Let's give it to them boys!", "Let's show them who we are!",
                "Are we rushin' in or are we going sneaky beaky like?", "For queen and country men.",
                "Remember, this is bandit country, shoot everything that moves.", "Gear up, we're going in.",
                "We are out of here.", "We're on.", "Right lads, we're on.",
                "These fellas are gonna regret waking up this morning.",
                "They're gonna wish they were never born.",
                "Let's have at it mates.", "Gear up, we aren't going on a windy walk here.",
                "Cheers big fella.",
        };

        Integer[] audioIds = new Integer[] {R.raw.sas_lastmanstanding02, R.raw.sas_niceshot12,
                R.raw.sas_onarollbrag03, R.raw.sas_onarollbrag04, R.raw.sas_onarollbrag06, R.raw.sas_onarollbrag08,
                R.raw.sas_onarollbrag09,
                R.raw.sas_onarollbrag11, R.raw.sas_onarollbrag13, R.raw.sas_onarollbrag14,
                R.raw.sas_radiobotendsolid01, R.raw.sas_radiobotendsolid07, R.raw.sas_radiobottime01,
                R.raw.sas_radio_enemyspotted05, R.raw.sas_radio_letsgo05, R.raw.sas_radio_locknload01,
                R.raw.sas_radio_locknload02, R.raw.sas_radio_locknload03, R.raw.sas_radio_locknload04,
                R.raw.sas_radio_locknload05, R.raw.sas_radio_locknload06, R.raw.sas_radio_locknload07,
                R.raw.sas_radio_locknload08, R.raw.sas_radio_locknload09, R.raw.sas_radio_locknload10,
                R.raw.sas_radio_locknload11, R.raw.sas_radio_locknload12, R.raw.sas_radio_locknload13,
                R.raw.sas_radio_locknload14, R.raw.sas_radio_locknload15, R.raw.sas_radio_locknload16,
                R.raw.sas_radio_locknload17, R.raw.sas_radio_locknload18, R.raw.sas_radio_locknload19,
                R.raw.sas_radio_locknload20, R.raw.sas_radio_locknload21, R.raw.sas_thanks04
        };

        ArrayList<Quote> quotes = new ArrayList<>();

        int bgColor = Color.parseColor("#CC000000");
        for (int i = 0; i != arrQuote.length; ++i){
            quotes.add(new Quote(arrQuote[i], audioIds[i]));
        }

        QuoteAdapter quoteAdapter = new QuoteAdapter(this, quotes, bgColor);
        ListView listView = findViewById(R.id.sas_list);
        listView.setAdapter(quoteAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()){
                    releaseMediaPlayer();
                }
                //TODO: small audio clips, any need for prepareAsync?
                //TODO: audioIds is stored on array audioIds, so no need of storing audioId in quote itself, but less modular?
                mediaPlayer = MediaPlayer.create(SASActivity.this, audioIds[i]);
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