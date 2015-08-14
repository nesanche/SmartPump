package com.smartpump.bismara.app.medic.ui.activities.registeractivity.fragments;

import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.PasswordTransformationMethod;
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
import com.smartpump.bismara.app.medic.util.MD5Encryptor;
import com.smartpump.bismara.requestmanager.RequestManager;
import com.smartpump.bismara.requestmanager.model.User;
import com.smartpump.bismara.requestmanager.model.UserRole;
import com.smartpump.bismara.requestmanager.model.UserState;

public class EnterUserFragment extends Fragment {

    /** Boton que lleva al siguiente paso de registracio */
    private Button btnNext;

    /**
     * Editores de texto para esta fase de registración
     */
    private EditText etUserName;
    private EditText etPassword;
    private EditText etRepeatPassword;

    /** Indicador de progreso que verifica que no exista el usuario */
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_enter_user,
                container, false);

        progress = new ProgressDialog(getActivity());
        btnNext = (Button) rootView.findViewById(R.id.btnNextStep);
        etUserName = (EditText) rootView.findViewById(R.id.etUsername);
        etPassword = (EditText) rootView.findViewById(R.id.etPass);
        etRepeatPassword = (EditText) rootView.findViewById(R.id.etRepeatPass);
        etPassword.setTypeface(Typeface.DEFAULT);
        etRepeatPassword.setTypeface(Typeface.DEFAULT);
        etPassword.setTransformationMethod(new PasswordTransformationMethod());
        etRepeatPassword
                .setTransformationMethod(new PasswordTransformationMethod());

        btnNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FieldsValidator.isEmpty(etUserName)) {
                    Toast.makeText(getActivity(), R.string.es_username_empty,
                            Toast.LENGTH_SHORT).show();
                    etUserName.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_mail, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etPassword)) {
                    Toast.makeText(
                            getActivity(),
                            getResources()
                                    .getString(R.string.es_password_empty),
                            Toast.LENGTH_LONG).show();
                    etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
                    return;
                }

                if (FieldsValidator.isEmpty(etRepeatPassword)) {
                    Toast.makeText(
                            getActivity(),
                            getResources().getString(
                                    R.string.es_repeat_password_empty),
                            Toast.LENGTH_LONG).show();
                    etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
                }

                User user = new User();
                user.setUsername(etUserName.getText().toString());
                user.setPassword(MD5Encryptor.md5(etPassword.getText()
                        .toString()));
                user.setState(new UserState(1, "Pending"));
                user.setRole(new UserRole(2, "Doctor"));
                EntityManager.getInstance().getDoctor().setUser(user);

                validateUsername();
            }
        });

        return rootView;
    }

    /**
     * Método que inicializa la tarea asincrona para la validacion de la
     * existencia de un usuario
     */
    private void validateUsername() {
        boolean usernameAvailability = false;
        try {
            usernameAvailability = new VerifyUserName().execute(
                    etUserName.getText().toString()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (usernameAvailability) {
            userNameIsOk();
        } else {
            userNameExists();
        }
    }

    /**
     * Metodo que refresca la UI cuando el usuario existe
     */
    private void userNameExists() {
        Toast.makeText(getActivity(), "El nombre de usuario ya existe",
                Toast.LENGTH_SHORT).show();
        etUserName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_mail,
                0, R.drawable.ic_wrong, 0);
    }

    /**
     * Metodo que valida que todos los campos y continua el registros
     */
    private void userNameIsOk() {
        if (!FieldsValidator.matchPattern(etPassword)) {
            Toast.makeText(getActivity(), R.string.es_pass_format,
                    Toast.LENGTH_SHORT).show();
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return;
        } else {
            etPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }

        if (!FieldsValidator.passwordsMatch(etPassword, etRepeatPassword)) {
            Toast.makeText(getActivity(), R.string.es_password_dont_match,
                    Toast.LENGTH_SHORT).show();
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_wrong, 0);
            return;
        } else {
            etRepeatPassword.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_pass, 0, R.drawable.ic_ok, 0);
        }

        setCurrentItem();
    }

    /**
     * Metodo que cambia la vista de la fase actual del registro
     */
    private void setCurrentItem() {
        ((RegisterActivity) getActivity()).setCurrentItem(2);
    }

    /**
     * Clase que representa una AsyncTask que verifica que no exista la el
     * usuario
     * 
     * @author nesanche
     *
     */
    private class VerifyUserName extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setTitle("Verificando...");
            progress.setMessage("Espere mientras verificamos que el nombre de usuario sea válido.");
            progress.show();
        }

        @Override
        protected Boolean doInBackground(String... username) {
            RequestManager requestManager = RequestManager.getInstance();
            return requestManager.checkUsernameAvailability(getActivity(),
                    username[0]);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progress.hide();
        }
    }
}
