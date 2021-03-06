package com.jd.jr.simpleconfig.util;

import java.io.Serializable;

/**
 * User: yangkuan@jd.com
 * Date: 15-3-17
 * Time: 下午5:40
 */
public class Query<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T query;

    public T getQuery() {
        return query;
    }

    public void setQuery(T query) {
        this.query = query;
    }
}