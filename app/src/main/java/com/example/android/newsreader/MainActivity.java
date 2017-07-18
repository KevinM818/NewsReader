package com.example.android.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import java.util.List;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsItem>> {

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_REQUEST_URL = "https://newsapi.org/v1/articles?source=cnn&apiKey=c51635c5472e4237b8b38bcf28da08c8";
    private NewsItemAdapter newsItemAdapter;
    private static final int NEWS_LOADER_ID = 1;
    private TextView mEmptyStateTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.list);
        newsItemAdapter = new NewsItemAdapter(this, new ArrayList<NewsItem>());
        listView.setAdapter(newsItemAdapter);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsItem newsItem = newsItemAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(newsItem.getUrl()));
                startActivity(intent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        }
        else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText("No internet connection.");
        }

    }


    @Override
    public Loader<List<NewsItem>> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(NEWS_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format","json");
        uriBuilder.appendQueryParameter("limit","10");

        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<NewsItem>> loader, List<NewsItem> data) {
        newsItemAdapter.clear();

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (data != null && !data.isEmpty()){
            newsItemAdapter.addAll(data);
        }
        mEmptyStateTextView.setText("No News Found");
    }

    @Override
    public void onLoaderReset(Loader<List<NewsItem>> loader) {
        newsItemAdapter.clear();
    }

}
