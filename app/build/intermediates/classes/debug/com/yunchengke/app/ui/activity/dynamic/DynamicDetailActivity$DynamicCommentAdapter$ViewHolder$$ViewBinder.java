// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DynamicDetailActivity$DynamicCommentAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.DynamicDetailActivity.DynamicCommentAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690144, "field 'ivItemDynamicCommentPortrait'");
    target.ivItemDynamicCommentPortrait = finder.castView(view, 2131690144, "field 'ivItemDynamicCommentPortrait'");
    view = finder.findRequiredView(source, 2131690146, "field 'tvItemDynamicCommentName'");
    target.tvItemDynamicCommentName = finder.castView(view, 2131690146, "field 'tvItemDynamicCommentName'");
    view = finder.findRequiredView(source, 2131690147, "field 'tvItemDynamicCommentDatetime'");
    target.tvItemDynamicCommentDatetime = finder.castView(view, 2131690147, "field 'tvItemDynamicCommentDatetime'");
    view = finder.findRequiredView(source, 2131690148, "field 'tvItemDynamicCommentContent'");
    target.tvItemDynamicCommentContent = finder.castView(view, 2131690148, "field 'tvItemDynamicCommentContent'");
  }

  @Override public void unbind(T target) {
    target.ivItemDynamicCommentPortrait = null;
    target.tvItemDynamicCommentName = null;
    target.tvItemDynamicCommentDatetime = null;
    target.tvItemDynamicCommentContent = null;
  }
}
