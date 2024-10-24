package p1;

import java.io.*;

public class readfile {
    public void readFile(File fileloc, DataOutputStream dos) throws IOException {
        System.out.println("Uploading file: " + fileloc.getName());

        try (FileInputStream fis = new FileInputStream(fileloc);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            String fileName = fileloc.getName();
            long fileSize = fileloc.length();

            dos.writeUTF(fileName);
            dos.writeLong(fileSize);

            int readBytes;
            long sentBytes = 0;
            byte[] buff = new byte[4 * 1024];

            while ((readBytes = bis.read(buff)) > 0) {
                sentBytes += readBytes;
                dos.write(buff, 0, readBytes);
                System.out.printf("Sent %d of %d bytes\n", sentBytes, fileSize);
            }

            dos.flush(); // Ensure all data is sent
            System.out.println("Finished uploading file: " + fileName);
        }
    }
}
