package com.itheima.mybatis.junit;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itheima.mybatis.mapper.UserMapper;
import com.itheima.mybatis.pojo.User;

public class JunitTest {

	
	@Test
	public void testMapper() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserMapper mapper = ac.getBean(UserMapper.class);
//		UserMapper mapper = (UserMapper) ac.getBean("userMapper");
		User user = mapper.findUserById(10);
		System.out.println(user);
	}

//测试Mpper的动态代理扫描版
	@Test
	public void test2() throws Exception {
//		 创建ApplicationnContext对象
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//		获取UserMapper对象
		UserMapper mapper = applicationContext.getBean(UserMapper.class);  //根据相应接口获取相应的对象

//		执行方法
		User user  = mapper.findUserById(10);

		System.out.println(user.toString());
	}
}
