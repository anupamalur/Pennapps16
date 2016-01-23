import serial
set = serial.Serial('/dev/ttyUSB0', 9600)
while 1 :
	readVoltage = ser.readline()
	if float(readVoltage) > 4.8
		print "Too fast"
	else
		print "You are doing well...good job"
		