package cn.itheima.web;

import cn.itheima.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public  static void main(String[] args) {
        //读取cxf.xml配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("cxf.xml");

        HelloService proxy = (HelloService) applicationContext.getBean("myClient"); //创建代理对象

        String s = proxy.sqlHello("小黑");

        System.out.println(s);

    }

}
