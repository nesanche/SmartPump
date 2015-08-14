package com.smartpump.bismara.requestmanager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ByteArrayEntity;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.smartpump.bismara.requestmanager.model.Doctor;
import com.smartpump.bismara.requestmanager.model.GCMRegistration;

public class RequestManager {

    private RequestSender sender;

    private static RequestManager requestManager;

    private RequestManager() {
        sender = new RequestSender();
    }

    public static RequestManager getInstance() {
        if (requestManager == null) {
            return new RequestManager();
        } else {
            return requestManager;
        }
    }

    public String confirmNotification(Context context, int notificationId) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.CONFIRM_NOTIFICATION_REQUEST;
        Map<String, String> header = new HashMap<String, String>();
        header.put("notification-id", notificationId + "");
        return sender.doGet(context, query, header, null);
    }

    public Boolean checkEmailAvailability(Context context, String email) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.CHECK_EMAIL_AVAILABILITY_REQUEST;
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("email", email);
        String responseString = sender.doGet(context, query, null, queryParams);
        if (responseString.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkUsernameAvailability(Context context, String username) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.CHECK_USERNAME_AVAILABILITY_REQUEST;
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("username", username);
        String responseString = sender.doGet(context, query, null, queryParams);
        if (responseString.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkRegNumberAvailability(Context context, String regNumber) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.CHECK_REG_NUMBER_AVAILABILITY_REQUEST;
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("regNumber", regNumber);
        String responseString = sender.doGet(context, query, null, queryParams);
        if (responseString.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Doctor registerDoctor(Context context, Doctor doctor) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.REGISTER_DOCTOR_REQUEST;
        String doctorString = new GsonBuilder().create().toJson(doctor);
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(doctorString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String response = sender.doPost(context, query, null, null, entity,
                "aplication/json");
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        Doctor result = gson.fromJson(json, Doctor.class);
        return result;
    }

    public GCMRegistration registerGcmRegistration(Context context,
            GCMRegistration registration) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.REGISTER_GCM_REQUEST;
        String registrationString = new GsonBuilder().create().toJson(
                registration);
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(registrationString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String response = sender.doPost(context, query, null, null, entity,
                "aplication/json");
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        GCMRegistration result = gson.fromJson(json, GCMRegistration.class);
        return result;
    }
}
