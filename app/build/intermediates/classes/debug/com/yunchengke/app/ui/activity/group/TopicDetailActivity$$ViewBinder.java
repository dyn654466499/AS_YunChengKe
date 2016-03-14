// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class TopicDetailActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.TopicDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690367, "field 'tvTopicDetailTitle'");
    target.tvTopicDetailTitle = finder.castView(view, 2131690367, "field 'tvTopicDetailTitle'");
    view = finder.findRequiredView(source, 2131690368, "field 'ivTopicDetailPortrait' and method 'onClick'");
    target.ivTopicDetailPortrait = finder.castView(view, 2131690368, "field 'ivTopicDetailPortrait'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690369, "field 'tvTopicDetailCreator'");
    target.tvTopicDetailCreator = finder.castView(view, 2131690369, "field 'tvTopicDetailCreator'");
    view = finder.findRequiredView(source, 2131690370, "field 'tvTopicDetailCreateTime'");
    target.tvTopicDetailCreateTime = finder.castView(view, 2131690370, "field 'tvTopicDetailCreateTime'");
    view = finder.findRequiredView(source, 2131690371, "field 'tvTopicDetailContent'");
    target.tvTopicDetailContent = finder.castView(view, 2131690371, "field 'tvTopicDetailContent'");
    view = finder.findRequiredView(source, 2131690374, "field 'tvTopicDetailCommentCount'");
    target.tvTopicDetailCommentCount = finder.castView(view, 2131690374, "field 'tvTopicDetailCommentCount'");
    view = finder.findRequiredView(source, 2131689997, "field 'lvTopicDetailComment'");
    target.lvTopicDetailComment = finder.castView(view, 2131689997, "field 'lvTopicDetailComment'");
    view = finder.findRequiredView(source, 2131689995, "field 'btnTopicDetailComment' and method 'onClick'");
    target.btnTopicDetailComment = finder.castView(view, 2131689995, "field 'btnTopicDetailComment'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689996, "field 'edtTopicDetailComment'");
    target.edtTopicDetailComment = finder.castView(view, 2131689996, "field 'edtTopicDetailComment'");
    view = finder.findRequiredView(source, 2131690373, "field 'lytTopicDetailComment'");
    target.lytTopicDetailComment = finder.castView(view, 2131690373, "field 'lytTopicDetailComment'");
    view = finder.findRequiredView(source, 2131690372, "field 'gdvTopicDetailImages'");
    target.gdvTopicDetailImages = finder.castView(view, 2131690372, "field 'gdvTopicDetailImages'");
  }

  @Override public void unbind(T target) {
    target.tvTopicDetailTitle = null;
    target.ivTopicDetailPortrait = null;
    target.tvTopicDetailCreator = null;
    target.tvTopicDetailCreateTime = null;
    target.tvTopicDetailContent = null;
    target.tvTopicDetailCommentCount = null;
    target.lvTopicDetailComment = null;
    target.btnTopicDetailComment = null;
    target.edtTopicDetailComment = null;
    target.lytTopicDetailComment = null;
    target.gdvTopicDetailImages = null;
  }
}
