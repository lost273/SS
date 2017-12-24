
package sspackage;
import java.rmi.*;
import java.util.*;
import java.rmi.server.*;

public class ServiceServerImpl extends UnicastRemoteObject implements ServiceServer {
    HashMap serviceList;
    
    public ServiceServerImpl() throws RemoteException{
        setUpServices();
    }
    private void setUpServices(){
        serviceList = new HashMap();
        //create services and add them to the list of services
        serviceList.put("Dice Rolling Service", new DiceService());
        serviceList.put("Day of the Week Service", new DayOfTheWeekService());
        serviceList.put("Visual Music Service", new MiniMusicService());
    }
     //method of displaying services in the browser
    public Object[] getServiceList(){
        System.out.println("in remote");
        return serviceList.keySet().toArray();
    }
    //method call when the user selects a service
    public Service getService(Object serviceKey) throws RemoteException{
        Service theService = (Service) serviceList.get(serviceKey);
        return theService;
    }
    public static void main(String[] args){
        try{
            Naming.rebind("ServiceServer", new ServiceServerImpl());
        } catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("Remote service is running");
    }
}
