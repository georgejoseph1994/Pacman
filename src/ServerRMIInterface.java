 import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIInterface extends Remote {

    public void receiveMove(int name, String value) throws RemoteException;
   
    void addPlayerListener(ClientRMIInterface addPlayerListener, int playerName) throws RemoteException;

    void removePlayerListener(ClientRMIInterface addPlayerListener) throws RemoteException;
    
    void setMaxCount(int n) throws RemoteException;
    
    void setStartCorner(int name, String location) throws RemoteException;

}
