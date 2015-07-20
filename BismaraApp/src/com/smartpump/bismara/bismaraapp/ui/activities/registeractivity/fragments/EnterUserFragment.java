package com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.fragments;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smartpump.bismara.bismaraapp.R;
import com.smartpump.bismara.bismaraapp.entities.User;
import com.smartpump.bismara.bismaraapp.entities.UserState;
import com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.bismaraapp.ui.util.FieldsValidator;
import com.smartpump.bismara.bismaraapp.util.EntityManager;
import com.smartpump.bismara.bismaraapp.util.MD5Encryptor;

public class EnterUserFragment extends Fragment {

    private Button btnNext;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etRepeatPassword;
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_user,
                container, false);

        progress = new ProgressDialog(getActivity());
        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        etUserName = (EditText) rootView.findViewById(R.id.etUsername);
        etPassword = (EditText) rootView.findViewById(R.id.etPass);
        etRepeatPassword = (EditText) rootView.findViewById(R.id.etRepeatPass);
        etPassword.setTypeface(Typeface.DEFAULT);
        etRepeatPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
        etRepeatPassword
                .setTransformationMethod(new PasswordTransformationMethod());

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etUserName)) {
                    Toast.makeText(getActivity(), R.string.en_username_empty,
                            Toast.LENGTH_SHORT).show();
                    etUserName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etPassword)) {
                    Toast.makeText(
                            getActivity(),
                            getResources()
                                    .getString(R.string.en_password_empty),
                            Toast.LENGTH_LONG).show();
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etRepeatPassword)) {
                    Toast.makeText(
                            getActivity(),
                            getResources().getString(
                                    R.string.en_repeat_password_empty),
                            Toast.LENGTH_LONG).show();
                    etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
                }
                
                User user = new User();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(MD5Encryptor.md5(etPassword.getText().toString()));
                user.setState(new UserState(1, "Pending"));
                EntityManager.getInstance().getDoctor().setUser(user);

                validateUsername();
            }
        });

        return rootView;
    }

    private void validateUsername() {
        new VerifyUserName().execute(etUserName.getText().toString());
    }

    private void userNameExists() {
        Toast.makeText(getActivity(), "Username exists", Toast.LENGTH_SHORT)
                .show();
        etUserName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail,
                0, R.drawable.ic_wrong, 0);
    }

    private void userNameIsOk() {

        if (!FieldsValidator.matchPattern(etPassword)) {
            Toast.makeText(getActivity(), R.string.en_pass_format,
                    Toast.LENGTH_SHORT).show();
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return;
        } else {
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }
        
        if(!FieldsValidator.passwordsMatch(etPassword, etRepeatPassword)) {
            Toast.makeText(getActivity(), R.string.en_passwords_dont_match,
                    Toast.LENGTH_SHORT).show();
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return;
        } else {
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }

        setCurrentItem();
    }

    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(2);
    }

    class VerifyUserName extends AsyncTask<String, Void, String> {
        private String responseString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verifying...");
            progress.setMessage("Wait while we verify your username is available");
            progress.show();
        }

        @Override
        protected String doInBackground(String... username) {
            String query = "http://bismara.elasticbeanstalk.com/rest/users/verifyUsername?username="
                    + username[0];
            SyncHttpClient client = new SyncHttpClient();
            client.get(query, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String response) {
                    responseString = response;
                }

                @Override
                public void onFailure(int statusCode, Throwable error,
                        String content) {
                    responseString = "error";
                    if (statusCode == 404) {
                        Log.d("ERROR", "Error 404 not found");
                    } else if (statusCode == 500) {
                        Log.d("ERROR", "Error 500");
                    } else {
                        Log.d("ERROR", "Unknown error");
                    }
                }
            });

            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progress.dismiss();
            if (result.contains("error")) {
                return;
            }

            if (result.contains("false")) {
                userNameExists();
                return;
            }

            if (result.contains("true")) {
                userNameIsOk();
                return;
            }
        }
    }
}