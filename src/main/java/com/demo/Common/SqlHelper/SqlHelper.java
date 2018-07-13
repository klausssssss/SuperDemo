package com.demo.Common.SqlHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@Scope("prototype")
public  class SqlHelper implements  ISqlHelper{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private  StringBuilder sqlBuilder = new StringBuilder();

    public ISqlHelper Insert(String table,String column,String value){
        this.sqlBuilder.append("Insert into ");
        this.sqlBuilder.append(table);
        this.sqlBuilder.append(" ( ");
        this.sqlBuilder.append(column);
        this.sqlBuilder.append(" ) values (");
        this.sqlBuilder.append(value);
        this.sqlBuilder.append(" ) ");
        return this;
    }
    public ISqlHelper BatchValue(String value){
        this.sqlBuilder.append(",(");
        this.sqlBuilder.append(value);
        this.sqlBuilder.append(")");
        return this;
    }
    public ISqlHelper Update(String table,String value){
        this.sqlBuilder.append("Update ");
        this.sqlBuilder.append(table);
        this.sqlBuilder.append(" set ");
        this.sqlBuilder.append(value);
        return this;
    }
    public ISqlHelper From(String value){
       this.sqlBuilder.append(" From ");
       this.sqlBuilder.append(value);
       return this;
    }

    public ISqlHelper Select(String value){
        this.sqlBuilder.append("Select ");
        this.sqlBuilder.append(value);
        return this;
    }
    public ISqlHelper Where(String column, Object value,SQLOperation operation){
        String op;
        if(sqlBuilder.toString().contains("Where"))
        {
            op = " And ";
        }
        else {
            op = " Where ";
        }
        this.sqlBuilder.append(op);
        this.sqlBuilder.append(column);
        this.sqlBuilder.append(operation.getName());
        this.sqlBuilder.append(" '");
        this.sqlBuilder.append(value);
        this.sqlBuilder.append("' ");
        return this;
    }
    public ISqlHelper Where(String column, Object value){
        String op;
        if(sqlBuilder.toString().contains("Where"))
        {
            op = " And ";
        }
        else {
            op = " Where ";
        }
        this.sqlBuilder.append(op);
        this.sqlBuilder.append(column);
        this.sqlBuilder.append(" = '");
        this.sqlBuilder.append(value);
        this.sqlBuilder.append("' ");
        return this;
    }
    public ISqlHelper And(String value) {
        if(sqlBuilder.toString().contains("Where")){
            this.sqlBuilder.append(" and ");
            this.sqlBuilder.append(value);
            return this;
        }else try {
            throw new SQLException("and不能在Where之前");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    public <T> List<T> findList(Class<T> t, Object... args){

        List<T> res =  this.jdbcTemplate.query(sqlBuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlBuilder = new StringBuilder();
        return res;
    }

    public <T> List<T> findPagerList(Class<T> t,int pageIndex,int pageSize,Object... args){
        if(pageIndex<0||pageSize<0){
            return null;
        }
        this.sqlBuilder.append(" limit ");
        this.sqlBuilder.append((pageIndex-1)*pageSize);
        this.sqlBuilder.append(",");
        this.sqlBuilder.append(pageIndex);
        List<T> res = this.jdbcTemplate.query(sqlBuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlBuilder = new StringBuilder();
        return res;
    }

    public <T> T findOne(Class<T> t,Object... args){
        List<T> res =  this.jdbcTemplate.query(sqlBuilder.toString(),new BeanPropertyRowMapper<>(t),args);
        sqlBuilder = new StringBuilder();
        if(res.isEmpty()){
            return null;
        }
        return res.get(0);
    }
    public int[] batchUpdate(){
        try {
            //this.jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
            DataSourceUtils.getConnection(this.jdbcTemplate.getDataSource()).setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int[] res = this.jdbcTemplate.batchUpdate(sqlBuilder.toString());
        sqlBuilder = new StringBuilder();
        return res;
    }
    public int sqlUpdate(){
        int res = this.jdbcTemplate.update(sqlBuilder.toString());
        sqlBuilder = new StringBuilder();
        return res;
    }
    public int sqlUpdateAutoCommit(){
        try {
            //this.jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
            DataSourceUtils.getConnection(this.jdbcTemplate.getDataSource()).setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int res = this.jdbcTemplate.update(sqlBuilder.toString());
        sqlBuilder = new StringBuilder();
        return res;
    }
}
