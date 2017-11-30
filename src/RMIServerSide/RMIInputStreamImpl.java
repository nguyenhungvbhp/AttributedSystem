package RMIServerSide;

import IO.RMIInputStreamInterf;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by manhhung on 11/17/17.
 */
public class RMIInputStreamImpl implements RMIInputStreamInterf {


    private InputStream in;
    private byte[] b;


    public RMIInputStreamImpl(InputStream in) throws IOException{
        this.in = in;
        UnicastRemoteObject.exportObject(this, 1088);
    }

    @Override
    public byte[] readBytes(int len) throws IOException, RemoteException {

        if (b == null || b.length != len) {
            b = new byte[len];
        }

        int len2 = in.read(b);

        if (len2 < 0) {
            return null;
        }

        if (len2 != len) {
            // copy bytes to byte[] of correct length and return it
            byte[] b2 = new byte[len2];

            System.arraycopy(b, 0, b2, 0, len2);
            return b2;
        }else {
            return b;
        }

    }

    @Override
    public int read() throws IOException, RemoteException {
        return in.read();
    }

    @Override
    public void close() throws IOException, RemoteException {
        in.close();
    }
}
