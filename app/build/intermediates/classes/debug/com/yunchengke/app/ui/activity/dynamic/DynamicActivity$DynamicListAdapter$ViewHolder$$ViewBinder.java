// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DynamicActivity$DynamicListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.DynamicActivity.DynamicListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690138, "field 'portrait'");
    target.portrait = finder.castView(view, 2131690138, "field 'portrait'");
    view = finder.findRequiredView(source, 2131690139, "field 'comment'");
    target.comment = finder.castView(view, 2131690139, "field 'comment'");
    view = finder.findRequiredView(source, 2131690140, "field 'name'");
    target.name = finder.castView(view, 2131690140, "field 'name'");
    view = finder.findRequiredView(source, 2131690141, "field 'date'");
    target.date = finder.castView(view, 2131690141, "field 'date'");
    view = finder.findRequiredView(source, 2131690142, "field 'title'");
    target.title = finder.castView(view, 2131690142, "field 'title'");
    view = finder.findRequiredView(source, 2131690143, "field 'images'");
    target.images = finder.castView(view, 2131690143, "field 'images'");
  }

  @Override public void unbind(T target) {
    target.portrait = null;
    target.comment = null;
    target.name = null;
    target.date = null;
    target.title = null;
    target.images = null;
  }
}
