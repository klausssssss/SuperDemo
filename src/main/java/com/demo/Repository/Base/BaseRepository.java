package com.demo.Repository.Base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseRepository<T,PK> implements  IBaseRepository<T,PK>{

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> clazz;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 通过反射获取参数类型
     */
    public BaseRepository() {
        //System.out.println(this);
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T getById(PK id) {
        if (id == null) return null;
        return (T) entityManager.find(clazz, id);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(PK id) {
        if (id != null) {
            Object entity = getById(id);
            if (entity != null) {
                entityManager.remove(entity);
            }
        }
    }

    public List<T> findList(String sql,Object... args){
        return this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(this.clazz),args);
    }
    public List<T> findPagerList(String sql,int pageIndex,int pageSize,Object... args){
        if(pageIndex<0||pageSize<0){
            return null;
        }
        sql = sql +" limit "+ (pageIndex-1)*pageSize+","+pageIndex;
        return this.jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(this.clazz),args);
    }
}
