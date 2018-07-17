package com.demo.Controller.UserManagement;

import com.demo.Model.Req.UserManagement.LoginInfo;
import com.demo.Model.Res.UserManagement.LoginRes;
import com.demo.Service.IService.IUserManagementService.IUserManagementService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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

    @ApiOperation(value = "Test")
    @RequestMapping(value = "/Test", method = RequestMethod.POST)
    public void Test(){
        iUserManagementService.BatchInsert();
    }

    @ApiOperation(value = "Give")
    @RequestMapping(value = "/Give", method = RequestMethod.POST)
    public void Give(HttpServletResponse response){
        List<String> list =new ArrayList<>();
        list.add("123");
        list.add("355");
        //Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sh = wb.createSheet("123");
        Row row = sh.createRow(0); //行
        //Cell cell;  //单元格

        //添加表头数据
        for (int i = 0; i < list.size(); i++) {
            //从前端接受到的参数封装成list集合，然后遍历下标从而取出对应的值
            String value = list.get(i);
            //将取到的值依次写到Excel的第一行的cell中
            row.createCell(i).setCellValue(value);
        }
        Row row1 = sh.createRow(1);
        row1.createCell(0).setCellValue("123");
        try {
            String fileName = new String("123.xlsx".getBytes("UTF-8"), "ISO_8859_1");
            response.reset();
            //response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            //response.setContentType("application/x-download");
            response.setContentType("application/binary");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @ApiOperation(value = "Giveg")
    @RequestMapping(value = "/Giveg", method = RequestMethod.GET)
    public void Giveg(HttpServletResponse response){
        List<String> list =new ArrayList<>();
        list.add("123");
        list.add("355");
        //Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sh = wb.createSheet("123");
//        Row row = sh.createRow(0); //行
//        //Cell cell;  //单元格
//
//        //添加表头数据
//        for (int i = 0; i < list.size(); i++) {
//            //从前端接受到的参数封装成list集合，然后遍历下标从而取出对应的值
//            String value = list.get(i);
//            //将取到的值依次写到Excel的第一行的cell中
//            row.createCell(i).setCellValue(value);
//        }
//        Row row1 = sh.createRow(1);
//        row1.createCell(0).setCellValue("123");
        try {
            String fileName = new String("123.xlsx".getBytes("UTF-8"), "ISO_8859_1");
            //response.reset();
            //response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            //response.setContentType("application/x-download");
            //response.setContentType("octets/stream");
            response.setContentType("application/binary");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
