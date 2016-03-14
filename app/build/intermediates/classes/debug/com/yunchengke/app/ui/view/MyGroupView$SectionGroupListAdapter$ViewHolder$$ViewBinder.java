// Generated code from Butter Knife. Do not modify!
package com.yunchengke.app.ui.view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MyGroupView$SectionGroupListAdapter$ViewHolder$$ViewBinder<T extends com.yunchengke.app.ui.view.MyGroupView.SectionGroupListAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131690259, "field 'sectionMargin'");
    target.sectionMargin = view;
    view = finder.findRequiredView(source, 2131690260, "field 'sectionName'");
    target.sectionName = finder.castView(view, 2131690260, "field 'sectionName'");
    view = finder.findRequiredView(source, 2131690258, "field 'lytSection'");
    target.lytSection = finder.castView(view, 2131690258, "field 'lytSection'");
    view = finder.findRequiredView(source, 2131690179, "field 'image'");
    target.image = finder.castView(view, 2131690179, "field 'image'");
    view = finder.findRequiredView(source, 2131690180, "field 'title'");
    target.title = finder.castView(view, 2131690180, "field 'title'");
    view = finder.findRequiredView(source, 2131690181, "field 'content'");
    target.content = finder.castView(view, 2131690181, "field 'content'");
    view = finder.findRequiredView(source, 2131690182, "field 'people'");
    target.people = finder.castView(view, 2131690182, "field 'people'");
    view = finder.findRequiredView(source, 2131689733, "field 'lytContent'");
    target.lytContent = finder.castView(view, 2131689733, "field 'lytContent'");
  }

  @Override public void unbind(T target) {
    target.sectionMargin = null;
    target.sectionName = null;
    target.lytSection = null;
    target.image = null;
    target.title = null;
    target.content = null;
    target.people = null;
    target.lytContent = null;
  }
}
