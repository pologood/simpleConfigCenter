package com.jd.jr.simpleconfig.controller;

import com.jd.jr.simpleconfig.cache.SystemConfigCache;
import com.jd.jr.simpleconfig.domain.HtmlElementConfig;

import java.lang.Long;

import com.jd.jr.simpleconfig.domain.HtmlElementConfig;
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
import java.util.List;
import java.util.Map;

import com.jd.jr.simpleconfig.util.ControllerUtil;

import javax.annotation.Resource;

import com.jd.jr.simpleconfig.service.*;
import com.jd.jr.simpleconfig.util.*;

/**
 * HtmlElementConfig(html组件配置，每个系统的序列化数据通过html组件图像化)的controller
 * User: yangkuan@jd.com
 */
@Controller
@RequestMapping("/htmlElementConfig")
public class HtmlElementConfigController {

    private static final Logger logger = LoggerFactory.getLogger(HtmlElementConfigController.class);

    @Resource(name = "controllerUtil")
    private ControllerUtil controllerUtil;


    @Resource(name="systemConfigCache")
    private SystemConfigCache systemConfigCache;

    @Resource(name="dictionaryConfig")
    Map<String,Map<String,String>> dictionaryConfig;

    @Resource(name = "htmlElementConfigService")
    private HtmlElementConfigService htmlElementConfigService;

    /**
     * 获取列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PaginationAnnotion(pageName = "p", pageSize = ConstantModel.pageSize)
    public String list(Model model, HtmlElementConfig htmlElementConfig, Integer p, HttpServletRequest request) throws Exception {
        PageQuery<HtmlElementConfig> pageQuery = new PageQuery<HtmlElementConfig>();
        p = p == null ? 1 : p;
        pageQuery.setPageSize(20);
        pageQuery.setPageNo((p - 1) * 20);
        pageQuery.setQuery(htmlElementConfig);
        logger.info(request.getRequestURI() + "请求报文:" + GsonUtils.toJson(pageQuery));
        List<HtmlElementConfig> htmlElementConfigList = htmlElementConfigService.queryBySelectiveForPagination(pageQuery);
        model.addAttribute("count", htmlElementConfigService.queryCountBySelective(pageQuery));
        model.addAttribute("htmlElementConfigList", htmlElementConfigList);
        ConstantModel.loadRequestParameterMapToModel(model, request);
        controllerUtil.loadConfigToModel(model, dictionaryConfig);
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        return "jsp/list/HtmlElementConfigList";
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public String toAdd(Model model) {

        controllerUtil.loadConfigToModel(model,dictionaryConfig);
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        return "jsp/add/HtmlElementConfigAdd";
    }


    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(HtmlElementConfig htmlElementConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
            Boolean baseResult = htmlElementConfigService.save(htmlElementConfig);
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

        controllerUtil.loadConfigToModel(model, dictionaryConfig);
        model.addAttribute("systemConfig_uq_name_Map", systemConfigCache.systemConfig_uq_name_Map);
        HtmlElementConfig htmlElementConfig = htmlElementConfigService.queryByPrimaryKey(id);
        model.addAttribute("htmlElementConfig", htmlElementConfig);
        return "jsp/edit/HtmlElementConfigEdit";
    }


    /**
     * 更新一个
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public Result edit(HtmlElementConfig htmlElementConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
            Boolean baseResult = htmlElementConfigService.updateByPrimaryKeySelective(htmlElementConfig);
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
            HtmlElementConfig htmlElementConfig = new HtmlElementConfig();
            htmlElementConfig.setId(id);

            Boolean baseResult = htmlElementConfigService.updateByPrimaryKeySelective(htmlElementConfig);
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
            Boolean baseResult = htmlElementConfigService.deleteByPrimaryKey(id);

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


}