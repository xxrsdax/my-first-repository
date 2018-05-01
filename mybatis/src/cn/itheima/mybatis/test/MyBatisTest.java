package cn.itheima.mybatis.test;


import cn.itheima.mybatis.mapper.UserMapper;
import cn.itheima.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisTest {

    @Test
    public  void  test() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行指定Mapper.xml中的sql语句
        User user = sqlSession.selectOne("user.findById", 10);

        System.out.println(user);
    }
//用于测试，findByUserName方法
    @Test
    public  void  test2() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行指定Mapper.xml中的sql语句
        List<User>  list = sqlSession.selectList("user.findByUserName", "王");

        for(User user : list) {
            System.out.println(user);
        }
    }

//    测试添加用户的功能
    @Test
    public  void  test3() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //准备数据
        User user = new User();
        user.setUsername("战");
        user.setSex("男");
        user.setAddress("sff");
        user.setBirthday(new Date()); //可获取当前的数据
        //4.执行指定Mapper.xml中的sql语句
       sqlSession.insert("user.insertUser",user );

       //6.提交事务
        sqlSession.commit();
    }

    //    测试添加用户时，并返回ID的功能
    @Test
    public  void  test4() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //准备数据
        User user = new User();
        user.setUsername("暗");
        user.setSex("男");
        user.setAddress("sff");
        user.setBirthday(new Date()); //可获取当前的数据
        //4.执行指定Mapper.xml中的sql语句
        sqlSession.insert("user.insertUser",user );

        //6.提交事务
        sqlSession.commit();

        System.out.println(user.getId());
    }

    //    根据ID 修改用户
    @Test
    public  void  test5() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //准备数据
        User user = new User();
        user.setId(29);
        user.setUsername("暗黑");
        user.setSex("男");
        user.setAddress("环");
        user.setBirthday(new Date()); //可获取当前的数据
        //4.执行指定Mapper.xml中的sql语句
        sqlSession.insert("user.updateUserById",user );
        //6.提交事务
        sqlSession.commit();


    }



    //    根据ID 删除用户
    @Test
    public  void  test6() throws Exception {
//        1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.创建Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //4.执行指定Mapper.xml中的sql语句
        sqlSession.insert("user.deleteUserById",29 );   /* 由于现在没有相应的user.xml文件，会报错 */
        //6.提交事务
        sqlSession.commit();


    }

}
