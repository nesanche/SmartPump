package com.smartpump.bismara.requestmanager;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class RequestSender {

    String doPost(Context context, String query, Map<String, String> header,
            Map<String, String> queryParams, ByteArrayEntity entity,
            String entityType) {
        query = getQueryWithParams(query, queryParams);
        final BismaraSyncHttpClient client = getClientWithHeaders(header);
        client.post(context, query, entity, entityType,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        client.setResponse(response);
                        Log.d("POST Request successful", response);
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
                        client.setResponse(error.getMessage());
                    }
                });
        return client.getResponse();
    }

    String doGet(Context context, String query, Map<String, String> header,
            Map<String, String> queryParams) {
        query = getQueryWithParams(query, queryParams);
        final BismaraSyncHttpClient client = getClientWithHeaders(header);
        client.get(context, query, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                client.setResponse(response);
                Log.d("GET Request successful", response);
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
                client.setResponse(error.getMessage());
            }
        });
        return client.getResponse();
    }

    private BismaraSyncHttpClient getClientWithHeaders(
            Map<String, String> header) {
        BismaraSyncHttpClient client = new BismaraSyncHttpClient();
        if (header == null)
            return client;
        for (Entry<String, String> headerElement : header.entrySet()) {
            client.addHeader(headerElement.getKey(), headerElement.getValue());
        }
        return client;
    }

    private String getQueryWithParams(String query,
            Map<String, String> queryParams) {
        if (queryParams == null)
            return query;
        if (queryParams.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append(query);
            builder.append("?");
            boolean first = true;
            for (Entry<String, String> param : queryParams.entrySet()) {
                if (!first) {
                    builder.append("&");
                }
                builder.append(param.getKey());
                builder.append("=");
                builder.append(param.getValue());
                first = false;
            }
            return builder.toString();
        } else {
            return query;
        }
    }
}
