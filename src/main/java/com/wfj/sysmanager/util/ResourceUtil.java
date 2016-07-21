package com.wfj.sysmanager.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

//import com.google.gson.Gson;

/**
 * 项目参数工具类
 *
 * @author 孙宇
 */
public class ResourceUtil {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("config");


    /**
     * 获得sessionInfo名字
     *
     * @return
     */
    public static final String getSessionInfoName() {
        return bundle.getString("sessionInfoName");
    }

    public static final String getOCXPath() {
        return bundle.getString("ocx_path");
    }

    public static final String getOCXVersion() {
        return bundle.getString("ocx_version");
    }

    public static final String getOCXDataPath() {
        return bundle.getString("ocx_data_path");
    }

    public static final String getOCXTemplatePath() {
        return bundle.getString("ocx_template_path");
    }
    public static final String getFedexUrl(){
    	return bundle.getString("fedexurl");
    }
    public static final String getZJSUserId(){
    	return bundle.getString("zjsuserid");
    }
    public static final String getZJSUrl(){
    	return bundle.getString("zjsurl");
    }
    public static final String getSFUrl(){
    	return bundle.getString("sfurl");
    }
    public static final String getSFUserId(){
    	return bundle.getString("sfuserid");
    }
    public static final String getSFCheckValue(){
    	return bundle.getString("sfcheckvalue");
    }
    public static final String getSolrUrl(){
    	return bundle.getString("solrurl");
    }
/*
    public static final List<ConfigJson> getWFJCondigSupply(){
    	String jsonResult=bundle.getString("supplyconfig");
    	Gson gson=new Gson();
    	if(null!=jsonResult&&!"".equals(jsonResult.trim())){
            ResultConfig resultConfig=gson.fromJson(jsonResult,ResultConfig.class);
            return resultConfig.getData();
    	}else{
    		return new ArrayList<ConfigJson>();
    	}


    }
*/
}
