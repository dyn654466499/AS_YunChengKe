// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.fragments.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CityMyselfFragment$$ViewBinder<T extends com.yunchengke.app.ui.fragments.city.CityMyselfFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690068, "field 'rgMyCityActive'");
    target.rgMyCityActive = finder.castView(view, 2131690068, "field 'rgMyCityActive'");
    view = finder.findRequiredView(source, 2131690067, "field 'lvCityList'");
    target.lvCityList = finder.castView(view, 2131690067, "field 'lvCityList'");
  }

  @Override public void unbind(T target) {
    target.rgMyCityActive = null;
    target.lvCityList = null;
  }
}
