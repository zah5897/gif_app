package com.zah.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zah.app.dao.GifDao;
import com.zah.app.model.GifModel;
import com.zah.app.util.JSONUtil;
import com.zah.app.util.RTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by zah on 2017/5/22.
 */
@Service
public class GifService {
    @Autowired
    GifDao gifDao;

    public ModelMap listGif(int type, Integer page, Integer limit, String key_word) {
        key_word = "";
        if (page == null || page <= 0) {
            page = 1;
        }
        if (limit == null) {
            limit = 20;
        }
        //-----------------------模糊查询

        List<GifModel> gifs = gifDao.listGif(type, page, limit, key_word);

        if (gifs == null) {
            gifs = loadRandom(type, page, limit);
        } else if (gifs.size() < limit) {
            gifs.addAll(loadRandom(type, page, limit - gifs.size()));
        }
        return RTUtil.getOKMap().addAttribute("gifs", gifs).addAttribute("hasMore", gifs.size() == limit);
    }


    private List<GifModel> loadRandom(int type, int pageIndex, int limit) {
        return gifDao.loadRandom(type, pageIndex, limit);
    }

    public ModelMap listGifForMgr(int type, Integer page, Integer limit) {

        if (page == null || page <= 0) {
            page = 1;
        }
        if (limit == null) {
            limit = 20;
        }
        //-----------------------模糊查询

        List<GifModel> gifs = loadRandom(type, page, limit);
        long count = gifDao.getCount(type);
        long pageCount = getPageCount(count, limit);
        return RTUtil.getOKMap().addAttribute("gifs", gifs).addAttribute("pageCount", pageCount).addAttribute("currentPageIndex", page);
    }

    public ModelMap for_AppStore(int pageIndex, int limit) {
        //-----------------------模糊查询

        List<GifModel> gifs = gifDao.loadfor_AppStore(pageIndex, limit);
        long count = gifDao.getCountfor_AppStore();
        long pageCount = getPageCount(count, limit);
        return RTUtil.getOKMap().addAttribute("gifs", gifs).addAttribute("pageCount", pageCount).addAttribute("currentPageIndex", pageIndex);
    }

    private long getPageCount(long count, int limit) {
        long pageCount = count / limit;
        if (count % limit > 0) {
            pageCount += 1;
        }
        return pageCount == 0 ? 1 : pageCount;
    }

    public ModelMap mgrDel(int type, String id, Integer currentPage, Integer limit) {
        gifDao.delGifById(id);


        List<GifModel> gifs = gifDao.loadRandom(type, currentPage, limit);
        ModelMap r = RTUtil.getOKMap();
        long count = gifDao.getCount(type);
        long pageCount = getPageCount(count, limit);
        r.put("pageCount", pageCount);
        if (gifs != null && gifs.size() > 0) {
            r.put("gif", gifs.get(gifs.size() - 1));
            r.put("currentPageIndex", currentPage);
        } else {
            r.put("currentPageIndex", currentPage - 1 > 0 ? currentPage - 1 : 1);
        }
        return r;
    }

    public ModelMap delBatch(int type, String ids, Integer currentPage, Integer limit) {
        List<String> id_s = JSONUtil.jsonToList(ids, new TypeReference<List<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        for (String id : id_s) {
            gifDao.delGifById(id);
        }


        List<GifModel> gifs = gifDao.loadRandom(type, currentPage, limit);
        ModelMap r = RTUtil.getOKMap();
        long count = gifDao.getCount(type);
        long pageCount = getPageCount(count, limit);
        r.put("pageCount", pageCount);
        if (gifs != null && gifs.size() > 0) {
            r.put("gifs", gifs);
            r.put("currentPageIndex", currentPage);
        } else {
            r.put("currentPageIndex", currentPage - 1 > 0 ? currentPage - 1 : 1);
        }

        return r;
    }

    public ModelMap addToAppStore(String id) {
        gifDao.updateFlag(id, 1);
        return RTUtil.getOKMap();
    }

    public ModelMap appStoreDel(String id, int currentPage, int limit) {
        gifDao.updateFlag(id, 0);


        List<GifModel> gifs = gifDao.loadfor_AppStore(currentPage, limit);
        ModelMap r = RTUtil.getOKMap();
        long count = gifDao.getCountfor_AppStore();
        long pageCount = getPageCount(count, limit);
        r.put("pageCount", pageCount);
        if (gifs != null && gifs.size() > 0) {
            r.put("gif", gifs.get(gifs.size() - 1));
            r.put("currentPageIndex", currentPage);
        } else {
            r.put("currentPageIndex", currentPage - 1 > 0 ? currentPage - 1 : 1);
        }
        return r;
    }

    public ModelMap appStoreDelBatch(String ids, Integer currentPage, Integer limit) {


        List<String> id_s = JSONUtil.jsonToList(ids, new TypeReference<List<String>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        for (String id : id_s) {
            gifDao.updateFlag(id, 0);
        }


        List<GifModel> gifs = gifDao.loadfor_AppStore(currentPage, limit);
        ModelMap r = RTUtil.getOKMap();
        long count = gifDao.getCountfor_AppStore();
        long pageCount = getPageCount(count, limit);
        r.put("pageCount", pageCount);
        if (gifs != null && gifs.size() > 0) {
            r.put("gifs", gifs);
            r.put("currentPageIndex", currentPage);
        } else {
            r.put("currentPageIndex", currentPage - 1 > 0 ? currentPage - 1 : 1);
        }
        return r;
    }

    public boolean save(GifModel img) {
        if (!exist(img)) {
            gifDao.insertObj(img);
            return true;
        }
        return false;
    }


    public boolean exist(GifModel img) {
        return gifDao.getImgCount(img.getImg_url()) > 0;
    }
}
