package com.mixdevelop.treeframesimple.section;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/9 19:46
 * Description:This is SectionBean
 */
public class SectionBean {

    /**
     * id : 60
     * name : 技术1组
     * children : []
     * parentId : 59
     * userNum : 1
     */

    private int id;
    private String name;
    private int parentId;
    private int userNum;
    private boolean selected;
    private List<SectionBean> children;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public List<SectionBean> getChildren() {
        return children;
    }

    public void setChildren(List<SectionBean> children) {
        this.children = children;
    }
}
