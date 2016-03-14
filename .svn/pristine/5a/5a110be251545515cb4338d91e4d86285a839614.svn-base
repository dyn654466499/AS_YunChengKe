package com.yunchengke.app.ui.activity.group;

import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.upyun.block.api.listener.ProgressListener;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.CreateTopicResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.request.CreateTopicRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.activity.dynamic.PublishDynamicActivity;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.OnNestedClickListener;
import com.yunchengke.app.ui.widget.UploadingDialogFragment;
import com.yunchengke.app.utils.UploadFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ui.SelectPicActivity;

/**
 * 类名：CreateTopicActivity <br/>
 * 描述：创建话题
 * 创建时间：2016/01/10 12:05
 *
 * @author hanter
 * @version 1.0
 */
public class CreateTopicActivity extends BaseActivity {
    public static final String FRAGMENT_TAG_UPLOADING_DIALOG = "uploading";

    public static final String EXTRA_GROUP_ID = "EXTRA_GROUP_ID";

    @Bind(R.id.edt_create_topic_title)
    EditText edtCreateTopicTitle;
    @Bind(R.id.edt_create_topic_content)
    EditText edtCreateTopicContent;
    @Bind(R.id.gdv_create_topic_image)
    GridView gdvCreateTopicImage;


    private long mGroupId;
    private String mImageFileName;
    private String mTopicPicUrl;

    private FragmentManager mFragmentManager;
    private UploadingDialogFragment mUploadingDialog;

