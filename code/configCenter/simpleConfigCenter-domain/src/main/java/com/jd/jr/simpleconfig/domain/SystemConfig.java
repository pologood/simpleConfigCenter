/**
 * Copyright(c) 2004- www.360buy.com
 * com.jd.jr.simpleconfig.domain.SystemConfig.java
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
public class SystemConfig implements Serializable {



	/**
	 *
	 */
	private Long id;



	/**
	 * 系统名字
	 */
	private String name;



	/**
	 * 系统描述
	 */
	private String nameDesc;



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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameDesc() {
		return nameDesc;
	}

	public void setNameDesc(String nameDesc) {
		this.nameDesc = nameDesc;
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