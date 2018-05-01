package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Decidedzone;
import com.itheima.ax.pojo.Region;
import com.itheima.ax.pojo.Subarea;
import com.itheima.ax.service.ISubareaService;
import com.itheima.ax.utils.FileUtils;
import com.itheima.ax.web.base.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {

    @Autowired
    private ISubareaService subareaServiceImpl;

    public String add() {
        //获取参数
        Subarea subarea= getModel();
        subareaServiceImpl.add(subarea);
        return LIST;
    }

//    SubareaAction_pageQuery.action
    public String pageQuery( ) {
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
        String addresskey = getModel().getAddresskey();
        Region region = getModel().getRegion();
        if(StringUtils.isNotBlank(addresskey)) {
            detachedCriteria.add(Restrictions.like("addresskey","%"+addresskey+"%")); //模糊查询
        }
        if(region != null) {
            String province = region.getProvince(); //省
            String city = region.getCity(); //市
            String district = region.getDistrict();//区
            detachedCriteria.createAlias("region","r"); //设置别名
            if(StringUtils.isNotBlank(province)) {
                detachedCriteria.add(Restrictions.like("r.province","%"+province+"%"));
            }
            if(StringUtils.isNotBlank(city)) {
                detachedCriteria.add(Restrictions.like("r.city","%"+city+"%"));
            }
            if(StringUtils.isNotBlank(district)) {
                detachedCriteria.add(Restrictions.like("r.district","%"+district+"%"));
            }

        }
        subareaServiceImpl.pageQuery(pageBean);
        Object2Json(pageBean,new String[ ] {"currentPage","detachedCriteria","pageSize","decidedzone","subareas"}); //排除不需要展示字段
        return NONE;
   }

   /**
    * 分区导出功能
    * */
   public String exportXls() throws IOException {
       //查询所有分区数据
      List<Subarea> subareasList = subareaServiceImpl.findAll();

      //使用POI将数据写入Excel
       HSSFWorkbook hssfWorkbook = new HSSFWorkbook(); //在内存中创建一个Excel文件
       HSSFSheet subareaSheet = hssfWorkbook.createSheet("分区数据");//创建标签页
       HSSFRow headRow = subareaSheet.createRow(0); //创建第一行 （标题行）
      headRow.createCell(0).setCellValue("分区编号");
      headRow.createCell(1).setCellValue("开始编号");
      headRow.createCell(2).setCellValue("结束编号");
      headRow.createCell(3).setCellValue("位置信息");
      headRow.createCell(4).setCellValue("省市区");

      //应该将查询到的数据，存入Excel对象中
       for (Subarea subarea : subareasList){
           HSSFRow dataRow = subareaSheet.createRow(subareaSheet.getLastRowNum() + 1);
           dataRow.createCell(0).setCellValue(subarea.getId());
           dataRow.createCell(1).setCellValue(subarea.getStartnum());
           dataRow.createCell(2).setCellValue(subarea.getEndnum());
           dataRow.createCell(3).setCellValue(subarea.getPosition());
           dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
       }

       //使用输出流进行Excel文件的下载  （一个流，两个头）
       String filename = "分区数据.xls";
       String contentType = ServletActionContext.getServletContext().getMimeType(filename); //动态获取ContentType类型

       ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();//获取输出流
       //设置响应头
//    ServletActionContext.getResponse().setContentType("application/vnd.ms-excel"); //根据文件类型，在 tomcat 的 web.xml 中 可以查找到对应的设置
       ServletActionContext.getResponse().setContentType(contentType);//根据文件类型，在 tomcat 的 web.xml 中 可以查找到对应的设置

       String agent = ServletActionContext.getRequest().getHeader("User-Agent"); //获取浏览器类型
       filename = FileUtils.encodeDownloadFilename(filename, agent);//根据不同的浏览器，对文件名进行不同的处理
       ServletActionContext.getResponse().setHeader("content-disposition","attachment;filename="+filename); //设响应头，表示下载，以及设置文件名

       hssfWorkbook.write(outputStream); //调用hssfWorkbook的write的方法，进行下载
       return NONE;
   }


    public String listajax() {
        //查询所有未被关联的分区
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.isNull("decidedzone"));
        List<Subarea> subareaList = subareaServiceImpl.findlistByDecidedzoneNull(detachedCriteria);
        //将数据转化为json格式的数据
        Object2Json(subareaList,new String[] {"decidedzone","region","startnum","endnum","single","id"}); //指定排除的

       return NONE;
    }

  //    SubareaAction_findListAssociationDecidedzone.action?decidedzoneId
   //属性注入
    private String decidedzoneId;
    public void setDecidedzoneId(String decidedzoneId) {
        this.decidedzoneId = decidedzoneId;
    }
    /**
     * 查询与指定定区关联的分区
     * */
    public String findListAssociationDecidedzone() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
        detachedCriteria.add(Restrictions.eq("decidedzone.id",decidedzoneId));
        List<Subarea> subareaList = subareaServiceImpl.findListAssociationDecidedzone(detachedCriteria);
        Object2Json(subareaList,new String[] {"decidedzone","subareas"});
        return NONE;  //查询与定区有关联的分区
    }

}

