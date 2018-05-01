package com.itheima.web;

public class Test {
     public static void main(String[] args){
         HelloServiceService helloServiceService = new HelloServiceService();
         HelloService helloServicePort = helloServiceService.getHelloServicePort();
         String s = helloServicePort.sayHello();
         System.out.println(s);
     }
}
