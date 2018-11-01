package com.mixdevelop.treeframesimple.section;

/**
 * Author:wang_sir
 * Time:2018/10/11 11:30
 * Description:This is SelectedDepsBean
 */
public class SelectedDepsBean {

    private int depId;
    private String depName;

    public SelectedDepsBean(int depId, String depName) {
        this.depId = depId;
        this.depName = depName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
