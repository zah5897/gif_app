package com.zah.app.util.spider;

import com.zah.app.comm.err.SpiderException;
import com.zah.app.push.PushUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/**
 * Created by zah on 2017/6/28.
 */
public class BaseSpider {
    public synchronized void start() {
    }

    protected String getTrimContent(String content) {
        return content.trim().replace(Jsoup.parse("&nbsp;").text(), "");
    }

    protected String getTrimContent(Element element) {
        return element.text().trim().replace(Jsoup.parse("&nbsp;").text(), "");
    }

    protected void reportErr(SpiderException err) {
        try {
            PushUtil.sendAndroidCustomizedcast(err.getSpiderName(), err.getMsg(), err.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
