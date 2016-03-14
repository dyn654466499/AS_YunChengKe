// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.dynamic;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DynamicDetailActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.dynamic.DynamicDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690284, "field 'ivDynamicDetailPortrait' and method 'onClick'");
    target.ivDynamicDetailPortrait = finder.castView(view, 2131690284, "field 'ivDynamicDetailPortrait'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690285, "field 'tvDynamicDetailName'");
    target.tvDynamicDetailName = finder.castView(view, 2131690285, "field 'tvDynamicDetailName'");
    view = finder.findRequiredView(source, 2131690286, "field 'tvDynamicDetailDate'");
    target.tvDynamicDetailDate = finder.castView(view, 2131690286, "field 'tvDynamicDetailDate'");
    view = finder.findRequiredView(source, 2131690287, "field 'tvDynamicDetailContent'");
    target.tvDynamicDetailContent = finder.castView(view, 2131690287, "field 'tvDynamicDetailContent'");
    view = finder.findRequiredView(source, 2131690288, "field 'gdvDynamicDetailImages'");
    target.gdvDynamicDetailImages = finder.castView(view, 2131690288, "field 'gdvDynamicDetailImages'");
    view = finder.findRequiredView(source, 2131690290, "field 'tvDynamicDetailCommentCount'");
    target.tvDynamicDetailCommentCount = finder.castView(view, 2131690290, "field 'tvDynamicDetailCommentCount'");
    view = finder.findRequiredView(source, 2131690289, "field 'lytDynamicDetailComment'");
    target.lytDynamicDetailComment = finder.castView(view, 2131690289, "field 'lytDynamicDetailComment'");
    view = finder.findRequiredView(source, 2131689780, "field 'btnDynamicDetailComment' and method 'onClick'");
    target.btnDynamicDetailComment = finder.castView(view, 2131689780, "field 'btnDynamicDetailComment'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689781, "field 'edtDynamicDetailComment'");
    target.edtDynamicDetailComment = finder.castView(view, 2131689781, "field 'edtDynamicDetailComment'");
    view = finder.findRequiredView(source, 2131689782, "field 'plvDynamicDetailComment'");
    target.plvDynamicDetailComment = finder.castView(view, 2131689782, "field 'plvDynamicDetailComment'");
  }

  @Override public void unbind(T target) {
    target.ivDynamicDetailPortrait = null;
    target.tvDynamicDetailName = null;
    target.tvDynamicDetailDate = null;
    target.tvDynamicDetailContent = null;
    target.gdvDynamicDetailImages = null;
    target.tvDynamicDetailCommentCount = null;
    target.lytDynamicDetailComment = null;
    target.btnDynamicDetailComment = null;
    target.edtDynamicDetailComment = null;
    target.plvDynamicDetailComment = null;
  }
}
