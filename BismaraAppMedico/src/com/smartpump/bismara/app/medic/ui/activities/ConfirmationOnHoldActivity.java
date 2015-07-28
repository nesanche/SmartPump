package com.smartpump.bismara.app.medic.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.entities.GCMRegistration;
import com.smartpump.bismara.app.medic.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.app.medic.util.EntityManager;

/**
 * Activity
 * 
 * @author nesanche
 *
 */
@SuppressWarnings("deprecation")
public class ConfirmationOnHoldActivity extends Activity {

    /** TextView que cierra la sesion */
    private TextView tvLogOut;

    private String registrationId;

    private GCMRegistration registration;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_on_hold);
        this.getActionBar().hide();

        tvLogOut = (TextView) findViewById(R.id.tvLogOut);
        tvLogOut.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        registration = new GCMRegistration();
        registration.setUser(EntityManager.getInstance().getDoctor().getUser());
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

    // public void registered() {
    // new PostRegistration().execute();
    // }

    // private class RegisterAsyncTask extends AsyncTask<Void, Void, String> {
    // @Override
    // protected String doInBackground(Void... params) {
    // String message = "";
    // try {
    // if (gcm == null) {
    // gcm = GoogleCloudMessaging
    // .getInstance(getApplicationContext());
    // }
    // registrationId = gcm
    // .register(ApplicationConstants.GCM_PROJECT_NUMBER);
    // message = "Device registered, registration ID="
    // + registrationId;
    // registration.setRegistrationId(registrationId);
    // Log.i("GCM", message);
    // } catch (Exception ex) {
    // message = "Error: " + ex.getMessage();
    // }
    // Log.e("GCM", message);
    // return message;
    // }
    //
    // @Override
    // protected void onPostExecute(String result) {
    // super.onPostExecute(result);
    // registered();
    // }
    //
    // }
    //
    // /**
    // * Clase que representa una AsyncTask que registra un doctor.
    // *
    // * @author nesanche
    // *
    // */
    // private class PostRegistration extends AsyncTask<Void, Void, String> {
    // private String responseString;
    //
    // @Override
    // protected void onPreExecute() {
    // super.onPreExecute();
    // progress.setTitle("Terminando...");
    // progress.setMessage("Espere mientras terminamos los últimos detalles de su registro.");
    // progress.show();
    // }
    //
    // @Override
    // protected String doInBackground(Void... empty) {
    // String query =
    // "http://bismara.elasticbeanstalk.com/rest/users/registerToGCM";
    // String registrationString = new GsonBuilder().create().toJson(
    // registration);
    // ByteArrayEntity entity = null;
    // try {
    // entity = new ByteArrayEntity(
    // registrationString.getBytes("UTF-8"));
    // } catch (UnsupportedEncodingException e) {
    // e.printStackTrace();
    // }
    // SyncHttpClient client = new SyncHttpClient();
    // client.post(ConfirmationOnHoldActivity.this, query, entity,
    // "application/json", new AsyncHttpResponseHandler() {
    // @Override
    // public void onSuccess(String response) {
    // responseString = response;
    // Log.d("GCM Registration State",
    // "Registered Successfuly");
    // }
    //
    // @Override
    // public void onFailure(int statusCode, Throwable error,
    // String content) {
    // responseString = "error";
    // if (statusCode == 404) {
    // Log.d("ERROR", "Error 404 not found");
    // } else if (statusCode == 500) {
    // Log.d("ERROR", "Error 500");
    // } else {
    // Log.d("ERROR", "Unknown error");
    // }
    // }
    // });
    //
    // return responseString;
    // }
    //
    // @Override
    // protected void onPostExecute(String result) {
    // super.onPostExecute(result);
    // progress.dismiss();
    // if (result.contains("error")) {
    // return;
    // }
    //
    // if (result.contains("true")) {
    // return;
    // }
    //
    // if (result.contains("false")) {
    // return;
    // }
    // }
    // }
}
