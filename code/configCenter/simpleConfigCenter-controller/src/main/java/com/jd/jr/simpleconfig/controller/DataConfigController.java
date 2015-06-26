package com.jd.jr.simpleconfig.controller;

import com.google.gson.reflect.TypeToken;
import com.jd.jr.simpleconfig.cache.DataConfigCache;
import com.jd.jr.simpleconfig.cache.HtmlElementConfigCache;
import com.jd.jr.simpleconfig.cache.SystemConfigCache;
import com.jd.jr.simpleconfig.domain.DataConfig;

import java.lang.Long;

import com.jd.jr.simpleconfig.domain.DataConfig;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
import com.jd.jr.simpleconfig.domain.HtmlElementConfigVm;
import com.jd.jr.simpleconfig.util.ConstantModel;
import com.jd.jr.simpleconfig.util.exception.ErpException;
import com.jd.jr.simpleconfig.util.pagination.PaginationAnnotion;
import com.jd.jr.simpleconfig.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jd.jr.simpleconfig.util.ControllerUtil;

import javax.annotation.Resource;

import com.jd.jr.simpleconfig.service.*;
import com.jd.jr.simpleconfig.util.*;

@Controller
@RequestMapping("/dataConfig")
public class DataConfigController {

    private static final Logger logger = LoggerFactory.getLogger(DataConfigController.class);

    @Resource(name = "controllerUtil")
    private ControllerUtil controllerUtil;


    @Resource(name = "dataConfigService")
    private DataConfigService dataConfigService;

    @Resource(name="systemConfigCache")
    private SystemConfigCache systemConfigCache;


    @Resource(name="htmlElementConfigCache")
    private HtmlElementConfigCache htmlElementConfigCache;

    /**
     * 获取列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PaginationAnnotion(pageName = "p", pageSize = ConstantModel.pageSize)
    public String list(Model model, DataConfig dataConfig, Integer p, HttpServletRequest request) throws Exception {
        PageQuery<DataConfig> pageQuery = new PageQuery<DataConfig>();
        p = p == null ? 1 : p;
        pageQuery.setPageSize(20);
        pageQuery.setPageNo((p - 1) * 20);
        pageQuery.setQuery(dataConfig);
        logger.info(request.getRequestURI() + "请求报文:" + GsonUtils.toJson(pageQuery));
        List<DataConfig> dataConfigList = dataConfigService.queryBySelectiveForPagination(pageQuery);
        model.addAttribute("count", dataConfigService.queryCountBySelective(pageQuery));
        model.addAttribute("dataConfigList", dataConfigList);
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        ConstantModel.loadRequestParameterMapToModel(model, request);
        return "jsp/list/DataConfigList";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) {
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        return "jsp/add/DataConfigAdd";
    }


    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(DataConfig dataConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
            Boolean baseResult = dataConfigService.save(dataConfig);
            result.setStatus(baseResult);
        } catch (Exception e) {
            logger.error(request.getRequestURI(), e);
            result.setStatus(Boolean.FALSE);
            if (e instanceof ErpException) {
                result.setReason(e.getMessage());
            } else {
                result.setReason("保存失败");
            }
        }
        return result;
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit(Model model, Long id, HttpServletRequest request) throws Exception {

        DataConfig dataConfig = dataConfigService.queryByPrimaryKey(id);
        Map<String, String> dataMap = GsonUtils.fromJson(dataConfig.getData(), new TypeToken<HashMap<String, String>>() {
        });
        List<HtmlElementConfigVm> htmlElementConfigList = (List<HtmlElementConfigVm>) htmlElementConfigCache.htmlElementConfigVm_index_systemName_MultiMap.get(dataConfig.getSystemName());
        for (HtmlElementConfigVm htmlElementConfigVm:htmlElementConfigList){
            String elementName = htmlElementConfigVm.getElementName();
            String elementSetValue = dataMap.get(elementName);
            htmlElementConfigVm.setSetValue(elementSetValue);
        }
        String html = VelocityTemplate.renderVelocityTemplate(htmlElementConfigList,"vm/dataConfigHtml.vm");
        html = html.replaceAll("\r\n", "");

        model.addAttribute("dataHtml", html);
        model.addAttribute("dataConfig", dataConfig);
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        return "jsp/edit/DataConfigEdit";
    }


    /**
     * 更新一个
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public Result edit(DataConfig dataConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
            Boolean baseResult = dataConfigService.updateByPrimaryKeySelective(dataConfig);
            result.setStatus(baseResult);
        } catch (Exception e) {
            logger.error("", e);
            result.setStatus(Boolean.FALSE);
            if (e instanceof ErpException) {
                result.setReason(e.getMessage());
            } else {
                result.setReason("更新失败");
            }
        }
        return result;
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.GET)
    @ResponseBody
    public Result updateStatus(Long id, Integer status, HttpServletRequest request) {
        Result result = new Result();
        try {
            DataConfig dataConfig = new DataConfig();
            dataConfig.setId(id);

            Boolean baseResult = dataConfigService.updateByPrimaryKeySelective(dataConfig);
            result.setStatus(baseResult);
        } catch (Exception e) {
            logger.error("", e);
            result.setStatus(Boolean.FALSE);
            if (e instanceof ErpException) {
                result.setReason(e.getMessage());
            } else {
                result.setReason("更新状态失败");
            }
        }
        return result;
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(Long id, HttpServletRequest request) {
        Result result = new Result();
        try {
            Boolean baseResult = dataConfigService.deleteByPrimaryKey(id);

            result.setStatus(baseResult);
        } catch (Exception e) {
            logger.error("", e);
            result.setStatus(Boolean.FALSE);
            if (e instanceof ErpException) {
                result.setReason(e.getMessage());
            } else {
                result.setReason("删除失败");
            }
        }
        return result;
    }


    @RequestMapping(value = "getConfigDataHTML", method = RequestMethod.GET)
    @ResponseBody
    public Result getConfigDataHTML(String systemName, HttpServletRequest request) {
        Result result = new Result();
        try {
            List<HtmlElementConfigVm> htmlElementConfigList = (List<HtmlElementConfigVm>) htmlElementConfigCache.htmlElementConfigVm_index_systemName_MultiMap.get(systemName);
            String html = VelocityTemplate.renderVelocityTemplate(htmlElementConfigList,"vm/dataConfigHtml.vm");
            html = html.replaceAll("\r\n", "");
            result.setContent(html);
        } catch (Exception e) {
            logger.error("获取html代码异常", e);
            result.setStatus(Boolean.FALSE);
            if (e instanceof ErpException) {
                result.setReason(e.getMessage());
            } else {
                result.setReason("获取html代码失败");
            }
        }
        return result;
    }
}