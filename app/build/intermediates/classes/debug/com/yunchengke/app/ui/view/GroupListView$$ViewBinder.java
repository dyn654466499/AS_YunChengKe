// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GroupListView$$ViewBinder<T extends com.yunchengke.app.ui.view.GroupListView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690453, "field 'btnGrouplistNew'");
    target.btnGrouplistNew = finder.castView(view, 2131690453, "field 'btnGrouplistNew'");
    view = finder.findRequiredView(source, 2131690454, "field 'lvGrouplistContent'");
    target.lvGrouplistContent = finder.castView(view, 2131690454, "field 'lvGrouplistContent'");
  }

  @Override public void unbind(T target) {
    target.btnGrouplistNew = null;
    target.lvGrouplistContent = null;
  }
}
