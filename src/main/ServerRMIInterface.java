package main;
 import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerRMIInterface extends Remote {

    public void receiveMove(int name, String value) throws RemoteException;
   
    public void addPlayerListener(ClientRMIInterface addPlayerListener) throws RemoteException;

    public void removePlayerListener(ClientRMIInterface addPlayerListener, int playerNo) throws RemoteException;

    public void setMaxCount(ClientRMIInterface client,int n) throws RemoteException;

    public boolean setLocation(ClientRMIInterface client, int location) throws RemoteException;

	public void setMap(ClientRMIInterface client, int mapId) throws RemoteException;

	public void restartGame() throws RemoteException;

}