    private List<String> mImageFiles;
    private TopicImagesAdapter mTopicImagesAdapter;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_create_topic, parent);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(EXTRA_GROUP_ID, mGroupId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mGroupId = getIntent().getLongExtra(EXTRA_GROUP_ID, 0);
        } else {
            mGroupId = savedInstanceState.getLong(EXTRA_GROUP_ID, 0);
        }

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.create_topic);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.send_topic);

        mImageFiles = new ArrayList<String>();
        mTopicImagesAdapter = new TopicImagesAdapter(this, new OnNestedClickListener() {
            @Override
            public void onNestedClickListener(int position, View view) {
                try {
                    mImageFiles.remove(position);
                    mTopicImagesAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });

        mTopicImagesAdapter.setRows(mImageFiles);
        gdvCreateTopicImage.setAdapter(mTopicImagesAdapter);

        gdvCreateTopicImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AppLog.e("点击位置：" + position);

                if (position == (parent.getCount() - 1)) {
                    pickImages();
                }
            }
        });

        gdvCreateTopicImage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position <= (parent.getCount() - 1)) {
                    mTopicImagesAdapter.setShowDelete(true);
                    mTopicImagesAdapter.notifyDataSetChanged();
                    return true;
                }

                return false;
            }
        });
    }

    private void pickImages() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            UIHelper.gotoSelectPicActivity(this,mImageFiles.size());
            //startActivityForResult(intent, 1);
        } catch (Exception e) {
            // 捕获异常
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_base_title_right:
                uploadImages();
                break;

            case R.id.tv_base_title_left:
                finish();
                break;

            default:
                super.onClick(v);
                break;
        }
    }

    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    private void uploadImages() {
        if (mImageFiles.size() > 0) {
            try {

                mUploadingDialog = new UploadingDialogFragment();

                mUploadingDialog.show(mFragmentManager, FRAGMENT_TAG_UPLOADING_DIALOG);

                UploadFileUtils.uploadMultiFiles(mImageFiles, new ProgressListener() {
                    @Override
                    public void transferred(long transferredBytes, long totalBytes) {

                    }

                }, new PublishDynamicActivity.MultiFileCompleteListener(mImageFiles.size()) {
                    @Override
                    public void onCompleteUploading(boolean isComplete, List<String> imageUrls) {
                        mUploadingDialog.dismiss();

                        if (isComplete) {
                            createTopic(imageUrls);
                        } else {
                            mUploadingDialog.dismiss();
                            Toast.makeText(CreateTopicActivity.this, "上传图片失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
        } else {
            createTopic(new ArrayList<String>());
        }
    }

    private void createTopic(List<String> imageUrls) {
        CreateTopicRequest req = new CreateTopicRequest(new HttpRequestListener<CreateTopicResult>() {

            @Override
            public void onFinishRequest() {
                super.onFinishRequest();

                mUploadingDialog.dismiss();
            }

            @Override
            public void onResponse(CreateTopicResult response) {
                super.onResponse(response);

                try {
                    Toast.makeText(CreateTopicActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    if (CreateTopicResult.RESULT_SUCCESS.equals(response.getResultState())) {
                        setResult(RESULT_OK);
                        CreateTopicActivity.this.finish();
                    }
                } catch (Exception e) {
                    // 异常处理
                }

            }
        });

        req.setRequestParams(mGroupId, imageUrls, edtCreateTopicTitle.getText().toString(),
                edtCreateTopicContent.getText().toString());
        HttpRequestQueue.addToRequestQueue(req);
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
                    } while (cursor.moveToNext());

                } catch (Exception e) {
                    // 捕获异常
                } finally {
                    cursor.close();
                }

                int start = mImageFiles.size() - 9;

                if (start < 0) {
                    start = 0;
                }

                mImageFiles.subList(0, start).clear();
                mTopicImagesAdapter.notifyDataSetChanged();

                AppLog.e("刷新数据", "个数：" + mImageFiles.size());
            }
        }else if(requestCode == SelectPicActivity.REQUEST_SELECT_PIC && resultCode == RESULT_OK && data != null){

            ArrayList<String> images = data.getStringArrayListExtra(SelectPicActivity.SELECT_PIC_LIST);
            for(String image:images){
                mImageFiles.add(image);
            }
            AppLog.e("images:"+images.size());
            int start = mImageFiles.size() - 9;

            if (start < 0) {
                start = 0;
            }

            mImageFiles.subList(0, start).clear();
            mTopicImagesAdapter.notifyDataSetChanged();
        }
    }

    public static class TopicImagesAdapter extends AbsBaseAdapter<String> {

        final static int DYNAMIC_IMAGE_MAX_COUNT = 9;

        private boolean mShowDelete;
        private OnNestedClickListener mOnNestedClickListener;

        public TopicImagesAdapter(Context context, OnNestedClickListener onNestedClickListener) {
            super(context);
            this.mOnNestedClickListener = onNestedClickListener;
        }

        @Override
        public int getCount() {

            if (super.getCount() >= DYNAMIC_IMAGE_MAX_COUNT) {
                return super.getCount();
            } else {
                return super.getCount() + 1;
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_create_topic_img, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.deleteImage.setTag(holder.deleteImage.getId(), position);

            if (position == (getCount() - 1)  && super.getCount() < DYNAMIC_IMAGE_MAX_COUNT) { // 最后一个
                holder.addImage.setVisibility(View.VISIBLE);
                holder.image.setVisibility(View.INVISIBLE);
                holder.deleteImage.setVisibility(View.GONE);
            } else {

                if (mShowDelete) {
                    holder.deleteImage.setVisibility(View.VISIBLE);
                } else {
                    holder.deleteImage.setVisibility(View.GONE);
                }

                holder.deleteImage.setClickable(true);
                holder.deleteImage.setOnClickListener(mOnNestedClickListener);

                holder.addImage.setVisibility(View.INVISIBLE);
                holder.image.setVisibility(View.VISIBLE);

                Glide.with(mContext).load(getItem(position)).into(holder.image);
            }

            return convertView;
        }

        public boolean isShowDelete() {
            return mShowDelete;
        }

        public void setShowDelete(boolean showDelete) {
            mShowDelete = showDelete;
        }

        static class ViewHolder {
            @Bind(R.id.image)
            ImageView image;
            @Bind(R.id.add_image)
            TextView addImage;
            @Bind(R.id.delete_image)
            ImageView deleteImage;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

}
