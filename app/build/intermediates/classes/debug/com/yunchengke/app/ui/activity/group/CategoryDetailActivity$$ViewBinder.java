// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CategoryDetailActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.CategoryDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689626, "field 'btnCategoryDetailCreateGroup'");
    target.btnCategoryDetailCreateGroup = finder.castView(view, 2131689626, "field 'btnCategoryDetailCreateGroup'");
    view = finder.findRequiredView(source, 2131689627, "field 'lvCategoryDetailRefresh'");
    target.lvCategoryDetailRefresh = finder.castView(view, 2131689627, "field 'lvCategoryDetailRefresh'");
    view = finder.findRequiredView(source, 2131689628, "field 'lvCategoryDetailContent'");
    target.lvCategoryDetailContent = finder.castView(view, 2131689628, "field 'lvCategoryDetailContent'");
  }

  @Override public void unbind(T target) {
    target.btnCategoryDetailCreateGroup = null;
    target.lvCategoryDetailRefresh = null;
    target.lvCategoryDetailContent = null;
  }
}
