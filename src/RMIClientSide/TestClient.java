package RMIClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by manhhung on 11/17/17.
 */
public class TestClient {

    final public static int BUF_SIZE = 1024 * 64;

    public static void copy(InputStream in, OutputStream out) throws IOException {
        System.out.println("using byte[] read/write");
        byte[] b = new byte[BUF_SIZE];
        System.out.println("Run1");
        int len;
        while ((len = in.read(b)) >= 0) {
            System.out.println("Run2");
            out.write(b, 0, len);
        }
        System.out.println("Run3");
        in.close();
        out.close();
    }

    ;

    public static void upload(TestSercer.Server server, File src, File dest) throws IOException {
        copy(new FileInputStream(src), server.getOutputStream(dest));
    }

    public static void download(TestSercer.Server server, File src, File dest) throws IOException {
        copy(server.getInputStream(src), new FileOutputStream(dest));
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, IOException {

        SwingContainerDemo();

//      String url = "rmi://localhost:1088/server";
//        TestSercer.Server server = (TestSercer.Server) Naming.lookup(url);

//        System.out.println("Server says: " + server.sayHello());


        File testFile = new File("/Users/manhhung/Documents/BKHN/HPT/Example1/src/kemduyen.mp3");
        long len = testFile.length();

        long t;
//        t = System.currentTimeMillis();
////        /Users/manhhung/Documents/BKHN/HPT/Example1/src/Test.mp4
//        download(server, testFile, new File("dowload_kemduyen.mp3"));
//        t = (System.currentTimeMillis() - t) / 1000;
//        System.out.println("download: " + (len / t / 1000000d) +
//                " MB/s");


//        UPLOAD FILE
        t = System.currentTimeMillis();
//        upload(server, new File("/Users/manhhung/Documents/BKHN/HPT/Example1/src/kemduyen.mp3"),
//                new File("upload/upload.mp3"));
//        t = (System.currentTimeMillis() - t) / 1000;

//        System.out.println("upload: " + (len / t / 1000000d) +
//                " MB/s");

    }

    private static JFrame mainFrame;
    private static JLabel headerLabel;
    private static JLabel statusLabel;
    private static JPanel controlPanel;
    private static JLabel msglabel;

    public static void SwingContainerDemo(){
        prepareGUI();
        showJFrameDemo();
    }

    private static void prepareGUI(){
        mainFrame = new JFrame("Vi du Java Swing");
        mainFrame.setSize(800,500);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);

        statusLabel.setSize(350,100);

        msglabel = new JLabel("Chao mung ban den voi bai huong dan Java Swing.", JLabel.CENTER);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private static void showJFrameDemo(){
        headerLabel.setText("Distributed System");


        JButton okButton = new JButton("Open a Frame");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Mot Frame duoc hien thi toi nguoi dung.");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    String nameFile = selectedFile.getName();
                    System.out.println(selectedFile.getName());
                    String url = "rmi://localhost:1088/server";
                    TestSercer.Server server = null;
                    try {
                        server = (TestSercer.Server) Naming.lookup(url);
                        try {
//                            upload(server, new File(selectedFile.getAbsoluteFile().toString()), new File("upload/hinh.jpg" ));
                            upload(server, new File(selectedFile.getAbsolutePath()),
                new File("upload/" + nameFile));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } catch (NotBoundException e1) {
                        e1.printStackTrace();
                    } catch (MalformedURLException e1) {
                        e1.printStackTrace();
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
        controlPanel.add(okButton);
        mainFrame.setVisible(true);
    }
}
