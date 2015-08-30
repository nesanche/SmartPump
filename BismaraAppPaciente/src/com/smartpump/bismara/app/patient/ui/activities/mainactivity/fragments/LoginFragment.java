package com.smartpump.bismara.app.patient.ui.activities.mainactivity.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.ConfirmationOnHoldActivity;
import com.smartpump.bismara.app.patient.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.MD5Encryptor;

public class LoginFragment extends Fragment {

    /** Constante para el estado 'Registrado' del médico */
    private static final int REGISTERED_CODE = 2;
    /** Constante para el estado 'Pediente' del médico */
    private static final int PENDING_CODE = 1;
    /** Editor de texto para el nombre de usuario */
    private EditText etUsername;
    /** Editor de texto para la contraseña */
    private EditText etPassword;
    /** BOtón para iniciar sesión */
    private Button btnSignIn;
    /** TextView que sugiere registrarse si no se tiene una cuenta */
    private TextView tvRegisterSuggestion;
    /**
     * Indicador de progreso mientras se verifican las credenciales para el
     * inicio de sesión
     */
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container,
                false);
        tvRegisterSuggestion = (TextView) rootView
                .findViewById(R.id.tvRegister);
        etUsername = (EditText) rootView.findViewById(R.id.etUser);
        etPassword = (EditText) rootView.findViewById(R.id.etPass);
        btnSignIn = (Button) rootView.findViewById(R.id.btnLogin);
        etPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
        progress = new ProgressDialog(getActivity());

        tvRegisterSuggestion.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                setCurrentItem();
            }
        });

        btnSignIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etUsername)
                        && FieldsValidator.isEmpty(etPassword)) {
                    Toast.makeText(getActivity(),
                            R.string.es_credentials_empty, Toast.LENGTH_SHORT)
                            .show();
                    etUsername.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etUsername)) {
                    Toast.makeText(getActivity(), R.string.es_username_empty,
                            Toast.LENGTH_SHORT).show();
                    etUsername.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etPassword)) {
                    Toast.makeText(getActivity(), R.string.es_password_empty,
                            Toast.LENGTH_SHORT).show();
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                new LoginTask().execute(etUsername.getText().toString(),
                        etPassword.getText().toString());
            }
        });

        return rootView;
    }

    /**
     * Metodo que inicia una activity sin funcionalidad si es que su
     * registracion está pendiente
     */
    private void pendingActivity() {
        this.startActivity(new Intent(getActivity(),
                ConfirmationOnHoldActivity.class));
        getActivity().finish();
    }

    /**
     * Clase que representa una AsyncTask para la verificacion de credenciales y
     * el inicio de sesion
     * 
     * @author nesanche
     *
     */
    class LoginTask extends AsyncTask<String, Void, String> {
        private String responseString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setMessage(getResources()
                    .getString(R.string.es_logging_in));
            progress.show();
        }

        @Override
        protected String doInBackground(String... credentials) {
            String query = "http://bismara.elasticbeanstalk.com/rest/doctors/getDoctor";
            SyncHttpClient client = new SyncHttpClient();
            client.addHeader("username", credentials[0]);
            client.addHeader("password", MD5Encryptor.md5(credentials[1]));
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

            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(result).getAsJsonObject();
            JsonObject user = parser.parse(json.get("user").toString())
                    .getAsJsonObject();
            JsonObject state = parser.parse(user.get("state").toString())
                    .getAsJsonObject();
            int stateId = state.get("id").getAsInt();
            if (stateId == REGISTERED_CODE) {
                Log.d("Registration State", "Registered and accepted");
            } else if (stateId == PENDING_CODE) {
                pendingActivity();
                Log.d("Registration State", "Pending registration");
            } else {
                Log.d("Registration State", stateId + "");
            }
        }
    }

    /**
     * Metodo que cambia la pestaña del viewpager que se esta observando
     */
    private void setCurrentItem() {
        ((MainActivity) getActivity()).setCurrentItem(1);
    }
}
