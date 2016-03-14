// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CreateTopicActivity$TopicImagesAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.CreateTopicActivity.TopicImagesAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689564, "field 'image'");
    target.image = finder.castView(view, 2131689564, "field 'image'");
    view = finder.findRequiredView(source, 2131689709, "field 'addImage'");
    target.addImage = finder.castView(view, 2131689709, "field 'addImage'");
    view = finder.findRequiredView(source, 2131690134, "field 'deleteImage'");
    target.deleteImage = finder.castView(view, 2131690134, "field 'deleteImage'");
  }

  @Override public void unbind(T target) {
    target.image = null;
    target.addImage = null;
    target.deleteImage = null;
  }
}
