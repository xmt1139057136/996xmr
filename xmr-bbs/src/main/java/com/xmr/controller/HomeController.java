package com.xmr.controller;

import com.xmr.service.HomeService;
import com.xmr.result.XmrResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeService.class);

    @Autowired
    private HomeService homeService;

    @RequestMapping("/getMenus")
    @ResponseBody
    public XmrResponse getMenus(){
        try{
            return XmrResponse.OK(homeService.getMenus());
        }catch (Exception e){
            LOGGER.error("获取菜单数据操作异常，{}", e.getMessage(), e);
        }
        return XmrResponse.ERROR();
    }

    @RequestMapping("/")
    public String index(){
        return "html/index.html";
    }
}
