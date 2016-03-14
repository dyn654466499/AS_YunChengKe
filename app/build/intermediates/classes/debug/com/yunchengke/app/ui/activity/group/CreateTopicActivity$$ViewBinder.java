// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CreateTopicActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.CreateTopicActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689752, "field 'edtCreateTopicTitle'");
    target.edtCreateTopicTitle = finder.castView(view, 2131689752, "field 'edtCreateTopicTitle'");
    view = finder.findRequiredView(source, 2131689753, "field 'edtCreateTopicContent'");
    target.edtCreateTopicContent = finder.castView(view, 2131689753, "field 'edtCreateTopicContent'");
    view = finder.findRequiredView(source, 2131689754, "field 'gdvCreateTopicImage'");
    target.gdvCreateTopicImage = finder.castView(view, 2131689754, "field 'gdvCreateTopicImage'");
  }

  @Override public void unbind(T target) {
    target.edtCreateTopicTitle = null;
    target.edtCreateTopicContent = null;
    target.gdvCreateTopicImage = null;
  }
}
