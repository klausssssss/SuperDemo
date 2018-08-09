package com.demo.Repository.Base;

public interface IRedisRepository {
    void setKey(String key,String value);
    String getValue(String key);
}
