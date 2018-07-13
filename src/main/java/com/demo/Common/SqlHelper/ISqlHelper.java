package com.demo.Common.SqlHelper;

import org.springframework.lang.Nullable;

import java.sql.SQLException;
import java.util.List;

public interface ISqlHelper {
    ISqlHelper Insert(String table,String colunum,String value);
    ISqlHelper Update(String table,String value);
    ISqlHelper BatchValue(String value);
    ISqlHelper From(String value);
    ISqlHelper Select(String value);
    ISqlHelper Where(String column, Object value,@Nullable SQLOperation operation);
    ISqlHelper Where(String column, Object value);
    ISqlHelper And(String value) ;

    <T> List<T> findPagerList(Class<T> t, int pageIndex, int pageSize, Object... args);

    <T> List<T> findList(Class<T> t, Object... args);

    <T> T findOne(Class<T> t,Object... args);

    int[]  batchUpdate();

    int sqlUpdate();

    int sqlUpdateAutoCommit();//不包事务立即提交
}
