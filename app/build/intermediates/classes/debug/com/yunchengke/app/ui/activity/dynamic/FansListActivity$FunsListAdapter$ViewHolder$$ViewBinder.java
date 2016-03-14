// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FansListActivity$FunsListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.FansListActivity.FunsListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690151, "field 'ivItemFansPortrait'");
    target.ivItemFansPortrait = finder.castView(view, 2131690151, "field 'ivItemFansPortrait'");
    view = finder.findRequiredView(source, 2131690153, "field 'tvItemFansName'");
    target.tvItemFansName = finder.castView(view, 2131690153, "field 'tvItemFansName'");
    view = finder.findRequiredView(source, 2131690152, "field 'btnItemFansRemove'");
    target.btnItemFansRemove = finder.castView(view, 2131690152, "field 'btnItemFansRemove'");
  }

  @Override public void unbind(T target) {
    target.ivItemFansPortrait = null;
    target.tvItemFansName = null;
    target.btnItemFansRemove = null;
  }
}
