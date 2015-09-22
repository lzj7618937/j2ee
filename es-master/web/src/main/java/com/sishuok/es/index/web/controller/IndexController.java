/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.sishuok.es.index.web.controller;

import com.sishuok.es.maintain.push.service.PushApi;
import com.sishuok.es.personal.calendar.service.CalendarService;
import com.sishuok.es.personal.message.service.MessageService;
import com.sishuok.es.sys.resource.entity.tmp.Menu;
import com.sishuok.es.sys.resource.service.ResourceService;
import com.sishuok.es.sys.user.entity.User;
import com.sishuok.es.sys.user.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-1-18 下午10:15
 * <p>Version: 1.0
 */
@Controller("indexIndexController")
@RequestMapping("/index")
public class IndexController {

    @RequestMapping(value = "index")
    public String index(@CurrentUser User user, Model model){
        System.out.println("this is return html view!");
        return "index/index";
    }

    @RequestMapping(value = "plan")
    public String makePlan(@CurrentUser User user, Model model){
        System.out.println("this is return html view!");
        return "index/plan";
    }

    @RequestMapping(value = "design")
    public String listDesign(@CurrentUser User user, Model model){
        System.out.println("this is return html view!");
        return "index/design";
    }

    @RequestMapping(value = "detail")
    public String detailDesign(@CurrentUser User user, Model model){
        System.out.println("this is return html view!");
        return "index/detail";
    }
}
