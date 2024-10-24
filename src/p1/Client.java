package p1;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        int port = 4000; // Update the port if provided
        if (args.length > 0) {
            port = Integer.parseInt(args[0]); // Update port if provided
        }

        System.out.println("Connecting to the server");

        try (Socket sock = new Socket("localhost", port);
             OutputStream os = sock.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os);
             DataOutputStream dos = new DataOutputStream(bos);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected");
            readfile fileReader = new readfile(); // Pass DataOutputStream to ReadFile

            while (true) {
                System.out.print("Input: file image or fortune (type 'break' to exit): ");
                String command = reader.readLine().trim().toLowerCase();

                if (command.equals("file")) {
                    System.out.print("Please input folder: ");
                    String folderPath = reader.readLine();
                    File folder = new File(folderPath);

                    if (folder.isDirectory()) {
                        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg") || name.endsWith(".mp3"));
                        if (files != null && files.length > 0) {
                            for (File file : files) {
                                try {
                                    fileReader.readFile(file, dos); // Call readFile without closing socket
                                } catch (IOException e) {
                                    System.err.println("Error while uploading file: " + e.getMessage());
                                }
                            }
                        } else {
                            System.out.println("No valid files found.");
                        }
                    } else {
                        System.out.println("Invalid directory.");
                    }
                } else if (command.equals("break")) {
                    dos.writeUTF("done"); // Send termination message
                    dos.flush(); // Ensure the "done" message is sent
                    System.out.println("Exiting.");
                    break;
                } else {
                    System.out.println("Unknown command. Please try again.");
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
