// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GroupMemberActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.GroupMemberActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689825, "field 'lvGroupMemberContent'");
    target.lvGroupMemberContent = finder.castView(view, 2131689825, "field 'lvGroupMemberContent'");
  }

  @Override public void unbind(T target) {
    target.lvGroupMemberContent = null;
  }
}
