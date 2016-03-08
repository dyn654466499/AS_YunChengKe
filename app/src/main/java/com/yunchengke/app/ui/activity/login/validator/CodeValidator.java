package com.yunchengke.app.ui.activity.login.validator;

import android.widget.EditText;

import com.andreabaccega.formedittextvalidator.Validator;
import com.yunchengke.app.utils.AccountCheckUtil;

/**
 * 作者：Tinchy
 * 创建时间：2016/1/13 14:31
 * 描述：
 * 版本：1.0
 */
public class CodeValidator extends Validator {
    public CodeValidator() {
        super("请输入6位数字验证码");
    }

    @Override
    public boolean isValid(EditText editText) {
        return AccountCheckUtil.isVerfiyCode(editText.getText().toString());
    }
}
