package com.demo.Common;



import com.alibaba.fastjson.JSON;
import com.demo.Common.Cloneable.DeepClone;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
//@Scope("prototype")
public class UserContext {
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
    private static UserContext instance;
    public synchronized void setUserContext(UserContext context){
        instance = new UserContext();
        instance.DepartmentName = context.DepartmentName;
        instance.DepartmentId = context.DepartmentId;
        instance.UserId = context.UserId;
        instance.UserName = context.UserName;
    }
    public static UserContext getInstance(){
        String json = JSON.toJSONString(instance);
        return JSON.parseObject(json,UserContext.class);
    }
}
