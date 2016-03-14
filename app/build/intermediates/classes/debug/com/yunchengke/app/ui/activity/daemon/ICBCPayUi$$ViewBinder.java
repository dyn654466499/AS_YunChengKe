// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.daemon;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ICBCPayUi$$ViewBinder<T extends com.yunchengke.app.ui.activity.daemon.ICBCPayUi> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689840, "field 'etPayCardPhone'");
    target.etPayCardPhone = finder.castView(view, 2131689840, "field 'etPayCardPhone'");
    view = finder.findRequiredView(source, 2131689841, "field 'etPayCardId'");
    target.etPayCardId = finder.castView(view, 2131689841, "field 'etPayCardId'");
    view = finder.findRequiredView(source, 2131689842, "field 'payCode'");
    target.payCode = finder.castView(view, 2131689842, "field 'payCode'");
    view = finder.findRequiredView(source, 2131689843, "field 'getPaycode' and method 'onClick'");
    target.getPaycode = finder.castView(view, 2131689843, "field 'getPaycode'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689844, "field 'btPayMoney' and method 'onClick'");
    target.btPayMoney = finder.castView(view, 2131689844, "field 'btPayMoney'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689970, "method 'onClick'");
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
    target.etPayCardPhone = null;
    target.etPayCardId = null;
    target.payCode = null;
    target.getPaycode = null;
    target.btPayMoney = null;
  }
}
