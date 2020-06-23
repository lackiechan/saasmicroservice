package com.tenlnet.framework.common.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: lackiechan
 * @Email: 2734858720@qq.com
 * @Date: 2019-07-30 17:53
 */
public interface BaseDao<T> {
    int save(T t);

    void save(Map<String, Object> map);

    void saveBatch(List<T> list);

    int update(T t);

    int update(Map<String, Object> map);

    int deleteByPrimaryKey(Object id);

    int delete(Map<String, Object> map);

    int deleteBatch(Object[] ids);

    T queryByPrimaryKey(Object id);

    List<T> queryList(Map<String, Object> map);

    List<T> queryList(Object id);

    int queryTotal(Map<String, Object> map);

    int queryTotal();
}
