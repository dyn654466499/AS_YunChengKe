package com.yunchengke.app.ui.activity.group;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.upyun.block.api.listener.CompleteListener;
import com.upyun.block.api.listener.ProgressListener;
import com.yunchengke.app.R;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.bean.CreateGroupResult;
import com.yunchengke.app.http.HttpRequestListener;
import com.yunchengke.app.http.HttpRequestQueue;
import com.yunchengke.app.http.HttpUrls;
import com.yunchengke.app.http.request.CreateGroupRequest;
import com.yunchengke.app.ui.base.BaseActivity;
import com.yunchengke.app.ui.widget.UploadingDialogFragment;
import com.yunchengke.app.utils.UploadFileUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名：CreateGroupActivity <br/>
 * 描述：创建小组
 * 创建时间：2016/01/10 12:01
 *
 * @author hanter
 * @version 1.0
 */
public class CreateGroupActivity extends BaseActivity {
    public final static int REQUEST_CREATE_GROUP = 1;

    public static final String FRAGMENT_TAG_UPLOADING_DIALOG = "uploading_dialog";

    public final static int REQUEST_PICK_IMAGE = 1;

    public final static String EXTRA_CATEGORY_ID = "EXTRA_CATEGORY_ID";
    public final static String EXTRA_CATEGORY_NAME = "EXTRA_CATEGORY_NAME";

    @Bind(R.id.btn_create_group_create)
    Button btnCreateGroupCreate;
    @Bind(R.id.tv_create_group_category_name)
    TextView tvCreateGroupCategoryName;
    @Bind(R.id.edt_create_group_name)
    EditText edtCreateGroupName;
    @Bind(R.id.iv_create_group_portrait)
    ImageView ivCreateGroupPortrait;
    @Bind(R.id.iv_create_group_image)
    ImageView ivCreateGroupImage;
    @Bind(R.id.btn_create_group_add_portrait)
    Button btnCreateGroupAddPortrait;
    @Bind(R.id.edt_create_group_introduction_content)
    EditText edtCreateGroupIntroductionContent;
    @Bind(R.id.rgp_create_group_type)
    RadioGroup rgpCreateGroupType;
    @Bind(R.id.lyt_create_group_logo)
    RelativeLayout lytCreateGroupLogo;
    @Bind(R.id.lyt_create_group_image)
    RelativeLayout lytCreateGroupImage;

    private int mCategoryId;

    private String mCategoryName;
    private String mImageFileName;
    private String mImageFileName2;
    private String mGroupLogoUrl;
    private String mGroupImageUrl;


    private FragmentManager mFragmentManager;
    private UploadingDialogFragment mUploadingDialog;


