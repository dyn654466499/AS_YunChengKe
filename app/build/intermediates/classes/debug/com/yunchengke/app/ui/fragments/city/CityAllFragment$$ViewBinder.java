// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.fragments.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CityAllFragment$$ViewBinder<T extends com.yunchengke.app.ui.fragments.city.CityAllFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690067, "field 'lvCityList'");
    target.lvCityList = finder.castView(view, 2131690067, "field 'lvCityList'");
    view = finder.findRequiredView(source, 2131690066, "field 'expandtabView'");
    target.expandtabView = finder.castView(view, 2131690066, "field 'expandtabView'");
  }

  @Override public void unbind(T target) {
    target.lvCityList = null;
    target.expandtabView = null;
  }
}
