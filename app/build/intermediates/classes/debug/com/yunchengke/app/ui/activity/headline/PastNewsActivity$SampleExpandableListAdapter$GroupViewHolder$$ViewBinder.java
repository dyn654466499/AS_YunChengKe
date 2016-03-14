// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PastNewsActivity$SampleExpandableListAdapter$GroupViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.PastNewsActivity.SampleExpandableListAdapter.GroupViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690132, "field 'tvItemMediaPastPeriod'");
    target.tvItemMediaPastPeriod = finder.castView(view, 2131690132, "field 'tvItemMediaPastPeriod'");
    view = finder.findRequiredView(source, 2131690133, "field 'ivItemMediaPastImage'");
    target.ivItemMediaPastImage = finder.castView(view, 2131690133, "field 'ivItemMediaPastImage'");
  }

  @Override public void unbind(T target) {
    target.tvItemMediaPastPeriod = null;
    target.ivItemMediaPastImage = null;
  }
}
