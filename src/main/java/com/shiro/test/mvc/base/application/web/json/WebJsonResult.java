package com.shiro.test.mvc.base.application.web.json;


import java.util.HashMap;
import java.util.Map;

public class WebJsonResult {
    public static final int SUCCESS_STATE = 0;
    public static final String SUCCESS_MESSAGE = "success";
    public static final int FAILURE_STATE = 1;
    public static final String FAILURE_MESSAGE = "failure";
    public static final Object EMPTY_OBJECT = new Object[0];
    private int state;
    private String message;
    private Object data;
    private final Map<String, Object> attributes;

    public static final WebJsonResult newSuccess() {
        return newSuccess(EMPTY_OBJECT);
    }

    public static final WebJsonResult newSuccess(Object data) {
        return newSuccess("success", data);
    }

    public static final WebJsonResult newSuccess(String message) {
        return newSuccess(message, EMPTY_OBJECT);
    }

    public static final WebJsonResult newSuccess(String message, Object data) {
        return new WebJsonResult(0, message, data);
    }

    public static final WebJsonResult newFailure() {
        return newFailure(EMPTY_OBJECT);
    }

    public static final WebJsonResult newFailure(Object data) {
        return newFailure("failure", data);
    }

    public static final WebJsonResult newFailure(String message) {
        return newFailure(message, EMPTY_OBJECT);
    }

    public static final WebJsonResult newFailure(String message, Object data) {
        return new WebJsonResult(1, message, data);
    }

    public WebJsonResult() {
        this(1, "failure", EMPTY_OBJECT, new HashMap());
    }

    public WebJsonResult(int state, String message, Object data) {
        this(state, message, data, new HashMap());
    }

    public WebJsonResult(int state, String message, Object data, Map<String, Object> attributes) {
        this.state = state;
        this.message = message;
        this.data = data;
        this.attributes = attributes;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes.clear();
        this.attributes.putAll(attributes);
    }

    public void putAttribute(String key, Object value) {
        this.attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    public void removeAttribute(String key) {
        this.attributes.remove(key);
    }
}
