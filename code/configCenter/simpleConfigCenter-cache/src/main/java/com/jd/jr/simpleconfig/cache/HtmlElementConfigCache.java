package com.jd.jr.simpleconfig.cache;

import com.google.gson.reflect.TypeToken;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.jd.jr.simpleconfig.domain.HtmlElementConfigVm;
import com.jd.jr.simpleconfig.service.HtmlElementConfigService;
import com.jd.jr.simpleconfig.util.GsonUtils;
import org.springframework.beans.BeanUtils;
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
 * HtmlElementConfig(html组件配置，每个系统的序列化数据通过html组件图像化)的cache
 * User: yangkuan@jd.com
 */
@Service("htmlElementConfigCache")
public class HtmlElementConfigCache implements Cache {

    private final static Logger log = LoggerFactory.getLogger(HtmlElementConfigCache.class);


    @Resource(name = "htmlElementConfigService")
    private HtmlElementConfigService htmlElementConfigService;

    public Table<String, String, HtmlElementConfig> htmlElementConfig_systemName_elementName_Table = HashBasedTable.create();


    public Multimap<String, HtmlElementConfig> htmlElementConfig_index_systemName_MultiMap = ArrayListMultimap.create();


    public Multimap<String, HtmlElementConfigVm> htmlElementConfigVm_index_systemName_MultiMap = ArrayListMultimap.create();

    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        List<HtmlElementConfig> htmlElementConfigList = htmlElementConfigService.queryBySelective(null);
                        Table<String, String, HtmlElementConfig> htmlElementConfig_systemName_elementName_Table1 = HashBasedTable.create();

                        Multimap<String, HtmlElementConfig> htmlElementConfig_index_systemName_MultiMap1 = ArrayListMultimap.create();
                        Multimap<String, HtmlElementConfigVm> htmlElementConfigVm_index_systemName_MultiMap1 = ArrayListMultimap.create();
                        for (HtmlElementConfig htmlElementConfig : htmlElementConfigList) {


                            htmlElementConfig_systemName_elementName_Table1.put(htmlElementConfig.getSystemName(), htmlElementConfig.getElementName(), htmlElementConfig);

                            htmlElementConfig_index_systemName_MultiMap1.put(htmlElementConfig.getSystemName(), htmlElementConfig);
                            HtmlElementConfigVm htmlElementConfigVm = new HtmlElementConfigVm();
                            String elementType = htmlElementConfig.getElementType();
                            String elmentInitValue = htmlElementConfig.getElementInitValue();
                            if (elementType.equals("select") || elementType.equals("checkbox")) {

                                if (elmentInitValue != null && !elmentInitValue.equals("")) {
                                    Map<String, String> map = GsonUtils.fromJson(elmentInitValue, new TypeToken<HashMap<String, String>>() {
                                    });
                                    htmlElementConfigVm.setInitValueMap(map);
                                }
                            }
                            BeanUtils.copyProperties(htmlElementConfig, htmlElementConfigVm);
                            htmlElementConfigVm_index_systemName_MultiMap1.put(htmlElementConfig.getSystemName(), htmlElementConfigVm);
                        }

                        htmlElementConfig_systemName_elementName_Table = htmlElementConfig_systemName_elementName_Table1;


                        htmlElementConfig_index_systemName_MultiMap = htmlElementConfig_index_systemName_MultiMap1;

                        htmlElementConfigVm_index_systemName_MultiMap = htmlElementConfigVm_index_systemName_MultiMap1;
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
