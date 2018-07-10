package com.demo.Model.HelloWorld;

import com.demo.Model.Base.BaseResponse;


import javax.persistence.*;

@Entity
@Table(name="test")
public class Hello extends BaseResponse {
    private String Name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    private Integer Id;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
