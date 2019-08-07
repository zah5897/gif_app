package com.zah.app.util.spider;

/**
 * Created by zah on 2017/6/7.
 */
public class SpiderManager {
    private SpiderManager() {
    }

    private static SpiderManager spiderManager;

    public static SpiderManager get() {
        if (spiderManager == null) {
            spiderManager = new SpiderManager();
        }
        return spiderManager;
    }

    public void start() {
        GifSpiderManager.get().start();
    }
}
