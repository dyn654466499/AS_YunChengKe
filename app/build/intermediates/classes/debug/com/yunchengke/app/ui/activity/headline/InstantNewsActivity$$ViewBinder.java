// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InstantNewsActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.InstantNewsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689848, "field 'lvInstantNewsContent'");
    target.lvInstantNewsContent = finder.castView(view, 2131689848, "field 'lvInstantNewsContent'");
  }

  @Override public void unbind(T target) {
    target.lvInstantNewsContent = null;
  }
}
