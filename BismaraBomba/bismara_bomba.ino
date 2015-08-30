#include <Time.h>
#include <TimeAlarms.h>
#include <SoftwareSerial.h>

#define RxD 3
#define TxD 4
SoftwareSerial BTSerial(RxD, TxD);

class AlarmDose
{
  public:
  AlarmID_t alarm;
  float dose;
};

class ProfileAlarm
{
  public:
  char profile[20];
  boolean valid;
  AlarmDose alarms[50];
};

ProfileAlarm profiles[5];
char* actualProfile;

//variables necesarias de lectura
char inData[255];
char inChar=-1;
byte index = 0;

/**
 * Establece los puertos que va a utilizar la placa.
 */
void setupSamplePorts(){
  pinMode(8,OUTPUT); 
  digitalWrite(8,LOW);
}

void setupInvalidProfiles() {
  for(int i=0; i<5; i++){
    
    profiles[i].valid = false;
  }
}

void setup() {
  Serial.begin(9600);
  //BTSerial.begin(9600);
  setupSamplePorts();
  setupInvalidProfiles();
}

void loop() {
  while (Serial.available() > 0) { //Change with BTSerial
    if(index < 254) {
      inChar = Serial.read(); //Change with BTSerial
      inData[index] = inChar;
      index++;
      inData[index] = '\0';
      }
   }
   char readed[255];
   for (int i=0;i<254;i++) {
       readed[i] = inData[i];
       inData[i]=0;
   }
   index=0;
   delay(2000);
   checkCommand(readed);  
}

/**
 * Verifica qué comando se recibio una vez leidos los caracteres que se enviaron por bluetooth.
 * Los comandos son:
 * 1. setTime: Cambia la hora de la bomba.
 * 2. setSchedule: Cambia/Actualiza la programación de la bomba.
 * 3. setProfile: Cambia el perfil actual de la bomba.
 * 4. removeProfile: Elimina un perfil creado.
 */
void checkCommand(char* serialReaded) {
  char* command;
  char* data;
  char* separator = strchr(serialReaded, '?');
  if(separator != 0) {
    *separator = 0;
    command = serialReaded;
    ++separator;
    data = separator;
    Serial.println("Command identified: ");
    delay(100);
    Serial.println(command);
    delay(700);
    Serial.println("Data: ");
    delay(100);
    Serial.println(data);
    if(strcmp(command,"setTime")==0) {
      setupPumpTime(data);
    }
    delay(500);
    if(strcmp(command,"setSchedule")==0) {
        setupSchedule(data);  
    }
    delay(500);
    if(strcmp(command,"setProfile")==0) {
       switchProfile(actualProfile,data);
    }
  }
}

//-------------------------------------------Comando "setTime". Cambiar hora de bomba-------------------------------------------


/**
 * Cambia la hora de la bomba.
 */
void setupPumpTime(char* command) {
  long bombtime;
  const long DEFAULT_TIME = 1357041600;
  bombtime = atol(command);
  if ( bombtime >= DEFAULT_TIME ) {
    setTime(bombtime);
    delay(1000);
  }
  Serial.println("Hour in pump has changed!:");
  digitalClockDisplay();
}


/**
 * Método que muestra la hora actual de la bomba formateada.
 */
void digitalClockDisplay(){
  Serial.print(hour());
  printDigits(minute());
  printDigits(second());
  Serial.print(" ");
  Serial.print(day());
  Serial.print(" ");
  Serial.print(month());
  Serial.print(" ");
  Serial.print(year()); 
  Serial.println(); 
}

/**
 * Necesario para mostrar los minutos y segundos de la hora.
 */
void printDigits(int digits){
  Serial.print(":");
  if(digits < 10)
    Serial.print('0');
  Serial.print(digits);
}

//-------------------------------------------Comando "setSchedule". Cambiar programación de bomba-------------------------------------------
void setupSchedule(char* schedule) {
  char* profileSent;
  char* data;
  char* separator = strchr(schedule, '$');
  if(separator != 0) {
    *separator = 0;
    profileSent = schedule;
    profileSent[strlen(schedule)] = '\0';
    ++separator;
    data = separator;
    Serial.println("Profile sent: ");
    delay(100);
    Serial.println(profileSent);
    Serial.println("Schedule: ");
    delay(100);
    Serial.println(data);
    createAlarms(profileSent,data);
  }
}

