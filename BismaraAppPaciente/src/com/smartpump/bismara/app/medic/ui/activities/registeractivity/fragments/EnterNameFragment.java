package com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments;

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
import com.smartpump.bismara.app.medic.ui.activities.registeractivity.RegisterActivity;
import com.smartpump.bismara.app.medic.ui.util.FieldsValidator;
import com.smartpump.bismara.app.medic.util.EntityManager;

public class EnterNameFragment extends Fragment {
    /** Boton que lleva al siguiente paso de registracio */
    private Button btnNext;

    /**
     * Editores de texto para esta fase de registración
     */
    private EditText etName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_name,
                container, false);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etPhone = (EditText) rootView.findViewById(R.id.etPhone);
        etAddress = (EditText) rootView.findViewById(R.id.etAddress);
        etName.requestFocus();
        etLastName = (EditText) rootView.findViewById(R.id.etLastName);
        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        btnNext.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etName)
                        && FieldsValidator.isEmpty(etLastName)
                        && FieldsValidator.isEmpty(etPhone)) {
                    Toast.makeText(getActivity(),
                            "Nombre, apellido y número no pueden estar vacios",
                            Toast.LENGTH_SHORT).show();
                    etName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etName)) {
                    Toast.makeText(getActivity(),
                            "Por favor ingrese su nombre", Toast.LENGTH_SHORT)
                            .show();
                    etName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                if (FieldsValidator.isEmpty(etLastName)) {
                    Toast.makeText(getActivity(),
                            "Por favor ingrese su apellido", Toast.LENGTH_SHORT)
                            .show();
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etLastName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                if (FieldsValidator.isEmpty(etPhone)) {
                    Toast.makeText(getActivity(),
                            "Por favor ingrese un número de telefono",
                            Toast.LENGTH_SHORT).show();
                    etPhone.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_wrong, 0);
                    return;
                } else {
                    etPhone.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_user, 0, R.drawable.ic_ok, 0);
                }

                EntityManager.getInstance().getDoctor()
                        .setFirstName(etName.getText().toString());
                EntityManager.getInstance().getDoctor()
                        .setLastName(etLastName.getText().toString());
                EntityManager.getInstance().getDoctor()
                        .setPhone(etPhone.getText().toString());
                EntityManager.getInstance().getDoctor()
                        .setAddress(etAddress.getText().toString());

                setCurrentItem();
            }
        });

        return rootView;
    }

    /**
     * Metodo que cambia el la fase de registro actual
     */
    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(1);
    }
}
