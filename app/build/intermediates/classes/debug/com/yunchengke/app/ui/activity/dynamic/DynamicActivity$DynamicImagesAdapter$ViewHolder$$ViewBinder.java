// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DynamicActivity$DynamicImagesAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.DynamicActivity.DynamicImagesAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690075, "field 'img'");
    target.img = finder.castView(view, 2131690075, "field 'img'");
  }

  @Override public void unbind(T target) {
    target.img = null;
  }
}