package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by manhhung on 11/17/17.
 */
public class RMIInputStream extends InputStream implements Serializable{
    RMIInputStreamInterf in;

    public RMIInputStream(RMIInputStreamInterf in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

//    public int read(byte[] b, int off, int len) throws IOException {
//        byte[] b2 = in.readBytes(len);
//        if(b2 == null) {
//            return -1;
//        }
//
//        int i = b.length;
//        System.arraycopy(b2, 0, b, off, i-1);
//
//        return i;
//    }

    public void close() throws IOException {
        super.close();
    }
}


