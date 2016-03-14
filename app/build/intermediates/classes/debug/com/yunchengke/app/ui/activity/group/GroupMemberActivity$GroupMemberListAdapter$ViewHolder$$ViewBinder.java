// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GroupMemberActivity$GroupMemberListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.GroupMemberActivity.GroupMemberListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690183, "field 'ivItemGroupMemberPortrait'");
    target.ivItemGroupMemberPortrait = finder.castView(view, 2131690183, "field 'ivItemGroupMemberPortrait'");
    view = finder.findRequiredView(source, 2131690184, "field 'ivItemGroupMemberName'");
    target.ivItemGroupMemberName = finder.castView(view, 2131690184, "field 'ivItemGroupMemberName'");
  }

  @Override public void unbind(T target) {
    target.ivItemGroupMemberPortrait = null;
    target.ivItemGroupMemberName = null;
  }
}
