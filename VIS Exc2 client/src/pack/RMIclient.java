package pack;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIclient {

    public static void main(String[] args) {


        try {
            String adress = "pack.RMIserver";
            //     Registry reg = LocateRegistry.getRegistry();
                  RMIEnvService rmIinterface = (RMIEnvService) Naming.lookup("//localhost:4444/pack.RMIserver");

            //     RMIEnvService rmIinterface = (RMIEnvService) reg.lookup(adress);




            while (true) {

                Thread.sleep(1000);
                String[] sensors = rmIinterface.requestEnvironmentDataTypes();

                for (String sensor : sensors) {

                    EnvData dataO = rmIinterface.requestEnvironmentData(sensor);

                    System.out.print(dataO);
                    System.out.println();
                    System.out.println("*****************************");

                }// for sensor

                System.out.println();
                System.out.println();

                EnvData[] dataOs = rmIinterface.requestAll();

                for (EnvData dataO : dataOs) {

                    System.out.println(dataO);

                } // for data

                System.out.println();
            }
            } catch (Exception e){
            e.printStackTrace();
        }

    }

}
