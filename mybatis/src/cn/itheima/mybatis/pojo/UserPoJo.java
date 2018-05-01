package cn.itheima.mybatis.pojo;

import java.io.Serializable;

public class UserPoJo implements Serializable {


    private  static final  long serialVersionUID = 1L;

    Integer[] ids = {1,2,3};

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
