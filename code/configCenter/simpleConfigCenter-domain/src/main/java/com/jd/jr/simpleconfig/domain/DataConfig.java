/**
 * Copyright(c) 2004- www.360buy.com
 * com.jd.jr.simpleconfig.domain.DataConfig.java
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
public class DataConfig implements Serializable {



    /**
     * 
     */
    	    private Long id;
        	


    /**
     * 系统名字
     */
    	    private String systemName;
        	


    /**
     * 数据唯一标识
     */
    	    private String dataKey;
        	


    /**
     * 数据描述
     */
    	    private String dataDesc;
        	


    /**
     * 序列化数据
     */
    	    private String data;
        	


    /**
     * 
     */
            private String createDate;
        	


    /**
     * 
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
        	
    	public String getDataKey() {
		return dataKey;
	}
	
	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}
        	
    	public String getDataDesc() {
		return dataDesc;
	}
	
	public void setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
	}
        	
    	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
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