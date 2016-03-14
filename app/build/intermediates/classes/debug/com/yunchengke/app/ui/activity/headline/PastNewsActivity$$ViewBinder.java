// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PastNewsActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.PastNewsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689953, "field 'lvPastNewsContent'");
    target.lvPastNewsContent = finder.castView(view, 2131689953, "field 'lvPastNewsContent'");
  }

  @Override public void unbind(T target) {
    target.lvPastNewsContent = null;
  }
}
