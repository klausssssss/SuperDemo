package com.demo.Model.Req.UserManagement;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class LoginInfo implements Serializable {
    private static final long serailVersionId = 1L;
    //@NotBlank
    private String userName;
    //@NotBlank
    private String passWord;

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    private int Time;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