    @Override
    protected void initViews(Bundle savedInstanceState) {
        super.initViews(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mCategoryId = getIntent().getIntExtra(EXTRA_CATEGORY_ID, 0);
            mCategoryName = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        } else {
            mCategoryId = savedInstanceState.getInt(EXTRA_CATEGORY_ID, 0);
            mCategoryName = savedInstanceState.getString(EXTRA_CATEGORY_NAME);
        }

        lytTitle.setBackgroundResource(R.color.group_background);
        setTitle(R.string.create_group);
        setTitleLeft(R.string.back);

        rgpCreateGroupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rd_create_group_private_group) {
                    lytCreateGroupLogo.setVisibility(View.GONE);
                    lytCreateGroupImage.setVisibility(View.GONE);
                }else{
                    lytCreateGroupLogo.setVisibility(View.VISIBLE);
                    lytCreateGroupImage.setVisibility(View.VISIBLE);
                }
            }
        });

        tvCreateGroupCategoryName.setText(mCategoryName);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(EXTRA_CATEGORY_NAME, mCategoryName);
        outState.putInt(EXTRA_CATEGORY_ID, mCategoryId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        return inflate(R.layout.activity_create_group, parent);
    }

    @Override
    @OnClick({R.id.btn_create_group_create, R.id.btn_create_group_add_portrait,R.id.btn_create_group_add_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_base_title_left:
                finish();
                break;

            case R.id.btn_create_group_create:

                if (rgpCreateGroupType.getCheckedRadioButtonId() == R.id.rd_create_group_private_group) {
                    // 直接创建私密小组
                    createGroup();
                } else {
                    uploadImage();
                }
                break;

            case R.id.btn_create_group_add_portrait:
                pickPicture();
                break;
            case R.id.btn_create_group_add_image:
                pickImage();
                break;

            default:
                super.onClick(view);
                break;
        }
    }

    private void uploadImage() {
        // 首先上传图片

        String introduction = edtCreateGroupIntroductionContent.getText().toString();
        String groupName = edtCreateGroupName.getText().toString();

        if (TextUtils.isEmpty(groupName)) {
            Toast.makeText(CreateGroupActivity.this, "小组名不能为空！", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(introduction)) {
            Toast.makeText(CreateGroupActivity.this, "小组简介不能为空！", Toast.LENGTH_SHORT).show();
        }

        if (!TextUtils.isEmpty(mImageFileName)) {
            try {

                mUploadingDialog = new UploadingDialogFragment();

                mUploadingDialog.show(mFragmentManager, FRAGMENT_TAG_UPLOADING_DIALOG);

                UploadFileUtils.upload(mImageFileName, new ProgressListener() {
                    @Override
                    public void transferred(long transferredBytes, long totalBytes) {

                    }

                }, new CompleteListener() {
                    @Override
                    public void result(boolean isComplete, String result, String error) {
                        if (isComplete) {
                            AppLog.e("创建小组", result);

                            JsonObject json = new JsonParser().parse(result).getAsJsonObject();

                            int httpCode = json.get("code").getAsInt();

                            if (httpCode == 200) {
                                mGroupLogoUrl = HttpUrls.HTTP_URL_SCHEME + HttpUrls.HTTP_UPYUN_UPLOAD_URL + json.get("path").getAsString();
                                uploadImage2();
                            } else {
                                mUploadingDialog.dismiss();
                            }

                        } else {
                            AppLog.i(error);
                            mUploadingDialog.dismiss();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
        }

    }

    private void uploadImage2() {
        // 首先上传图片

        String introduction = edtCreateGroupIntroductionContent.getText().toString();
        String groupName = edtCreateGroupName.getText().toString();

        if (TextUtils.isEmpty(groupName)) {
            Toast.makeText(CreateGroupActivity.this, "小组名不能为空！", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(introduction)) {
            Toast.makeText(CreateGroupActivity.this, "小组简介不能为空！", Toast.LENGTH_SHORT).show();
        }

        if (!TextUtils.isEmpty(mImageFileName2)) {
            try {

                mUploadingDialog = new UploadingDialogFragment();

                mUploadingDialog.show(mFragmentManager, FRAGMENT_TAG_UPLOADING_DIALOG);

                UploadFileUtils.upload(mImageFileName2, new ProgressListener() {
                    @Override
                    public void transferred(long transferredBytes, long totalBytes) {

                    }

                }, new CompleteListener() {
                    @Override
                    public void result(boolean isComplete, String result, String error) {
                        if (isComplete) {
                            AppLog.e("创建小组", result);

                            JsonObject json = new JsonParser().parse(result).getAsJsonObject();

                            int httpCode = json.get("code").getAsInt();

                            if (httpCode == 200) {
                                mGroupImageUrl = HttpUrls.HTTP_URL_SCHEME + HttpUrls.HTTP_UPYUN_UPLOAD_URL + json.get("path").getAsString();
                                createGroup();
                            } else {
                                mUploadingDialog.dismiss();
                            }

                        } else {
                            AppLog.i(error);
                            mUploadingDialog.dismiss();
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                mUploadingDialog.dismiss();
            }
        }

    }

    /**
     * 创建小组
     */
    private void createGroup() {
        String introduction = edtCreateGroupIntroductionContent.getText().toString();
        String groupName = edtCreateGroupName.getText().toString();

        if (rgpCreateGroupType.getCheckedRadioButtonId() == R.id.rd_create_group_private_group) {
            if (!TextUtils.isEmpty(mCategoryName) && !TextUtils.isEmpty(introduction)
                    && !TextUtils.isEmpty(groupName)) {
                createGroupRequest(groupName, introduction);
            } else {
                Toast.makeText(CreateGroupActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (!TextUtils.isEmpty(mGroupLogoUrl) && !TextUtils.isEmpty(mCategoryName)
                    && !TextUtils.isEmpty(introduction)
                    && !TextUtils.isEmpty(groupName)) {
                createGroupRequest(groupName, introduction);
            } else {
                Toast.makeText(CreateGroupActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void createGroupRequest(String groupName, String introduction) {
        CreateGroupRequest req = new CreateGroupRequest(new HttpRequestListener<CreateGroupResult>() {
            @Override
            public void onFinishRequest() {
                super.onFinishRequest();
                mUploadingDialog.dismiss();
            }

            @Override
            public void onResponse(CreateGroupResult response) {
                super.onResponse(response);

                try {
                    Toast.makeText(CreateGroupActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                    if (CreateGroupResult.RESULT_SUCCESS.equals(response.getResultState())) {
                        setResult(RESULT_OK);
                        CreateGroupActivity.this.finish();
                    }
                } catch (Exception e) {
                    // 异常处理
                }
            }
        });

        int id = rgpCreateGroupType.getCheckedRadioButtonId();

        String type;

        switch (id) {
            case R.id.rd_create_group_public_group:
                type = "";
                break;
            case R.id.rd_create_group_private_group:
            default:
                type = "3";
                break;
        }

        req.setRequestParams(mCategoryId, mCategoryName, type, groupName, introduction, mGroupLogoUrl,mGroupImageUrl);
        HttpRequestQueue.addToRequestQueue(req);
    }

    public void pickPicture() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            AppLog.e(e.getMessage());
        }
    }
    public void pickImage() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,2);
        } catch (Exception e) {
            AppLog.e(e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                ivCreateGroupPortrait.setImageURI(data.getData());
                ivCreateGroupPortrait.setVisibility(View.VISIBLE);

                File file = new File(picturePath);

                mImageFileName = picturePath;

                AppLog.e("CreateGroupActivity", Long.toHexString(System.currentTimeMillis()) + file.getName());
            }
        }else  if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                ivCreateGroupImage.setImageURI(data.getData());
                ivCreateGroupImage.setVisibility(View.VISIBLE);

                File file = new File(picturePath);

                mImageFileName2 = picturePath;

                AppLog.e("CreateGroupActivity", Long.toHexString(System.currentTimeMillis()) + file.getName());
            }
        }
    }

}
