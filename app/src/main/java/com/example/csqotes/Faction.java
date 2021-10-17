package com.example.csqotes;

public class Faction {

    private int mImageId;
    private String mFactionName;

    public Faction(int imageId, String factionName){
        mImageId = imageId;
        mFactionName = factionName;
    }

    public int getImageId() {
        return mImageId;
    }

    public String getFactionName() {
        return mFactionName;
    }
}
