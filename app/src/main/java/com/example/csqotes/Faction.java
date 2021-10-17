package com.example.csqotes;

public class Faction {

    private int mImageId;
    private String mFactionName;
    private int mBGColorId;

    public Faction(int imageId, String factionName, int BGColorId){
        mImageId = imageId;
        mFactionName = factionName;
        mBGColorId = BGColorId;
    }

    public int getImageId() {
        return mImageId;
    }

    public String getFactionName() {
        return mFactionName;
    }

    public int getBGColorId() {
        return mBGColorId;
    }
}
