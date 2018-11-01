package com.mixdevelop.treeframesimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 选择部门
     */
    private TextView mSelectDepsTv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mSelectDepsTv = (TextView) findViewById(R.id.select_deps_tv);
        mSelectDepsTv.setOnClickListener(this);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText("树形结构数据的展示和选择");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.select_deps_tv:
                startActivityForResult(new Intent(this, SelectDepsActivity.class),100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (100==resultCode) {
            mSelectDepsTv.setText(data.getStringExtra("selectedDeps"));
        }
    }
}
