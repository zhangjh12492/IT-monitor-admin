package com.wfj.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;
import net.sf.json.util.JSONUtils;

/**
 * Created by MaYong on 2014/6/24.
 */
public class JsonValueProcessor implements DefaultValueProcessor {

    @Override
    public Object getDefaultValue(Class type) {
        if (JSONUtils.isArray(type)) {
            return new JSONArray();
        } else if (JSONUtils.isNumber(type)) {
            if (JSONUtils.isDouble(type)) {
                return null;
            } else {
                return null;
            }
        } else if (JSONUtils.isBoolean(type)) {
            return Boolean.FALSE;
        } else if (JSONUtils.isString(type)) {
            return "";
        }
        return JSONNull.getInstance();
    }
}
