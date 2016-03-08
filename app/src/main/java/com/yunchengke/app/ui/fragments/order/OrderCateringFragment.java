package com.yunchengke.app.ui.fragments.order;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.order.Resp_OrderCateringList;
import com.yunchengke.app.interfaces.FragmentListener;
import com.yunchengke.app.ui.adapter.daemon.myconsume.ConsumeCateringAdapter;
import com.yunchengke.app.utils.daemon.ScreenUtil;

import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link OrderTicketFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link OrderTicketFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class OrderCateringFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static List<Resp_OrderCateringList.Rows> list_rows;

    private FragmentListener mListener;
   private ConsumeCateringAdapter adapter;
    private PullToRefreshListView lv_my_consume_order_catering;
    public OrderCateringFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OrderTicketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderCateringFragment newInstance(List<Resp_OrderCateringList.Rows> list_rows) {
        OrderCateringFragment fragment = new OrderCateringFragment();
        OrderCateringFragment.list_rows = list_rows;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            info = getArguments().getParcelable(Constants.KEY_PARCELABLE);
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootLayout = (ViewGroup)inflater.inflate(R.layout.fragment_order_catering, container, false);
        if(list_rows!=null&&list_rows.size()>0) {
             lv_my_consume_order_catering = (PullToRefreshListView) rootLayout.findViewById(R.id.lv_my_consume_order_catering);
            View view = new View(getActivity());
            view.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,15));
            view.setBackgroundColor(getResources().getColor(R.color.ticket_bg_gray));
            lv_my_consume_order_catering.getRefreshableView().addFooterView(view);
            lv_my_consume_order_catering.getRefreshableView().addHeaderView(view);

            lv_my_consume_order_catering.getRefreshableView().setDivider(getResources().getDrawable(R.drawable.ticket_bg_gray));
            lv_my_consume_order_catering.getRefreshableView().setDividerHeight(ScreenUtil.dip2px(getActivity(), 15));
            lv_my_consume_order_catering.getRefreshableView().setVerticalScrollBarEnabled(false);
            lv_my_consume_order_catering.getRefreshableView().setHorizontalScrollBarEnabled(false);

            adapter = new ConsumeCateringAdapter(getActivity(), list_rows);

            lv_my_consume_order_catering.setPullLoadEnabled(true);
            lv_my_consume_order_catering.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (mListener != null)
                        mListener.onPullDownToRefresh();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if (mListener != null)
                        mListener.onPullUpToRefresh();
                }
            });
            lv_my_consume_order_catering.getRefreshableView().setAdapter(adapter);
        }else{
            View errorLayout = inflater.inflate(R.layout.layout_order_no, container, false);
            rootLayout.addView(errorLayout);
        }
        return rootLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void notifyDataSetChanged() {
            if(adapter!=null){
                adapter.notifyDataSetChanged();
                lv_my_consume_order_catering.onPullRefreshComplete();
        }
    }

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            mListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
