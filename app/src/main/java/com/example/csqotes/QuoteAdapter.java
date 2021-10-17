package com.example.csqotes;

import android.app.Activity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class QuoteAdapter extends ArrayAdapter<Quote> {

    private int mColor;
    public QuoteAdapter(Activity context, ArrayList<Quote> quotes, int color){
        super(context, 0, quotes);
        mColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView quoteText = listItemView.findViewById(R.id.list_text);

        Quote quote = getItem(position);

        quoteText.setText(quote.getText());

//        listItemView.setBackgroundColor(mColor);

        return listItemView;
    }
}
