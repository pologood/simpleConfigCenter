/**
 * Copyright(c) 2004- www.360buy.com
 * com.jd.jr.simpleconfig.domain.HtmlElementConfig.java
 */
package com.jd.jr.simpleconfig.domain;

import java.io.Serializable;
 import java.sql.Timestamp;

/**
 * 
 * @author yangkuan@jd.com
 * @date 
 *
 *
 */
public class HtmlElementConfig implements Serializable {



    /**
     * 
     */
    	    private Long id;
        	


    /**
     * 系统名字
     */
    	    private String systemName;
        	


    /**
     * html组件name
     */
    	    private String elementName;
        	


    /**
     * html组件展示名字
     */
    	    private String elementDisplay;
        	


    /**
     * html组件类型
     */
    	    private String elementType;
        	


    /**
     * html值的类型
     */
    	    private String elementValueType;
        	


    /**
     * html组件是否必填。0：不必，1：必填
     */
    	    private Boolean elementValueCheck;
        	


    /**
     * html初始值
     */
    	    private String elementInitValue;
        	


    /**
     * 状态0：无效，1：有效
     */
    	    private Boolean status;
        	


    /**
     * 创建时间
     */
            private String createDate;
        	


    /**
     * 更新时间
     */
            private String updateTime;
        	
	
	
    	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
        	
    	public String getSystemName() {
		return systemName;
	}
	
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
        	
    	public String getElementName() {
		return elementName;
	}
	
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
        	
    	public String getElementDisplay() {
		return elementDisplay;
	}
	
	public void setElementDisplay(String elementDisplay) {
		this.elementDisplay = elementDisplay;
	}
        	
    	public String getElementType() {
		return elementType;
	}
	
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
        	
    	public String getElementValueType() {
		return elementValueType;
	}
	
	public void setElementValueType(String elementValueType) {
		this.elementValueType = elementValueType;
	}
        	
    	public Boolean getElementValueCheck() {
		return elementValueCheck;
	}
	
	public void setElementValueCheck(Boolean elementValueCheck) {
		this.elementValueCheck = elementValueCheck;
	}
        	
    	public String getElementInitValue() {
		return elementInitValue;
	}
	
	public void setElementInitValue(String elementInitValue) {
		this.elementInitValue = elementInitValue;
	}
        	
    	public Boolean getStatus() {
		return status;
	}
	
	public void setStatus(Boolean status) {
		this.status = status;
	}
        	
        public String getCreateDate() {
    return createDate;
    }

    public void setCreateDate(String createDate) {
    this.createDate = createDate;
    }
        	
        public String getUpdateTime() {
    return updateTime;
    }

    public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
    }
        	

}