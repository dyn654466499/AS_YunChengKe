// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class PublishDynamicActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.PublishDynamicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689957, "field 'edtPublishDynamicContent'");
    target.edtPublishDynamicContent = finder.castView(view, 2131689957, "field 'edtPublishDynamicContent'");
    view = finder.findRequiredView(source, 2131689958, "field 'gdvPublishDynamicImage'");
    target.gdvPublishDynamicImage = finder.castView(view, 2131689958, "field 'gdvPublishDynamicImage'");
  }

  @Override public void unbind(T target) {
    target.edtPublishDynamicContent = null;
    target.gdvPublishDynamicImage = null;
  }
}
