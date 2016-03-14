// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YCKCommentActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.city.YCKCommentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690375, "field 'mPullToRefreshListView'");
    target.mPullToRefreshListView = finder.castView(view, 2131690375, "field 'mPullToRefreshListView'");
  }

  @Override public void unbind(T target) {
    target.mPullToRefreshListView = null;
  }
}
