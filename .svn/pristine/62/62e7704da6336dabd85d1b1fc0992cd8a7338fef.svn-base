package com.yunchengke.app.ui.activity.dynamic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompatSideChannelService;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upyun.block.api.listener.CompleteListener;
import com.upyun.block.api.listener.ProgressListener;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.CommonRequestResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.PublishDynamicRequest;
import com.yunchengke.app.ui.UIHelper;
import com.yunchengke.app.ui.adapter.AbsBaseAdapter;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.view.MyGridView;
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
 * 类名：PublishDynamicActivity <br/>
 * 描述：发布新闻
 * 创建时间：2016/01/28 23:12
 *
 * @author hanter
 * @version 1.0
 */
public class PublishDynamicActivity extends BaseActivity {
    public static final String FRAGMENT_TAG_UPLOADING_DIALOG = "uploading_dialog";

    @Bind(R.id.edt_publish_dynamic_content)
    EditText edtPublishDynamicContent;
    @Bind(R.id.gdv_publish_dynamic_image)
    GridView gdvPublishDynamicImage;

    private ArrayList<String> mImageFiles;
//    private ArrayList<String> mImageUrls;
    private PublishDynamicImagesAdapter mPublishDynamicImagesAdapter;

    private FragmentManager mFragmentManager;
    private UploadingDialogFragment mUploadingDialog;

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_publish_dynamic, parent);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        lytTitle.setBackgroundResource(R.color.dynamic_title_background);
        setTitle(R.string.publish_dynamic_title);
        setTitleLeft(R.string.back);
        setTitleRight(R.string.publish_dynamic_send);

        mFragmentManager = getSupportFragmentManager();

        mImageFiles = new ArrayList<String>();
//        mImageUrls = new ArrayList<String>();

        mPublishDynamicImagesAdapter = new PublishDynamicImagesAdapter(this, new OnNestedClickListener() {
            @Override
            public void onNestedClickListener(int position, View view) {
                try {
                    mImageFiles.remove(position);
                    mPublishDynamicImagesAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    // 捕获异常
                }
            }
        });
        mPublishDynamicImagesAdapter.setRows(mImageFiles);
        gdvPublishDynamicImage.setAdapter(mPublishDynamicImagesAdapter);

        gdvPublishDynamicImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mPublishDynamicImagesAdapter.setShowDelete(false);
                mPublishDynamicImagesAdapter.notifyDataSetChanged();

                if ( position == (parent.getCount() - 1)) {

                        pickImages();

                }
            }
        });

        gdvPublishDynamicImage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (position <= (parent.getCount() - 1)) {
                    mPublishDynamicImagesAdapter.setShowDelete(true);
                    mPublishDynamicImagesAdapter.notifyDataSetChanged();
                    return true;
                }

                return false;
            }
        });
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
                mPublishDynamicImagesAdapter.notifyDataSetChanged();

                AppLog.e("刷新数据", "个数：" + mImageFiles.size());
            }
        }else if(requestCode == SelectPicActivity.REQUEST_SELECT_PIC && resultCode == RESULT_OK && data != null){
            ArrayList<String> images = data.getStringArrayListExtra(SelectPicActivity.SELECT_PIC_LIST);
            for(String image:images){
                mImageFiles.add(image);
            }
            int start = mImageFiles.size() - 9;

            if (start < 0) {
                start = 0;
            }

            mImageFiles.subList(0, start).clear();
            mPublishDynamicImagesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.tv_base_title_right:
                uploadImages();
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    private void pickImages() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
           // startActivityForResult(intent, 1);
            UIHelper.gotoSelectPicActivity(this,mImageFiles.size());
        } catch (Exception e) {
            // 捕获异常
        }
    }

    private void uploadImages() {
        if (mImageFiles.size() <=0) {
            publishDynamic(null);
        }else
        {
            try {

                mUploadingDialog = new UploadingDialogFragment();

                mUploadingDialog.show(mFragmentManager, FRAGMENT_TAG_UPLOADING_DIALOG);

                UploadFileUtils.uploadMultiFiles(mImageFiles, new ProgressListener() {
                    @Override
                    public void transferred(long transferredBytes, long totalBytes) {

                    }

                }, new MultiFileCompleteListener(mImageFiles.size()) {
                    @Override
                    public void onCompleteUploading(boolean isComplete, List<String> imageUrls) {
                        mUploadingDialog.dismiss();

                        if (isComplete) {
                            publishDynamic(imageUrls);
                        } else {
                            mUploadingDialog.dismiss();
                            Toast.makeText(PublishDynamicActivity.this, "发布失败!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
        }
    }

    public void publishDynamic(List<String> imageUrls) {
        PublishDynamicRequest req = new PublishDynamicRequest(new HttpRequestListener<CommonRequestResult>() {

            @Override
            public void onFinishRequest() {
                super.onFinishRequest();
                mUploadingDialog.dismiss();

            }

            @Override
            public void onResponse(CommonRequestResult response) {
                super.onResponse(response);

                AppLog.e("上传完成");

                Toast.makeText(PublishDynamicActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        req.setRequestParams(edtPublishDynamicContent.getText().toString(), imageUrls);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public abstract static class MultiFileCompleteListener implements CompleteListener {

        private boolean mComplete = true;
        private int mFileCount;

        private List<String> mImageUrls;

        public MultiFileCompleteListener(int fileCount) {
            this.mFileCount = fileCount;
            this.mImageUrls = new ArrayList<String>();
        }

        @Override
        public void result(boolean isComplete, String result, String error) {

            AppLog.e(result);
            AppLog.e(error);

            JsonObject json = new JsonParser().parse(result).getAsJsonObject();
            int httpCode = json.get("code").getAsInt();

            if (isComplete && httpCode == 200) {
                mFileCount--;
                String imageUrl = HttpUrls.HTTP_URL_SCHEME + HttpUrls.HTTP_UPYUN_UPLOAD_URL
                        + json.get("path").getAsString();
                mImageUrls.add(imageUrl);
            } else {
                mFileCount--;
                mComplete = false;
            }

            if (mFileCount <= 0) {
                // 完成
                onCompleteUploading(mComplete, mImageUrls);
            }
        }

        public abstract void onCompleteUploading(boolean isComplete, List<String> imageUrls);
    }

    public static class PublishDynamicImagesAdapter extends AbsBaseAdapter<String> {

        final static int DYNAMIC_IMAGE_MAX_COUNT = 9;

        private boolean mShowDelete;
        private OnNestedClickListener mOnNestedClickListener;

        public PublishDynamicImagesAdapter(Context context, OnNestedClickListener onNestedClickListener) {
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_publish_dynamic_img, parent, false);
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
