package com.demo.Repository.Base;

import com.sun.istack.internal.Nullable;

import java.util.List;

public  interface IBaseRepository<T,PK>{


    void save(T entity);

    T getById(PK id);

    void update(T entity);

    void delete(PK id);


    List<T> findList(String sql,@Nullable Object... args);

    List<T> findPagerList(String sql,int pageIndex,int pageSize,@Nullable Object... args);
}
