# -------------------------------------------------------------
#
# -------------------------------------------------------------
import RPi.GPIO as GPIO 
import time

'''
----------------------------------
CONFIGURATION
----------------------------------
'''
GPIO.setmode(GPIO.BCM)
GPIO.setup(25,GPIO.OUT)

'''
----------------------------------
main activity
----------------------------------
'''
while True:
	GPIO.output(25, GPIO.HIGH)
	time.sleep(0.25)
	GPIO.output(25, GPIO.LOW)
	time.sleep(0.25)
