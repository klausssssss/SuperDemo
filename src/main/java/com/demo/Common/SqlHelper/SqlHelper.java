package com.demo.Common.SqlHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@Scope("prototype")
public  class SqlHelper implements  ISqlHelper{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private  StringBuilder sqlbuilder = new StringBuilder();

    public ISqlHelper From(String value){
       this.sqlbuilder.append(" From ");
       this.sqlbuilder.append(value);
       return this;
    }

    public ISqlHelper Select(String value){
        this.sqlbuilder.append("Select ");
        this.sqlbuilder.append(value);
        return this;
    }
    public ISqlHelper Where(String column, Object value,SQLOperation operation){
        String op;
        if(sqlbuilder.toString().contains("Where"))
        {
            op = " And ";
        }
        else {
            op = " Where ";
        }
        this.sqlbuilder.append(op);
        this.sqlbuilder.append(column);
        this.sqlbuilder.append(operation.getName());
        this.sqlbuilder.append(" '");
        this.sqlbuilder.append(value);
        this.sqlbuilder.append("' ");
        return this;
    }
    public ISqlHelper Where(String column, Object value){
        String op;
        if(sqlbuilder.toString().contains("Where"))
        {
            op = " And ";
        }
        else {
            op = " Where ";
        }
        this.sqlbuilder.append(op);
        this.sqlbuilder.append(column);
        this.sqlbuilder.append(" = '");
        this.sqlbuilder.append(value);
        this.sqlbuilder.append("' ");
        return this;
    }
    public ISqlHelper And(String value) {
        if(sqlbuilder.toString().contains("Where")){
            this.sqlbuilder.append(" and ");
            this.sqlbuilder.append(value);
            return this;
        }else try {
            throw new SQLException("and不能在Where之前");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    public <T> List<T> findList(Class<T> t, Object... args){

        List<T> res =  this.jdbcTemplate.query(sqlbuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlbuilder = new StringBuilder();
        return res;
    }

    public <T> List<T> findPagerList(Class<T> t,int pageIndex,int pageSize,Object... args){
        if(pageIndex<0||pageSize<0){
            return null;
        }
        this.sqlbuilder.append(" limit ");
        this.sqlbuilder.append((pageIndex-1)*pageSize);
        this.sqlbuilder.append(",");
        this.sqlbuilder.append(pageIndex);
        List<T> res = this.jdbcTemplate.query(sqlbuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlbuilder = new StringBuilder();
        return res;
    }

    public <T> T findOne(Class<T> t,Object... args){
        List<T> res =  this.jdbcTemplate.query(sqlbuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlbuilder = new StringBuilder();
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);
    }
}
