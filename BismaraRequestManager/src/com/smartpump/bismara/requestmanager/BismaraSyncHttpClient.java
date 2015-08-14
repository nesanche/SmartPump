package com.smartpump.bismara.requestmanager;

import com.loopj.android.http.SyncHttpClient;

public class BismaraSyncHttpClient extends SyncHttpClient {

    private String response;

    String getResponse() {
        return response;
    }

    void setResponse(String response) {
        this.response = response;
    }

}
