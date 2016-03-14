// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PastNewsActivity$SampleExpandableListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.PastNewsActivity.SampleExpandableListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690261, "field 'image'");
    target.image = finder.castView(view, 2131690261, "field 'image'");
    view = finder.findRequiredView(source, 2131690262, "field 'title'");
    target.title = finder.castView(view, 2131690262, "field 'title'");
    view = finder.findRequiredView(source, 2131690263, "field 'introduction'");
    target.introduction = finder.castView(view, 2131690263, "field 'introduction'");
    view = finder.findRequiredView(source, 2131690264, "field 'creator'");
    target.creator = finder.castView(view, 2131690264, "field 'creator'");
    view = finder.findRequiredView(source, 2131690265, "field 'split'");
    target.split = view;
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.title = null;
    target.introduction = null;
    target.creator = null;
    target.split = null;
  }
}
