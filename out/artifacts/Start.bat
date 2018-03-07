call UAVS.bat
ping -n 1 127.0.0.1
call FlightPlanner.bat
ping -n 1 127.0.0.1
call ObjectAvoidance1.bat
ping -n 1 127.0.0.1
call ObjectAvoidance2.bat
rem ping -n 5 127.0.0.1
call ObjectDetection.bat