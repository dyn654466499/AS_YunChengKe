package com.yunchengke.app.ui.adapter.daemon.order;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunchengke.app.R;
import com.yunchengke.app.bean.daemon.ticket.Req_PassengerInfo;
import com.yunchengke.app.interfaces.Commands;
import com.yunchengke.app.interfaces.ListViewListener;
import com.yunchengke.app.ui.activity.daemon.TypeSelectActivity;
import com.yunchengke.app.ui.view.daemon.CustomEditText;
import com.yunchengke.app.utils.RegexUtils;
import com.yunchengke.app.utils.ToastUtils;

import java.util.ArrayList;

import static com.yunchengke.app.consts.Constants.KEY_TYPE;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_CABIN_POSITION;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_CERT;
import static com.yunchengke.app.consts.Constants.KEY_TYPE_PASSENGER_CERT_POSITION;
import static com.yunchengke.app.consts.Constants.REQUEST_CODE_CERTIFICATE;

public class OrderPassengerAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Req_PassengerInfo> infos;
    private Commands sizeChangeCommand;
    private ListViewListener listViewListener;
    private int index = -1;
    private ArrayList<Integer> indexs;

    public OrderPassengerAdapter(final Activity activity, ArrayList<Req_PassengerInfo> infos, SparseIntArray type_positions, ArrayList<Integer> indexs) {
        super();
        this.activity = activity;
        this.infos = infos;
        this.indexs = indexs;
    }

    public void setListViewListener(ListViewListener listViewListener) {
        this.listViewListener = listViewListener;
    }

    public void setSizeChangeCommand(Commands command) {
        this.sizeChangeCommand = command;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Req_PassengerInfo getItem(int position) {
        // TODO Auto-generated method stub
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_order_passenger_info, null, false);
            holder.tv_order_certType = (TextView) convertView.findViewById(R.id.tv_order_certType);

            holder.et_order_passengers = (CustomEditText) convertView.findViewById(R.id.et_order_passengers);
            holder.et_order_certNum = (CustomEditText) convertView.findViewById(R.id.et_order_certNum);

            holder.btn_order_moreCert = (Button) convertView.findViewById(R.id.btn_order_moreCert);
            holder.btn_order_deleteItem = (Button) convertView.findViewById(R.id.btn_order_deleteItem);

            holder.tv_order_certNumTips = (TextView) convertView.findViewById(R.id.tv_order_certNumTips);
            holder.linearLayout_order_certNumTips = (LinearLayout) convertView.findViewById(R.id.linearLayout_order_certNumTips);

            holder.et_order_certNum.setOnFocusChangeListener(new OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // TODO Auto-generated method stub
                    if (hasFocus) {
                        holder.btn_order_deleteItem.setTextColor(activity.getResources().getColor(R.color.ticket_title_color));
                    } else {
                        holder.btn_order_deleteItem.setTextColor(activity.getResources().getColor(R.color.ticket_font_gray));
                    }

                }
            });

            convertView.setTag(holder);
            //Log.e("if", "position = "+position);

        } else {
            holder = (ViewHolder) convertView.getTag();
            //Log.e("else", "position = "+position);
        }


        holder.position = position;
        holder.et_order_passengers.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                int position = holder.position;
                infos.get(position).name = s.toString();
            }
        });
        /**
         * 号码内容监听
         */
        holder.et_order_certNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                int position = holder.position;
                infos.get(position).certNum = s.toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub

            }
        });

//		holder.et_order_certNum.setOnFocusChangeListener(new OnFocusChangeListener() {
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if(holder.et_order_passengers.hasFocus() || !hasFocus){
//					//if (indexs.get(holder.position) == position) {
//						if (infos.get(position).certNum.length() == 15 || infos.get(position).certNum.length() == 18) {
//							try {
//								if(holder.linearLayout_order_certNumTips.getVisibility() == View.VISIBLE ) {
//									String result = CommonUtil.IDCardValidate(infos.get(position).certNum);
//									if (TextUtils.isEmpty(result)) {
//										holder.linearLayout_order_certNumTips.setVisibility(View.GONE);
//									}
//								}
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
//						} else {
//							if(holder.linearLayout_order_certNumTips.getVisibility() == View.GONE ) {
//								holder.linearLayout_order_certNumTips.setVisibility(View.VISIBLE);
//								holder.tv_order_certNumTips.setText("身份证格式有误");
//							}
//						}
//					//}
//				}
//			}
//		});

        holder.et_order_certNum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    indexs.set(holder.position, position);
                }
                return false;
            }
        });


        holder.et_order_passengers.setText(infos.get(position).name);

        holder.et_order_certNum.setText(infos.get(position).certNum);

        holder.tv_order_certType.setText(infos.get(position).certType);

        /**
         * 下面两个button的位置一定要放到上面的if else外面
         */
        holder.btn_order_moreCert.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                /**
                 * 记录点击哪位乘机人“更多证件”的位置
                 */
                Intent intent = new Intent(activity, TypeSelectActivity.class);
                intent.putExtra(KEY_TYPE, KEY_TYPE_CERT);
                /**
                 * 传递点击哪位乘机人“更多证件”的位置对于的证件类型位置。
                 */
                intent.putExtra(KEY_TYPE_CABIN_POSITION, infos.get(position).cert_position);
                /**
                 * 传递点击哪位乘机人“更多证件”的位置。
                 */
                intent.putExtra(KEY_TYPE_PASSENGER_CERT_POSITION, position);
                activity.startActivityForResult(intent, REQUEST_CODE_CERTIFICATE);
                activity.overridePendingTransition(R.anim.activity_up, 0);
            }
        });
        /**
         * 删除
         */
        holder.btn_order_deleteItem.setText(String.valueOf(position + 1));
        holder.btn_order_deleteItem.setEnabled(true);
        holder.btn_order_deleteItem.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (infos.size() <= 1) {
                    v.setEnabled(false);

                } else {
                    infos.remove(holder.position);
                    indexs.remove(holder.position);
                    v.setEnabled(true);
                }
                Log.e("remove", "position = " + position);
                notifyDataSetChanged();

                if (listViewListener != null) listViewListener.onItemDelete();
            }
        });

        holder.et_order_passengers.setOnFocusChangeListener(new OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    holder.btn_order_deleteItem.setTextColor(activity.getResources().getColor(R.color.ticket_title_color));

                } else {
                    holder.btn_order_deleteItem.setTextColor(activity.getResources().getColor(R.color.ticket_font_gray));
//
                }
            }
        });

        return convertView;
    }


    static class ViewHolder {
        TextView tv_order_certType, tv_order_certNumTips;
        Button btn_order_moreCert;
        Button btn_order_deleteItem;
        CustomEditText et_order_passengers, et_order_certNum;
        LinearLayout linearLayout_order_certNumTips;

        int position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (sizeChangeCommand != null) sizeChangeCommand.executeCommand(null);
    }
}
