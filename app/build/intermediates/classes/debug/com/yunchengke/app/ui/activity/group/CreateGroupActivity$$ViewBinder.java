// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.group;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CreateGroupActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.group.CreateGroupActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689732, "field 'btnCreateGroupCreate' and method 'onClick'");
    target.btnCreateGroupCreate = finder.castView(view, 2131689732, "field 'btnCreateGroupCreate'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689739, "field 'tvCreateGroupCategoryName'");
    target.tvCreateGroupCategoryName = finder.castView(view, 2131689739, "field 'tvCreateGroupCategoryName'");
    view = finder.findRequiredView(source, 2131689741, "field 'edtCreateGroupName'");
    target.edtCreateGroupName = finder.castView(view, 2131689741, "field 'edtCreateGroupName'");
    view = finder.findRequiredView(source, 2131689744, "field 'ivCreateGroupPortrait'");
    target.ivCreateGroupPortrait = finder.castView(view, 2131689744, "field 'ivCreateGroupPortrait'");
    view = finder.findRequiredView(source, 2131689748, "field 'ivCreateGroupImage'");
    target.ivCreateGroupImage = finder.castView(view, 2131689748, "field 'ivCreateGroupImage'");
    view = finder.findRequiredView(source, 2131689745, "field 'btnCreateGroupAddPortrait' and method 'onClick'");
    target.btnCreateGroupAddPortrait = finder.castView(view, 2131689745, "field 'btnCreateGroupAddPortrait'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689751, "field 'edtCreateGroupIntroductionContent'");
    target.edtCreateGroupIntroductionContent = finder.castView(view, 2131689751, "field 'edtCreateGroupIntroductionContent'");
    view = finder.findRequiredView(source, 2131689735, "field 'rgpCreateGroupType'");
    target.rgpCreateGroupType = finder.castView(view, 2131689735, "field 'rgpCreateGroupType'");
    view = finder.findRequiredView(source, 2131689742, "field 'lytCreateGroupLogo'");
    target.lytCreateGroupLogo = finder.castView(view, 2131689742, "field 'lytCreateGroupLogo'");
    view = finder.findRequiredView(source, 2131689746, "field 'lytCreateGroupImage'");
    target.lytCreateGroupImage = finder.castView(view, 2131689746, "field 'lytCreateGroupImage'");
    view = finder.findRequiredView(source, 2131689749, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.btnCreateGroupCreate = null;
    target.tvCreateGroupCategoryName = null;
    target.edtCreateGroupName = null;
    target.ivCreateGroupPortrait = null;
    target.ivCreateGroupImage = null;
    target.btnCreateGroupAddPortrait = null;
    target.edtCreateGroupIntroductionContent = null;
    target.rgpCreateGroupType = null;
    target.lytCreateGroupLogo = null;
    target.lytCreateGroupImage = null;
  }
}
