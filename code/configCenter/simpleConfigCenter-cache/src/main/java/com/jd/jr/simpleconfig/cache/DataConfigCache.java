package com.jd.jr.simpleconfig.cache;

import com.jd.jr.simpleconfig.domain.DataConfig;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jd.jr.simpleconfig.service.DataConfigService;
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
@Service("dataConfigCache")
public class DataConfigCache implements Cache {

    private final static Logger log = LoggerFactory.getLogger(DataConfigCache.class);


    @Resource(name = "dataConfigService")
    private DataConfigService dataConfigService;

    public Table<String, String, DataConfig> dataConfig_systemName_dataKey_Table = HashBasedTable.create();


    public Multimap<String, DataConfig> dataConfig_index_systemName_MultiMap = ArrayListMultimap.create();

    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(;;) {
                    try {

                        Thread.sleep(2000l);
                        List<DataConfig> dataConfigList = dataConfigService.queryBySelective(null);


                        Table<String, String, DataConfig> dataConfig_systemName_dataKey_Table1 = HashBasedTable.create();

                        Multimap<String, DataConfig> dataConfig_index_systemName_MultiMap1 = ArrayListMultimap.create();

                        for (DataConfig dataConfig : dataConfigList) {
                            dataConfig_systemName_dataKey_Table1.put(dataConfig.getSystemName(), dataConfig.getDataKey(), dataConfig);
                            dataConfig_index_systemName_MultiMap1.put(dataConfig.getSystemName(), dataConfig);
                        }
                        dataConfig_systemName_dataKey_Table = dataConfig_systemName_dataKey_Table1;
                        dataConfig_index_systemName_MultiMap = dataConfig_index_systemName_MultiMap1;


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
