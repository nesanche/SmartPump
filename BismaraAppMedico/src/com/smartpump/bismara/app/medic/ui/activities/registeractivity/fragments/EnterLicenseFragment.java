package com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments;

import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.ui.activities.ConfirmationOnHoldActivity;
import com.smartpump.bismara.app.medic.ui.util.FieldsValidator;
import com.smartpump.bismara.app.medic.util.EntityManager;
import com.smartpump.bismara.requestmanager.RequestManager;
import com.smartpump.bismara.requestmanager.model.Doctor;

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
                    boolean licenseAvailability = false;
                    try {
                        licenseAvailability = new VerifyLicense().execute(
                                etLicense.getText().toString()).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    if (licenseAvailability) {
                        licenseIsOk();
                    } else {
                        licenseExists();
                    }
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
    private class VerifyLicense extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verificando...");
            progress.setMessage("Espere mientras verificamos que la matrícula no esté registrada.");
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... regNumber) {
            RequestManager requestManager = RequestManager.getInstance();
            return requestManager.checkRegNumberAvailability(getActivity(),
                    regNumber[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progress.hide();
        }
    }

    /**
     * Clase que representa una AsyncTask que registra un doctor.
     * 
     * @author nesanche
     *
     */
    private class PostDoctor extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Registrando...");
            progress.setMessage("Espere mientras lo registramos en el sistema");
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... empty) {
            RequestManager requestManager = RequestManager.getInstance();
            Doctor result = requestManager.registerDoctor(getActivity(),
                    EntityManager.getInstance().getDoctor());
            EntityManager.getInstance().setDoctor(result);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progress.hide();
            onHoldActivity();
        }

    }
}
