package com.jd.jr.simpleconfig.dao;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
import java.util.List;
import com.jd.jr.simpleconfig.util.PageQuery;
import com.jd.jr.simpleconfig.util.Query;

public interface HtmlElementConfigMapper{

     public List<HtmlElementConfig> findAll();

     public long findCount();

     public List<HtmlElementConfig> queryBySelective(Query<HtmlElementConfig> htmlElementConfig);

     public long queryCountBySelective(Query<HtmlElementConfig> htmlElementConfig);
     
     public HtmlElementConfig queryByPrimaryKey(Long id);

     public int deleteByPrimaryKey(Long id);
 
     public int updateByPrimaryKeySelective(HtmlElementConfig htmlElementConfig);

     public Long save(HtmlElementConfig htmlElementConfig);

     public List<HtmlElementConfig> queryBySelectiveForPagination(PageQuery<HtmlElementConfig> htmlElementConfig);

     public long queryCountBySelectiveForPagination(PageQuery<HtmlElementConfig> htmlElementConfig);

        public int deleteByUniqueIndexsystemNameelementName(HtmlElementConfig htmlElementConfig);
    

        public int deleteByCommonIndexsystemName(HtmlElementConfig htmlElementConfig);
    }