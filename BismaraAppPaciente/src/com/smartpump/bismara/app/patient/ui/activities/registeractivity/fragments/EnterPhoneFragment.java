package com.smartpump.bismara.app.patient.ui.activities.registeractivity.fragments;

import com.smartpump.bismara.app.patient.R;
import com.smartpump.bismara.app.patient.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.patient.ui.util.FieldsValidator;
import com.smartpump.bismara.app.patient.util.EntityManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPhoneFragment extends Fragment {

    private EditText etPhone;
    private EditText etPhone2;
    private EditText etAddress;
    private Button btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_phone, container, false);

        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        etPhone2 = (EditText) rootView.findViewById(R.id.etPhone2);
        etAddress = (EditText) rootView.findViewById(R.id.etAddress);
        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        btnNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (FieldsValidator.isEmpty(etPhone)) {
                    Toast.makeText(getActivity(), "Por favor ingrese un número de telefono", Toast.LENGTH_SHORT).show();
                    etPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                if (FieldsValidator.isEmpty(etPhone2)) {
                    Toast.makeText(getActivity(), "Por favor ingrese un número de telefono", Toast.LENGTH_SHORT).show();
                    etPhone2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etPhone2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                if (FieldsValidator.isEmpty(etAddress)) {
                    Toast.makeText(getActivity(), "Por favor ingrese su dirección", Toast.LENGTH_SHORT).show();
                    etAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_user, 0,
                            R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etAddress.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_user, 0, R.drawable.ic_ok,
                            0);
                }
                
                EntityManager.getInstance().getPatient().setPhoneOne(etPhone.getText().toString());
                EntityManager.getInstance().getPatient().setPhoneTwo(etPhone2.getText().toString());
                EntityManager.getInstance().getPatient().setAddress(etAddress.getText().toString());
                
                setCurrentItem();
            }
        });
        return rootView;
    }
    
    /**
     * Metodo que cambia el la fase de registro actual
     */
    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(2);
    }
}
