package com.example.android.newsreader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Kevin Mangal on 5/22/2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsItem> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        List<NewsItem> result = QueryUtils.fetchNewsData(mUrl);
        return result;
    }
}
