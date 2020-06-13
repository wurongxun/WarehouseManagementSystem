package com.shiro.test.mvc.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Menu
 * @Description Menu
 * @Author Rongxun.Wu
 * @Date 2019/12/25 21:40
 * @Version 1.0
 */
public class MenuInformationBean  {
    private String id;
    private String url;
    private String parentId;
    private String text;/*shiro 默认树节点名为text 其他名字需要处理*/
    /*子菜单*/
    private List<MenuInformationBean> children = new ArrayList<>();/*shiro 默认子节点名为children 其他名字需要处理*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<MenuInformationBean> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInformationBean> children) {
        this.children = children;
    }
}