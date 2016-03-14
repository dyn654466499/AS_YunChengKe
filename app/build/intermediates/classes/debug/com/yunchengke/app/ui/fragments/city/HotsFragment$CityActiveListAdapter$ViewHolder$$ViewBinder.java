// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.fragments.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HotsFragment$CityActiveListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.fragments.city.HotsFragment.CityActiveListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690123, "field 'ivActiveCity'");
    target.ivActiveCity = finder.castView(view, 2131690123, "field 'ivActiveCity'");
    view = finder.findRequiredView(source, 2131690124, "field 'tvActiveAttend'");
    target.tvActiveAttend = finder.castView(view, 2131690124, "field 'tvActiveAttend'");
    view = finder.findRequiredView(source, 2131690125, "field 'tvActiveFollow'");
    target.tvActiveFollow = finder.castView(view, 2131690125, "field 'tvActiveFollow'");
    view = finder.findRequiredView(source, 2131690126, "field 'tvActiveTitle'");
    target.tvActiveTitle = finder.castView(view, 2131690126, "field 'tvActiveTitle'");
    view = finder.findRequiredView(source, 2131690127, "field 'tvActiveType'");
    target.tvActiveType = finder.castView(view, 2131690127, "field 'tvActiveType'");
    view = finder.findRequiredView(source, 2131690128, "field 'tvActiveAddresss'");
    target.tvActiveAddresss = finder.castView(view, 2131690128, "field 'tvActiveAddresss'");
    view = finder.findRequiredView(source, 2131690129, "field 'tvActiveTime'");
    target.tvActiveTime = finder.castView(view, 2131690129, "field 'tvActiveTime'");
  }

  @Override public void unbind(T target) {
    target.ivActiveCity = null;
    target.tvActiveAttend = null;
    target.tvActiveFollow = null;
    target.tvActiveTitle = null;
    target.tvActiveType = null;
    target.tvActiveAddresss = null;
    target.tvActiveTime = null;
  }
}
