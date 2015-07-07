package com.smartpump.bismara.bismaraapp.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.smartpump.bismara.bismaraapp.R;
import com.smartpump.bismara.bismaraapp.ui.activities.mainactivity.MainActivity;

public class ConfirmationOnHoldActivity extends Activity {

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
    
    private void startLogin() {
        this.startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
