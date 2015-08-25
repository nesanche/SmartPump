#include <Time.h>
#include <TimeAlarms.h>
#include <SoftwareSerial.h>
#include <HashMap.h>
#include <String.h>

#define RxD 3
#define TxD 4

const byte MAX_HASH_SIZE = 5;
HashType<char*, float> hashRawArray[MAX_HASH_SIZE];
HashMap<char*, float> scheduleMap = HashMap<char*,float>( hashRawArray , MAX_HASH_SIZE );
SoftwareSerial BTSerial(RxD, TxD);
String profiles[5];
char inData[255];
char inChar=-1;
byte index = 0;
char* profile;
AlarmID_t alarms[255];

/**
 * Setea los puertos que va a utilizar la placa.
 */
void setupSamplePorts(){
  pinMode(8,OUTPUT); 
  digitalWrite(8,LOW);
}

/**
 * Método de inicio que configura los parámetros de la placa.
 */
void setup() {
  Serial.begin(9600);
  //BTSerial.begin(9600);
  setupSamplePorts();
}

/**
 * Método que se ejecuta constantemente y hace al funcionamiento de la placa.
 */
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
       profile = data;
    }
  }
}

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

/**
 * Método encargado de cargar una nueva programación en la bomba.
 */
void setupSchedule(char* schedule) {
  scheduleMap = HashMap<char*,float>( hashRawArray , MAX_HASH_SIZE );
  int i = 0;
  char* alarm = strtok(schedule, "&");
  Serial.println(alarm);
  while(alarm != 0) {
    char* separator = strtok(alarm, ":");
    if(separator != 0) {
      Serial.println("Charging new Dose...");
      char* profile = separator;
      Serial.print("Profile: ");
      Serial.println(profile);
      delay(400);
      separator = strtok(0, ":");
      int dayInt = atoi(separator);
      Serial.print("Day: ");
      Serial.println(dayInt);
      delay(400);
      timeDayOfWeek_t dayFormated = getDay(dayInt);
      separator = strtok(0, ":");
      int hourInt = atoi(separator);
      Serial.print("Hour: ");
      Serial.println(hourInt);
      delay(400);
      separator = strtok(0, ":");
      int minuteInt = atoi(separator);
      Serial.print("Minutes: ");
      Serial.println(minuteInt);
      delay(400);
      separator = strtok(0, ":");
      float dose = atof(separator);
      Serial.print("Dose: ");
      Serial.println(dose);
      delay(400);
      char* key = parseKey(profile, dayInt, hourInt, minuteInt);
      Serial.print("Key: ");
      Serial.println(key);
      scheduleMap[i](key,dose); 
      i++;
      delay(2000);
      Serial.print("Value charged: ");
      Serial.println(scheduleMap.getValueOf(key));
    }
    alarm = strtok(0, "&");
    Serial.print("New alarm: ");
    Serial.println(alarm);
  }
  //Alarm.alarmRepeat(getDay(1),20,30,0, executeDose);
}

void executeDose() {
   char* key = parseKey(profile, weekday()+1, hour(), minute());
   float doseQuant = scheduleMap.getValueOf(key);
   dose(doseQuant);
}

void dose(float dose) {
  Serial.print("Execute dose: ");
  Serial.print(dose);
}

char* intToChar(int value) {
  String str;
  char valueChar[3];
  str=String(value);
  str.toCharArray(valueChar,3);
  return valueChar;
}

char* parseKey(char* profile, int dayInt, int hourInt, int minuteInt) {
  char* key = "";
  strcpy(key,profile);
  strcat(key,":");
  strcat(key,intToChar(dayInt));
  strcat(key,":");
  strcat(key,intToChar(hourInt));
  strcat(key,":");
  strcat(key,intToChar(minuteInt));
  return key;
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

