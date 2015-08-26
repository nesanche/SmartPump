package com.smartpump.bismara.requestmanager;

public class RequestConstants {
    
    public static final int PATIENT = 0;
    
    public static final int DOCTOR = 1;

    static final String BISMARA_URL = "http://bismara.elasticbeanstalk.com/rest";

    static final String CONFIRM_NOTIFICATION_REQUEST = "/users/confirmNotification";

    static final String CHECK_DOCTOR_EMAIL_AVAILABILITY_REQUEST = "/doctors/verifyEmail";
    
    static final String CHECK_PATIENT_EMAIL_AVAILABILITY_REQUEST = "/patients/verifyEmail";
        
    static final String CHECK_REG_NUMBER_AVAILABILITY_REQUEST = "/doctors/verifyRegNumber";
    
    static final String CHECK_PIN_AND_MAC_MATCHES = "/patients/verifyPump";
    
    static final String CHECK_USERNAME_AVAILABILITY_REQUEST = "/users/verifyUsername";
    
    static final String GET_EXISTING_PUMP = "/patients/getPump";

    static final String REGISTER_DOCTOR_REQUEST = "/doctors/new";
    
    static final String REGISTER_PATIENT_REQUEST = "/patients/new";

    static final String REGISTER_GCM_REQUEST = "/users/registerToGCM";
    
    static final String RETRIEVE_DOCTOR = "/doctors/getByRegNumber";
}
