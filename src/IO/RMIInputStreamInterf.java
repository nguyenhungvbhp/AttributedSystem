package IO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by manhhung on 11/17/17.
 */
public interface RMIInputStreamInterf extends Remote {
    public byte[] readBytes(int len) throws IOException, RemoteException;

    public int read() throws IOException, RemoteException;

    public void close() throws IOException, RemoteException;
}
