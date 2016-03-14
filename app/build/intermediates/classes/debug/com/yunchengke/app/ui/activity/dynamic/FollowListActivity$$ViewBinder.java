// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FollowListActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.FollowListActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689809, "field 'lvFollowListContent'");
    target.lvFollowListContent = finder.castView(view, 2131689809, "field 'lvFollowListContent'");
  }

  @Override public void unbind(T target) {
    target.lvFollowListContent = null;
  }
}
