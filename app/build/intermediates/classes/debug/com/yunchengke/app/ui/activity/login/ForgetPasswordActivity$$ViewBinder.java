// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.login;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ForgetPasswordActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.login.ForgetPasswordActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689810, "field 'username'");
    target.username = finder.castView(view, 2131689810, "field 'username'");
    view = finder.findRequiredView(source, 2131689811, "field 'code'");
    target.code = finder.castView(view, 2131689811, "field 'code'");
    view = finder.findRequiredView(source, 2131689813, "field 'password'");
    target.password = finder.castView(view, 2131689813, "field 'password'");
    view = finder.findRequiredView(source, 2131689814, "field 'showp' and method 'showp'");
    target.showp = finder.castView(view, 2131689814, "field 'showp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.showp();
        }
      });
    view = finder.findRequiredView(source, 2131689812, "field 'getc' and method 'getc'");
    target.getc = finder.castView(view, 2131689812, "field 'getc'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.getc();
        }
      });
    view = finder.findRequiredView(source, 2131689815, "field 'reset' and method 'reset'");
    target.reset = finder.castView(view, 2131689815, "field 'reset'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.reset();
        }
      });
  }

  @Override public void unbind(T target) {
    target.username = null;
    target.code = null;
    target.password = null;
    target.showp = null;
    target.getc = null;
    target.reset = null;
  }
}
