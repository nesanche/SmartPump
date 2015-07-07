package com.smartpump.bismara.bismaraapp.ui.activities.mainactivity.fragments;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smartpump.bismara.bismaraapp.R;
import com.smartpump.bismara.bismaraapp.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.bismaraapp.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.bismaraapp.ui.util.FieldsValidator;
import com.smartpump.bismara.bismaraapp.util.EntityManager;

public class EnterMailFragment extends Fragment {

    private Button btnStartRegistration;
    private EditText etMail;
    private ProgressDialog progress;
    private TextView tvHaveAccount;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_mail,
                container, false);

        progress = new ProgressDialog(getActivity());
        tvHaveAccount = (TextView) rootView.findViewById(R.id.tvHaveAccount);
        btnStartRegistration = (Button) rootView
                .findViewById(R.id.btnStartRegistration);
        etMail = (EditText) rootView.findViewById(R.id.etEmail);
        
        tvHaveAccount.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                setCurrentItem();
            }
        });
        
        btnStartRegistration.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!FieldsValidator.isEmpty(etMail)) {
                    if (FieldsValidator.mailPatternIsOk(etMail)) {
                        verifyMailExists();
                    } else {
                        Toast.makeText(getActivity(),
                                "El formato del mail es inválido",
                                Toast.LENGTH_SHORT).show();
                        etMail.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
                    }
                } else {
                    Toast.makeText(getActivity(), "Please enter a valid mail",
                            Toast.LENGTH_SHORT).show();
                    etMail.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
                }
            }
        });
        ;

        return rootView;
    }

    private void verifyMailExists() {
        new VerifyMail().execute(etMail.getText().toString());
    }

    private void startRegistrationFlow() {
        etMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail, 0,
                R.drawable.ic_ok, 0);
        EntityManager.getInstance().getDoctor().setEmail(etMail.getText().toString());
//        getActivity().getWindow().setExitTransition(new Explode());
//        Intent intent = new Intent(getActivity(), RegisterActivity.class);
//        getActivity().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
        this.startActivity(new Intent(getActivity(), RegisterActivity.class));
        getActivity().finish();
    }

    private void errorMailExists() {
        Toast.makeText(getActivity(), "Mail already exists", Toast.LENGTH_SHORT)
                .show();
        etMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail, 0,
                R.drawable.ic_wrong, 0);
    }

    class VerifyMail extends AsyncTask<String, Void, String> {
        private String responseString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verifying...");
            progress.setMessage("Wait while we verify your email");
            progress.show();
        }

        @Override
        protected String doInBackground(String... email) {
            String query = "http://bismara.elasticbeanstalk.com/rest/doctors/verifyEmail?email="
                    + email[0];
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
                errorMailExists();
                return;
            }

            if (result.contains("true")) {
                startRegistrationFlow();
                return;
            }
        }
    }
    
    private void setCurrentItem() {
        ((MainActivity) getActivity()).setCurrentItem(0);
    }
}
