package com.jd.jr.simpleconfig.util;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * User: yangkuan@jd.com
 * Date: 14-11-25
 * Time: 上午11:22
 */
public class ConstantModel {
    public final static int pageSize = 10;

    public final static int indexId = 1000000000;//10亿

    public final  static  String Nofeetime_ItemExtendFieldName= "NofeetimeData";//存储无领取费用时间内容的数据扩展字段
    public final  static  String NofeetimeContext_ItemExtendFieldName= "NofeetimeDataContext";//存储无领取费用时间展示内容的数据扩展字段
    public final  static  String InsProductContract_ItemExtendFieldName= "insProductContract";//存储文本合同的数据扩展字段


    public enum VelocityTable {
        historyBenefitDataTable("historyBenefitDataTable", "vm/historyBenefitDataTable.vm", "实际年化收益率动态表格"),
        responsibilityData("responsibilityDataTable", "vm/responsibilityDataTable.vm", "保险责任动态表格"),
        nofeetimeType3Table("nofeetimeType3Table", "vm/nofeetimeType3Table.vm", "保险责任动态表格");
        private final String displayName;
        private final String fileName;
        private final String desc;

        private VelocityTable(String displayName, String fileName, String desc) {
            this.displayName = displayName;
            this.fileName = fileName;
            this.desc = desc;
        }

        public String fileName() {
            return fileName;
        }

        public String displayName() {
            return displayName;
        }
    }

    public enum VelocityConfig {
        historyBenefitDataTable("historyBenefitDataTable", "vm/historyBenefitDataTable.vm", "实际年化收益率动态表格"),
        responsibilityData("responsibilityDataTable", "vm/responsibilityData.vm", "保险责任动态表格");

        private final String displayName;
        private final String fileName;
        private final String desc;

        private VelocityConfig(String displayName, String fileName, String desc) {
            this.displayName = displayName;
            this.fileName = fileName;
            this.desc = desc;
        }

        public String fileName() {
            return fileName;
        }

        public String displayName() {
            return displayName;
        }
    }

    public enum SingleVelocityConfig {

        nofeetimeTable("nofeetimeTable", "vm/nofeetimeTable.vm", "无领取费用时间表格");

        private final String displayName;
        private final String fileName;
        private final String desc;

        private SingleVelocityConfig(String displayName, String fileName, String desc) {
            this.displayName = displayName;
            this.fileName = fileName;
            this.desc = desc;
        }

        public String fileName() {
            return fileName;
        }

        public String displayName() {
            return displayName;
        }
    }

    public static void loadRequestParameterMapToModel(Model model, HttpServletRequest request) {
        model.addAttribute("parameterMap", request.getParameterMap());
    }


}
