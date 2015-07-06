package com.smartpump.bismara.bismaraapp.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import com.smartpump.bismara.bismaraapp.R;

public class ConfirmationOnHoldActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_on_hold);
        this.getActionBar().hide();
    }
}
