// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyHeadlineView$$ViewBinder<T extends com.yunchengke.app.ui.view.MyHeadlineView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690457, "field 'lvMyHeadlineNews'");
    target.lvMyHeadlineNews = finder.castView(view, 2131690457, "field 'lvMyHeadlineNews'");
  }

  @Override public void unbind(T target) {
    target.lvMyHeadlineNews = null;
  }
}
