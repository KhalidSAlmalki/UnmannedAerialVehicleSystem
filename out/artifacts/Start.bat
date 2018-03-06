rem call XInput.bat
rem sleep 10
rem ping -n 5 127.0.0.1
rem call XOutput.bat
rem sleep 1
rem ping -n 1 127.0.0.1
rem call X1.bat
rem sleep 1
rem ping -n 1 127.0.0.1
rem call X2.bat
rem sleep 1
rem ping -n 1 127.0.0.1

call XOutput.bat
rem sleep 1
ping -n 1 127.0.0.1
call X1.bat
rem sleep 1
ping -n 1 127.0.0.1
call X2.bat
rem sleep 1
ping -n 1 127.0.0.1
call XInput.bat
rem sleep 10
ping -n 1 127.0.0.1