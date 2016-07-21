package com.wfj.sysmanager.cond;

import java.io.Serializable;

/**
 * Created by MaYong on 2015/9/22.
 */
public class BaseCond implements Serializable {

    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
