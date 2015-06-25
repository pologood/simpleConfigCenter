package com.jd.jr.simpleconfig.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * User: yangkuan@jd.com
 * Date: 14-8-11
 * Time: 下午5:51
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "jsp/index";
    }





}
