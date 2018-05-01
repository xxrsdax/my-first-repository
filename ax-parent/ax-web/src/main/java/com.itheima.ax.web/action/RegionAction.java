package com.itheima.ax.web.action;

import com.itheima.ax.pojo.Region;
import com.itheima.ax.service.IRegionService;
import com.itheima.ax.utils.PageBean;
import com.itheima.ax.utils.PinYin4jUtils;
import com.itheima.ax.web.base.BaseAction;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{

    @Autowired
    private IRegionService regionServiceImpl;

    //属性驱动
    private File regionFile;
    public void setRegionFile(File regionFile) {
        this.regionFile = regionFile;
    }
    /**
     * 一件上传文件
     * */
    public String importXls() throws IOException {
        //使用apache POI 解析Excel文件
        //创建 HSSFWorkbook
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(regionFile));
//        hssfWorkbook.getSheetAt(0);  根据索引获取页面
        HSSFSheet sheet1 = hssfWorkbook.getSheet("Sheet1");
        //遍历页面对象
        List<Region>  regionList = new ArrayList();  //
        for(Row row :sheet1){
            int rowNum = row.getRowNum();  //获取行号
            if(rowNum == 0) {    //判断行号是否等于零
                continue;
            }
            String id = row.getCell(0).getStringCellValue();  //区域编号
            String province = row.getCell(1).getStringCellValue(); //省份
            String city = row.getCell(2).getStringCellValue();  //城市
            String district = row.getCell(3).getStringCellValue(); //区域
            String postcode = row.getCell(4).getStringCellValue(); //邮政编码
            Region region = new Region(id,   province,   city,   district, postcode,   null,   null,   null); //创建对象

           //截取字符串
            province = StringUtils.substring(province,0,province.length() - 1);
            city = StringUtils.substring(city,0,city.length() - 1);
            district = StringUtils.substring(district,0,district.length() - 1);

            //将省市区进行拼接
            String info  = province + city + district;
            //调用pinyin4j的工具类
            //获取城市简码
            String[] headByString = PinYin4jUtils.getHeadByString(info);
            String shortCode = StringUtils.join(headByString);

            //获取城市编码
            String cityCode = PinYin4jUtils.hanziToPinyin(city, "");//设置separator

            //设置简码   城市编码
            region.setShortcode(shortCode);
            region.setCitycode(cityCode);
            //  将遍历出来的结果，
            regionList.add(region);
        }
        regionServiceImpl.saveBatch(regionList);
        return NONE;  // 由于不刷新页面，所以不需要返回什么值
    }


//    RegionAction_pageQuery   区域分页
    public String pageQuery() throws IOException {

        //2.调用方法查询结果
        regionServiceImpl.pageQuery(pageBean);
        this.Object2Json(pageBean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
        return NONE; //回显json格式数据。所以不需要配置回显
    }


    private  String q;

    /**
     * 查询数组集合
     * */
    public  String ajaxList() {
        if(StringUtils.isNotBlank(q)) {
            List<Region> regionList = regionServiceImpl.findListByQ(q);
            Object2Json(regionList,new String[ ]{"province","city","district","postcode","shortcode","citycode","subareas"});
            return NONE;

        }else {
            //查询所有的区域信息
            List<Region> regionList = regionServiceImpl.findAll();
            //将查询的结果，转化成json格式，并将信息回写
            Object2Json(regionList,new String[ ]{"province","city","district","postcode","shortcode","citycode","subareas"});
            return NONE; //回写 json 格式数据，不需要设置结果集
        }
    }

    public void setQ(String q) {
        this.q = q;
    }
}
