// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.login;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.login.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689849, "field 'username'");
    target.username = finder.castView(view, 2131689849, "field 'username'");
    view = finder.findRequiredView(source, 2131689850, "field 'password'");
    target.password = finder.castView(view, 2131689850, "field 'password'");
    view = finder.findRequiredView(source, 2131689851, "field 'showp' and method 'login_showp'");
    target.showp = finder.castView(view, 2131689851, "field 'showp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_showp();
        }
      });
    view = finder.findRequiredView(source, 2131689852, "field 'forgetp' and method 'login_forgetp'");
    target.forgetp = finder.castView(view, 2131689852, "field 'forgetp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_forgetp();
        }
      });
    view = finder.findRequiredView(source, 2131689853, "field 'login' and method 'login_login'");
    target.login = finder.castView(view, 2131689853, "field 'login'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_login();
        }
      });
    view = finder.findRequiredView(source, 2131689854, "field 'register' and method 'login_register'");
    target.register = finder.castView(view, 2131689854, "field 'register'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_register();
        }
      });
    view = finder.findRequiredView(source, 2131689855, "field 'weixin' and method 'login_weixin'");
    target.weixin = finder.castView(view, 2131689855, "field 'weixin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_weixin();
        }
      });
    view = finder.findRequiredView(source, 2131689856, "field 'weibo' and method 'login_weibo'");
    target.weibo = finder.castView(view, 2131689856, "field 'weibo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.login_weibo();
        }
      });
  }

  @Override public void unbind(T target) {
    target.username = null;
    target.password = null;
    target.showp = null;
    target.forgetp = null;
    target.login = null;
    target.register = null;
    target.weixin = null;
    target.weibo = null;
  }
}
