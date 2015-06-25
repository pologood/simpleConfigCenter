package com.jd.jr.simpleconfig.service;
import com.jd.jr.simpleconfig.service.BaseService;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
public interface HtmlElementConfigService  extends BaseService<HtmlElementConfig>{

        public Boolean deleteByUniqueIndexsystemNameelementName(String systemName, String elementName);


      public Boolean deleteByCommonIndexsystemName(String systemName);

}