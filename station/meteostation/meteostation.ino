#include "RestClient.h"
#include "ArduinoJson.h"
#include <Wire.h>
#include <Adafruit_BMP085.h>
#include "DHTesp.h"

#define BODY_BUFFER_SIZE                                                    1024
#define PORT                                                                8080
#define IP_ADDRESS                                              "123.123.123.123"
#define SSID                                                             "******"
#define PASSWD                                                          "*******"

#define LED_BUILTIN                                                           13
#define TEST_SWITCH                                                           14
#define DHT_PIN                                                               16


RestClient client = RestClient(IP_ADDRESS, PORT);
StaticJsonDocument<BODY_BUFFER_SIZE> doc;
DHTesp dht;
Adafruit_BMP085 bmp;

const char* REQUEST_PREFIX = "mutation{addMeasurement(";
const char* REQUEST_POSTFIX = ")}";
const char QUOTE = '"';

const char* field_DeviceId = "deviceId:";
const char* object_Dht11dto = "dht11dto:";
const char* object_Bmp085dto = "bmp085dto:";

const char* field_Dht11dto_Temperature = "temperature:";
const char* field_Dht11dto_Humidity = "humidity:";

const char* field_Bmp085dto_Temperature = "temperature:";
const char* field_Bmp085dto_Pressure = "pressure:";
const char* field_Bmp085dto_Altitude = "altitude:";
const char* field_Bmp085dto_SeaLevelPressure = "seaLevelPressure:";




String DeviceIdValue = "";

boolean LED_STATUS = false;
int testButtonState = 0; 

String enquote(String value) {
  return QUOTE + value + QUOTE;
}

char* buildRequestBody(String deviceId,
                       String dht11Temp, String dht11Humid,
                       String bmp085Temp, String bmp085Pressure, String bmp085Alt, String bmp085SLPress) {

      char req[BODY_BUFFER_SIZE] = {0};
      
      sprintf(req, "%s %s %s %s{%s %s %s %s} %s{%s %s %s %s %s %s %s %s} %s",
    
            REQUEST_PREFIX,
            
            field_DeviceId,
            deviceId.c_str(),
            
            object_Dht11dto,
                  
                  field_Dht11dto_Temperature,
                  dht11Temp.c_str(),
                  
                  field_Dht11dto_Humidity,
                  dht11Humid.c_str(),
  
            object_Bmp085dto,
  
                  field_Bmp085dto_Temperature,
                  bmp085Temp.c_str(),
                  
                  field_Bmp085dto_Pressure,
                  bmp085Pressure.c_str(),
                  
                  field_Bmp085dto_Altitude,
                  bmp085Alt.c_str(),
                  
                  field_Bmp085dto_SeaLevelPressure,
                  bmp085SLPress.c_str(),
            
            REQUEST_POSTFIX);

     return req;       
}

//Setup
void setup() {
  pinMode(LED_BUILTIN, OUTPUT);
  pinMode(TEST_SWITCH, INPUT);

  Serial.begin(115200);
  Serial.println("connect to WiFi network");
  client.begin(SSID, PASSWD);
  Serial.println("Setup!");

  DeviceIdValue = enquote(String(ESP.getChipId()));

  Serial.println(" ESP8266 Chip id = " + DeviceIdValue);

  if (!bmp.begin()) {
    Serial.println("Could not find a valid BMP085 sensor, check wiring!");
    while (1) {}
  }

  dht.setup(DHT_PIN, DHTesp::DHT11);
}

void loop(){
  digitalWrite(LED_BUILTIN, LED_STATUS);
  LED_STATUS = !LED_STATUS;

  testButtonState = digitalRead(TEST_SWITCH);
  
  String response = "";
  String request = "";
  char prebuiltRequest[BODY_BUFFER_SIZE] = {0};

  doc.clear();

  if (testButtonState == LOW) {
        strcpy(prebuiltRequest, buildRequestBody(
                                                  DeviceIdValue,
                                                  enquote(String(dht.getTemperature()) + " C"), enquote(String(dht.getHumidity()) + " %"),
                                                  enquote(String(bmp.readTemperature()) + " C"), enquote(String(bmp.readPressure()) + " Pa"), enquote(String(bmp.readAltitude(101500)) + " m"), enquote(String(bmp.readSealevelPressure()) + " Pa")
                                                ));
  } else {
        strcpy(prebuiltRequest,
        buildRequestBody(DeviceIdValue, enquote(String("test")),
        enquote(String("test")),enquote(String("test")),
        enquote(String("test")),enquote(String("test")),enquote(String("test"))));
  }


  
//  doc["query"] = "mutation{addMeasurement(deviceId: \"a555sd\" dht11dto:{humidity: \"hum1\" temperature: \"temp1\"} bmp085dto:{temperature:\"temp2\" pressure: \"press2\" altitude: \"alt2\" seaLevelPressure: \"sea2\"})}";

  doc["query"] = prebuiltRequest;
  serializeJson(doc, request);
  client.post("/graphql", request.c_str(), &response);
  doc.clear();

  delay(dht.getMinimumSamplingPeriod());
  delay(700);
}
