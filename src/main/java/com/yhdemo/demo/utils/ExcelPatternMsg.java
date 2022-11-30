package com.yhdemo.demo.utils;

/**
 * excel正则表达式，以及错误信息
 * @author wyh
 * @date 2022/11/30 13:47
 */
public class ExcelPatternMsg {

    public static final String DECIMAL = "^[0-9]+\\.{0,1}[0-9]{0,2}$";
    public static final String DECIMAL_MSG = "只能输入整数或者小数";

    public static final String DATE1 =
            "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|"
                    +
                    "((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|"
                    +
                    "((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$";
    public static final String DATE1_MSG = "输入正确的日期格式：yyyy/MM/dd";

    public static final String EMAIL = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$";
    public static final String EMAIL_MSG = "请输入正确的邮箱";

    public static final String NUMBER_LETTER = "^[a-z0-9A-Z]+$";
    public static final String NUMBER_LETTER_MSG = "只能输入数字和字母";

    public static final String NUMBER = "^[0-9]*$";
    public static final String NUMBER_MSG = "只能输入数字";

    public static final String PASSWORD = "^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{8,18}$";
    public static final String PASSWORD_MSG = "1.密码必须由字母、数字组成，区分大小写\n" + "2.密码长度为8-18位";
}
