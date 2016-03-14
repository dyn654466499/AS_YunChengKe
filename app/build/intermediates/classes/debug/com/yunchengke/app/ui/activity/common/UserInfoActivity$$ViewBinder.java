// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.common;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class UserInfoActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.common.UserInfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690001, "field 'ivUserInfoPortrait'");
    target.ivUserInfoPortrait = finder.castView(view, 2131690001, "field 'ivUserInfoPortrait'");
    view = finder.findRequiredView(source, 2131690002, "field 'tvUserInfoName'");
    target.tvUserInfoName = finder.castView(view, 2131690002, "field 'tvUserInfoName'");
    view = finder.findRequiredView(source, 2131690003, "field 'tvUserInfoLocation'");
    target.tvUserInfoLocation = finder.castView(view, 2131690003, "field 'tvUserInfoLocation'");
    view = finder.findRequiredView(source, 2131690005, "field 'tvUserInfoFollowNumber'");
    target.tvUserInfoFollowNumber = finder.castView(view, 2131690005, "field 'tvUserInfoFollowNumber'");
    view = finder.findRequiredView(source, 2131690007, "field 'tvUserInfoFansNumber'");
    target.tvUserInfoFansNumber = finder.castView(view, 2131690007, "field 'tvUserInfoFansNumber'");
    view = finder.findRequiredView(source, 2131690009, "field 'btnUserInfoFollow' and method 'onClick'");
    target.btnUserInfoFollow = finder.castView(view, 2131690009, "field 'btnUserInfoFollow'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690010, "field 'btnUserInfoMessage' and method 'onClick'");
    target.btnUserInfoMessage = finder.castView(view, 2131690010, "field 'btnUserInfoMessage'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690008, "field 'lytUserInfoState'");
    target.lytUserInfoState = finder.castView(view, 2131690008, "field 'lytUserInfoState'");
    view = finder.findRequiredView(source, 2131690004, "field 'lytUserInfoFollow' and method 'onClick'");
    target.lytUserInfoFollow = finder.castView(view, 2131690004, "field 'lytUserInfoFollow'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131690006, "field 'lytUserInfoFans' and method 'onClick'");
    target.lytUserInfoFans = finder.castView(view, 2131690006, "field 'lytUserInfoFans'");
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
    target.ivUserInfoPortrait = null;
    target.tvUserInfoName = null;
    target.tvUserInfoLocation = null;
    target.tvUserInfoFollowNumber = null;
    target.tvUserInfoFansNumber = null;
    target.btnUserInfoFollow = null;
    target.btnUserInfoMessage = null;
    target.lytUserInfoState = null;
    target.lytUserInfoFollow = null;
    target.lytUserInfoFans = null;
  }
}
