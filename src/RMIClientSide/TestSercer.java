package RMIClientSide;

import IO.RMIInputStream;
import IO.RMIOutputStream;
import RMIServerSide.RMIInputStreamImpl;
import RMIServerSide.RMIOutputStreamImpl;

import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by manhhung on 11/17/17.
 */
public class TestSercer {

    public interface Server extends Remote {
        public String sayHello() throws RemoteException;

        public OutputStream getOutputStream(File f) throws IOException;

        public InputStream getInputStream(File f) throws IOException;

    }


    public static class ServerImpl extends UnicastRemoteObject implements Server {

        protected ServerImpl() throws RemoteException {
            super();
        }

        Registry rmiRegistry;


        //        Thực hiện start server
        public void start() throws Exception {
            rmiRegistry = LocateRegistry.createRegistry(1088);
            rmiRegistry.bind("server", this);
            System.out.println("Server start");
        }

        //        Dừng server
        public void stop() throws Exception {
            rmiRegistry.unbind("server");
            unexportObject(this, true);
            unexportObject(rmiRegistry, true);
            System.out.println("Server stopped");
        }

        @Override
        public String sayHello() throws RemoteException {
            return "Hello world!";
        }

        @Override
        public OutputStream getOutputStream(File f) throws IOException {
            return new RMIOutputStream(new RMIOutputStreamImpl(new FileOutputStream(f)));

        }

        @Override
        public InputStream getInputStream(File f) throws IOException {
            return new RMIInputStream(new RMIInputStreamImpl(new FileInputStream(f)));
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            ServerImpl sever = new ServerImpl();
            sever.start();
            Thread.sleep(9 * 60 * 1000);
            sever.stop();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
