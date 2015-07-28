package com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.ByteArrayEntity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.entities.Doctor;
import com.smartpump.bismara.app.medic.ui.activities.ConfirmationOnHoldActivity;
import com.smartpump.bismara.app.medic.ui.util.FieldsValidator;
import com.smartpump.bismara.app.medic.util.EntityManager;

@SuppressWarnings("deprecation")
public class EnterLicenseFragment extends Fragment {

    private EditText etLicense;
    private Button btnFinish;
    private ProgressDialog progress;
    private final Context context = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_license,
                container, false);

        progress = new ProgressDialog(getActivity());
        etLicense = (EditText) rootView.findViewById(R.id.etLicenseNumber);
        btnFinish = (Button) rootView.findViewById(R.id.btnFinishRegistration);
        btnFinish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (FieldsValidator.isEmpty(etLicense)) {
                    Toast.makeText(getActivity(),
                            "Por favor ingrese su matrícula", Toast.LENGTH_LONG)
                            .show();
                    etLicense.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    new VerifyLicense().execute(etLicense.getText().toString());
                }
            }
        });

        return rootView;
    }

    /**
     * Metodo que inicializa una Activity sin funcionalidad si el registro fue
     * exitoso
     */
    private void onHoldActivity() {
        this.startActivity(new Intent(getActivity(),
                ConfirmationOnHoldActivity.class));
    }

    /**
     * Método que muestra un toast de error
     */
    private void errorToast() {
        Toast.makeText(getActivity(), "Error in registration",
                Toast.LENGTH_SHORT).show();
    }

    private void licenseExists() {
        Toast.makeText(getActivity(), "Su matrícula ya existe",
                Toast.LENGTH_LONG).show();
        etLicense.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user,
                0, R.drawable.ic_wrong, 0);
        return;
    }

    private void licenseIsOk() {
        etLicense.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user,
                0, R.drawable.ic_ok, 0);
        EntityManager.getInstance().getDoctor()
                .setRegistrationNumber(etLicense.getText().toString());
        new PostDoctor().execute();
    }

    /**
     * Clase que representa una AsyncTask que verifica que no exista la el
     * usuario
     * 
     * @author nesanche
     *
     */
    private class VerifyLicense extends AsyncTask<String, Void, String> {
        private String responseString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verificando...");
            progress.setMessage("Espere mientras verificamos que la matrícula no esté registrada.");
            progress.show();
        }

        @Override
        protected String doInBackground(String... regNumber) {
            String query = "http://bismara.elasticbeanstalk.com/rest/doctors/verifyRegNumber?regNumber="
                    + regNumber[0];
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
                licenseExists();
                return;
            }

            if (result.contains("true")) {
                licenseIsOk();
                return;
            }
        }
    }

    /**
     * Clase que representa una AsyncTask que registra un doctor.
     * 
     * @author nesanche
     *
     */
    private class PostDoctor extends AsyncTask<Void, Void, String> {
        private String responseString;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Registrando...");
            progress.setMessage("Espere mientras lo registramos en el sistema");
            progress.show();
        }

        @Override
        protected String doInBackground(Void... empty) {
            Doctor doctor = EntityManager.getInstance().getDoctor();
            String query = "http://bismara.elasticbeanstalk.com/rest/doctors/new";
            String doc = new GsonBuilder().create().toJson(doctor);
            ByteArrayEntity entity = null;
            try {
                entity = new ByteArrayEntity(doc.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            SyncHttpClient client = new SyncHttpClient();
            client.post(context, query, entity, "application/json",
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String response) {
                            Gson gson = new GsonBuilder().create();
                            JsonParser parser = new JsonParser();
                            JsonObject json = parser.parse(response)
                                    .getAsJsonObject();
                            Doctor doctor = gson.fromJson(json, Doctor.class);
                            EntityManager.getInstance().setDoctor(doctor);
                            onHoldActivity();
                            Log.d("Registration State",
                                    "Registered Successfuly");
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable error,
                                String content) {
                            errorToast();
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

    }
}
