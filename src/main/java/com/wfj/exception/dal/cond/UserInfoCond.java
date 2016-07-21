package com.wfj.exception.dal.cond;

/**
 * Created by MaYong on 2015/8/17.
 */
public class UserInfoCond extends BaseCond {
    private String tel;  //
    private String userName;  //
    private Integer id;  //
    private String email;  //
    private String userCode;  //


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
