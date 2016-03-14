// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MediaMemberActivity$MediaMemberListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.MediaMemberActivity.MediaMemberListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690190, "field 'ivItemMediaMemberPortrait'");
    target.ivItemMediaMemberPortrait = finder.castView(view, 2131690190, "field 'ivItemMediaMemberPortrait'");
    view = finder.findRequiredView(source, 2131690191, "field 'tvItemMediaMemberName'");
    target.tvItemMediaMemberName = finder.castView(view, 2131690191, "field 'tvItemMediaMemberName'");
  }

  @Override public void unbind(T target) {
    target.ivItemMediaMemberPortrait = null;
    target.tvItemMediaMemberName = null;
  }
}
