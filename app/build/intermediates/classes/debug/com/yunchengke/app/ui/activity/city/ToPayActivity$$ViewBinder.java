// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ToPayActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.city.ToPayActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689983, "field 'mTimeRB1'");
    target.mTimeRB1 = finder.castView(view, 2131689983, "field 'mTimeRB1'");
    view = finder.findRequiredView(source, 2131689984, "field 'mTimeRB2'");
    target.mTimeRB2 = finder.castView(view, 2131689984, "field 'mTimeRB2'");
    view = finder.findRequiredView(source, 2131689985, "field 'mTimeRB3'");
    target.mTimeRB3 = finder.castView(view, 2131689985, "field 'mTimeRB3'");
    view = finder.findRequiredView(source, 2131689987, "field 'mPriceRB1'");
    target.mPriceRB1 = finder.castView(view, 2131689987, "field 'mPriceRB1'");
    view = finder.findRequiredView(source, 2131689988, "field 'mPriceRB2'");
    target.mPriceRB2 = finder.castView(view, 2131689988, "field 'mPriceRB2'");
    view = finder.findRequiredView(source, 2131689989, "field 'mPriceRB3'");
    target.mPriceRB3 = finder.castView(view, 2131689989, "field 'mPriceRB3'");
    view = finder.findRequiredView(source, 2131689982, "field 'mTimeRG'");
    target.mTimeRG = finder.castView(view, 2131689982, "field 'mTimeRG'");
    view = finder.findRequiredView(source, 2131689986, "field 'mPriceRG'");
    target.mPriceRG = finder.castView(view, 2131689986, "field 'mPriceRG'");
    view = finder.findRequiredView(source, 2131689990, "field 'mMinus'");
    target.mMinus = finder.castView(view, 2131689990, "field 'mMinus'");
    view = finder.findRequiredView(source, 2131689992, "field 'mAdd'");
    target.mAdd = finder.castView(view, 2131689992, "field 'mAdd'");
    view = finder.findRequiredView(source, 2131689991, "field 'mNum'");
    target.mNum = finder.castView(view, 2131689991, "field 'mNum'");
    view = finder.findRequiredView(source, 2131689993, "field 'pay' and method 'btn_city_pay'");
    target.pay = finder.castView(view, 2131689993, "field 'pay'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.btn_city_pay(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mTimeRB1 = null;
    target.mTimeRB2 = null;
    target.mTimeRB3 = null;
    target.mPriceRB1 = null;
    target.mPriceRB2 = null;
    target.mPriceRB3 = null;
    target.mTimeRG = null;
    target.mPriceRG = null;
    target.mMinus = null;
    target.mAdd = null;
    target.mNum = null;
    target.pay = null;
  }
}
