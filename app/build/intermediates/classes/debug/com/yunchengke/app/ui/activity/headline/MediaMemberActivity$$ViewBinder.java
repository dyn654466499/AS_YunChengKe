// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.headline;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MediaMemberActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.headline.MediaMemberActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689876, "field 'lvMediaMemberContent'");
    target.lvMediaMemberContent = finder.castView(view, 2131689876, "field 'lvMediaMemberContent'");
  }

  @Override public void unbind(T target) {
    target.lvMediaMemberContent = null;
  }
}
