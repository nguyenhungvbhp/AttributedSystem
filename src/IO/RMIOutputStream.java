package IO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Created by manhhung on 11/17/17.
 */
public class RMIOutputStream extends OutputStream implements Serializable {
    private RMIOutputStreamInterf out;

    public RMIOutputStream(RMIOutputStreamInterf out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException{
        out.write(b, off, len);

    }

    public void close() throws IOException{
        out.close();
    }
}
