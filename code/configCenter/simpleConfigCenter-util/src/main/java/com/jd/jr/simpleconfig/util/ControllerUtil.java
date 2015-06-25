package com.jd.jr.simpleconfig.util;

import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.Map;

/**
 * User: yangkuan@jd.com
 * Date: 15-3-23
 * Time: 下午4:42
 */
@Service("controllerUtil")
public class ControllerUtil {




    public <T> void loadConfigToModel(Model model, Multimap<String, T> objectMultimap) {
        for (String key : objectMultimap.keySet()) {
            model.addAttribute(key, objectMultimap.get(key));
        }
    }

    public <T> void loadConfigToModel(Model model, Map<String, Map<String, T>> objectMap) {
        for (String key : objectMap.keySet()) {
            model.addAttribute(key, objectMap.get(key));
        }
    }

    public <T> void loadDicToModel(Model model, Table<String, String, T> dictionaryInfoVoTable) {
        for (String key : dictionaryInfoVoTable.rowKeySet()) {
            model.addAttribute(key, dictionaryInfoVoTable.row(key));
        }
    }
}
