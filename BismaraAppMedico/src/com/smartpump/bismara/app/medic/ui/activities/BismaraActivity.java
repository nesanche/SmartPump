package com.smartpump.bismara.app.medic.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

public class BismaraActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("fromNotification")) {
            if (extras.getBoolean("fromNotification")) {
                new ConfirmNotification().execute(extras
                        .getInt("notificationId"));
            }
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    private class ConfirmNotification extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... params) {
            String query = "http://bismara.elasticbeanstalk.com/rest/users/confirmNotification";
            int notificationId = params[0];
            SyncHttpClient client = new SyncHttpClient();
            client.addHeader("notification-id", notificationId + "");
            client.get(BismaraActivity.this, query,
                    new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(String response) {
                            Log.d("Confirmed notification", response);
                        }

                        @Override
                        public void onFailure(int statusCode, Throwable error,
                                String content) {
                            if (statusCode == 404) {
                                Log.d("ERROR", "Error 404 not found");
                            } else if (statusCode == 500) {
                                Log.d("ERROR", "Error 500");
                            } else {
                                Log.d("ERROR", "Unknown error");
                            }
                        }
                    });
            return "";
        }
    }

}
