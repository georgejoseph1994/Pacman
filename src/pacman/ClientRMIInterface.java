import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface ClientRMIInterface extends Remote
{
    void mapChanged(Map<String, String> response) throws RemoteException;
}
