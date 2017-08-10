#include <SoftwareSerial.h>
SoftwareSerial serialBT(8, 9);

String data = "";
//int counter = 0;
int led = 2;

unsigned int i,j = 0;
char sbuffer[30];  
char ch = 0;
unsigned char pos = 0;
unsigned char systolic, diastolic, pulse, sys, dia, pul;


void setup() {

  pinMode(led, OUTPUT);
  delay(2000);
  Serial.begin(9600);  // Blood Pressure Device//
  serialBT.begin(9600);  //BT 
}

void loop() {

  while (serialBT.available() > 0) {
    data += char(serialBT.read());

  }

  if (data == "restart\n") {
    digitalWrite(led, HIGH);
    delay(1000);
    digitalWrite(led, LOW);
    serialBT.print(" ");
    serialBT.print(sys);
    serialBT.print(",");
    serialBT.print(" ");
    serialBT.print(dia);
    serialBT.print(",");
    serialBT.print(" ");
    serialBT.println(pul);

  }
  
  data = "";
  if (Serial.available() > 0)   
  {
    while (Serial.available() > 0) 
    {
      ch = Serial.read();         
      sbuffer[pos] = ch;  // Armazena os caracteres ASCII no Buffer//
      pos++;
    }
    delay(100);
  }
  pos = 0;       
  systolic = ((sbuffer[1] - '0') * 100) + ((sbuffer[2] - '0') * 10) + (sbuffer[3] - '0'); //Systolic //
  diastolic = ((sbuffer[6] - '0') * 100) + ((sbuffer[7] - '0') * 10) + (sbuffer[8] - '0'); //Diastolic //
  pulse = ((sbuffer[11] - '0') * 100) + ((sbuffer[12] - '0') * 10) + (sbuffer[13] - '0'); //pulse//
  delay(1000);

  
  if (sbuffer[1] == 0x30 || sbuffer[1] == 0x31 || sbuffer[1] == 0x32)
  {
    if (i == j)
    {
      sys = systolic;
      dia = diastolic;
      pul = pulse;
      i++;
      Serial.print(" ");
      Serial.print("patient:");
      Serial.print(i);
      Serial.print(" ");
      Serial.print(systolic);
      Serial.print(" ");
      Serial.print(diastolic);
      Serial.print(" ");
      Serial.println(pulse);
      delay(1000);
      
    }
    else
    {if (sys == systolic)
  { 
    }else
    {
      j++;
    }
      }
    }
}


