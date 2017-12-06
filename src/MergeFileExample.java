import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manhhung on 12/6/17.
 */
public class MergeFileExample {
//    /Users/manhhung/Documents/BKHN/HPT/Example1/src/TextFile.txt
    private static String FILE_NAME = "/Users/manhhung/Documents/BKHN/HPT/Example1/src/test/duyen.mp3";
    private static String FILE = "/Users/manhhung/Documents/BKHN/HPT/Example1/src/test/kemduyen.mp3";
    public static void main(String[] args) {
        File ofile = new File(FILE_NAME);
        FileOutputStream fos;
        FileInputStream fis;
        byte[] fileBytes;
        int bytesRead = 0;
        List<File> list = new ArrayList<File>();
        list.add(new File(FILE+".part0"));
        list.add(new File(FILE+".part1"));
        list.add(new File(FILE+".part2"));
        list.add(new File(FILE+".part3"));
        list.add(new File(FILE+".part4"));
        list.add(new File(FILE+".part5"));

        try {
            fos = new FileOutputStream(ofile,true);
            for (File file : list) {
                fis = new FileInputStream(file);
                fileBytes = new byte[(int) file.length()];
                bytesRead = fis.read(fileBytes, 0,(int)  file.length());
                assert(bytesRead == fileBytes.length);
                assert(bytesRead == (int) file.length());
                fos.write(fileBytes);
                fos.flush();
                fileBytes = null;
                fis.close();
                fis = null;
            }
            fos.close();
            fos = null;
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
