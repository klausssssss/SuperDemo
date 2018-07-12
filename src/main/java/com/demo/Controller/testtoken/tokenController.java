package com.demo.Controller.testtoken;

import com.demo.Common.UserContext;
import com.demo.Model.Req.UserManagement.LoginInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/token")
public class tokenController {
    //@Autowired
    //private UserContext userContext;

    @ApiOperation(value = "Login")
    @RequestMapping(value = "/xx", method = RequestMethod.POST)
    public void xx(@RequestBody LoginInfo info){
        UserContext context = UserContext.getInstance();//必须在Controller层获取userContext，减少线程安全问题

    }
}
