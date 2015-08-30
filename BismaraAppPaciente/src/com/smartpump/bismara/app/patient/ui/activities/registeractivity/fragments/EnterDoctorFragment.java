package com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments;

import android.app.ProgressDialog;
import android.content.Context;
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

import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.EntityManager;
import com.smartpump.bismara.requestmanager.RequestManager;
import com.smartpump.bismara.requestmanager.model.Doctor;

public class EnterDoctorFragment extends Fragment {

    private Doctor selectedDoctor;
    private EditText etLicense;
    private Button btnCheckDoctor;
    private Button btnNext;
    private ProgressDialog progress;
    private TextView tvDoctorName;
    private TextView tvDoctorLicense;
    private final Context context = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_doctor,
                container, false);

        tvDoctorName = (TextView) rootView.findViewById(R.id.tvDoctorName);
        tvDoctorLicense = (TextView) rootView.findViewById(R.id.tvDoctorLicense);
        progress = new ProgressDialog(getActivity());
        etLicense = (EditText) rootView.findViewById(R.id.etLicense);
        btnCheckDoctor = (Button) rootView.findViewById(R.id.btnCheckDoctor);
        btnCheckDoctor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etLicense)) {
                    //TODO HACER STRING
                    Toast.makeText(getActivity(), "Por favor ingrese la matrícula de su médico",
                            Toast.LENGTH_LONG).show();
                    etLicense.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etLicense.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                new VerifyDoctorLicense().execute(etLicense.getText().toString());
            }
        });
        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        btnNext.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                if(selectedDoctor == null) {
                    Toast.makeText(getActivity(), "Por favor ingrese la matrícula de su médico y verifiquelo para poder continuar",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                EntityManager.getInstance().getPatient().setDoctor(selectedDoctor);
                setCurrentItem();
            }
        });

        return rootView;
    }
    
    private void doctorIsOk(Doctor doctor) {
        selectedDoctor = doctor;
        tvDoctorName.setText(getActivity().getResources().getString(R.string.doctor_name) + " " + doctor.getLastName() + ", " + doctor.getFirstName());
        tvDoctorLicense.setText(getActivity().getResources().getString(R.string.doctor_license) + " " + doctor.getRegistrationNumber());
        tvDoctorName.setVisibility(View.VISIBLE);
        tvDoctorLicense.setVisibility(View.VISIBLE);
    }

    /**
     * Cambia de pestaña del ViewPager de la respectiva Activity
     */
    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(4);
    }

    /**
     * Método que muestra un toast de error
     */
    private void errorToast() {
        Toast.makeText(getActivity(), "Hubo un error recuperando su médico, verifique los datos en intentelo nuevamente",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Clase que representa una AsyncTask que verifica que no exista la
     * Matrícula médica
     * 
     * @author nesanche
     *
     */
    class VerifyDoctorLicense extends AsyncTask<String, Void, Doctor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verficando...");
            progress.setMessage("Espera mientras verificamos tus datos...");
            progress.show();
        }

        @Override
        protected Doctor doInBackground(String... license) {
            RequestManager manager = RequestManager.getInstance();
            Doctor result = manager.checkDoctorForPatientRegistration(context, license[0]);
            return result;
        }

        @Override
        protected void onPostExecute(Doctor result) {
            super.onPostExecute(result);
            progress.dismiss();
            if(result == null) {
                errorToast();
            } else {
                doctorIsOk(result);
            }
        }
    }
}
