package com.jd.jr.simpleconfig.cache;

import com.jd.jr.simpleconfig.domain.SystemConfig;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jd.jr.simpleconfig.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: yangkuan@jd.com
 * Date: 15-3-23
 * Time: 下午10:03
 */
@Service("systemConfigCache")
public class SystemConfigCache implements Cache {

    private final static Logger log = LoggerFactory.getLogger(SystemConfigCache.class);


    @Resource(name = "systemConfigService")
    private SystemConfigService systemConfigService;
    public Map<String, SystemConfig> systemConfig_uq_name_Map = new HashMap<String, SystemConfig>();


    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {

                        Thread.sleep(2000l);
                        List<SystemConfig> systemConfigList = systemConfigService.queryBySelective(null);


                        Map<String, SystemConfig> systemConfig_uq_name_Map1 = new HashMap<String, SystemConfig>();


                        for (SystemConfig systemConfig : systemConfigList) {

                            systemConfig_uq_name_Map1.put(systemConfig.getName(), systemConfig);


                        }
                        systemConfig_uq_name_Map = systemConfig_uq_name_Map1;


                    } catch (Exception e) {
                        log.error("请求服务异常:", e);

                    }
                }
            }
        });
            thread.setDaemon(true);
            thread.start();

    }


    @Override
    public void reload() {
        //super.clearAll(this);
        this.init();
    }

}
