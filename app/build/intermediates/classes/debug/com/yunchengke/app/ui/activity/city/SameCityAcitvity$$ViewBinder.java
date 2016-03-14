// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SameCityAcitvity$$ViewBinder<T extends com.yunchengke.app.ui.activity.city.SameCityAcitvity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689691, "field 'hots'");
    target.hots = finder.castView(view, 2131689691, "field 'hots'");
    view = finder.findRequiredView(source, 2131689692, "field 'cityAll'");
    target.cityAll = finder.castView(view, 2131689692, "field 'cityAll'");
    view = finder.findRequiredView(source, 2131689693, "field 'cityMyself'");
    target.cityMyself = finder.castView(view, 2131689693, "field 'cityMyself'");
    view = finder.findRequiredView(source, 2131689690, "field 'sgpCityTop'");
    target.sgpCityTop = finder.castView(view, 2131689690, "field 'sgpCityTop'");
    view = finder.findRequiredView(source, 2131689970, "field 'tvBaseTitleLeft'");
    target.tvBaseTitleLeft = finder.castView(view, 2131689970, "field 'tvBaseTitleLeft'");
    view = finder.findRequiredView(source, 2131689971, "field 'tvBaseTitleRight' and method 'onClick'");
    target.tvBaseTitleRight = finder.castView(view, 2131689971, "field 'tvBaseTitleRight'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689969, "field 'edCityactiveKeyword'");
    target.edCityactiveKeyword = finder.castView(view, 2131689969, "field 'edCityactiveKeyword'");
    view = finder.findRequiredView(source, 2131689972, "field 'tvBaseTitleCancelRight'");
    target.tvBaseTitleCancelRight = finder.castView(view, 2131689972, "field 'tvBaseTitleCancelRight'");
  }

  @Override public void unbind(T target) {
    target.hots = null;
    target.cityAll = null;
    target.cityMyself = null;
    target.sgpCityTop = null;
    target.tvBaseTitleLeft = null;
    target.tvBaseTitleRight = null;
    target.edCityactiveKeyword = null;
    target.tvBaseTitleCancelRight = null;
  }
}
