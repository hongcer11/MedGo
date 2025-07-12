package com.group4.ui.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.data.model.News;
import com.group4.medgo.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    List<News> newsList;

    public NewsAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        News news = newsList.get(position);

        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsSummary.setText(news.getNewsSummary());
        holder.newsDate.setText(news.getNewsDate());
        holder.newsCategory.setText(news.getNewsCategory());
        holder.newsImage.setImageResource(news.getNewsImage());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public void updateData(List<News> filteredList) {
        this.newsList = filteredList;
        notifyDataSetChanged();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle, newsSummary, newsDate, newsCategory;
        ImageView newsImage;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsSummary = itemView.findViewById(R.id.newsSummary);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsCategory = itemView.findViewById(R.id.newsCategory);
            newsImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
