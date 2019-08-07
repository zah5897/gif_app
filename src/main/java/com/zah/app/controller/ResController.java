package com.zah.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zah on 2017/5/22.
 */
@RestController
public class ResController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ResController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/avatar/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {
//        try {
//            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ImageSaveUtils.getAVATARROOT(), filename).toString()));
//        } catch (Exception e) {
//            return ResponseEntity.notFound().build();
//        }
        return null;
    }
}
