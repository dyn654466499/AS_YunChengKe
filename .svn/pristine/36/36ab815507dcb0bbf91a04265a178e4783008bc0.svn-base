package com.yunchengke.app.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 作者：Tinchy
 * 创建时间：2016/2/2 15:16
 * 描述：
 * 版本：1.0
 */
public class SoftInputUtil {
    public static void hideSoftInput(Context context, EditText editText){
        InputMethodManager imm =  (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }

}
