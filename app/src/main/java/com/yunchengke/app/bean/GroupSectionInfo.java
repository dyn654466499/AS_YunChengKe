package com.yunchengke.app.bean;

/**
 * 类名：GroupSectionInfo <br/>
 * 描述：小组的Section头部
 * 创建时间：2016/02/17 20:20
 *
 * @author hanter
 * @version 1.0
 */
public class GroupSectionInfo extends BaseEntity {
    private String title;

    public boolean isShowMargin() {
        return showMargin;
    }

    public void setShowMargin(boolean showMargin) {
        this.showMargin = showMargin;
    }

    private boolean showMargin;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
