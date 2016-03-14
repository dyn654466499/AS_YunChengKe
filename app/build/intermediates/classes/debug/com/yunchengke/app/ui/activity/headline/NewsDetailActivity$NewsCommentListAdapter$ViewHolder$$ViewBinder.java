// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsDetailActivity$NewsCommentListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.NewsDetailActivity.NewsCommentListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690215, "field 'ivItemNewsCommentPortrait'");
    target.ivItemNewsCommentPortrait = finder.castView(view, 2131690215, "field 'ivItemNewsCommentPortrait'");
    view = finder.findRequiredView(source, 2131690217, "field 'tvItemNewsCommentName'");
    target.tvItemNewsCommentName = finder.castView(view, 2131690217, "field 'tvItemNewsCommentName'");
    view = finder.findRequiredView(source, 2131690218, "field 'tvItemNewsCommentDatetime'");
    target.tvItemNewsCommentDatetime = finder.castView(view, 2131690218, "field 'tvItemNewsCommentDatetime'");
    view = finder.findRequiredView(source, 2131690219, "field 'tvItemNewsCommentContent'");
    target.tvItemNewsCommentContent = finder.castView(view, 2131690219, "field 'tvItemNewsCommentContent'");
  }

  @Override public void unbind(T target) {
    target.ivItemNewsCommentPortrait = null;
    target.tvItemNewsCommentName = null;
    target.tvItemNewsCommentDatetime = null;
    target.tvItemNewsCommentContent = null;
  }
}
