package com.demo.Repository.IRepository;

import com.demo.Model.UserManagement.User;
import com.demo.Repository.Base.IBaseRepository;

public interface IUserRepositpry extends IBaseRepository<User,String> {
    User findUserInfo(String userName,String passWord);
    void BatchInsertTest();
    void setKey(String key,String value);
}
