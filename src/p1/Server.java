package p1;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 4000; // Default port
        if (args.length > 0) {
            port = Integer.parseInt(args[0]); // Update port if provided
        }

        try (ServerSocket serverSock = new ServerSocket(port)) {
            System.out.println("Server listening on port " + port);

            while (true) {
                try (Socket clientSock = serverSock.accept()) {
                    System.out.println("Client connected");
                    handleClient(clientSock);
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(-1);
        }
    }

    private static void handleClient(Socket clientSock) {
        String receiveDir = "C:\\Users\\yongl\\VTTP\\RevisionWk3\\ToReceive";
        File dir = new File(receiveDir);
        if (!dir.exists()) {
            dir.mkdirs(); // Create the directory if it doesn't exist
        }
    
        try (DataInputStream dis = new DataInputStream(new BufferedInputStream(clientSock.getInputStream()))) {
            while (true) {
                try {
                    // Read the file name
                    String fileName = dis.readUTF();
    
                    // Check for the termination message
                    if (fileName.equalsIgnoreCase("done")) {
                        System.out.println("Client finished uploading files.");
                        break; // Exit the loop
                    }
    
                    // Read the file size
                    long fileSize = dis.readLong();
                    System.out.println("Receiving file: " + fileName + " (" + fileSize + " bytes)");
    
                    // Create a file to save in the 'ToReceive' directory
                    File fileToSave = new File(receiveDir, fileName);
                    try (FileOutputStream fos = new FileOutputStream(fileToSave);
                         BufferedOutputStream bos = new BufferedOutputStream(fos)) {
    
                        byte[] buffer = new byte[4096]; // 4 KB buffer
                        long totalBytesRead = 0;
                        int bytesRead = 0;
    
                        // Read file data from the client in chunks
                        while (totalBytesRead < fileSize) {
                            //Very important
                            bytesRead = dis.read(buffer, 0, (int) Math.min(buffer.length, fileSize - totalBytesRead));

                            if (bytesRead == -1) {
                                // End of stream reached unexpectedly
                                System.err.println("Error: unexpected end of stream.");
                                break;
                            }
    
                            totalBytesRead += bytesRead;
                            bos.write(buffer, 0, bytesRead);
                            System.out.printf("Received %d of %d bytes\n", totalBytesRead, fileSize);
                                //Same result
                                // bytesRead = dis.read(buffer,0,(int)Math.min(buffer.length,fileSize-totalBytesRead));
                                // bos.write(buffer,0,bytesRead);
                                // totalBytesRead += bytesRead;
                                // System.out.printf("Received %d of %d\n",totalBytesRead,fileSize);
                        }
                            // // Close the new file
                            // bos.flush();
                            // fos.close();
    
                        // Ensure all data is flushed to the file
                        bos.flush();
                        System.out.println("File received and saved as: " + fileToSave.getAbsolutePath());
                    }
                } catch (IOException e) {
                    System.err.println("Error receiving file: " + e.getMessage());
                    break; // Exit on error
                }
            }
        } catch (IOException e) {
            System.err.println("Error in data stream: " + e.getMessage());
        } finally {
            try {
                clientSock.close();
                System.out.println("Client disconnected");
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
    
    
}
