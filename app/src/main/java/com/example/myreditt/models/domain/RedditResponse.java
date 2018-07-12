package com.example.myreditt.models.domain;

/**
 * Created by Sanoop
 */

public class RedditResponse {

    private String kind;
    private RedditData data;

    public String getKind() {
        return kind;
    }

    public RedditData getData() {
        return data;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setData(RedditData data) {
        this.data = data;
    }
}
