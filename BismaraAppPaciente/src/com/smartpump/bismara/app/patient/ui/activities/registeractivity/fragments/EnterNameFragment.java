package com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.EntityManager;

@SuppressLint("SimpleDateFormat")
public class EnterNameFragment extends Fragment {
    /** Boton que lleva al siguiente paso de registracio */
    private Button btnNext;

    /**
     * Editores de texto para esta fase de registración
     */
    private EditText etName;
    private EditText etLastName;
    private DatePicker dpBirth;
    private RadioGroup radioSex;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_name, container, false);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etName.requestFocus();
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);

        radioSex = (RadioGroup) rootView.findViewById(R.id.radioSex);
        
        dpBirth = (DatePicker) rootView.findViewById(R.id.dpBirthDate);
        dpBirth.setMaxDate(new Date().getTime());

        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        btnNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etName) && FieldsValidator.isEmpty(etLastName)) {
                    // TODO Create string
                    Toast.makeText(getActivity(), "Nombre y apellido no pueden estar vacios", Toast.LENGTH_SHORT)
                            .show();
                    etName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etName)) {
                    Toast.makeText(getActivity(), "Por favor ingrese su nombre", Toast.LENGTH_SHORT).show();
                    etName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                if (FieldsValidator.isEmpty(etLastName)) {
                    Toast.makeText(getActivity(), "Por favor ingrese su apellido", Toast.LENGTH_SHORT).show();
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }
                
                if(radioSex.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getActivity(), "Por favor seleccione su sexo", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if(radioSex.getCheckedRadioButtonId() == R.id.radioFem) {
                        EntityManager.getInstance().getPatient().setSex("F");
                    }
                    
                    if(radioSex.getCheckedRadioButtonId() == R.id.radioMasc) {
                        EntityManager.getInstance().getPatient().setSex("M");
                    }
                }
                
                
                EntityManager.getInstance().getPatient().setFirstName(etName.getText().toString());
                EntityManager.getInstance().getPatient().setLastName(etLastName.getText().toString());
                EntityManager.getInstance().getPatient().setBirthDate(getBirthDate());
                setCurrentItem();
            }
        });

        return rootView;
    }
    
    private String getBirthDate() {
        int mDay = dpBirth.getDayOfMonth();
        int mMonth = dpBirth.getMonth();
        int mYear = dpBirth.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(mYear, mMonth, mDay);
        String birthday = sdf.format(cal.getTime());
       
        return birthday;
    }

    /**
     * Metodo que cambia el la fase de registro actual
     */
    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(1);
    }
}
