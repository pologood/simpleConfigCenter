package com.jd.jr.simpleconfig.dao;
import com.jd.jr.simpleconfig.domain.DataConfig;
import java.util.List;
import com.jd.jr.simpleconfig.util.PageQuery;
import com.jd.jr.simpleconfig.util.Query;

public interface DataConfigMapper{

     public List<DataConfig> findAll();

     public long findCount();

     public List<DataConfig> queryBySelective(Query<DataConfig> dataConfig);

     public long queryCountBySelective(Query<DataConfig> dataConfig);
     
     public DataConfig queryByPrimaryKey(Long id);

     public int deleteByPrimaryKey(Long id);
 
     public int updateByPrimaryKeySelective(DataConfig dataConfig);

     public Long save(DataConfig dataConfig);

     public List<DataConfig> queryBySelectiveForPagination(PageQuery<DataConfig> dataConfig);

     public long queryCountBySelectiveForPagination(PageQuery<DataConfig> dataConfig);

        public int deleteByUniqueIndexsystemNamedataKey(DataConfig dataConfig);
    

    }