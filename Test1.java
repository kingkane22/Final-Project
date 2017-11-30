# Final-Project
import org.sintef.jarduino.AnalogPin;
import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.JArduino;
import org.sintef.jarduino.PinMode;
import org.sintef.jarduino.comm.Serial4JArduino;
import org.sintef.jarduino.comm.Serial4JArduino.SerialReader;

public class Test1 extends JArduino {
	
	
	public Test1(String port)
	{
		super(port);
	}
	
	int val = 0; //value for storing moisture value 
	//int soilPin = A0;//Declare a variable for the soil moisture sensor 
	int soilPower = 7;//Variable for Soil moisture Power

	//Rather than powering the sensor through the 3.3V or 5V pins, 
	//we'll use a digital pin to power the sensor. This will 
	//prevent corrosion of the sensor as it sits in the soil. 

	 protected void setup() 
	{
	  
	  pinMode(DigitalPin.PIN_7, PinMode.OUTPUT);//Set D7 as an OUTPUT
	  digitalWrite(DigitalPin.PIN_7, DigitalState.LOW);//Set to LOW so no power is flowing through the sensor
	}

	protected void loop() 
	{
	System.out.print("Soil Moisture = ");    
	//get soil moisture value from the function below and print it
	System.out.println(readSoil());

	//This 1 second timefrme is used so you can test the sensor and see it change in real-time.
	//For in-plant applications, you will want to take readings much less frequently.
	delay(1000);//take a reading every second
	}
	//This is a function used to get the soil moisture content
	int readSoil()
	{

	    digitalWrite(DigitalPin.PIN_7, DigitalState.HIGH);//turn D7 "On"
	    delay(10);//wait 10 milliseconds 
	    val = analogRead(AnalogPin.A_0);//Read the SIG value form sensor 
	    digitalWrite(DigitalPin.PIN_7, DigitalState.LOW);//turn D7 "Off"
	    return val;//send current moisture value
	}
	
	
	public static void main(String[] args)
	{
		JArduino arduino = new Test1("COM3");
		arduino.runArduinoProcess();
	}
	
	

}
