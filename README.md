UAV System – Heartbeat Availability Tactics
Introduction
UAV System is our architectural attempt at representing the software system of an Unmanned Aerial Vehicle System.
Even though a UAV System would be composed of dozens of interconnected components, we have taken a small subset of it and implemented Heartbeat tactics over it. We have taken the components ‘Camera System’, ‘GPS System’, ‘Temperature System’ and a ‘Health Monitor’ into consideration. ‘Health Monitor’ keeps a track of status of all these other systems.
We have used Java RMI to enable a System’s heartbeat to be heard by the ‘Health Monitor’. If ‘Health Monitor’ does not listen to it in 3 seconds, it proclaims that System as dead.
How to run
For demo purposes, we have the following set of executable jar files:
CameraSystem.jar
GPSSystem.jar
HealthMonitor.jar
TemperaturesSVM.jar
UAVS.jar
Entire system starts by simply double clicking/ running the UAVS.jar. It internally starts all the Systems.
You can manually start any component by clicking on the buttons in the System GUI.
Khalid Almalki
Palash Jain
