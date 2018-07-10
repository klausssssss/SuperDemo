package com.demo.Model.Req.HelloWord;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class insertReq {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NotNull(message="id is null")

    private Integer id;
    @NotBlank(message = "123")
    private String name;
}
