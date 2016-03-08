package com.yunchengke.app.ui.activity.city;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.upyun.block.api.listener.ProgressListener;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.city.AddActivitiesResult;
import com.yunchengke.app.bean.city.CityType;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.AddActivitiesRequest;
import com.yunchengke.app.http.request.GetCityTypeRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.activity.dynamic.PublishDynamicActivity;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.widget.UploadingDialogFragment;
import com.yunchengke.app.utils.ToastUtils;
import com.yunchengke.app.utils.UploadFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import ui.SelectPicActivity;

/**
 * Created by Administrator on 2016/1/30.
 */
public class AddCreateActivities extends BaseActivity {



    public static final String FRAGMENT_TAG_UPLOADING_DIALOG = "uploading_dialog";

    @Bind(R.id.tv_create_activities_type_eidt)
    TextView tvCreateActivitiesTypeEidt;
    @Bind(R.id.tv_create_activities_name_eidt)
    EditText tvCreateActivitiesNameEidt;
    @Bind(R.id.tv_create_activities_image_show)
    ImageView tvCreateActivitiesImageShow;
    @Bind(R.id.tv_create_activities_pick_image)
    ImageView tvCreateActivitiesPickImage;

    @Bind(R.id.images)
    LinearLayout tvCreateActivitiesImagesShow;
    @Bind(R.id.tv_create_activities_pick_images)
    ImageView tvCreateActivitiesPickImages;
    @Bind(R.id.tv_create_activities_area_eidt)
    TextView tvCreateActivitiesAreaEidt;
    @Bind(R.id.tv_create_activities_address_eidt)
    EditText tvCreateActivitiesAddressEidt;
    @Bind(R.id.tv_create_activities_phone_eidt)
    EditText tvCreateActivitiesPhoneEidt;
    @Bind(R.id.tv_create_activities_cost_eidt)
    EditText tvCreateActivitiesCostEidt;
    @Bind(R.id.tv_create_activities_numbermax_eidt)
    EditText tvCreateActivitiesNumbermaxEidt;
    @Bind(R.id.tv_create_activities_start_time_eidt)
    TextView tvCreateActivitiesStartTimeEidt;
    @Bind(R.id.tv_create_activities_end_time_eidt)
    TextView tvCreateActivitiesEndTimeEidt;

    @Bind(R.id.tv_create_activities_sign_start_time_edit)
    TextView tvCreateActivitiesSignStartTimeEidt;
    @Bind(R.id.tv_create_activities_sign_end_time_edit)
    TextView tvCreateActivitiesSignEndTimeEidt;
    @Bind(R.id.tv_create_activities_introduce_eidt)
    EditText tvCreateActivitiesIntroduceEidt;
    @Bind(R.id.btn_create_activities)
    TextView btnCreateActivities;

    private ArrayList<String> mImageFiles;
    private FragmentManager mFragmentManager;

    private UploadingDialogFragment mUploadingDialog;

    private String UId = "yesicity";
    private String YHID = "1";
    private String Yesicity = "1";
    private String HDFL = "1";
    //活动名称
    private String actname;
    //小图
    private String actminimage;
    //大图
    private String actmaximage;
    //图片list
    private String actimagelist;
    //地区
    private String actregion;
    //地址
    private String actaddress;
    //电话
    private String actphone;
    //费用
    private String actcost;
    //人数限制
    private String actnumbermax;
    //开始时间
    private String actstarttime;
    //结束时间
    private String actendtime;
    //开始报名时间
    private String actstartsignup;
    //结束报名时间
    private String actendsignup;
    //简介
    private String actintro;

    private Boolean isOnePick = false;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);
        btnCreateActivities.setOnClickListener(this);
        setTitle(R.string.title_create_activities);
        lytTitle.setBackgroundResource(R.color.create_activites_title);
        mFragmentManager = getSupportFragmentManager();
        setTitleLeft(R.string.back);

        mImageFiles = new ArrayList<String>();
