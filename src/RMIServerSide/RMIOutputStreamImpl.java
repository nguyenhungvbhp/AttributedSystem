package RMIServerSide;

import IO.RMIOutputStreamInterf;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by manhhung on 11/17/17.
 */
public class RMIOutputStreamImpl implements RMIOutputStreamInterf {
    private OutputStream out;


    public RMIOutputStreamImpl(OutputStream out) throws IOException {
        this.out = out;
        UnicastRemoteObject.exportObject(this, 1099);
    }

    @Override
    public void write(int b) throws IOException, RemoteException {
        out.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException, RemoteException {
        out.write(b, off, len);
    }

    @Override
    public void close() throws IOException, RemoteException {
        out.close();
    }
}
