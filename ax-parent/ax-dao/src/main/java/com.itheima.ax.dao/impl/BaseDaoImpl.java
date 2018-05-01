package com.itheima.ax.dao.impl;

import com.itheima.ax.dao.base.IBaseDao;
import com.itheima.ax.pojo.Region;
import com.itheima.ax.pojo.Staff;
import com.itheima.ax.utils.AxUtils;
import com.itheima.ax.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.omg.Dynamic.Parameter;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {

//注入SessionFactory
    @Resource  //Resource 可以根据对象名注入，也可根据类型注入    （根据类型注入Spring 工厂中的会话工厂对象sessionFactory）
    public void setMySessionFactory(SessionFactory sessionFactory) {
         super.setSessionFactory(sessionFactory);
    }

    Class<T>  entityClass  ;

    public BaseDaoImpl () {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass(); //获取父类类型，并强转为ParameterizedType类型

        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();//获取父类声明上的泛型数组

        entityClass = (Class<T>) actualTypeArguments[0];  //获取数组中的第一个值，并强转为 Class类型
    }

    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    public T  findById(Serializable id) {

       return this.getHibernateTemplate().get(entityClass,id);//获取

    }

  
    public List<T> findAll() {

        String hql = " from  "+entityClass.getSimpleName();

        return (List<T>) this.getHibernateTemplate().find(hql);

    }

 /**
  * 通用更新
  * */
    public void excuteUpdate(String queryName, Object... objects) {
//        获取与线程绑定的session对象
        Session session = this.getSessionFactory().getCurrentSession();

//
        Query namedQuery = session.getNamedQuery(queryName);//根据 参数 获取  query对象

        int i = 0;
        for(Object object : objects) {  //遍历 objects
            namedQuery.setParameter(i++,object);  //为 ？ 赋值
        }

//        执行更新
        namedQuery.executeUpdate();
    }

    /**
     * 通用分页
     * */
    public void pageQuery(PageBean pageBean) {
        //获取出pageBean中包含的参数
        int currentPage = pageBean.getCurrentPage();
        int pageSize = pageBean.getPageSize();
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();

        //查询total  及总条数
        detachedCriteria.setProjection(Projections.rowCount()); //设置聚合函数
        List<Long>  criteria = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        pageBean.setTotal(criteria.get(0));

        detachedCriteria.setProjection(null);  //清除聚合函数
        detachedCriteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);  //设置hibernate框架封装数据的方式, （1）若不设置立即加载时，可能会出现问题
        //查询分页的内容
        int firstResult = (currentPage - 1 ) * pageSize; //查询分页的起始页
        int maxResults = pageSize; //最大查询条数
        List  list = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
        pageBean.setRows(list);

    }

     /**
      * 更新或保存
      * */
    public void saveOrUpdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }

    /**
     * 根据离线查询对象查询结果
     * */
    public List<T> find(DetachedCriteria detachedCriteria) {

        return   (List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria);

    }
}
