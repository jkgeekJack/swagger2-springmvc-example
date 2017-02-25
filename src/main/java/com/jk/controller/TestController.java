/*
 *
 *  * Copyright (c) 2015-2016 zjc@ppfix.cn
 *  * May you do good and not evil.
 *  * May you find forgiveness for yourself and forgive others.
 *  * May you share freely,never taking more than you give.
 *  * Please do not del these words when you share or copy this file
 *
 */

package com.jk.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "测试API", description = "测试API")
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/hello")
    public String hello(String name) {
        return "hello "+name;
    }

}
