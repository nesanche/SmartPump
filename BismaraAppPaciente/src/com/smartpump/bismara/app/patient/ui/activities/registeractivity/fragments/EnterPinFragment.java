package com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments;

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

import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.ConfirmationOnHoldActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.EntityManager;
import com.smartpump.bismara.requestmanager.RequestManager;
import com.smartpump.bismara.requestmanager.model.Patient;
import com.smartpump.bismara.requestmanager.model.Pump;

public class EnterPinFragment extends Fragment {

    private EditText etPin;
    private Button btnFinish;
    private ProgressDialog progress;
    private final Context context = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_pin,
                container, false);

        progress = new ProgressDialog(getActivity());
        etPin = (EditText) rootView.findViewById(R.id.etPin);
        btnFinish = (Button) rootView.findViewById(R.id.btnFinishRegistration);
        btnFinish.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etPin)) {
                    //TODO HACER STRING
                    Toast.makeText(getActivity(), "Por favor ingrese un PIN",
                            Toast.LENGTH_LONG).show();
                    etPin.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                new VerifyPinVsMac().execute(etPin.getText().toString(), EntityManager.getInstance().getPatient().getTreatment().getPump().getMacAddress());
            }
        });

        return rootView;
    }

    /**
     * Metodo que inicializa una Activity sin funcionalidad si el registro fue
     * exitoso
     */
    private void onHoldActivity() {
        etPin.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
        this.startActivity(new Intent(getActivity(),
                ConfirmationOnHoldActivity.class));
    }

    /**
     * Método que muestra un toast de error
     */
    private void errorToast(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Clase que representa una AsyncTask que verifica que no exista la
     * Matrícula médica
     * 
     * @author nesanche
     *
     */
    class VerifyPinVsMac extends AsyncTask<String, Void, Pump> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verficando...");
            progress.setMessage("Espera mientras verificamos tus datos...");
            progress.show();
        }

        @Override
        protected Pump doInBackground(String... pin) {
            RequestManager requestManager = RequestManager.getInstance();
            Boolean matches = requestManager.checkPinMatchesWithMac(context, pin[0], pin[1]);
            Pump result = null;
            if(matches) {
                result = requestManager.getExistingPump(context, pin[0], pin[1]);
            } else {
                result = null;
                errorToast("El pin de verificación no coincide con el de su bomba. Verifique e intente nuevamente");
                this.cancel(true);
            }
            
            return result;
        }

        @Override
        protected void onPostExecute(Pump result) {
            super.onPostExecute(result);
            progress.dismiss();
            if(result != null) {
                EntityManager.getInstance().getPatient().getTreatment().setPump(result);
                new PostPatient().execute();
            } else {
                errorToast("Se produjo un error en la verificación de su bomba. Verifique e intente nuevamente.");
            }
        }
    }
    
    class PostPatient extends AsyncTask<Void, Void, Patient> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Registrando...");
            progress.setMessage("Espera mientras registramos tu usuario...");
            progress.show();
        }

        @Override
        protected Patient doInBackground(Void... nothing) {
            RequestManager requestManager = RequestManager.getInstance();
            Patient patient = requestManager.registerPatient(context, EntityManager.getInstance().getPatient());
            return patient;
        }

        @Override
        protected void onPostExecute(Patient result) {
            super.onPostExecute(result);
            progress.dismiss();
            if(EntityManager.getInstance().getPatient().getEmail().equals(result.getEmail())) {
                EntityManager.getInstance().setPatient(result);
                onHoldActivity();
            } else {
                errorToast("Se produjo un error en su registración");
            }
        }
    }
}
