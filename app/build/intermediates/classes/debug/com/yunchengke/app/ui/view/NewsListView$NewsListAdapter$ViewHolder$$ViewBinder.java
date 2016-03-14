// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsListView$NewsListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.view.NewsListView.NewsListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690209, "field 'ivItemNewsImage'");
    target.ivItemNewsImage = finder.castView(view, 2131690209, "field 'ivItemNewsImage'");
    view = finder.findRequiredView(source, 2131690210, "field 'tvItemNewsContent'");
    target.tvItemNewsContent = finder.castView(view, 2131690210, "field 'tvItemNewsContent'");
    view = finder.findRequiredView(source, 2131690212, "field 'tvItemNewsGroup'");
    target.tvItemNewsGroup = finder.castView(view, 2131690212, "field 'tvItemNewsGroup'");
    view = finder.findRequiredView(source, 2131690213, "field 'tvItemNewsDatetime'");
    target.tvItemNewsDatetime = finder.castView(view, 2131690213, "field 'tvItemNewsDatetime'");
    view = finder.findRequiredView(source, 2131690214, "field 'tvItemNewsCount'");
    target.tvItemNewsCount = finder.castView(view, 2131690214, "field 'tvItemNewsCount'");
  }

  @Override public void unbind(T target) {
    target.ivItemNewsImage = null;
    target.tvItemNewsContent = null;
    target.tvItemNewsGroup = null;
    target.tvItemNewsDatetime = null;
    target.tvItemNewsCount = null;
  }
}
