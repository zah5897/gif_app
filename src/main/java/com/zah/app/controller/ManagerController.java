package com.zah.app.controller;

import com.zah.app.util.RTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zah on 2017/5/22.
 */
@Controller
public class ManagerController {
    public static Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @RequestMapping("/")
    public String login() {
        return "login";
    }
    @RequestMapping("/mgr/forword")
    public String forword(String path) {
        return path;
    }

    @RequestMapping("/mgr/logout")
    public String logout(String path) {
        return "redirect:login";
    }

    @RequestMapping("/mgr/login")
    public ModelAndView login(String userName, String password) {
        ModelAndView rs = new ModelAndView("index");
        rs.addObject("username", userName);
        return rs;
    }
}
