
package com.lzycug.aop.controller;

import com.lzycug.aop.config.LogAop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description：用于测试的访问控制层
 * author：lzyCug
 * date: 2020/4/5 23:00
 */
@RestController
@RequestMapping("/aop")
public class AopController {
    @GetMapping("get")
    public void testGet() {
        System.out.println("get...");
    }

    @PostMapping("post")
    public void testPost() {
        System.out.println("post...");
    }

    @LogAop
    @RequestMapping("request")
    public void testRequest() {
        System.out.println("request...");
    }
}
