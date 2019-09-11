 import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIInterface extends Remote {

    public void receiveMove(String name, String value) throws RemoteException;
   
    void addPlayerListener(ClientRMIInterface addPlayerListener) throws RemoteException;

    void removePlayerListener(ClientRMIInterface addPlayerListener) throws RemoteException;
    
    void setMaxCount(int n) throws RemoteException;

}