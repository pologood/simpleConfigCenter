package com.jd.jr.simpleconfig.controller;
import com.jd.jr.simpleconfig.domain.SystemConfig;

import java.lang.Long;

import com.jd.jr.simpleconfig.domain.SystemConfig;
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

import com.jd.jr.simpleconfig.util.ControllerUtil;

import javax.annotation.Resource;

import com.jd.jr.simpleconfig.service.*;
import com.jd.jr.simpleconfig.util.*;

@Controller
@RequestMapping("/systemConfig")
public class SystemConfigController {

private static final Logger logger = LoggerFactory.getLogger(SystemConfigController.class);

@Resource(name = "controllerUtil")
private ControllerUtil controllerUtil;


@Resource(name = "systemConfigService")
private SystemConfigService systemConfigService;

/**
* 获取列表
*
* @param model
* @return
*/
@RequestMapping(value = "/list", method = RequestMethod.GET)
@PaginationAnnotion(pageName = "p", pageSize = ConstantModel.pageSize)
public String list(Model model, SystemConfig systemConfig, Integer p, HttpServletRequest request) throws Exception {
PageQuery<SystemConfig> pageQuery = new PageQuery<SystemConfig>();
    p = p == null ? 1 : p;
    pageQuery.setPageSize(20);
    pageQuery.setPageNo((p - 1) * 20);
    pageQuery.setQuery(systemConfig);
    logger.info(request.getRequestURI() + "请求报文:" + GsonUtils.toJson(pageQuery));
    List<SystemConfig> systemConfigList = systemConfigService.queryBySelectiveForPagination(pageQuery);
        model.addAttribute("count", systemConfigService.queryCountBySelective(pageQuery));
        model.addAttribute("systemConfigList", systemConfigList);
        ConstantModel.loadRequestParameterMapToModel(model, request);
        return "jsp/list/SystemConfigList";
        }

        @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
        public String toAdd(Model model) {
        return "jsp/add/SystemConfigAdd";
        }


        /**
        * 新增
        *
        * @return
        */
        @RequestMapping(value = "/save", method = RequestMethod.POST)
        @ResponseBody
        public Result save(SystemConfig systemConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
        Boolean baseResult = systemConfigService.save(systemConfig);
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

        SystemConfig systemConfig = systemConfigService.queryByPrimaryKey(id);
        model.addAttribute("systemConfig", systemConfig);
        return "jsp/edit/SystemConfigEdit";
        }


        /**
        * 更新一个
        *
        * @return
        */
        @RequestMapping(value = "/edit", method = RequestMethod.PUT)
        @ResponseBody
        public Result edit(SystemConfig systemConfig, HttpServletRequest request) {
        Result result = new Result();
        try {
        Boolean baseResult = systemConfigService.updateByPrimaryKeySelective(systemConfig);
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
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setId(id);

        Boolean baseResult = systemConfigService.updateByPrimaryKeySelective(systemConfig);
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
        Boolean baseResult = systemConfigService.deleteByPrimaryKey(id);

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