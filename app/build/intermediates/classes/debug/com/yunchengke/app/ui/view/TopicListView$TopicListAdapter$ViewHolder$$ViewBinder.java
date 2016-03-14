// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TopicListView$TopicListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.view.TopicListView.TopicListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690272, "field 'image'");
    target.image = finder.castView(view, 2131690272, "field 'image'");
    view = finder.findRequiredView(source, 2131690273, "field 'title'");
    target.title = finder.castView(view, 2131690273, "field 'title'");
    view = finder.findRequiredView(source, 2131690274, "field 'name'");
    target.name = finder.castView(view, 2131690274, "field 'name'");
    view = finder.findRequiredView(source, 2131690275, "field 'commentcount'");
    target.commentcount = finder.castView(view, 2131690275, "field 'commentcount'");
    view = finder.findRequiredView(source, 2131690276, "field 'time'");
    target.time = finder.castView(view, 2131690276, "field 'time'");
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.title = null;
    target.name = null;
    target.commentcount = null;
    target.time = null;
  }
}
