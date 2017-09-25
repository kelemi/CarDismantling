package com.aofan.cardismantling.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/25.
 */
public class NewsListItem implements Serializable {

    private String newsId;
    private String newsName;
    private String newsPubDate;
    private String newsType;


    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsName() {
        return newsName;
    }

    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }

    public String getNewsPubDate() {
        return newsPubDate;
    }

    public void setNewsPubDate(String newsPubDate) {
        this.newsPubDate = newsPubDate;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    @Override
    public String toString() {
        return "NewsListItem{" +
                "newsId='" + newsId + '\'' +
                ", newsName='" + newsName + '\'' +
                ", newsPubDate='" + newsPubDate + '\'' +
                ", newsType='" + newsType + '\'' +
                '}';
    }
}
