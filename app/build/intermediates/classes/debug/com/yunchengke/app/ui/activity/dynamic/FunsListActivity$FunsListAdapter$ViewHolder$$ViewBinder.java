// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FunsListActivity$FunsListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.FunsListActivity.FunsListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690176, "field 'ivItemFunsPortrait'");
    target.ivItemFunsPortrait = finder.castView(view, 2131690176, "field 'ivItemFunsPortrait'");
    view = finder.findRequiredView(source, 2131690177, "field 'tvItemFunsName'");
    target.tvItemFunsName = finder.castView(view, 2131690177, "field 'tvItemFunsName'");
    view = finder.findRequiredView(source, 2131690178, "field 'btnItemFunsRemove'");
    target.btnItemFunsRemove = finder.castView(view, 2131690178, "field 'btnItemFunsRemove'");
  }

  @Override public void unbind(T target) {
    target.ivItemFunsPortrait = null;
    target.tvItemFunsName = null;
    target.btnItemFunsRemove = null;
  }
}
