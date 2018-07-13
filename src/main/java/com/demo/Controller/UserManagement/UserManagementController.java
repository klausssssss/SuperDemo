package com.demo.Controller.UserManagement;

import com.demo.Model.Req.UserManagement.LoginInfo;
import com.demo.Model.Res.UserManagement.LoginRes;
import com.demo.Service.IService.IUserManagementService.IUserManagementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/UserManagement")
public class UserManagementController {

    @Autowired
    private IUserManagementService iUserManagementService;

    @ApiOperation(value = "Login")
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public LoginRes Login(@RequestBody @Valid LoginInfo req){
        return iUserManagementService.Login(req);
    }

    @ApiOperation(value = "Register")
    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public LoginRes Register(@RequestBody @Valid LoginInfo req){
        return iUserManagementService.Register(req);
    }

    @ApiOperation(value = "BatchInsert")
    @RequestMapping(value = "/BatchInsert", method = RequestMethod.POST)
    public void BatchInsert(){
        iUserManagementService.BatchInsert();
    }
}
