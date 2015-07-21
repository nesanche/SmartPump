package com.smartpump.bismara.app.medic.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.smartpump.bismara.app.medic.R;
import com.smartpump.bismara.app.medic.ui.activities.mainactivity.MainActivity;

/**
 * Activity
 * 
 * @author nesanche
 *
 */
public class ConfirmationOnHoldActivity extends Activity {

    /** TextView que cierra la sesion */
    private TextView tvLogOut;

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
}
