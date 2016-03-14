package com.yunchengke.app.utils;

import com.upyun.block.api.exception.UpYunException;
import com.upyun.block.api.listener.CompleteListener;
import com.upyun.block.api.listener.ProgressListener;
import com.upyun.block.api.main.UploaderManager;
import com.upyun.block.api.utils.UpYunUtils;
import com.yunchengke.app.app.AppLog;
import com.yunchengke.app.http.HttpUrls;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * 名称: UploadFileUtils <br/>
 * 描述: 上传文件使用工具，Upyun上传图片工具类 <br/>
 * 创建时间：2016/1/22 13:52
 *
 * @author wangmingshuo@ddsoucai.cn
 * @version 1.0
 */
public class UploadFileUtils {

    // 空间名
    public final static String BUCKET = HttpUrls.HTTP_UPYUN_UPLOAD_URL;

    // 表单密钥
    public final static String FORM_API_SECRET = "fOne2AFTslQkYHLji6avWxX69ZU=";

    // 本地文件路径
//    private String localFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.jpg";

    // 保存到又拍云的路径
//    String savePath = "/test70.png";

    public static void upload(String localFilePath, ProgressListener progressListener, CompleteListener completeListener) throws UpYunException, FileNotFoundException {
        File localFile = new File(localFilePath);

        UploaderManager uploaderManager = UploaderManager.getInstance(BUCKET);
        uploaderManager.setConnectTimeout(60);
        uploaderManager.setResponseTimeout(60);

        String savePath = "/yunchengke/images/" +  Long.toHexString(System.currentTimeMillis()) + "_" + localFile.getName();

        Map<String, Object> paramsMap = uploaderManager.fetchFileInfoDictionaryWith(localFile, savePath);

        AppLog.i("上传文件名：" + savePath);

        // signature & policy 建议从服务端获取
        String policyForInitial = UpYunUtils.getPolicy(paramsMap);
        String signatureForInitial = UpYunUtils.getSignature(paramsMap, FORM_API_SECRET);

        uploaderManager.upload(policyForInitial, signatureForInitial, localFile, progressListener, completeListener);
    }

    public static void uploadMultiFiles(List<String> localFileList, ProgressListener progressListener, CompleteListener completeListener) throws UpYunException, FileNotFoundException {

        UploaderManager uploaderManager = UploaderManager.getInstance(BUCKET);
        uploaderManager.setConnectTimeout(60);
        uploaderManager.setResponseTimeout(60);

        for (String filePath : localFileList) {
            File localFile = new File(filePath);

            String savePath = "/yunchengke/images/" +  Long.toHexString(System.currentTimeMillis()) + "_" + localFile.getName();

            Map<String, Object> paramsMap = uploaderManager.fetchFileInfoDictionaryWith(localFile, savePath);

            AppLog.i("上传文件名：" + savePath);

            // signature & policy 建议从服务端获取
            String policyForInitial = UpYunUtils.getPolicy(paramsMap);
            String signatureForInitial = UpYunUtils.getSignature(paramsMap, FORM_API_SECRET);

            uploaderManager.upload(policyForInitial, signatureForInitial, localFile, progressListener, completeListener);
        }

    }
}
