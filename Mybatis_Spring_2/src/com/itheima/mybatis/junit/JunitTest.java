package com.itheima.mybatis.junit;


import com.itheima.mybatis.mapper.UserMapper;
import com.itheima.mybatis.pojo.User;
import com.itheima.mybatis.pojo.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JunitTest {

//    测试逆向工程生成的文件
    @Test
    public void testUserMapper() {
//        创建ApplicationContext对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

//        获取指定Mapper对象
        UserMapper mapper = applicationContext.getBean(UserMapper.class);

//        创建UserExample对象
        UserExample userExample = new UserExample();
//        创建Criterria查询，并添加条件
        String username = "明";
        userExample.createCriteria().andSexEqualTo("1").andUsernameLike("%"+username+"%");
//        设置排序
        userExample.setOrderByClause("id desc");

//        将UserExample对象做为参数，执行查询方法
        List<User> userList = mapper.selectByExample(userExample);

//        mapper.insert(new User());   增加
//        mapper.countByExample( )   统计数量

        for(User user : userList) {
            System.out.println(user.toString());
        }

    }


//    @Autowired
//    public UserMapper userMapper;
//
//    public void test2(){
//
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andSexEqualTo("1");
//        int i = userMapper.countByExample(userExample);
//        System.out.println(i);
//
//    }
//
//    @Test
//    public void test3() {
//        test2();
//    }
//    此处有一个疑惑： service 层与Test 测试环境的区别？？？？？


}
