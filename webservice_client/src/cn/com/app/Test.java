package cn.com.app;

import cn.com.webxml.MobileCodeWS;
import cn.com.webxml.MobileCodeWSSoap;

public class Test {
    public static void main(String[] args){
        MobileCodeWS mobileCodeWS = new MobileCodeWS();
        MobileCodeWSSoap mobileCodeWSSoap = mobileCodeWS.getMobileCodeWSSoap();
        String mobileCodeInfo = mobileCodeWSSoap.getMobileCodeInfo("110", null);
        System.out.println(mobileCodeInfo);
    }

}
