package com.mixdevelop.treeframesimple;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mixdevelop.treeframesimple.section.SectionBean;
import com.mixdevelop.treeframesimple.section.SectionFour;
import com.mixdevelop.treeframesimple.section.SectionOne;
import com.mixdevelop.treeframesimple.section.SectionThree;
import com.mixdevelop.treeframesimple.section.SectionTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luoxw on 2016/8/9.
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;
    public static final int TYPE_LEVEL_3 = 3;
    public static final int TYPE_LEVEL_4 = 4;

    private List<Integer> level1s = new ArrayList<>();
    private List<Integer> level2s = new ArrayList<>();
    private List<Integer> level3s = new ArrayList<>();
    private List<Integer> level4s = new ArrayList<>();
    private List<Integer> level5s = new ArrayList<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_1);
        addItemType(TYPE_LEVEL_2, R.layout.item_expandable_2);
        addItemType(TYPE_LEVEL_3, R.layout.item_expandable_3);
        addItemType(TYPE_LEVEL_4, R.layout.item_expandable);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final SectionOne sectionOne = (SectionOne) item;
                final SectionBean sectionBean = sectionOne.getSectionBean();
                holder.setText(R.id.expand_list_section_name_tv, sectionBean.getName() + " (" + sectionBean.getUserNum() + "人)");
                if (sectionOne.getSubItems() != null && sectionOne.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionOne.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox1 = holder.getView(R.id.expand_list_cb1);
                holder.addOnClickListener(R.id.expand_list_cb1);
                initCheckboxStatus(sectionBean, checkBox1);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionOne.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final SectionTwo sectionTwo = (SectionTwo) item;
                final SectionBean sectionBean2 = sectionTwo.getSectionBean();
                holder.setText(R.id.expand_list_section_name_tv, sectionBean2.getName() + " (" + sectionBean2.getUserNum() + "人)");
                if (sectionTwo.getSubItems() != null && sectionTwo.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionTwo.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }

                final CheckBox checkBox = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean2, checkBox);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionTwo.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_2:
                final SectionThree sectionThree = (SectionThree) item;
                final SectionBean sectionBean3 = sectionThree.getSectionBean();
                holder.setText(R.id.expand_list_section_name_tv, sectionBean3.getName() + " (" + sectionBean3.getUserNum() + "人)");
                if (sectionThree.getSubItems() != null && sectionThree.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionThree.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox3 = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean3, checkBox3);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionThree.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_3:
                final SectionFour sectionFour = (SectionFour) item;
                SectionBean sectionBean4 = sectionFour.getSectionBean();
                holder.setText(R.id.expand_list_section_name_tv, sectionBean4.getName() + " (" + sectionBean4.getUserNum() + "人)");
                if (sectionFour.getSubItems() != null && sectionFour.getSubItems().size() > 0) {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.VISIBLE);
                    holder.setImageResource(R.id.expand_list_arrow_iv, sectionFour.isExpanded() ? R.mipmap.arror_down : R.mipmap.arror_right);

                } else {
                    holder.getView(R.id.expand_list_arrow_iv).setVisibility(View.INVISIBLE);
                }
                final CheckBox checkBox4 = holder.getView(R.id.expand_list_cb);
                holder.addOnClickListener(R.id.expand_list_cb);

                initCheckboxStatus(sectionBean4, checkBox4);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (sectionFour.isExpanded()) {
                            collapse(pos);
                        } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_4:
                break;
        }
    }

    /**
     * 初始化checkbox状态
     * @param sectionBean
     * @param checkBox
     */
    private void initCheckboxStatus(SectionBean sectionBean, CheckBox checkBox) {

        boolean selected = sectionBean.isSelected();
        if (selected) {
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }
    }
}
