package com.jd.jr.simpleconfig.export;

import com.jd.jr.simpleconfig.cache.DataConfigCache;
import com.jd.jr.simpleconfig.domain.DataConfig;
import com.jd.jr.simpleconfig.export.DTO.DataConfigDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/rest/dataConfig/")
public class DataConfigExportImpl implements DataConfigExport {

    @Resource(name = "dataConfigCache")
    DataConfigCache dataConfigCache;


    @RequestMapping(value = "queryBySystemAndDataKey", method = RequestMethod.POST)
    public DataConfigDTO queryBySystemAndDataKey(DataConfigDTO dataConfigDTO) {
        DataConfig dataConfig = dataConfigCache.dataConfig_systemName_dataKey_Table.get(dataConfigDTO.getSystemName(), dataConfigDTO.getDataKey());
       if(dataConfig==null){
           return null;
       }
        DataConfigDTO dataConfigDTOResponse = new DataConfigDTO();
        dataConfigDTOResponse.setSystemName(dataConfig.getSystemName());
        dataConfigDTOResponse.setDataKey(dataConfig.getDataKey());
        dataConfigDTOResponse.setData(dataConfig.getData());
        dataConfigDTOResponse.setDataDesc(dataConfig.getDataDesc());
        return dataConfigDTOResponse;
    }


    @RequestMapping(value = "queryBySystemName", method = RequestMethod.POST)
    public List<DataConfigDTO> queryBySystemName(String systemName) {
        Collection<DataConfig> dataConfigList = dataConfigCache.dataConfig_index_systemName_MultiMap.get(systemName);
        List<DataConfigDTO> dataConfigDTOList = new ArrayList<DataConfigDTO>();
        for (DataConfig dataConfig : dataConfigList) {
            DataConfigDTO dataConfigDTOResponse = new DataConfigDTO();
            BeanUtils.copyProperties(dataConfig,dataConfigDTOResponse);
            dataConfigDTOList.add(dataConfigDTOResponse);
        }
        return dataConfigDTOList;
    }
}