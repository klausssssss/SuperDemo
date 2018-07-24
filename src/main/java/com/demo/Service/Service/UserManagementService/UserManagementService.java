package com.demo.Service.Service.UserManagementService;

import com.auth0.jwt.interfaces.Claim;
import com.demo.Common.KeyHelper.KeyHelper;
import com.demo.Common.UserContext;
import com.demo.Model.Req.UserManagement.LoginInfo;
import com.demo.Model.Res.UserManagement.LoginRes;
import com.demo.Model.UserManagement.User;
import com.demo.Repository.IRepository.IUserRepositpry;
import com.demo.Service.IService.IUserManagementService.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserManagementService implements IUserManagementService {


    private IUserRepositpry iUserRepositpry;

    @Autowired
    private  UserManagementService(IUserRepositpry IUserRepositpry){
        iUserRepositpry = IUserRepositpry;
    }

    public LoginRes Login(LoginInfo req){
        LoginRes res = new LoginRes();
        UserContext context = new UserContext();
        String passWord;

        passWord = KeyHelper.EncoderMD5(req.getPassWord());

        User user = iUserRepositpry.findUserInfo(req.getUserName(),passWord);
        if(user==null){
            res.setMsg("登录失败");
            return res;
        }
        context.setUserId(user.getUuid());
        context.setUserName(user.getUserName());
        res.setToken(KeyHelper.createJWT(context));
        Map<String,Claim> a = KeyHelper.verifyJWT(KeyHelper.createJWT(context));
        res.setMsg("登录成功");
        return res;
    }


    public LoginRes Register(LoginInfo req){
        LoginRes res = new LoginRes();
        UserContext context = new UserContext();
        String passWord;
        passWord = KeyHelper.EncoderMD5(req.getPassWord());
        User registerUser = new User();

        User user = iUserRepositpry.findUserInfo(req.getUserName(),passWord);
        if(user!=null){
            res.setMsg("用户已存在");
            return res;
        }

        registerUser.setUserName(req.getUserName());
        registerUser.setPassWord(passWord);
        registerUser.setUuid(KeyHelper.generateUUID());
        iUserRepositpry.save(registerUser);
        registerUser = iUserRepositpry.findUserInfo(req.getUserName(),passWord);

        context.setUserId(registerUser.getUuid());
        context.setUserName(registerUser.getUserName());
        res.setToken(KeyHelper.createJWT(context));
        res.setMsg("注册成功");
        return res;
    }

    public void BatchInsert(){
        iUserRepositpry.BatchInsertTest();
    }
}
