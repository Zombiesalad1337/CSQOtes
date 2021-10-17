package com.example.csqotes;

public class Quote {
    private String mText;
    //different QuoteAdapter for each activity, so store bg color there
    //transparency value too
    private int mAudioResourceId = 0;

    public Quote(String text, int audioResourceId) {
        mText = text;
        mAudioResourceId = audioResourceId;
    }

    public String getText() {
        return mText;
    }

    public int getAudioResourceId() {
        return getAudioResourceId();
    }
}
