package com.yunchengke.app.ui.fragments.catering;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lee.pullrefresh.PullToRefreshBase;
import com.lee.pullrefresh.PullToRefreshListView;
import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.catering.Resp_CateringList;
import com.yunchengke.app.interfaces.FragmentListener;
import com.yunchengke.app.ui.adapter.daemon.catering.CateringAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CateringFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CateringFragment extends Fragment {
    private FragmentListener mListener;
    private static List<Resp_CateringList.Rows> list_rows;
    private CateringAdapter adapter;
    private PullToRefreshListView lv_catering;

    public CateringFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment CateringFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CateringFragment newInstance(List<Resp_CateringList.Rows> list_rows) {
        CateringFragment fragment = new CateringFragment();
        CateringFragment.list_rows = list_rows;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootLayout = (ViewGroup)inflater.inflate(R.layout.fragment_catering, container, false);
        if(list_rows!=null&&list_rows.size()>0) {
            lv_catering = (PullToRefreshListView) rootLayout.findViewById(R.id.lv_catering);
            lv_catering.getRefreshableView().setVerticalScrollBarEnabled(false);
            lv_catering.getRefreshableView().setHorizontalScrollBarEnabled(false);

            adapter = new CateringAdapter(getActivity(), list_rows);
            lv_catering.setPullLoadEnabled(true);
            lv_catering.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if(mListener!=null)
                    mListener.onPullDownToRefresh();
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    if(mListener!=null)
                    mListener.onPullUpToRefresh();
                }
            });
            lv_catering.getRefreshableView().setAdapter(adapter);
        } else{
            View errorLayout = inflater.inflate(R.layout.layout_order_no, container, false);
            rootLayout.addView(errorLayout);
        }
        return rootLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void notifyDataSetChanged() {
            if(adapter!=null){
                adapter.notifyDataSetChanged();
                lv_catering.onPullRefreshComplete();
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
