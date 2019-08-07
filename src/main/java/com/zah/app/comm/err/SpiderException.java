package com.zah.app.comm.err;

/**
 * Created by zah on 2017/6/20.
 */
public class SpiderException extends RuntimeException {
    String cause;
    String url;
    String spiderName;

    public SpiderException(String spiderName, String cause, String url) {
        this.spiderName = spiderName;
        this.cause = cause;
        this.url = url;
    }

    public String getMsg() {
        return cause;
    }

    public String getUrl() {
        return url;
    }

    public String getSpiderName() {
        return spiderName;
    }
}

