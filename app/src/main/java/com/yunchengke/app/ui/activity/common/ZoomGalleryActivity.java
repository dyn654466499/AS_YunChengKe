package com.yunchengke.app.ui.activity.common;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yunchengke.app.R;
import com.yunchengke.app.ui.base.AbsBaseActivity;
import com.yunchengke.app.ui.view.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：ZoomGalleryActivity <br/>
 * 描述：可以点击放大缩小的图片浏览
 * 创建时间：2016/01/31 1:12
 *
 * @author hanter
 * @version 1.0
 */
public class ZoomGalleryActivity extends AbsBaseActivity implements View.OnClickListener{

    public static final String EXTRA_IMAGE_LIST = "EXTRA_IMAGE_LIST";
    public static final String EXTRA_IMAGE_POSITION = "EXTRA_IMAGE_POSITION";

    private int mPosition;
    private ArrayList<String> mImages;
    private ImagesPagerAdapter mImagesPagerAdapter;
    private ViewPager mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setBackgroundResource(R.color.black);

        if (savedInstanceState == null) {
            mPosition = getIntent().getIntExtra(EXTRA_IMAGE_POSITION, 0);
            mImages = getIntent().getStringArrayListExtra(EXTRA_IMAGE_LIST);
        } else {
            mPosition = savedInstanceState.getInt(EXTRA_IMAGE_POSITION);
            mImages = savedInstanceState.getStringArrayList(EXTRA_IMAGE_LIST);
        }

        setContentView(R.layout.activity_zoom_gallery);
        initViews();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList(EXTRA_IMAGE_LIST, mImages);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        mContent = (ViewPager) findViewById(R.id.vpg_zoom_gallery_content);
        mImagesPagerAdapter = new ImagesPagerAdapter(mImages);
        mContent.setAdapter(mImagesPagerAdapter);
        mContent.setCurrentItem(mPosition);
    }


    public class ImagesPagerAdapter extends PagerAdapter {
        private List<String> mImageUrls;

        public ImagesPagerAdapter(List<String> imageUrls) {
            this.mImageUrls = imageUrls;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            if (mImageUrls != null) {
                return mImageUrls.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(container.getContext());
            view.enable();
            container.addView(view, 0);

            String imageUrl = mImageUrls.get(position);
            Glide.with(container.getContext()).load(imageUrl).into(view);
            view.setOnClickListener(ZoomGalleryActivity.this);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
