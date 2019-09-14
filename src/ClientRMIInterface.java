
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import pacman.PacmanMap;

public interface ClientRMIInterface extends Remote
{
    void mapChanged(String[][] grid) throws RemoteException;
}
