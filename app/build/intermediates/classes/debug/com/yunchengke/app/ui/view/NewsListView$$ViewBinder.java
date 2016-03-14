// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsListView$$ViewBinder<T extends com.yunchengke.app.ui.view.NewsListView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690459, "field 'lvNewsListContent'");
    target.lvNewsListContent = finder.castView(view, 2131690459, "field 'lvNewsListContent'");
  }

  @Override public void unbind(T target) {
    target.lvNewsListContent = null;
  }
}
