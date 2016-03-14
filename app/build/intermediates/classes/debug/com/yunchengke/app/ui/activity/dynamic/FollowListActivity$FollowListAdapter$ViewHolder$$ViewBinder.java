// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FollowListActivity$FollowListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.FollowListActivity.FollowListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690173, "field 'ivItemFollowPortrait'");
    target.ivItemFollowPortrait = finder.castView(view, 2131690173, "field 'ivItemFollowPortrait'");
    view = finder.findRequiredView(source, 2131690175, "field 'tvItemFollowName'");
    target.tvItemFollowName = finder.castView(view, 2131690175, "field 'tvItemFollowName'");
    view = finder.findRequiredView(source, 2131690174, "field 'btnItemFollowCancel'");
    target.btnItemFollowCancel = finder.castView(view, 2131690174, "field 'btnItemFollowCancel'");
  }

  @Override public void unbind(T target) {
    target.ivItemFollowPortrait = null;
    target.tvItemFollowName = null;
    target.btnItemFollowCancel = null;
  }
}
