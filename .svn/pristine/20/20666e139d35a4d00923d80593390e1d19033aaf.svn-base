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
public class PhoneValidator extends Validator {
    public PhoneValidator() {
        super("请输入正确的手机号！");
    }
    @Override
    public boolean isValid(EditText et) {
        return AccountCheckUtil.isMobileNum(et.getText().toString());
    }
}
