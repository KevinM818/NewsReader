package com.example.android.newsreader;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Kevin Mangal on 5/21/2017.
 */

public class NewsItemAdapter extends ArrayAdapter<NewsItem> {

    public NewsItemAdapter(Activity context, ArrayList<NewsItem> newsItems){
        super(context, 0, newsItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list__item, parent, false);
        }

        NewsItem currentNewsItem = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        titleTextView.setText(currentNewsItem.getTitle());

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.news_description);
        descriptionTextView.setText(currentNewsItem.getDescription());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.news_author);
        authorTextView.setText(currentNewsItem.getAuthor());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        Picasso.with(getContext()).load(currentNewsItem.getImageUrl()).into(imageView);

        return listItemView;
    }


}