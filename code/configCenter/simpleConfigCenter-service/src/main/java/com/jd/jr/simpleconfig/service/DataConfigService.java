package com.jd.jr.simpleconfig.service;
import com.jd.jr.simpleconfig.service.BaseService;
import com.jd.jr.simpleconfig.domain.DataConfig;
public interface DataConfigService  extends BaseService<DataConfig>{

        public Boolean deleteByUniqueIndexsystemNamedataKey(String systemName  ,String dataKey  );



}