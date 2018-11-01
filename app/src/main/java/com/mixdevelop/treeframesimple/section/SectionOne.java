package com.mixdevelop.treeframesimple.section;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mixdevelop.treeframesimple.ExpandableItemAdapter;

/**
 * Author:wang_sir
 * Time:2018/10/10 14:00
 * Description:This is SectionOne
 */
public class SectionOne extends AbstractExpandableItem<SectionTwo> implements MultiItemEntity {
    private SectionBean sectionBean;

    public SectionBean getSectionBean() {
        return sectionBean;
    }

    public void setSectionBean(SectionBean sectionBean) {
        this.sectionBean = sectionBean;
    }
    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
