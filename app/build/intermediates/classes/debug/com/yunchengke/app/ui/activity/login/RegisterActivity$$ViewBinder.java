// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.login;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.login.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689959, "field 'username'");
    target.username = finder.castView(view, 2131689959, "field 'username'");
    view = finder.findRequiredView(source, 2131689960, "field 'code'");
    target.code = finder.castView(view, 2131689960, "field 'code'");
    view = finder.findRequiredView(source, 2131689962, "field 'password'");
    target.password = finder.castView(view, 2131689962, "field 'password'");
    view = finder.findRequiredView(source, 2131689963, "field 'showp' and method 'showp'");
    target.showp = finder.castView(view, 2131689963, "field 'showp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.showp();
        }
      });
    view = finder.findRequiredView(source, 2131689961, "field 'getc' and method 'getc'");
    target.getc = finder.castView(view, 2131689961, "field 'getc'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getc();
        }
      });
    view = finder.findRequiredView(source, 2131689964, "field 'register' and method 'register'");
    target.register = finder.castView(view, 2131689964, "field 'register'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.register();
        }
      });
    view = finder.findRequiredView(source, 2131689966, "field 'weixin'");
    target.weixin = finder.castView(view, 2131689966, "field 'weixin'");
    view = finder.findRequiredView(source, 2131689967, "field 'weibo'");
    target.weibo = finder.castView(view, 2131689967, "field 'weibo'");
    view = finder.findRequiredView(source, 2131689965, "field 'checkBox1'");
    target.checkBox1 = finder.castView(view, 2131689965, "field 'checkBox1'");
  }

  @Override public void unbind(T target) {
    target.username = null;
    target.code = null;
    target.password = null;
    target.showp = null;
    target.getc = null;
    target.register = null;
    target.weixin = null;
    target.weibo = null;
    target.checkBox1 = null;
  }
}