//        mImageUrls = new ArrayList<String>();


        tvCreateActivitiesImageShow.setOnClickListener(this);
        tvCreateActivitiesImagesShow.setOnClickListener(this);
        tvCreateActivitiesStartTimeEidt.setOnClickListener(this);
        tvCreateActivitiesEndTimeEidt.setOnClickListener(this);
        tvCreateActivitiesSignStartTimeEidt.setOnClickListener(this);
        tvCreateActivitiesSignEndTimeEidt.setOnClickListener(this);
        findViewById(R.id.add_image2).setOnClickListener(this);
        tvCreateActivitiesAreaEidt.setOnClickListener(this);
        tvCreateActivitiesTypeEidt.setOnClickListener(this);

        GetCityTypeRequest req = new GetCityTypeRequest(mListener);
        HttpRequestQueue.addToRequestQueue(req);
    }

    List<CityType.RowsEntity> cityTypes;
    HttpRequestListener<CityType> mListener = new HttpRequestListener<CityType>() {
        @Override
        public void onResponse(CityType response) {
            super.onResponse(response);
            cityTypes = response.getRows();
        }

    };

    private void showCityType(){
        if(cityTypes!=null){
            pos = 0;
            AlertDialog.Builder builder=new AlertDialog.Builder(AddCreateActivities.this);
            builder.setTitle("选择类型");
            builder.setIcon(android.R.drawable.ic_dialog_info);
            final String[] strings = new String[cityTypes.size()];
            for (int i = 0;i<cityTypes.size();i++)
                strings[i] = cityTypes.get(i).getField_HDFL();

            builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pos = which;

                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tvCreateActivitiesTypeEidt.setText(cityTypes.get(pos).getField_HDFL());
                }
            });
            builder.setNegativeButton("取消", null);
            AlertDialog dialog=builder.create();
            dialog.show();
        }

    }



    @Override
    public void onClick(View v) {
        AppLog.e("v",v.getId()+"");
        switch (v.getId()) {
            case R.id.tv_create_activities_type_eidt:
                showCityType();
                break;
            case R.id.btn_create_activities:

                sendCover();
                break;
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.tv_create_activities_pick_image:
            case R.id.tv_create_activities_image_show:
            case R.id.add_image2:
                pickImage();
                break;

            case R.id.tv_create_activities_pick_images:
                isOnePick = false;
                break;
            case R.id.tv_create_activities_start_time_eidt: {
                showTime(tvCreateActivitiesStartTimeEidt);
            }
                break;
            case R.id.tv_create_activities_end_time_eidt: {
                showTime(tvCreateActivitiesEndTimeEidt);
            }
            break;
            case R.id.tv_create_activities_sign_end_time_edit: {

                showTime(tvCreateActivitiesSignEndTimeEidt);
            }
                break;
            case R.id.tv_create_activities_sign_start_time_edit: {
                showTime(tvCreateActivitiesSignStartTimeEidt);
            }
                break;
            case R.id.hScrollView:
            case  R.id.images:{
                pickImages();
            }
            break;
            case R.id.tv_create_activities_area_eidt:
                showLocal();
                break;

            default:
                super.onClick(v);
                break;
        }
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        View view = inflate(R.layout.activity_create_activities, parent);
        return view;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] projection = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    projection, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                try {
                    do {
                        int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                        String picturePath = cursor.getString(columnIndex);

                        AppLog.e("文件", picturePath);

                        if (!TextUtils.isEmpty(picturePath)) {
                            File file = new File(picturePath);

                            if (file.exists()) {
                                mImageFiles.add(picturePath);
                            }

                        }
                    } while (mImageFiles.size()<2);

                } catch (Exception e) {
                    // 捕获异常
                } finally {
                    cursor.close();
                }

                int start = mImageFiles.size() - 9;

                if (start < 0) {
                    start = 0;
                }

                if (!isOnePick){
                    mImageFiles.subList(0, start).clear();
                }else{
                    tvCreateActivitiesImageShow.setImageURI(selectedImage);
                }


                AppLog.e("刷新数据", "个数：" + mImageFiles.size());
            }
        }else if(requestCode == 2  && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                tvCreateActivitiesImageShow.setImageURI(data.getData());

                File file = new File(picturePath);

                actminimage = picturePath;

                findViewById(R.id.add_image2).setLayoutParams(new RelativeLayout.LayoutParams(1,1));
                AppLog.e("CreateGroupActivity", Long.toHexString(System.currentTimeMillis()) + file.getName());
            }
        }
        else if(requestCode == SelectPicActivity.REQUEST_SELECT_PIC && resultCode == RESULT_OK && data != null){
            ArrayList<String> images = data.getStringArrayListExtra(SelectPicActivity.SELECT_PIC_LIST);
            for(String image:images){
                mImageFiles.add(image);
            }
            int start = mImageFiles.size() - 9;

            if (start < 0) {
                start = 0;
            }

            mImageFiles.subList(0, start).clear();

            tvCreateActivitiesImagesShow.removeAllViews();

            for(int i=0;i<mImageFiles.size();i++){
                View  convertView = LayoutInflater.from(this).inflate(R.layout.item_addcreate_img, null, false);
                ImageView image = (ImageView)convertView.findViewById(R.id.image);
                Glide.with(this).load(mImageFiles.get(i)).into(image);
                tvCreateActivitiesImagesShow.addView(convertView);
            }

        }
    }
    int pos =0;

    private void showLocal(){
        pos = 0;
        AlertDialog.Builder builder=new AlertDialog.Builder(AddCreateActivities.this);
        builder.setTitle("选择地区");
        builder.setIcon(android.R.drawable.ic_dialog_info);
       final String[] strings = new String[]{"宁波", "其他"};

        builder.setSingleChoiceItems(new String[]{"宁波", "其他"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pos = which;

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvCreateActivitiesAreaEidt.setText(strings[pos]);
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void addActivitiesResult(List<String> imageUrls) {
        final AddActivitiesRequest req = new AddActivitiesRequest(new HttpRequestListener<AddActivitiesResult>() {
            @Override
            public void onFinishRequest() {
                super.onFinishRequest();
                mUploadingDialog.dismiss();
            }

            @Override
            public void onResponse(AddActivitiesResult response) {
                super.onResponse(response);
                mUploadingDialog.dismiss();
                try {
                    if (AddActivitiesResult.RESULT_SUCCESS.equals(response.getResultState())) {
                        Intent intent = new Intent();
                        intent.setAction("update_samecity");
                        sendBroadcast(intent);
                        AddCreateActivities.this.finish();

                    }else{
                    }
                }catch (Exception e){
                    // 异常处理
                }
            }
        });

        req.setRequestParams(HDFL,actname,
                mCover,mCover,actimagelist,actregion,
                actaddress,actphone,actcost,actnumbermax,
                actstarttime,actendtime,actstartsignup,actendsignup,actintro,imageUrls);

        HttpRequestQueue.addToRequestQueue(req);
    }

    public void pickImage() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,2);
        } catch (Exception e) {
            AppLog.e(e.getMessage());
        }
    }




    private void pickImages() {
        try {

            UIHelper.gotoSelectPicActivity(this, mImageFiles.size());
        } catch (Exception e) {
            // 捕获异常
            ToastUtils.show(this, "选择图片失败，请重试");
        }
    }



    private void uploadImages() {

        if (mImageFiles.size() <=0) {
            addActivitiesResult(null);
        }else
        {
            try {

                UploadFileUtils.uploadMultiFiles(mImageFiles, new ProgressListener() {
                    @Override
                    public void transferred(long transferredBytes, long totalBytes) {

                    }

                }, new PublishDynamicActivity.MultiFileCompleteListener(mImageFiles.size()) {
                    @Override
                    public void onCompleteUploading(boolean isComplete, List<String> imageUrls) {
                        mUploadingDialog.dismiss();

                        if (isComplete) {
                            addActivitiesResult(imageUrls);
                        } else {
                            mUploadingDialog.dismiss();
                            Toast.makeText(AddCreateActivities.this, "发布失败!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
        }
    }

    private String mCover;
    private void sendCover() {
        actname = tvCreateActivitiesNameEidt.getText().toString();
        actregion = tvCreateActivitiesAreaEidt.getText().toString();
        actaddress = tvCreateActivitiesAddressEidt.getText().toString();
        actphone = tvCreateActivitiesPhoneEidt.getText().toString();
        actcost = tvCreateActivitiesCostEidt.getText().toString();
        actnumbermax = tvCreateActivitiesNumbermaxEidt.getText().toString();
        actstarttime = tvCreateActivitiesStartTimeEidt.getText().toString();
        actendtime = tvCreateActivitiesEndTimeEidt.getText().toString();
        actstartsignup = tvCreateActivitiesStartTimeEidt.getText().toString();
        actendsignup = tvCreateActivitiesSignEndTimeEidt.getText().toString();
        actintro = tvCreateActivitiesIntroduceEidt.getText().toString();

        if(TextUtils.isEmpty(actname)||TextUtils.isEmpty(actregion)||
                TextUtils.isEmpty(actaddress)||TextUtils.isEmpty(actphone)||
                TextUtils.isEmpty(actcost)||TextUtils.isEmpty(actnumbermax)||
                TextUtils.isEmpty(actstartsignup)||TextUtils.isEmpty(actendsignup)||
                TextUtils.isEmpty(actstarttime)||TextUtils.isEmpty(actendtime)||
                TextUtils.isEmpty(actintro)
                ){

            ToastUtils.show(this,"表单不能为空");
            return ;
        }
            try {
                mUploadingDialog = new UploadingDialogFragment();

                mUploadingDialog.show(mFragmentManager, FRAGMENT_TAG_UPLOADING_DIALOG);
                List<String> images = new ArrayList<String>();

                if(actminimage!=null && !"".equals(actminimage)){
                    images.add(actminimage);
                    UploadFileUtils.uploadMultiFiles(images, new ProgressListener() {
                        @Override
                        public void transferred(long transferredBytes, long totalBytes) {

                        }

                    }, new PublishDynamicActivity.MultiFileCompleteListener(images.size()) {
                        @Override
                        public void onCompleteUploading(boolean isComplete, List<String> imageUrls) {
                            mUploadingDialog.dismiss();

                            if (isComplete) {
                                mCover = imageUrls.get(0);
                                AppLog.e("","mCover:"+mCover);
                                uploadImages();
                            } else {
                                mUploadingDialog.dismiss();
                                Toast.makeText(AddCreateActivities.this, "发布失败!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    uploadImages();
                }



            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
    }



    private boolean isTimeSelected =false;
    private void showTime(final TextView textView){
        isTimeSelected = false;
        final Calendar calendar = Calendar.getInstance();
        //通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCreateActivities.this);
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_date_picker, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        //设置日期简略显示 否则详细显示 包括:星期周
        datePicker.setCalendarViewShown(false);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        long time = calendar.getTimeInMillis();
        datePicker.setMinDate(time);
        //初始化当前日期
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);
        //设置date布局
        builder.setView(view);
        builder.setTitle("设置日期信息");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                final String date = datePicker.getYear()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getDayOfMonth();
                //赋值后面闹钟使用
                int mYear = datePicker.getYear();
                int mMonth = datePicker.getMonth();
                int mDay = datePicker.getDayOfMonth();

                dialog.cancel();

                //自定义控件
                AlertDialog.Builder builder = new AlertDialog.Builder(AddCreateActivities.this);
                View view = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_time_picker, null);
                final TimePicker timePicker = (TimePicker) view.findViewById(R.id.time_picker);
                //初始化时间
                calendar.setTimeInMillis(System.currentTimeMillis());
                timePicker.setIs24HourView(true);

                timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
                timePicker.setCurrentMinute(Calendar.MINUTE);
                //设置time布局
                builder.setView(view);
                builder.setTitle("设置时间信息");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int mHour = timePicker.getCurrentHour();
                        int mMinute = timePicker.getCurrentMinute();
                        //时间小于10的数字 前面补0 如01:12:00
                        textView.setText(date + " " + new StringBuilder().append(mHour < 10 ? 0 + mHour : mHour).append(":")
                                .append(mMinute < 10 ? 0 + mMinute : mMinute).append(":00"));
                        dialog.cancel();
                        isTimeSelected = true;
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


}
