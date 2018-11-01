package com.mixdevelop.treeframesimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mixdevelop.treeframesimple.section.SectionBean;
import com.mixdevelop.treeframesimple.section.SectionFive;
import com.mixdevelop.treeframesimple.section.SectionFour;
import com.mixdevelop.treeframesimple.section.SectionOne;
import com.mixdevelop.treeframesimple.section.SectionThree;
import com.mixdevelop.treeframesimple.section.SectionTwo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SelectDepsActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private RecyclerView mSelectDepsRv;
    /**
     * 确定
     */
    private TextView mConfirmTv;
    private String content = "";
    private List<SectionBean> sectionBeans;
    private ExpandableItemAdapter expandableItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_deps);
        initView();

    }

    private void initView() {
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("选择部门");
        mSelectDepsRv = (RecyclerView) findViewById(R.id.select_deps_rv);
        mConfirmTv = (TextView) findViewById(R.id.confirm_tv);
        mConfirmTv.setOnClickListener(this);
        getRvData();
    }

    private void getRvData() {
        content = getResources().getString(R.string.test_data);
        sectionBeans = analysisJsonData(content);
        ArrayList<MultiItemEntity> lists = generateData(sectionBeans);
        expandableItemAdapter = new ExpandableItemAdapter(lists);
        LinearLayoutManager managere = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mSelectDepsRv.setLayoutManager(managere);
        mSelectDepsRv.setAdapter(expandableItemAdapter);
        expandableItemAdapter.expand(0);
        expandableItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<MultiItemEntity> lists = (ArrayList<MultiItemEntity>) adapter.getData();
                MultiItemEntity multiItemEntity = lists.get(position);
                SectionBean sectionBean = null;
                switch (multiItemEntity.getItemType()) {
                    case 0:
                        SectionOne sectionOne = (SectionOne) multiItemEntity;
                        sectionBean = sectionOne.getSectionBean();
                        break;
                    case 1:
                        SectionTwo sectionTwo = (SectionTwo) multiItemEntity;
                        sectionBean = sectionTwo.getSectionBean();

                        break;
                    case 2:
                        SectionThree sectionThree = (SectionThree) multiItemEntity;
                        sectionBean = sectionThree.getSectionBean();

                        break;
                    case 3:
                        SectionFour sectionFour = (SectionFour) multiItemEntity;
                        sectionBean = sectionFour.getSectionBean();

                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
                if (sectionBean.isSelected()) {
                    unSelect(sectionBean);
                } else {
                    select(sectionBean);
                }
            }
        });
    }


    /**
     * 重新整理数据 得到适配器的数据
     *
     * @param sectionBeans
     * @return
     */
    private ArrayList<MultiItemEntity> generateData(List<SectionBean> sectionBeans) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        if (sectionBeans.size() > 0) {
            for (SectionBean bean : sectionBeans) {
                bean.setSelected(false);
                SectionOne sectionOne = new SectionOne();
                sectionOne.setSectionBean(bean);
                if (bean.getChildren().size() > 0) {
                    for (SectionBean sectionBean : bean.getChildren()) {
                        sectionBean.setSelected(false);
                        SectionTwo sectionTwo = new SectionTwo();
                        sectionTwo.setSectionBean(sectionBean);
                        if (sectionBean.getChildren().size() > 0) {
                            for (SectionBean sectionBean1 : sectionBean.getChildren()) {
                                sectionBean1.setSelected(false);
                                SectionThree sectionThree = new SectionThree();
                                sectionThree.setSectionBean(sectionBean1);
                                if (sectionBean1.getChildren().size() > 0) {
                                    for (SectionBean sectionBean2 : sectionBean1.getChildren()) {
                                        sectionBean2.setSelected(false);
                                        SectionFour sectionFour = new SectionFour();
                                        sectionFour.setSectionBean(sectionBean2);
                                        if (sectionBean2.getChildren().size() > 0) {
                                            for (SectionBean sectionBean3 : sectionBean2.getChildren()) {
                                                sectionBean3.setSelected(false);
                                                SectionFive sectionFive = new SectionFive();
                                                sectionFour.addSubItem(sectionFive);
                                            }
                                        }
                                        sectionThree.addSubItem(sectionFour);
                                    }
                                }
                                sectionTwo.addSubItem(sectionThree);
                            }
                        }
                        sectionOne.addSubItem(sectionTwo);
                    }
                }
                res.add(sectionOne);
            }
        }
        return res;
    }

    /**
     * 解析json数据
     */
    private List<SectionBean> analysisJsonData(String content) {
        List<SectionBean> arrays = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(content);
            JSONArray data = jsonObject.getJSONArray("data");
            if (data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    SectionBean sectionBean = new SectionBean();
                    JSONObject outerObject = (JSONObject) data.get(i);
                    sectionBean.setId(outerObject.getInt("id"));
                    sectionBean.setName(outerObject.getString("name"));
                    sectionBean.setUserNum(outerObject.getInt("userNum"));
                    sectionBean.setParentId(outerObject.getInt("parentId"));
                    JSONArray jsonArray = outerObject.getJSONArray("children");
                    List<SectionBean> sectionBeanList = getSectionList(jsonArray);
                    sectionBean.setChildren(sectionBeanList);
                    arrays.add(sectionBean);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrays;
    }

    @NonNull
    private List<SectionBean> getSectionList(JSONArray jsonArray) throws JSONException {
        List<SectionBean> sectionBeanList = new ArrayList<>();
        if (jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                SectionBean bean = new SectionBean();
                JSONObject object = (JSONObject) jsonArray.get(i);
                bean.setId(object.getInt("id"));
                bean.setName(object.getString("name"));
                bean.setUserNum(object.getInt("userNum"));
                bean.setParentId(object.getInt("parentId"));
                JSONArray array = object.getJSONArray("children");
                List<SectionBean> list = getSectionList(array);
                bean.setChildren(list);
                sectionBeanList.add(bean);
            }
        }
        return sectionBeanList;
    }

    /**
     * 获取选中的部门id或者名称
     * type 0 代表id  1代表名称
     */
    private String getSelectedDepsInfo(int type) {
        List<SectionBean> sectionBeanList = new ArrayList<>();
        List<SectionBean> allSelectedDeps = getSelectedSectionBeans(sectionBeanList, sectionBeans);
        boolean isId = type == 0 ? true : false;
        StringBuffer sb = new StringBuffer();
        if (allSelectedDeps != null) {
            if (allSelectedDeps.size() > 0) {
                for (SectionBean allSelectedDep : allSelectedDeps) {
                    if (isId) {
                        sb.append(allSelectedDep.getId() + ",");
                    } else {
                        sb.append(allSelectedDep.getName() + ",");

                    }
                }
            }
        }
        String deps = sb.toString();
        if (deps.contains(",")) {
            deps = deps.substring(0, deps.length() - 1);
        }
        return deps;
    }


    /**
     * 从所有数据中遍历查找指定sectionBean的父Sectionbean
     *
     * @param sectionBean
     * @return
     */

    private List<SectionBean> getPresentSectionBean2(List<SectionBean> arraysreturn, List<SectionBean> arrays, SectionBean sectionBean) {
        if (sectionBean == null) {
            return arraysreturn;
        }
        for (SectionBean bean : arrays) {
            if (bean.getId() == sectionBean.getParentId()) {
                arraysreturn.add(bean);
                break;
            } else {
                if (bean.getChildren() != null && bean.getChildren().size() > 0) {
                    getPresentSectionBean2(arraysreturn, bean.getChildren(), sectionBean);
                }
            }
        }
        return arraysreturn;
    }

    /**
     * 从所有数据中遍历查找选中的sectionbean
     *
     * @return
     */

    private List<SectionBean> getSelectedSectionBeans(List<SectionBean> arraysreturn, List<SectionBean> arrays) {
        for (SectionBean bean : arrays) {
            if (bean.isSelected()) {
                arraysreturn.add(bean);
            }
            if (bean.getChildren() != null && bean.getChildren().size() > 0) {
                getSelectedSectionBeans(arraysreturn, bean.getChildren());
            }
        }
        return arraysreturn;
    }

    /**
     * 从所有数据中遍历查找指定sectionBean的父Sectionbean
     *
     * @param sectionBean
     * @return
     */
    private SectionBean getPresentSectionBean(List<SectionBean> arrays, SectionBean sectionBean) {
        SectionBean presentBean = null;
        List<SectionBean> sectionBeanList = new ArrayList<>();
        List<SectionBean> presentSections = getPresentSectionBean2(sectionBeanList, arrays, sectionBean);
        if (presentSections.size() == 0) {
            return presentBean;
        } else {
            return presentSections.get(0);
        }

    }


    //刷新下级选中状态
    private void selectLow(SectionBean bean) {
        for (SectionBean selectionBean : bean.getChildren()) {
            if (selectionBean.getParentId() == bean.getId()) {
                selectionBean.setSelected(true);
                selectLow(selectionBean);
            }
        }
    }

    //刷新下级未选中状态
    private void unSelectLow(SectionBean bean) {
        for (SectionBean selectionBean : bean.getChildren()) {
            if (selectionBean.getParentId() == bean.getId()) {
                selectionBean.setSelected(false);
                unSelectLow(selectionBean);
            }
        }
    }

    //刷新父级选中状态
    private void selectPrent(SectionBean bean) {
        SectionBean pBean = getPresentSectionBean(sectionBeans, bean);
        if (pBean != null) {
            int count = 0;
            for (SectionBean selectionBean : pBean.getChildren()) {
                if (selectionBean.getParentId() == bean.getParentId() && selectionBean.isSelected()) {
                    count++;
                }
            }
            if (count == pBean.getChildren().size()) {
                pBean.setSelected(true);
            }
            selectPrent(pBean);
        }

    }

    //刷新父级取消选中状态
    private void unSelectPrent(SectionBean bean) {
        SectionBean presentBean = getPresentSectionBean(sectionBeans, bean);
        if (presentBean != null) {
            presentBean.setSelected(false);
            unSelectPrent(presentBean);
        }
    }

    /**
     * 选中
     *
     * @param bean
     */
    private void select(SectionBean bean) {
        bean.setSelected(true);
        selectPrent(bean);
        selectLow(bean);
        //记得notify列表
        expandableItemAdapter.notifyDataSetChanged();

    }

    /**
     * 取消选中
     *
     * @param bean
     */
    private void unSelect(SectionBean bean) {
        bean.setSelected(false);
        unSelectPrent(bean);
        unSelectLow(bean);
        //记得notify列表
        expandableItemAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.confirm_tv:
                String selectedDeps = getSelectedDepsInfo(1);
                if (TextUtils.isEmpty(selectedDeps)) {
                    Toast.makeText(getApplicationContext(), "还没有选择部门", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("selectedDeps", selectedDeps);
                setResult(100, intent);
                finish();
                break;
        }
    }
}
