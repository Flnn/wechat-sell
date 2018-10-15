package com.flnn.wechatsell.mock;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/mock")
public class MockController {

    @RequestMapping(path="/goods", method = RequestMethod.GET)
    @ResponseBody
    public String getGoodsList(){
        return "{'status': '0','msg':'商品列表'}";
    }
}
