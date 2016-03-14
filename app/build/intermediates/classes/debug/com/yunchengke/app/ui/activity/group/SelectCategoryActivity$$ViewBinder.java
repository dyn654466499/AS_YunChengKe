// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SelectCategoryActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.SelectCategoryActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689981, "field 'cgvSelectCategoryContent'");
    target.cgvSelectCategoryContent = finder.castView(view, 2131689981, "field 'cgvSelectCategoryContent'");
  }

  @Override public void unbind(T target) {
    target.cgvSelectCategoryContent = null;
  }
}
