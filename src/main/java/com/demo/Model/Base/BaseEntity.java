package com.demo.Model.Base;

import com.demo.Common.KeyHelper.KeyHelper;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {
    public  BaseEntity(){
        uuid = KeyHelper.generateUUID();
        //createTime = new Date();
        //updateTime = new Date();
        //isDeleted = false;
    }
    //private Integer id;//物理主键
    @Column(name="uuid")
    private String uuid;//逻辑主键

   // private Date createTime;

    //private Date updateTime;

    //private boolean isDeleted;//软删除标记

//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        isDeleted = deleted;
//    }


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer Id) {
//        id = Id;
//    }
    @Id
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
