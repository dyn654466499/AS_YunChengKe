// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.activity.city;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CityDetailsActivity$$ViewBinder<T extends com.yunchengke.app.ui.activity.city.CityDetailsActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131689480, "field 'scrollView'");
    target.scrollView = finder.castView(view, 2131689480, "field 'scrollView'");
    view = finder.findRequiredView(source, 2131689758, "field 'topTv1'");
    target.topTv1 = finder.castView(view, 2131689758, "field 'topTv1'");
    view = finder.findRequiredView(source, 2131689759, "field 'topTv2'");
    target.topTv2 = finder.castView(view, 2131689759, "field 'topTv2'");
    view = finder.findRequiredView(source, 2131689757, "field 'topIv'");
    target.topIv = finder.castView(view, 2131689757, "field 'topIv'");
    view = finder.findRequiredView(source, 2131689761, "field 'tvType'");
    target.tvType = finder.castView(view, 2131689761, "field 'tvType'");
    view = finder.findRequiredView(source, 2131689762, "field 'tvCast'");
    target.tvCast = finder.castView(view, 2131689762, "field 'tvCast'");
    view = finder.findRequiredView(source, 2131689760, "field 'tvTitle'");
    target.tvTitle = finder.castView(view, 2131689760, "field 'tvTitle'");
    view = finder.findRequiredView(source, 2131689763, "field 'tvAddress'");
    target.tvAddress = finder.castView(view, 2131689763, "field 'tvAddress'");
    view = finder.findRequiredView(source, 2131689764, "field 'tvPhonenum'");
    target.tvPhonenum = finder.castView(view, 2131689764, "field 'tvPhonenum'");
    view = finder.findRequiredView(source, 2131689765, "field 'tvJoin' and method 'onClick'");
    target.tvJoin = finder.castView(view, 2131689765, "field 'tvJoin'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689767, "field 'tvFocus' and method 'onClick'");
    target.tvFocus = finder.castView(view, 2131689767, "field 'tvFocus'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689766, "field 'textViewJ'");
    target.textViewJ = finder.castView(view, 2131689766, "field 'textViewJ'");
    view = finder.findRequiredView(source, 2131689768, "field 'textViewF'");
    target.textViewF = finder.castView(view, 2131689768, "field 'textViewF'");
    view = finder.findRequiredView(source, 2131689769, "field 'llSee' and method 'onClick'");
    target.llSee = finder.castView(view, 2131689769, "field 'llSee'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689771, "field 'tvIntroduction'");
    target.tvIntroduction = finder.castView(view, 2131689771, "field 'tvIntroduction'");
    view = finder.findRequiredView(source, 2131689774, "field 'lvComments'");
    target.lvComments = finder.castView(view, 2131689774, "field 'lvComments'");
    view = finder.findRequiredView(source, 2131689770, "field 'gvActivityImg'");
    target.gvActivityImg = finder.castView(view, 2131689770, "field 'gvActivityImg'");
    view = finder.findRequiredView(source, 2131689776, "field 'gvActivityJoined'");
    target.gvActivityJoined = finder.castView(view, 2131689776, "field 'gvActivityJoined'");
    view = finder.findRequiredView(source, 2131689773, "field 'llWatchCommment' and method 'onClick'");
    target.llWatchCommment = finder.castView(view, 2131689773, "field 'llWatchCommment'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689775, "field 'llWatchJoined' and method 'onClick'");
    target.llWatchJoined = finder.castView(view, 2131689775, "field 'llWatchJoined'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689772, "field 'tv_show'");
    target.tv_show = finder.castView(view, 2131689772, "field 'tv_show'");
    view = finder.findRequiredView(source, 2131689777, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131689778, "method 'onClick'");
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
    target.scrollView = null;
    target.topTv1 = null;
    target.topTv2 = null;
    target.topIv = null;
    target.tvType = null;
    target.tvCast = null;
    target.tvTitle = null;
    target.tvAddress = null;
    target.tvPhonenum = null;
    target.tvJoin = null;
    target.tvFocus = null;
    target.textViewJ = null;
    target.textViewF = null;
    target.llSee = null;
    target.tvIntroduction = null;
    target.lvComments = null;
    target.gvActivityImg = null;
    target.gvActivityJoined = null;
    target.llWatchCommment = null;
    target.llWatchJoined = null;
    target.tv_show = null;
  }
}
