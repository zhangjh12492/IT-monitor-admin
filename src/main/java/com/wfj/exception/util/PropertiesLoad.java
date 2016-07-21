package com.wfj.exception.util;

import java.util.Enumeration;
import java.util.Properties;

public class PropertiesLoad {

    private static Properties pros = new Properties();    //存储config.properties中的所有配置项

    /**
     * 初始化properties
     *
     * @param pro
     * @return void
     * @throws
     * @Title: init
     * @author Administrator
     * @date 2015-6-24 下午7:01:51
     */
    public static void init(Properties pro) {
        if (pros.size() == 0) {
            pros = pro;
        } else {
            Enumeration enu2 = pro.propertyNames();
            while (enu2.hasMoreElements()) {
                String key = (String) enu2.nextElement();
                String value = (String) pro.get(key);
                pros.put(key, value);
            }
        }
    }

    public static String getProperties(String key) {
        return pros.getProperty(key);
    }
    /**
     * 返回指定属性的值
     * @Methods Name getContextProperty
     * @param name 属性的Key
     * @param defultValue 如果没找到的默认值
     * @return 值，如没有返回defultValue
     */
    public static String getProperties(String name, String defultValue) {
        // TODO Auto-generated method stub
        return pros.getProperty(name, defultValue);
    }
    /**
     * 添加properties
     *
     * @param key
     * @param value
     * @return void
     * @throws
     * @Title: putProperties
     * @author Administrator
     * @date 2015-6-24 下午7:02:04
     */
    public static void putProperties(String key, String value) {
        pros.put(key, value);
    }
}
