// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GroupDetailActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.GroupDetailActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690298, "field 'ivGroupDetailBackground'");
    target.ivGroupDetailBackground = finder.castView(view, 2131690298, "field 'ivGroupDetailBackground'");
    view = finder.findRequiredView(source, 2131690305, "field 'ivGroupDetailLogo'");
    target.ivGroupDetailLogo = finder.castView(view, 2131690305, "field 'ivGroupDetailLogo'");
    view = finder.findRequiredView(source, 2131690300, "field 'tvGroupDetailTitle'");
    target.tvGroupDetailTitle = finder.castView(view, 2131690300, "field 'tvGroupDetailTitle'");
    view = finder.findRequiredView(source, 2131690301, "field 'tvGroupDetailCreator'");
    target.tvGroupDetailCreator = finder.castView(view, 2131690301, "field 'tvGroupDetailCreator'");
    view = finder.findRequiredView(source, 2131690302, "field 'tvGroupDetailMemberCount'");
    target.tvGroupDetailMemberCount = finder.castView(view, 2131690302, "field 'tvGroupDetailMemberCount'");
    view = finder.findRequiredView(source, 2131690303, "field 'tvGroupDetailMemberDetail' and method 'onClick'");
    target.tvGroupDetailMemberDetail = finder.castView(view, 2131690303, "field 'tvGroupDetailMemberDetail'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690304, "field 'tvGroupDetailContent'");
    target.tvGroupDetailContent = finder.castView(view, 2131690304, "field 'tvGroupDetailContent'");
    view = finder.findRequiredView(source, 2131690299, "field 'lytGroupDetailContent'");
    target.lytGroupDetailContent = finder.castView(view, 2131690299, "field 'lytGroupDetailContent'");
    view = finder.findRequiredView(source, 2131689823, "field 'btnGroupDetailCreateTopic' and method 'onClick'");
    target.btnGroupDetailCreateTopic = finder.castView(view, 2131689823, "field 'btnGroupDetailCreateTopic'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690306, "field 'btnGroupDetailJoin' and method 'onClick'");
    target.btnGroupDetailJoin = finder.castView(view, 2131690306, "field 'btnGroupDetailJoin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689824, "field 'lvGroupDetailTopics'");
    target.lvGroupDetailTopics = finder.castView(view, 2131689824, "field 'lvGroupDetailTopics'");
  }

  @Override public void unbind(T target) {
    target.ivGroupDetailBackground = null;
    target.ivGroupDetailLogo = null;
    target.tvGroupDetailTitle = null;
    target.tvGroupDetailCreator = null;
    target.tvGroupDetailMemberCount = null;
    target.tvGroupDetailMemberDetail = null;
    target.tvGroupDetailContent = null;
    target.lytGroupDetailContent = null;
    target.btnGroupDetailCreateTopic = null;
    target.btnGroupDetailJoin = null;
    target.lvGroupDetailTopics = null;
  }
}
