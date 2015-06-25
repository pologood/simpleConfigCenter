package com.jd.jr.simpleconfig.util.exception;

/**
 * User: yangkuan@jd.com
 * Date: 14-12-4
 * Time: 下午2:21
 */
public class AuthException extends Exception {
    private String key;
    private String message;


    public AuthException(String key, String message) {
        super(key);
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
