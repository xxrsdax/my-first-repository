package com.itheima.ax.web.base;

import com.itheima.ax.pojo.Region;
import com.itheima.ax.utils.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    public  final  String HOME = "home";
    public  final  String LIST = "list";

    private T model;

    public T getModel() {
        return model;
    } //模型驱动

    //1.创建PageBean对象
   protected PageBean pageBean = new PageBean();

   private DetachedCriteria detachedCriteria = null;

    public void setRows(int rows) {
        pageBean.setPageSize(rows);  //设置每页显示的条数
    }
    public void setPage(int page) {
        pageBean.setCurrentPage(page); //设置当前页数
    }

    public void Object2Json(Object object,String[] excludes) {
        //3.调用JSON-lib  将pageBean转化为json格式数据
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes); //设置不需要转换为json的字段
        String json = JSONObject.fromObject(object, jsonConfig).toString();//将对象按要求转换成json格式数据
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8"); //设置响应格式 和 编码
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //方法的重载
    public void Object2Json(List list, String[] excludes) {
        //3.调用JSON-lib  将pageBean转化为json格式数据
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes); //设置不需要转换为json的字段
        String json = JSONArray.fromObject(list, jsonConfig).toString();//将对象按要求转换成json格式数据
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8"); //设置响应格式 和 编码
        try {
            ServletActionContext.getResponse().getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public BaseAction (){
        //获取父类的Class
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
//        获取运行期的泛型类型
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
//        运用反射创建对象
        Class<T> actualTypeArgument = (Class<T>)actualTypeArguments[0];
        detachedCriteria = DetachedCriteria.forClass(actualTypeArgument); //根据Class对象 ，创建离线查询对象 DetachedCriteria
        pageBean.setDetachedCriteria(detachedCriteria);  //为pageBean赋值
        try {
//            利用反射，创建对象
            model = actualTypeArgument.newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
