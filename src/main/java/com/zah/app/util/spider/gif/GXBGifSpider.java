package com.zah.app.util.spider.gif;


import com.zah.app.SpringUtil;
import com.zah.app.model.GifModel;
import com.zah.app.model.type.ChannelType;
import com.zah.app.push.PushUtil;
import com.zah.app.service.GifService;
import com.zah.app.util.TextUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by zah on 2017/7/19.
 */
public class GXBGifSpider extends BaseImgSpider {
    String url = "http://www.hugao8.com/category/gao-gif/";
    boolean spidering = false;

    private final static int MAX_SPIDER_COUNT_EVERYDAY = 30;

    @Override
    public synchronized void start() {
        super.start();
        if (spidering) {
            return;
        }
        spidering = true;
        String encode = "utf8";
//        String encode = "GBK";
        try {
            System.out.println(GXBGifSpider.class.getName() + " start work!");
            Document doc = getDoc(url, encode);
//            Document doc = getDocScroll(url, encode);
            if (doc != null) {
                prase(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("当前URL=" + url);
            PushUtil.sendAndroidCustomizedcast(this.getClass().getName(), e.getMessage(), url);
        }
        spidering = false;
    }

    private void prase(Document doc) throws IOException {
        Elements elements = doc.select("ul.clearfix").select(".pList");
        if (elements.size() > 0) {
            Elements lis = elements.get(0).select("li");
            Collections.reverse(lis);
            for (Element e : lis) {
                Elements imgs = e.select("div.picBox>a>img");
                if (imgs.size() == 1) {
                    String title = imgs.get(0).attr("alt");
                    String src = imgs.get(0).attr("src");
                    GifModel gifModel = new GifModel();
                    gifModel.setChannel(ChannelType.Funny.ordinal());
                    gifModel.setImg_url(src);
                    gifModel.setDescription(title);
                    if (TextUtils.isEmpty(gifModel.getImg_url()) || TextUtils.isEmpty(gifModel.getDescription())) {
                        throw new RuntimeException("imgmodel content is error url=" + url);
                    }
                    String subFix = src.substring(src.lastIndexOf(".") + 1);
                    if (subFix.equalsIgnoreCase("GIF")) {
                        boolean result = SpringUtil.getBean(GifService.class).save(gifModel);
                        if (result) {
                        }
                    }


                }

                //-------end for
            }
        }
    }

    public static void main(String[] args) {
        new GXBGifSpider().start();
    }
}
