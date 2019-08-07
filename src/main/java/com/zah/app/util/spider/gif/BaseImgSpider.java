package com.zah.app.util.spider.gif;

import com.zah.app.util.spider.BaseSpider;
import com.zah.app.util.spider.SpiderPraser;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by zah on 2017/7/19.
 */
public class BaseImgSpider extends BaseSpider {
    protected Document getDoc(String url, String code) throws IOException {
        String src = SpiderPraser.getHtml(url, code);
        return SpiderPraser.toDoc(src);
    }

    protected Document getDocScroll(String url, String code) throws IOException {
        String src = SpiderPraser.getHtmlWithScroll(url, code);
        return SpiderPraser.toDoc(src);
    }
}
