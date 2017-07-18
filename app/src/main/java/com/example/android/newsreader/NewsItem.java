package com.example.android.newsreader;

/**
 * Created by Kevin Mangal on 5/21/2017.
 */

public class NewsItem {

    private String title;
    private String description;
    private String author;
    private String url;
    private String imageUrl;

    public NewsItem(String title, String description, String author, String url, String imageUrl) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

