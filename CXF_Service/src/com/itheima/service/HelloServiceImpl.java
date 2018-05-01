package com.itheima.service;

public class HelloServiceImpl implements HelloService {

    public String SqlHello(String name) {

        System.out.println("基于CXF框架开发的webService 服务端被访问了");

        return "Hello"+name;

    }
}
