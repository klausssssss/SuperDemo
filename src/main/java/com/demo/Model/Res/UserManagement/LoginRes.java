package com.demo.Model.Res.UserManagement;

import com.demo.Model.Base.BaseResponse;

public class LoginRes extends BaseResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
