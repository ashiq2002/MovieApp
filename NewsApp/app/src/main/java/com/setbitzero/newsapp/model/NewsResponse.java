package com.setbitzero.newsapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class NewsResponse implements Parcelable {
    private String status;
    private int totalResults;
    private List<NewsArticles> articles;

    public NewsResponse(){}

    public NewsResponse(String status, int totalResults, List<NewsArticles> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    protected NewsResponse(Parcel in) {
        status = in.readString();
        totalResults = in.readInt();
    }

    public static final Creator<NewsResponse> CREATOR = new Creator<NewsResponse>() {
        @Override
        public NewsResponse createFromParcel(Parcel in) {
            return new NewsResponse(in);
        }

        @Override
        public NewsResponse[] newArray(int size) {
            return new NewsResponse[size];
        }
    };

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsArticles> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public String toString() {
        return "NewsResponse{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(status);
        dest.writeInt(totalResults);
    }
}
