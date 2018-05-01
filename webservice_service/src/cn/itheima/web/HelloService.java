package cn.itheima.web;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
@WebService
public class HelloService {
    public String SayHello() {
        System.out.println("Hello sayHello webService 服务端被调用了 ");
        return "Hello";
    }
    public static void main(String[] args) {
        String address = "http://192.168.45.1:8080/hello";
        HelloService helloService = new HelloService();
        Endpoint.publish(address,helloService); //地址，服务提供者
    }

}
