package com.smartpump.bismara.app.patient.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.app.patient.util.ApplicationConstants;
import com.smartpump.bismara.app.patient.util.EntityManager;
import com.smartpump.bismara.requestmanager.RequestManager;
import com.smartpump.bismara.requestmanager.model.GCMRegistration;

/**
 * Activity
 * 
 * @author nesanche
 *
 */
public class ConfirmationOnHoldActivity extends Activity {

    /** TextView que cierra la sesion */
    private TextView tvLogOut;
    
    private String registrationId;
    private GoogleCloudMessaging gcm;
    private GCMRegistration registration;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_on_hold);
        this.getActionBar().hide();
        progress = new ProgressDialog(this);
        tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        tvLogOut.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
        registration = new GCMRegistration();
        registration.setUser(EntityManager.getInstance().getPatient().getUser());
        new RegisterAsyncTask().execute();
    }

    /**
     * Intent que vuelve a la pantalla de login
     */
    private void startLogin() {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    
    public void registered() {
        new PostRegistration().execute();
    }
    
    private class RegisterAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging
                            .getInstance(ConfirmationOnHoldActivity.this);
                }
                registrationId = gcm
                        .register(ApplicationConstants.GCM_PROJECT_NUMBER);
                registration.setRegistrationId(registrationId);
                Log.i("GCM", "Device registered, registration ID="
                        + registrationId);
            } catch (Exception ex) {
                Log.e("GCM", "Error: " + ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            registered();
        }

    }

    /**
     * Clase que representa una AsyncTask que registra un doctor.
     *
     * @author nesanche
     *
     */
    private class PostRegistration extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Terminando...");
            progress.setMessage("Espere mientras terminamos los últimos detalles de su registro.");
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... empty) {
            RequestManager requestManager = RequestManager.getInstance();
            requestManager.registerGcmRegistration(
                    ConfirmationOnHoldActivity.this, registration);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progress.hide();
        }
    }
}
