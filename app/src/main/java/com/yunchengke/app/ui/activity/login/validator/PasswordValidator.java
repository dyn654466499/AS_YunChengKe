package com.yunchengke.app.ui.activity.login.validator;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;
import com.yunchengke.app.utils.AccountCheckUtil;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 14:30
 * 描述：
 * 版本：1.0
 */
public class PasswordValidator extends Validator {
    public PasswordValidator() {
        super("密码由6到16位数字和字母组成，不包含特殊字符");
    }

    @Override
    public boolean isValid(EditText editText) {
        if (editText.getText().toString().length() > 16 || editText.getText().toString().length() < 6) {

            return false;
        }
        return AccountCheckUtil.isPasswordOnly(editText.getText().toString());
    }
}
