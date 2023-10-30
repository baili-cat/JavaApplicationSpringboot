package com.example.test.xskytest.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author baili
 * @date 2022年11月02日16:57
 */
@RestController
@CrossOrigin
@RequestMapping("/tomcatController/")
//纯http请求
public class TomcatController {

    /**
     * @param description get返回list
     * @author baili
     * @date 2022/11/2 17:00
     */
    @GetMapping("returnString")
    public String returnString(@RequestParam(value = "description", defaultValue = "TestOps平台~") String description) {

        return "tomcat-9.0.71";
    }


}
