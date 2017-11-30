package IO;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by manhhung on 11/17/17.
 */
public interface  RMIOutputStreamInterf extends Remote {

    public void write(int b) throws IOException, RemoteException;

    public void write(byte[] b, int off, int len) throws IOException, RemoteException;

    public void close() throws IOException, RemoteException;

}
