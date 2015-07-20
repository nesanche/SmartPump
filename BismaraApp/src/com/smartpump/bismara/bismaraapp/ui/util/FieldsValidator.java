package com.smartpump.bismara.bismaraapp.ui.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import android.widget.EditText;

public class FieldsValidator {

    /** Objeto patrón */
    private static Pattern pattern;
    /**  */
    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
    private static final String EMAIL_PATTERN = 
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean isEmpty(EditText v) {
        return StringUtils.isEmpty(v.getText().toString());
    }

    public static boolean passwordsMatch(EditText etPassword,
            EditText etRepeatPassword) {
        return (etPassword.getText().toString().equals(etRepeatPassword
                .getText().toString())) ? true : false;
    }

    public static boolean matchPattern(EditText etPassword) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(etPassword.getText().toString());
        return matcher.matches();
    }
    
    public static boolean mailPatternIsOk(EditText etMail) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(etMail.getText().toString());
        return matcher.matches();
    }
}
