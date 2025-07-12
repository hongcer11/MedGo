package com.group4.data.model;

public class News {
    String newsTitle;
    String newsSummary;
    int newsImage;
    String newsDate;
    String newsCategory;

    public News(String newsTitle, String newsSummary, int newsImage, String newsDate, String newsCategory) {
        this.newsTitle = newsTitle;
        this.newsSummary = newsSummary;
        this.newsImage = newsImage;
        this.newsDate = newsDate;
        this.newsCategory = newsCategory;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSummary() {
        return newsSummary;
    }

    public void setNewsSummary(String newsSummary) {
        this.newsSummary = newsSummary;
    }

    public int getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(int newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }
}
