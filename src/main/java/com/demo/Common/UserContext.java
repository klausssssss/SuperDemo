package com.demo.Common;

import com.alibaba.fastjson.JSON;
import com.demo.Common.Cloneable.DeepClone;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
//@Scope("prototype")
public class UserContext implements Serializable {

    private static ThreadLocal<UserContext> loginEntityThreadLocal=new ThreadLocal<>();

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    private String UserId;
    private String UserName;
    private String DepartmentId;
    private String DepartmentName;


    public static UserContext getUserContext() {
        return loginEntityThreadLocal.get();
    }

    public static void setUserContext(UserContext entity) {
        loginEntityThreadLocal.set(entity);
    }

    public static void removeUserSession() {
        loginEntityThreadLocal.remove();
    }
}
