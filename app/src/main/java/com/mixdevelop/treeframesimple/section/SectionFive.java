package com.mixdevelop.treeframesimple.section;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mixdevelop.treeframesimple.ExpandableItemAdapter;

/**
 * Author:wang_sir
 * Time:2018/10/10 14:00
 * Description:This is SectionOne
 */
public class SectionFive extends AbstractExpandableItem<SectionBean> implements MultiItemEntity {
    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_4;
    }

    @Override
    public int getLevel() {
        return 4;
    }
}
