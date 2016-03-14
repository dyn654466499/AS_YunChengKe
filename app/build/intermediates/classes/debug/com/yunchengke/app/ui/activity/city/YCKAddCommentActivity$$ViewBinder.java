// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class YCKAddCommentActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.city.YCKAddCommentActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690013, "field 'mEdt_publish_comment_content'");
    target.mEdt_publish_comment_content = finder.castView(view, 2131690013, "field 'mEdt_publish_comment_content'");
  }

  @Override public void unbind(T target) {
    target.mEdt_publish_comment_content = null;
  }
}
