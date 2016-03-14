// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MediaListView$MediaListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.view.MediaListView.MediaListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690185, "field 'image'");
    target.image = finder.castView(view, 2131690185, "field 'image'");
    view = finder.findRequiredView(source, 2131690186, "field 'logo'");
    target.logo = finder.castView(view, 2131690186, "field 'logo'");
    view = finder.findRequiredView(source, 2131690187, "field 'name'");
    target.name = finder.castView(view, 2131690187, "field 'name'");
    view = finder.findRequiredView(source, 2131690188, "field 'introduction'");
    target.introduction = finder.castView(view, 2131690188, "field 'introduction'");
    view = finder.findRequiredView(source, 2131690189, "field 'count'");
    target.count = finder.castView(view, 2131690189, "field 'count'");
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.logo = null;
    target.name = null;
    target.introduction = null;
    target.count = null;
  }
}
