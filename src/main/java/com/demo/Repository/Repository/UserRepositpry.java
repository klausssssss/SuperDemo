package com.demo.Repository.Repository;

import com.demo.Common.KeyHelper.KeyHelper;
import com.demo.Common.SqlHelper.ISqlHelper;
import com.demo.Model.UserManagement.User;
import com.demo.Repository.Base.BaseRepository;
import com.demo.Repository.IRepository.IUserRepositpry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositpry extends BaseRepository<User, String> implements IUserRepositpry {
    @Autowired
    public ISqlHelper iSqlHelper;

    public User findUserInfo(String userName, String passWord) {
        return iSqlHelper.Select("*")
                .From("User")
                .Where("userName", userName)
                .Where("passWord", passWord)
                .findOne(User.class);

    }

    public void BatchInsertTest() {
        for (int j = 0; j < 100; j++) {
            String uuid = KeyHelper.generateUUID();
            ISqlHelper sql = iSqlHelper.Insert("user", "uuid,username,password", "'" + uuid + "','1','2'");

            for (int i = 0; i < 1000; i++) {
                String newuuid = KeyHelper.generateUUID();
                sql.BatchValue("'" + newuuid + "','1','1'");
            }
            int[] a = sql.batchUpdate();
        }
        System.out.print("1");
    }
}