void createAlarms(char* profileSent, char* schedule) {
  ProfileAlarm profileAlarm = ProfileAlarm(); 
  int k = 0, dayInt, hourInt, minuteInt;
  char* q;
  timeDayOfWeek_t dayFormated;
  float dose;
  char* alarm = strtok_r(schedule, "&", &q);
  Serial.println(alarm);
  while(strlen(alarm) > 0) {
    Serial.println(alarm);
    char* separator = strtok(alarm, ":");
    if(separator != 0) {
      Serial.println("Charging new Dose...");
      dayInt = atoi(separator);
      Serial.print("Day: ");
      Serial.println(dayInt);
      delay(400);
      dayFormated = getDay(dayInt);
      separator = strtok(0, ":");
      hourInt = atoi(separator);
      Serial.print("Hour: ");
      Serial.println(hourInt);
      delay(400);
      separator = strtok(0, ":");
      minuteInt = atoi(separator);
      Serial.print("Minutes: ");
      Serial.println(minuteInt);
      delay(400);
      separator = strtok(0, ":");
      dose = atof(separator);
      Serial.print("Dose: ");
      Serial.println(dose);
      delay(400);
      AlarmID_t alarmID = Alarm.alarmRepeat(dayFormated, hourInt, minuteInt, 0, executeDose);
      Alarm.disable(alarmID);
      AlarmDose alarmDose = AlarmDose();
      alarmDose.alarm = alarmID;
      alarmDose.dose = dose;
      profileAlarm.alarms[k] = alarmDose;
      k++;
    }
    alarm = strtok_r(NULL, "&", &q);
  }
  boolean exist = false;
  int pos = -1;
  for(int i=0; i<5; i++) {
    if(!profiles[i].valid) {
      Serial.println("No profile in position:");
      Serial.println(i);
      if(i<= pos || pos == -1) {
        pos = i;
      }
    } else {
      if(strcmp(profiles[i].profile, profileSent)==0) {
        Serial.println("Profile found.");
        exist = true;
        pos = i;
      }
    }
  }
  memcpy(profileAlarm.profile, profileSent, strlen(profileSent)+1 );
  profileAlarm.valid = true;
  Serial.println("Profile will be stored in position");
  Serial.println(pos);
  if(pos != -1) {
    if(!exist) {
      Serial.println("Profile does not exist. Creating new one...");
      profiles[pos] = profileAlarm;    
    } else {
      if(strcmp(actualProfile,profileSent)==0) {
        Serial.println("Profile is created and been used. Replacing...");
        clearProfile(profiles[pos].profile);
        profiles[pos] = profileAlarm;
        enableProfile(profileSent);
      } else {
        Serial.println("Profile is created but not in use. Replacing...");
        clearProfile(profiles[pos].profile);
        profiles[pos] = profileAlarm;
      }
    }
  }
  
}

void executeDose() {
  AlarmID_t id = Alarm.getTriggeredAlarmId();
  for(int i=0; i<5; i++) {
    if(strcmp(profiles[i].profile,actualProfile) == 0) {
      for(int j=0; j<50; j++) {
        if(profiles[i].alarms[j].alarm == id) {
          dose(profiles[i].alarms[j].dose);
        }
      }
    }
  }
}

void dose(float dose) {
  Serial.println("Executing dose...");
  Serial.print(dose);
}

//-------------------------------------------Métodos de utilidad----------------------------------------------------------------------------
void switchProfile(char* oldProfile,char* newProfile) {
   disableProfile(oldProfile);
   enableProfile(newProfile);
   actualProfile = newProfile;
}

void disableProfile(char* profile) {
  for(int i=0; i<5; i++) {
    if(strcmp(profiles[i].profile,profile) == 0) {
      for(int j=0; j<50; j++) {
          Alarm.disable(profiles[i].alarms[j].alarm);
      }
    }
  }
}

void enableProfile(char* profile) {
  for(int i=0; i<5; i++) {
    if(strcmp(profiles[i].profile,profile) == 0) {
      for(int j=0; j<50; j++) {
          Alarm.enable(profiles[i].alarms[j].alarm);
      }
    }
  }
}

void clearProfile(char* clearableProfile) {
  for(int i=0; i<5; i++) {
    if(strcmp(profiles[i].profile,clearableProfile) == 0) {
      for(int j=0; j<50; j++) {
        Alarm.free(profiles[i].alarms[j].alarm);
      }
    }
  }
}

char* intToChar(int value) {
  String str;
  char valueChar[3];
  str=String(value);
  str.toCharArray(valueChar,3);
  return valueChar;
}

timeDayOfWeek_t getDay(int dayInt) {
  if(dayInt == 1) {
    return dowMonday; 
  } else if(dayInt == 2) {
    return dowTuesday;
  } else if (dayInt == 3) {
    return dowWednesday;
  } else if (dayInt == 4) {
    return dowThursday;
  } else if (dayInt == 5) {
    return dowFriday;
  } else if (dayInt == 6) {
    return dowSaturday;
  } else {
    return dowSunday;
  }
}
