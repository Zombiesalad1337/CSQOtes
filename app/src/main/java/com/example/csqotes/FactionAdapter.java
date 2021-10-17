package com.example.csqotes;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FactionAdapter extends ArrayAdapter<Faction> {

    private int itemHeightPx;
    private int itemWidthPx;
    public FactionAdapter(Activity context, ArrayList<Faction> factions, int widthPx, int heightPx){
        super(context, 0, factions);
        itemWidthPx = widthPx / 2;
        itemHeightPx = heightPx / 3;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null){
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }
        //finding child views of grid item's relative layout?
        ImageView factionImage = gridItemView.findViewById(R.id.faction_image);
        TextView factionName = gridItemView.findViewById(R.id.faction_name);

        Faction faction = getItem(position);

        factionImage.setImageResource(faction.getImageId());
        factionName.setText(faction.getFactionName());
//        factionName.setTextColor(Color.parseColor("#FFFFFF"));

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(itemHeightPx,itemHeightPx);
        gridItemView.setLayoutParams(layoutParams);

        gridItemView.setBackgroundColor(faction.getBGColorId());



        return gridItemView;
    }
}
