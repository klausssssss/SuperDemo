package com.demo.Service.IService.IUserManagementService;

import com.demo.Model.Req.UserManagement.LoginInfo;
import com.demo.Model.Res.UserManagement.LoginRes;

public interface IUserManagementService {

    LoginRes Login(LoginInfo req);
    LoginRes Register(LoginInfo req);
}
