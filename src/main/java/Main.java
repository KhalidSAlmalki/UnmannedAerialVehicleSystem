import java.io.*;

public class Main {
    static Process theProcess = null;

    public static void main(String args[]) {


        Runnable producer = () -> startProcess();

        Runnable consumer = () -> readProcess();

        new Thread(producer).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(consumer).start();

    }
    public static void startProcess(){

        // call the CSAMP1 program
        try
        {
            theProcess = Runtime.getRuntime().exec("java -Dname=khalidANaswesome -jar /Users/khalidalmalki/Documents/GitHub/UnmannedAerialVehicleSystem/out/artifacts/UnmannedAerialVehicleSystem_jar/UnmannedAerialVehicleSystem.jar");

        }
        catch(IOException e)
        {
            System.err.println("Error on exec() method");
            e.printStackTrace();
        }

    }

    public  static void readProcess(){
        BufferedReader inStream = null;

        // read from the called program's standard output stream
        try
        {
            inStream = new BufferedReader(new InputStreamReader
                    (theProcess.getInputStream()));
            while (inStream.readLine().length()!=-1){
                System.out.println(inStream.readLine());

            }


        }
        catch(IOException e)
        {
            System.err.println("Error on inStream.readLine()");
            e.printStackTrace();
        }

    }}






