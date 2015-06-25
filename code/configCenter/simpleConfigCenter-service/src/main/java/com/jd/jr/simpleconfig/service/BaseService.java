package com.jd.jr.simpleconfig.service;


import com.jd.jr.simpleconfig.domain.DataConfig;
import com.jd.jr.simpleconfig.util.PageQuery;
import com.jd.jr.simpleconfig.util.Query;

import java.util.List;

/**
 * User: yangkuan@jd.com
 * Date: 15-1-7
 * Time: 下午5:01
 */
public interface BaseService<T>{
    public <T> T queryByPrimaryKey(Long id);
    public List<T> findAll();

    public Long findCount();

    public List<T> queryBySelective(Query<T> Query);

    public Long queryCountBySelective(Query<T> query);


    public Boolean deleteByPrimaryKey(Long id);

    public Boolean updateByPrimaryKeySelective(T t);

    public Boolean save(T t) throws Exception;

    public Boolean batchSave(List<T> tList);

    public Boolean batchDelete(List<T> tList);


   public Boolean batchDeleteAndSave(List<T> tList);

   // public Boolean batchDeleteAndSave(List<T> saveList, List<T> deleteList);

    public List<T> queryBySelectiveForPagination(PageQuery<T> pageQuery);

}
