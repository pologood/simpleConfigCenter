package com.jd.jr.simpleconfig.service;
import com.jd.jr.simpleconfig.service.BaseService;
import com.jd.jr.simpleconfig.domain.SystemConfig;
public interface SystemConfigService  extends BaseService<SystemConfig>{

        public Boolean deleteByUniqueIndexname(String name  );



}