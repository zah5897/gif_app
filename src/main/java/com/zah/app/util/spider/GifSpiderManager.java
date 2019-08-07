package com.zah.app.util.spider;


import com.zah.app.util.spider.gif.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zah on 2017/6/7.
 */
public class GifSpiderManager {
    private GifSpiderManager() {
        spiders = new ArrayList<>();
    }

    private static GifSpiderManager spiderManager;

    private List<BaseImgSpider> spiders;

    public static GifSpiderManager get() {
        if (spiderManager == null) {
            spiderManager = new GifSpiderManager();
            spiderManager.initSpider();
        }
        return spiderManager;
    }

    private void initSpider() {
        //spiders.add(new DuoWanGifSpider());
        spiders.add(new BQDGifSpider());
        spiders.add(new JZYLGifSpider());
//        spiders.add(new GXBGifSpider());
    }

    public void start() {
        for (BaseImgSpider spider : spiders) {
            spider.start();
        }
    }
}
