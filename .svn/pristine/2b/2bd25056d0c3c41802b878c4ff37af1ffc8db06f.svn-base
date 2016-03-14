package com.yunchengke.app.dynamic.activity.homepage.dynamic;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yunchengke.app.R;
import com.yunchengke.app.dynamic.activity.BaseActivity;
import com.yunchengke.app.dynamic.adapter.AbsBaseAdapter;
import com.yunchengke.app.dynamic.view.MyGridView;
import com.yunchengke.app.dynamic.view.TitleActionBar;
import com.yunchengke.app.dynamic.xutils.HttpUtils;
import com.yunchengke.app.dynamic.xutils.exception.HttpException;
import com.yunchengke.app.dynamic.xutils.http.RequestParams;
import com.yunchengke.app.dynamic.xutils.http.ResponseInfo;
import com.yunchengke.app.dynamic.xutils.http.callback.RequestCallBack;
import com.yunchengke.app.dynamic.xutils.http.client.HttpRequest.HttpMethod;
import com.yunchengke.app.dynamic.xutils.util.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ReleaseDynamicActivity extends BaseActivity {
	StringBuffer b = new StringBuffer();
	char c = '"';
	private MyGridView gridView;
	/** 选择图片请求 */
	public static final int REQUEST_CODE_IMG_SELECTOR = 112;
	/** 照相请求 */
	public static final int REQUEST_CODE_CAMERA = 111;
	private String curPath;
	private List<String> list = new ArrayList<String>();
	AddImageAdapter addImageAdapter;
	private int removePosition;
	private EditText dynamic_msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_dynamic_release);
		TitleActionBar titleActionBar = getTitleActionBar();
		titleActionBar.getTopTitle().setBackgroundColor(getResources().getColor(R.color.red));
		TextView appTopTitle = titleActionBar.getAppTopTitle();
		appTopTitle.setText("发布动态");
		appTopTitle.getPaint().setFakeBoldText(true); 
		appTopTitle.setTextSize(14);
		titleActionBar.setAppTitleLeftButton(0, new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		titleActionBar.setAppTitleRightButton1("发送", 0, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String msg = dynamic_msg.getText().toString().trim();
				if(msg.equals(""))
				{
					Toast.makeText(getApplicationContext(), "动态输入内容不能为空!", Toast.LENGTH_SHORT).show();
				}else
				{
					String url = "http://c.ezhanyun.com/x_userlogic/yesicity2015/comm_Add";
					StringBuffer buffer = new StringBuffer();
					char c = '"';
					buffer.append("Field_DTJL").append(c).append(":").append(c).append("我是动态内容哦").append(c).append(",").append(c).append("X6_Product_PicList").append(c)
							.append(":").append(c).append("\\/X_UserUpload/day_151225/201512251534368361_small.jpg").append(c).append("}");
//					showProgressDialog(R.string.loading, false);
					HttpUtils http = new HttpUtils();
					RequestParams params = new RequestParams();
					params.addQueryStringParameter("UId", "yesicity2015");
					params.addQueryStringParameter("param", buffer.toString());

					http.send(HttpMethod.GET, url, params, new RequestCallBack<String>() {

						@Override
						public void onSuccess(ResponseInfo<String> responseInfo) {
							LogUtils.i(responseInfo.result);
//							hideProgressDialog();
							Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
							finish();
						}

						@Override
						public void onFailure(HttpException error, String msg) {
//							hideProgressDialog();
							LogUtils.i("result:" + msg);
						}

					});
				}
			}
		});
		dynamic_msg = (EditText) findViewById(R.id.dynamic_msg);
		gridView = (MyGridView) findViewById(R.id.add_grid);
		list.add("");
		addImageAdapter = new AddImageAdapter(getApplicationContext(), list);
		gridView.setAdapter(addImageAdapter);
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				if (arg2 > 8) {
					Toast.makeText(getApplicationContext(), "已达到最大上限", Toast.LENGTH_SHORT).show();
					return;
				}
				if (list.size() == 0)
					list.add("");
				if (arg2 == list.size() - 1) {
					removePosition = arg2;
					showPicDialog();
					addImageAdapter.notifyDataSetChanged();
					gridView.invalidate();
				}
				arg1.findViewById(R.id.add_del).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						list.remove(arg2);
						addImageAdapter.updateALLData(list);
						gridView.invalidate();
					}
				});
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_CODE_CAMERA) {
				// getSmallPic();
				Uri uri = Uri.fromFile(new File(curPath));
				// Uri uri = data.getData();
				// saveImages(uri);
				curPath = getPath(getApplicationContext(), uri);
				getSmallPic();

			} else if (requestCode == REQUEST_CODE_IMG_SELECTOR) {
				Uri uri = data.getData();
				curPath = getPath(getApplicationContext(), uri);
				getSmallPic();

				// addPic(uri);

			} else if (requestCode == 13) {
				// Uri uri = data.getData();
				// addPic(uri);

				Bitmap bmp = data.getParcelableExtra("data");
				curPath = saveBitmap(bmp);
				// list.add(curPath);
				getSmallPic();
			}
		}
		this.addImageAdapter.notifyDataSetChanged();
		this.gridView.invalidate();
	}

	// 上传图片上传原图
	private void getSmallPic() {
		list.remove(removePosition);
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				list.add(curPath);

			} else {
				list.add("");
			}
		}
		addImageAdapter.updateALLData(list);
	}

	public String saveBitmap(Bitmap bitmap) {
		String filePath = null;
		String fileName = System.currentTimeMillis() + ".jpg";
		File files = new File(getAppPath() + "/");
		if (!files.exists()) {
			files.mkdirs();
		}
		File f = new File(files, fileName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
			out.flush();
			out.close();
			filePath = f.toString();
		} catch (Exception e) {
			e.printStackTrace();
			filePath = null;
		}
		return filePath;
	}

	private void showPicDialog() {
		ArrayList<String> operate = new ArrayList<String>();
		operate.add("拍照");
		operate.add("图库");
		AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseDynamicActivity.this);
		builder.setTitle("图片来源");
		builder.setItems(operate.toArray(new String[operate.size()]), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					String fileName = System.currentTimeMillis() + ".jpg";
					File file = new File(getAppPath() + "/");
					if (!file.exists()) {
						file.mkdirs();
					}
					File filePath = new File(file, fileName);
					Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
					it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePath));
					curPath = filePath.toString();
					startActivityForResult(it, REQUEST_CODE_CAMERA);
				} else if (which == 1) {
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.setType("image/*");
					startActivityForResult(intent, REQUEST_CODE_IMG_SELECTOR);
				}
			}
		});
		builder.show();
	}

	public class AddImageAdapter extends AbsBaseAdapter<String> {

		protected AddImageAdapter(Context context, List<String> list) {
			super(context, list, R.layout.list_item_addimg);
		}

		@Override
		protected View newView(View convertView, String t, int position) {
			ViewHolder viewHodler = (ViewHolder) convertView.getTag();
			if (viewHodler == null) {
				viewHodler = new ViewHolder();
				viewHodler.imageViewdel = (ImageView) convertView.findViewById(R.id.add_del);
				viewHodler.imageView = (ImageView) convertView.findViewById(R.id.add_img);
				convertView.setTag(viewHodler);
			}
			if (t.equals("")) {
				viewHodler.imageView.setImageResource(R.drawable.icon_addpic);
				viewHodler.imageViewdel.setVisibility(View.GONE);
			} else {
				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(t, options);
				options.inSampleSize = calculateInSampleSize(options, 100, 100);
				options.inJustDecodeBounds = false;
				viewHodler.imageViewdel.setVisibility(View.VISIBLE);
				Bitmap bitmap = BitmapFactory.decodeFile(t, options);
				viewHodler.imageView.setImageBitmap(bitmap);// 加入处理过的图片
			}

			return convertView;
		}
	}
	
	/**
	 * 获取应用程序本地文件夹路径
	 */
	public static String getAppPath() {
		String path = getSdcardPath() + "gulang/clubs";
		if (isSdcard())
		{
			File dirFile = new File(path);
			
			if (!dirFile.exists())
			{
				dirFile.mkdirs();
			}
		}
		
		return path;
	}
	/**
	 * 判断是否有存储卡
	 */
	public static boolean isSdcard() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 获取存储卡路径
	 */
	public static String getSdcardPath() {
		if (isSdcard())
		{
			return Environment.getExternalStorageDirectory().getPath() + "/";
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 计算图片的缩放值
	 * 
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth)
		{
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	public class ViewHolder {
		public ImageView imageView;
		public ImageView imageViewdel;
	}

	public static String getPath(final Context context, final Uri uri) {

		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

		// DocumentProvider
		if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
			// ExternalStorageProvider
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}

				// TODO handle non-primary volumes
			}
			// DownloadsProvider
			else if (isDownloadsDocument(uri)) {

				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

				return getDataColumn(context, contentUri, null, null);
			}
			// MediaProvider
			else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}

				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };

				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		}// MediaStore (and general)
		else if ("content".equalsIgnoreCase(uri.getScheme())) {

			// Return the remote address
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();

			return getDataColumn(context, uri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;
	}

	/**
	 * Get the value of the data column for this Uri. This is useful for
	 * MediaStore Uris, and other file-based ContentProviders. 调用Android4.4系统相册
	 * 
	 * @param context
	 *            The context.
	 * @param uri
	 *            The Uri to query.
	 * @param selection
	 *            (Optional) Filter used in the query.
	 * @param selectionArgs
	 *            (Optional) Selection arguments used in the query.
	 * @return The value of the _data column, which is typically a file path.
	 */
	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri
	 *            The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}
