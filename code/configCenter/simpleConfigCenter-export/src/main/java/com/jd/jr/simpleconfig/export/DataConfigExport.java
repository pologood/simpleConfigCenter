package com.jd.jr.simpleconfig.export;
import com.jd.jr.simpleconfig.export.DTO.DataConfigDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/rest/dataConfig/")
@Consumes( { "application/json" })
@Produces( { "application/json" })
public interface DataConfigExport{

    /**
     * 取得系统下某个配置
     * @param dataConfigDTO
     * @return
     */
    @POST
    @Path("queryBySystemAndDataKey")
    DataConfigDTO queryBySystemAndDataKey(DataConfigDTO dataConfigDTO);


    /**
     * 取得系统的所有配置
     * @param systemName
     * @return
     */
    @POST
    @Path("queryBySystemName")
    List<DataConfigDTO> queryBySystemName(String systemName);
}