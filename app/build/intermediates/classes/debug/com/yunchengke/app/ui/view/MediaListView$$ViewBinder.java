// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MediaListView$$ViewBinder<T extends com.yunchengke.app.ui.view.MediaListView> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690455, "field 'lvMedialistContent'");
    target.lvMedialistContent = finder.castView(view, 2131690455, "field 'lvMedialistContent'");
  }

  @Override public void unbind(T target) {
    target.lvMedialistContent = null;
  }
}
