package com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.fragments;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartpump.bismara.bismaraapp.R;

public class RegisterFragment extends Fragment {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private EditText etMail;
    private Button button;
    private Pattern pattern;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_register, container,
                false);

        etFirstName = (EditText) rootView.findViewById(R.id.etName);
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        etUserName = (EditText) rootView.findViewById(R.id.etUsername);
        etPassword = (EditText) rootView.findViewById(R.id.etPass);
        etRepeatPassword = (EditText) rootView.findViewById(R.id.etRepeatPass);
        etMail = (EditText) rootView.findViewById(R.id.etEmail);

        button = (Button) rootView.findViewById(R.id.btnContinue);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

        etPassword.setTypeface(Typeface.DEFAULT);
        etRepeatPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
        etRepeatPassword
                .setTransformationMethod(new PasswordTransformationMethod());

        pattern = Pattern.compile(PASSWORD_PATTERN);

        return rootView;
    }
    
    public boolean validateFields() {
        return validateFieldsAreNotEmpty() && checkUsername() && validatePasswords();
    }
    
    // Validation of fields
    public boolean validateFieldsAreNotEmpty() {
        if (StringUtils.isEmpty(etFirstName.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_firstname_empty),
                    Toast.LENGTH_LONG).show();
            etFirstName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etFirstName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
        }
        if (StringUtils.isEmpty(etLastName.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_lastname_empty),
                    Toast.LENGTH_LONG).show();
            etLastName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etLastName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
        }
        if (StringUtils.isEmpty(etUserName.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_username_empty),
                    Toast.LENGTH_LONG).show();
            etUserName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etUserName.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
        }
        if (StringUtils.isEmpty(etPassword.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_password_empty),
                    Toast.LENGTH_LONG).show();
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }
        if (StringUtils.isEmpty(etRepeatPassword.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_repeat_password_empty),
                    Toast.LENGTH_LONG).show();
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }
        if (StringUtils.isEmpty(etMail.getText().toString())) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_mail_empty),
                    Toast.LENGTH_LONG).show();
            etMail.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etMail.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_mail, 0, R.drawable.ic_ok, 0);
        }

        return true;
    }
    
    private boolean validatePasswords() {
        if(!passMatchPattern()) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_pass_format),
                    Toast.LENGTH_LONG).show();
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }
        
        if(!passwordsMatch()) {
            Toast.makeText(getActivity(),
                    getResources().getString(R.string.en_passwords_dont_match),
                    Toast.LENGTH_LONG).show();
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return false;
        } else {
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }
        
        return true;
    }

    private boolean checkUsername() {
        // TODO check if username is available via rest
        return true;
    }
    // Password Validation
    private boolean passwordsMatch() {
        return (etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) ? true : false;
    }
    
    private boolean passMatchPattern() {
        Matcher matcher = pattern.matcher(etPassword.getText().toString());
        return matcher.matches();
    }
}
