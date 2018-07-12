package com.demo.Repository.Repository;

import com.demo.Common.SqlHelper.ISqlHelper;
import com.demo.Model.UserManagement.User;
import com.demo.Repository.Base.BaseRepository;
import com.demo.Repository.IRepository.IUserRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositpry extends BaseRepository<User,String> implements IUserRepositpry {
    @Autowired
    public ISqlHelper iSqlHelper;

    public User findUserInfo(String userName,String passWord){
        return iSqlHelper.Select("*")
                .From("User")
                .Where("userName",userName)
                .Where("passWord",passWord)
                .findOne(User.class);

    }
}
