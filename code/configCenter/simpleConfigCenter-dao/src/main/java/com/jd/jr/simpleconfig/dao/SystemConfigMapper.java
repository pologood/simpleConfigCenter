package com.jd.jr.simpleconfig.dao;
import com.jd.jr.simpleconfig.domain.SystemConfig;
import java.util.List;
import com.jd.jr.simpleconfig.util.PageQuery;
import com.jd.jr.simpleconfig.util.Query;

public interface SystemConfigMapper{

     public List<SystemConfig> findAll();

     public long findCount();

     public List<SystemConfig> queryBySelective(Query<SystemConfig> systemConfig);

     public long queryCountBySelective(Query<SystemConfig> systemConfig);
     
     public SystemConfig queryByPrimaryKey(Long id);

     public int deleteByPrimaryKey(Long id);
 
     public int updateByPrimaryKeySelective(SystemConfig systemConfig);

     public Long save(SystemConfig systemConfig);

     public List<SystemConfig> queryBySelectiveForPagination(PageQuery<SystemConfig> systemConfig);

     public long queryCountBySelectiveForPagination(PageQuery<SystemConfig> systemConfig);

        public int deleteByUniqueIndexname(SystemConfig systemConfig);
    

    }