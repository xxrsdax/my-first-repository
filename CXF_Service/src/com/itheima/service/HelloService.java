package com.itheima.service;

import javax.jws.WebService;

@WebService
public interface HelloService {

    public String SqlHello(String name);
}
