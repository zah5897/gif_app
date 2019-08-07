package com.zah.app.model.node;

/**
 * Created by zah on 2017/6/7.
 */
public class Node {
    protected int type = 0;
    protected String title;
    protected String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
