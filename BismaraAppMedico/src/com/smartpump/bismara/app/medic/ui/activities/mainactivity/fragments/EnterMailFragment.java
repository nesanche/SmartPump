package com.smartpump.bismara.app.medic.ui.activities.mainactivity.fragments;

import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.ui.activities.mainactivity.MainActivity;
import com.smartpump.bismara.app.medic.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.medic.ui.util.FieldsValidator;
import com.smartpump.bismara.app.medic.util.EntityManager;
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
    
    private static final int DOCTOR = 1;

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
        Boolean availability = false;
        try {
            availability = new VerifyMail()
                    .execute(etMail.getText().toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (availability) {
            startRegistrationFlow();
        } else {
            errorMailExists();
        }

    }

    /**
     * Metodo que inicializa la activity de registro
     */
    private void startRegistrationFlow() {
        etMail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail, 0,
                R.drawable.ic_ok, 0);
        EntityManager.getInstance().getDoctor()
                .setEmail(etMail.getText().toString());
        this.startActivity(new Intent(getActivity(), RegisterActivity.class));
        getActivity().finish();
    }

    /**
     * Método que refresca la interfaz en caso de que el mail exista
     */
    private void errorMailExists() {
        Toast.makeText(getActivity(), "El mail ingresado ya existe",
                Toast.LENGTH_SHORT).show();
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
    private class VerifyMail extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verificando...");
            progress.setMessage("Espere mientras verificamos su email");
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... email) {
            RequestManager requestManager = RequestManager.getInstance();
            return requestManager.checkEmailAvailability(getActivity(), DOCTOR,
                    email[0]);

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progress.hide();
        }

    }

    /**
     * Cambia de pestaña del ViewPager de la respectiva Activity
     */
    private void setCurrentItem() {
        ((MainActivity) getActivity()).setCurrentItem(0);
    }
}
