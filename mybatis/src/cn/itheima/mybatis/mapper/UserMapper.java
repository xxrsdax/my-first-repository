package cn.itheima.mybatis.mapper;

import cn.itheima.mybatis.pojo.User;
import cn.itheima.mybatis.pojo.UserPoJo;

import java.util.List;

public interface UserMapper {
//    满足四大原则
/**
* 根据id 查询用户
* */
    public User findById(Integer id);

    /**
     * 根据用户名，查询用户
     * */
    public List<User> findByUserName(String username);
    /**
     *    增加用户
     * */
    public  User insertUser(User user);

    /**
     * 根据id修改用户
     * */
    public User updateUserById(User user);

    /**
     * 根据 id 删除用户
     * */
    public User deleteUserById(Integer id);

    public  List<User> findByUserPoJo(UserPoJo userPoJo);
//    public  User findByUserPoJo2(UserPoJo userPoJo);

//    查询  根据多个id  查询用户
    public List<User> findByIds(UserPoJo userPoJo);


    //    查询  根据多个id  查询用户
    public List<User> findByIds2(Integer[] ids);

    //    查询  根据多个id  查询用户
    public List<User> findByIds3(List ids);

//    查询所有用户的订单
    public List<User> selectUserLsit();
}
