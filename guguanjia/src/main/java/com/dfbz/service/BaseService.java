package com.dfbz.service;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2019/12/23 14:34
 * @desciption
 */
public interface BaseService<T> {
    public int deleteByPrimaryKey(Object key);

    public int delete(T record);

    public int insert(T record);

    public int insertSelective(T record);

    public boolean existsWithPrimaryKey(Object key);

    public List<T> selectAll();

    public T selectByPrimaryKey(Object key);

    public int selectCount(T record);

    public List<T> select(T record);

    public T selectOne(T record);

    public int updateByPrimaryKey(T record);

    public int updateByPrimaryKeySelective(T record);

    public int deleteByExample(Object example);

    public List<T> selectByExample(Object example);

    public int selectCountByExample(Object example);

    public T selectOneByExample(Object example);

    public int updateByExample(T record, Object example);

    public int updateByExampleSelective(T record, Object example);

    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds);

    public List<T> selectByRowBounds(T record, RowBounds rowBounds);

}
