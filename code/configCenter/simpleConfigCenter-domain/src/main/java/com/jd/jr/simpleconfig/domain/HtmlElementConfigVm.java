/**
 * Copyright(c) 2004- www.360buy.com
 * com.jd.jr.simpleconfig.domain.HtmlElementConfig.java
 */
package com.jd.jr.simpleconfig.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author yangkuan@jd.com
 * @date 
 *
 *
 */
public class HtmlElementConfigVm extends HtmlElementConfig implements Serializable {

	/**
	 * 如果控件是select或者readio,elementInitValues必须是json格式
	 */
	private Map<String,String> initValueMap;

	public Map<String, String> getInitValueMap() {
		return initValueMap;
	}

	public void setInitValueMap(Map<String, String> initValueMap) {
		this.initValueMap = initValueMap;
	}
}