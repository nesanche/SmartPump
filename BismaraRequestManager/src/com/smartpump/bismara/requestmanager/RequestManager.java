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
import com.smartpump.bismara.requestmanager.model.Patient;
import com.smartpump.bismara.requestmanager.model.Pump;

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

    public Doctor checkDoctorForPatientRegistration(Context context,
            String license) {
        Boolean doctorExists = checkRegNumberAvailability(context, license);
        Doctor doctor = null;
        if (!doctorExists) {
            String query = RequestConstants.BISMARA_URL
                    + RequestConstants.RETRIEVE_DOCTOR;
            Map<String, String> header = new HashMap<String, String>();
            header.put("registrationNumber", license);
            String responseString = sender.doGet(context, query, header,
                    null);
            Gson gson = new GsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(responseString).getAsJsonObject();
            doctor = gson.fromJson(json, Doctor.class);
        }

        return doctor;
    }

    public Boolean checkEmailAvailability(Context context, int role,
            String email) {
        String query = RequestConstants.BISMARA_URL;
        if (role == RequestConstants.PATIENT) {
            query += RequestConstants.CHECK_PATIENT_EMAIL_AVAILABILITY_REQUEST;
        }
        if (role == RequestConstants.DOCTOR) {
            query += RequestConstants.CHECK_DOCTOR_EMAIL_AVAILABILITY_REQUEST;
        }
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("email", email);
        String responseString = sender.doGet(context, query, null, queryParams);
        if (responseString.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkPinMatchesWithMac(Context context, String pin,
            String macAddress) {
        String query = RequestConstants.BISMARA_URL
                + RequestConstants.CHECK_PIN_AND_MAC_MATCHES;
        Map<String, String> header = new HashMap<String, String>();
        header.put("macAddress", macAddress);
        header.put("verificationPin", pin);
        String responseString = sender.doGet(context, query, header, null);
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
    
    public Pump getExistingPump(Context context, String pin,
            String macAddress) {
        String query = RequestConstants.BISMARA_URL + RequestConstants.GET_EXISTING_PUMP;
        Map<String, String> header = new HashMap<String, String>();
        header.put("macAddress", macAddress);
        header.put("verificationPin", pin);
        String responseString = sender.doGet(context, query, header, null);
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(responseString).getAsJsonObject();
        Pump result = gson.fromJson(json, Pump.class);
        return result;        
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
                "application/json");
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        Doctor result = gson.fromJson(json, Doctor.class);
        return result;
    }
    
    public Patient registerPatient(Context context, Patient patient) {
        String query = RequestConstants.BISMARA_URL + RequestConstants.REGISTER_PATIENT_REQUEST;
        String patientString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(patient);
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(patientString.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String response = sender.doPost(context, query, null, null, entity,
                "application/json");
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        Patient result = gson.fromJson(json, Patient.class);
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
                "application/json");
        Gson gson = new GsonBuilder().create();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
        GCMRegistration result = gson.fromJson(json, GCMRegistration.class);
        return result;
    }
}
