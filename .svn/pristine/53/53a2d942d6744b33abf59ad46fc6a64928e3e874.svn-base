package com.yunchengke.app.ui.activity.group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.yunchengke.app.R;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.CategoryView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类名：SelectCategoryActivity <br/>
 * 描述：选择创建小组的分类
 * 创建时间：2016/01/10 12:02
 *
 * @author hanter
 * @version 1.0
 */
public class SelectCategoryActivity extends BaseActivity {

    @Bind(R.id.cgv_select_category_content)
    CategoryView cgvSelectCategoryContent;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_select_groupcategory, parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.select_category);
        setTitleLeft(R.string.back);

        cgvSelectCategoryContent.setCategoryType(CategoryView.CATEGORY_TYPE_CREATE_GROUP);
        cgvSelectCategoryContent.setActivity(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CreateGroupActivity.REQUEST_CREATE_GROUP || resultCode == RESULT_OK) {
            finish();
        }
    }
}
