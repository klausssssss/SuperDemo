package com.demo.Controller;

import com.demo.Model.HelloWorld.Hello;
import com.demo.Model.Req.HelloWord.insertReq;
import com.demo.Repository.IRepository.IHelloRepository;
import com.demo.Service.IService.IHelloService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hello")

public class HelloWorldController {

    @Autowired
    private IHelloService iHelloService;

    @Autowired
    private IHelloRepository iHelloRespository;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Hello hello() {
        Hello res = new Hello();
        res.setName("123");
        return res;
    }

    @ApiOperation(value = "hello")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void hello(@RequestBody @Valid insertReq res) {
        iHelloService.insert(res);
        List<Hello> a = iHelloRespository.findPagerList("Select * from test where id=?", 2, 1, 1);
        System.out.print(a);
    }

}
