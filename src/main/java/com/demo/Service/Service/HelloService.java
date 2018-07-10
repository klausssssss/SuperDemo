package com.demo.Service.Service;

import com.demo.Model.HelloWorld.Hello;
import com.demo.Model.Req.HelloWord.insertReq;
import com.demo.Repository.IRepository.IHelloRepository;
import com.demo.Service.IService.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService implements IHelloService {
    @Autowired
    private IHelloRepository iHelloRepository;

    public void insert(insertReq res) {
        Hello o = new Hello();
        o.setName(res.getName());
        o.setId(res.getId());
        iHelloRepository.save(o);
    }
}
