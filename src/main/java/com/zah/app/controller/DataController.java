package com.zah.app.controller;

import com.zah.app.service.GifService;
import com.zah.app.util.RTUtil;
import com.zah.app.util.spider.SpiderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zah on 2017/5/22.
 */
@RestController
@RequestMapping("list")
public class DataController {
    public static Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    GifService gifService;

    @RequestMapping("/{type}")
    public ModelMap listData(@PathVariable int type, Integer page, Integer pageIndex, Integer limit, String key_word) {
        if (page == null && pageIndex != null) {
            page = pageIndex;
        }
        return gifService.listGif(type, page, limit, key_word);
    }

    @RequestMapping("/report")
    public ModelMap report(String id, String desc) {
        return RTUtil.getOKMap();
    }

    @RequestMapping("/mgr/{type}")
    public ModelMap mgrData(@PathVariable int type, Integer page, Integer pageIndex, Integer limit) {
        if (page == null && pageIndex != null) {
            page = pageIndex;
        }
        return gifService.listGifForMgr(type, page, limit);
    }

    @RequestMapping("/mgr/del")
    public ModelMap mgrDel(int type, String id, Integer currentPage, Integer limit) {
        return gifService.mgrDel(type, id, currentPage, limit);
    }

    @RequestMapping("/mgr/delBatch")
    public ModelMap delBatch(String ids, int type, Integer currentPage, Integer limit) {
        return gifService.delBatch(type, ids, currentPage, limit);
    }

    @RequestMapping("/mgr/appStore")
    public ModelMap addToAppStore(int type, String id) {
        return gifService.addToAppStore(id);
    }

    @RequestMapping("/for_AppStore")
    public ModelMap for_AppStore(Integer page, Integer pageIndex, Integer limit) {
        if (page == null && pageIndex != null) {
            page = pageIndex;
        }
        return gifService.for_AppStore(page == null ? 1 : page, limit == null ? 20 : limit);
    }

    @RequestMapping("/appStore/del")
    public ModelMap appStoreDel(String id, int currentPage, int limit) {
        return gifService.appStoreDel(id, currentPage, limit);
    }

    @RequestMapping("/appStore/delBatch")
    public ModelMap appStoreDelBatch(String ids, Integer currentPage, Integer limit) {
        return gifService.appStoreDelBatch(ids, currentPage, limit);
    }

    @RequestMapping("/spider_start")
    public ModelMap spider_start(String ids, Integer currentPage, Integer limit) {
        new Thread() {
            @Override
            public void run() {
                SpiderManager.get().start();
            }
        }.start();
        return RTUtil.getOKMap();
    }

}
