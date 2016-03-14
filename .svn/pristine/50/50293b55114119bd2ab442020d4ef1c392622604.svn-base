package imageloader;

import java.util.LinkedList;
import java.util.List;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import android.witch.gift.photo_select.R;

import bean.ImageAgent;
import bean.ImageAlbum;
import ui.SelectPicActivity;
import utils.CommonAdapter;


public class MyAdapter extends CommonAdapter<ImageAgent>
{

	
	private SelectPicActivity mActivity;
	/**
	 * 
	 * 用户选择的图片，存储为图片的完整路径
	 * 
	 */
	public static List<ImageAgent> mSelectedImage = new LinkedList<ImageAgent>();

	/**
	 * 
	 * 文件夹路径
	 * 
	 */
	private String mDirPath;
	private int mNumber;

	public MyAdapter(SelectPicActivity context, List<ImageAgent> mDatas, int itemLayoutId,
			String dirPath,int number)
	{
		super(context, mDatas, itemLayoutId);
		mNumber = number;
		mActivity = context;
		this.mDirPath = dirPath;
	}

	@Override
	public void convert(final utils.ViewHolder helper, final ImageAgent item,final int position)
	{
		//设置no_selected
		helper.setImageResource(R.id.id_item_select,
						R.drawable.picture_unselected);
		//设置图片
		helper.setImageByUrl(R.id.id_item_image, item.getOriginalImagePath());
		
		
		final ImageView mImageView = helper.getView(R.id.id_item_image);
		mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageAlbum photoAlbum = new ImageAlbum();
				photoAlbum.setPostion(position);
				for(ImageAgent str :mDatas){
					ImageAgent pa = new ImageAgent();
					pa.setOriginalImagePath(str.getOriginalImagePath());
					photoAlbum.getPhotoList().add(pa);
				}

			}
		});
		
		final ImageView mSelect = helper.getView(R.id.id_item_select);
		
		mImageView.setColorFilter(null);
		//设置ImageView的点击事件
		mSelect.setOnClickListener(new OnClickListener()
		{
			//选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v)
			{

				if(mNumber +mSelectedImage.size() >8){
					Toast.makeText(mContext, "你已经选择了9张图", Toast.LENGTH_SHORT).show();
					return;
				}
				// 已经选择过该图片
				if (mSelectedImage.contains(item))
				{
					mSelectedImage.remove(item);
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
				} else
				// 未选择该图片
				{
					mSelectedImage.add(item);
					mSelect.setImageResource(R.drawable.pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}
				mActivity.update();
			}
		});
		
		
		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(item))
		{
			
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
}
