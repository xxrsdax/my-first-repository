package cn.itheima.mybatis.test;


import cn.itheima.mybatis.mapper.OrdersMapper;
import cn.itheima.mybatis.mapper.UserMapper;
import cn.itheima.mybatis.pojo.Orders;
import cn.itheima.mybatis.pojo.User;
import cn.itheima.mybatis.pojo.UserPoJo;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.core.config.Order;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MapperTest {

    @Test
    public  void test1 () throws Exception {
            //1.读取配置文件
            InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
            SqlSession sqlSession = sessionFactory.openSession();

        //4.获取Mapper,          让SqlSession 对象，根据Mapper.class  接口，生成相应的UserMapper对象，（动态代理）
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        调用其中的方法
        User user = mapper.findById(1);
        System.out.println(user.getUsername());

    }

    @Test
    public  void test2 () throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        //4.获取Mapper,          让SqlSession 对象，根据Mapper.class  接口，生成相应的UserMapper对象，（动态代理）
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

//        调用其中的方法
        User user = new User();
        user.setUsername("五");
        UserPoJo userPoJo = new UserPoJo();
        userPoJo.setUser(user);
        List<User> byUserPoJo = mapper.findByUserPoJo(userPoJo);
       for(User u : byUserPoJo) {
           System.out.println(u.getId()+":"+u.getUsername());
       }
    }

      @Test
    public  void test3 () throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
// 4.获取Mapper
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);

        List<Orders> orderLsit = mapper.findOrder();

        for(Orders orders : orderLsit) {
            System.out.println(orders.getUserId());
        }
    }


    @Test
    public  void test4 () throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserPoJo userPoJo = new UserPoJo();
        List<User> userList = mapper.findByIds(userPoJo);

        for (User user : userList){
            System.out.println(user.getUsername());
        }
    }


    @Test
    public  void test5 () throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
         Integer[] ids = {1,2,3};
        List<User> userList = mapper.findByIds2(ids);
        for (User user : userList){
            System.out.println(user.getUsername());
        }
    }

    @Test
    public  void test6() throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
      List<Integer> ids = new ArrayList<>();
      ids.add(1);
      ids.add(2);
      ids.add(3);
        List<User> userList = mapper.findByIds3(ids);
        for (User user : userList){
            System.out.println(user.getUsername());
        }
    }
    @Test
    public  void test7() throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> ordersList = mapper.selectOrders();

        for (Orders orders : ordersList){
            System.out.println(orders+"==="+orders.getUser().getId() +"==="+orders.getUser().getUsername());
        }
    }

    /*一对多测试*/
    @Test
    public  void test8() throws Exception {
        //1.读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("config/SqlMapConfig.xml");
//        2.创建SqlSessionFactory对象
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        3.获取SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.selectUserLsit();
//              此处的代码存在着一定的异常     最后的打印结果与数据库的查询结果有所不同

        for (User user : userList){
            System.out.println(user.toString());
            List<Orders> list = user.getOrdersList();
            if(list != null && list.size() >0) {
                for(Orders orders : list ) {
                    System.out.println(orders.toString());
                }
            }
        }
    }

}
