// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewsDetailActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.NewsDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689885, "field 'btnNewsDetailComment' and method 'onClick'");
    target.btnNewsDetailComment = finder.castView(view, 2131689885, "field 'btnNewsDetailComment'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689886, "field 'edtNewsDetailComment'");
    target.edtNewsDetailComment = finder.castView(view, 2131689886, "field 'edtNewsDetailComment'");
    view = finder.findRequiredView(source, 2131690353, "field 'tvNewsDetailTitle'");
    target.tvNewsDetailTitle = finder.castView(view, 2131690353, "field 'tvNewsDetailTitle'");
    view = finder.findRequiredView(source, 2131690354, "field 'ivNewsDetailImage'");
    target.ivNewsDetailImage = finder.castView(view, 2131690354, "field 'ivNewsDetailImage'");
    view = finder.findRequiredView(source, 2131690355, "field 'tvNewsDetailContent'");
    target.tvNewsDetailContent = finder.castView(view, 2131690355, "field 'tvNewsDetailContent'");
    view = finder.findRequiredView(source, 2131690356, "field 'tvNewsDetailInterval'");
    target.tvNewsDetailInterval = finder.castView(view, 2131690356, "field 'tvNewsDetailInterval'");
    view = finder.findRequiredView(source, 2131690357, "field 'tvNewsDetailCreator'");
    target.tvNewsDetailCreator = finder.castView(view, 2131690357, "field 'tvNewsDetailCreator'");
    view = finder.findRequiredView(source, 2131690358, "field 'tvNewsDetailCreateTime'");
    target.tvNewsDetailCreateTime = finder.castView(view, 2131690358, "field 'tvNewsDetailCreateTime'");
    view = finder.findRequiredView(source, 2131690360, "field 'tvNewsDetailCommentCount'");
    target.tvNewsDetailCommentCount = finder.castView(view, 2131690360, "field 'tvNewsDetailCommentCount'");
    view = finder.findRequiredView(source, 2131690359, "field 'lytNewsDetailComment'");
    target.lytNewsDetailComment = finder.castView(view, 2131690359, "field 'lytNewsDetailComment'");
  }

  @Override public void unbind(T target) {
    target.btnNewsDetailComment = null;
    target.edtNewsDetailComment = null;
    target.tvNewsDetailTitle = null;
    target.ivNewsDetailImage = null;
    target.tvNewsDetailContent = null;
    target.tvNewsDetailInterval = null;
    target.tvNewsDetailCreator = null;
    target.tvNewsDetailCreateTime = null;
    target.tvNewsDetailCommentCount = null;
    target.lytNewsDetailComment = null;
  }
}
