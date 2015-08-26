package com.smartpump.bismara.app.patient.ui.activities.mainactivity.fragments;

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
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.EntityManager;
import com.smartpump.bismara.requestmanager.RequestConstants;
import com.smartpump.bismara.requestmanager.RequestManager;


public class EnterMailFragment extends Fragment {

    /** Botón que comienza la registración */
    private Button btnStartRegistration;
    /** EditText donde se ingresa el mail */
    private EditText etMail;
    /** Indicador de progreso mientras se verifica el mail */
    private ProgressDialog progress;
    /** Texto que vuelve a la pantalla de login en caso de ya tener una cuenta */
    private TextView tvHaveAccount;
    
    private Context context = getActivity();
    
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
                    Toast.makeText(getActivity(),
                            "Por favor ingrese un mail válido",
                            Toast.LENGTH_SHORT).show();
                    etMail.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
                }
            }
        });
        ;

        return rootView;
    }

    /**
     * Metodo que ejecuta el asynctask para verificar la existencia del mail
     * contra la REST API
     */
    private void verifyMailExists() {
        new VerifyMail().execute(etMail.getText().toString());
    }

    /**
     * Metodo que inicializa la activity de registro
     */
    private void startRegistrationFlow() {
        etMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail, 0,
                R.drawable.ic_ok, 0);
        EntityManager.getInstance().getPatient()
                .setEmail(etMail.getText().toString());
        this.startActivity(new Intent(getActivity(), RegisterActivity.class));
        getActivity().finish();
    }

    /**
     * Método que refresca la interfaz en caso de que el mail exista
     */
    private void errorMailExists() {
        
        Toast.makeText(getActivity(), "Mail already exists", Toast.LENGTH_SHORT)
                .show();
        etMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail, 0,
                R.drawable.ic_wrong, 0);
    }

    /**
     * Clase que representa una AsyncTask para hacer consultas http y refrescar
     * la UI
     * 
     * @author nesanche
     *
     */
    class VerifyMail extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verificando...");
            progress.setMessage("Espere mientras verificamos su email");
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... email) {           
            RequestManager manager = RequestManager.getInstance();
            return manager.checkEmailAvailability(context, RequestConstants.PATIENT, email[0]);
        }

        @Override
        protected void onPostExecute(Boolean isAvailable) {
            super.onPostExecute(isAvailable);
            progress.dismiss();
            if (isAvailable) {
                startRegistrationFlow();
                return;
            } else {
                errorMailExists();
            }
        }
    }

    /**
     * Cambia de pestaña del ViewPager de la respectiva Activity
     */
    private void setCurrentItem() {
        ((MainActivity) getActivity()).setCurrentItem(0);
    }
}
