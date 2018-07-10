package com.demo.Model.HelloWorld;

import com.demo.Model.Base.BaseResponse;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="demo")
public class Demo extends BaseResponse {
    private String Name;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    @Id
    private Integer Id;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
