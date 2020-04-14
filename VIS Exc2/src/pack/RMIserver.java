package pack;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;


public class RMIserver extends UnicastRemoteObject implements RMIEnvService {
    private ArrayList<EnvData> sensorArrayList = new ArrayList<EnvData>();


    public RMIserver() throws RemoteException {
        super();
    }


    public static void main(String[] args) {
        try {

            RMIserver rs = new RMIserver();
            Registry reg = LocateRegistry.createRegistry(4444);
            reg.rebind("pack.RMIserver", rs);

            System.out.println("Server is waiting for queries …");
        } catch (Exception _e) {
            _e.printStackTrace();
        }
    }


    @Override
    public String[] requestEnvironmentDataTypes() throws RemoteException {
        String[] sensorTypes = {"light", "noise", "air"};

        return sensorTypes;
    }

    @Override
    public EnvData requestEnvironmentData(String _type) throws RemoteException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        int[] sensorData = new int[3];
        for (int i = 0; i < 3; i++) {
            sensorData[i] = new Random().nextInt((255 - 100) + 1) + 100;
        }
        EnvData sensor = new EnvData(_type, timestamp, sensorData);

        sensorArrayList.add(sensor);

        return sensor;
    }

    @Override
    public EnvData[] requestAll() throws RemoteException {
        EnvData[] sensorArray = new EnvData[sensorArrayList.size()];

        for (int i = 0; i < sensorArray.length; i++) {

            sensorArray[i] = sensorArrayList.get(i);

        }

        return sensorArray;
    }
}
