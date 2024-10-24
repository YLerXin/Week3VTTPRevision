package p1;

import java.util.*;

import java.nio.file.Paths;
import java.util.stream.*;
import java.nio.file.*;
import java.io.*;
import java.nio.*;


public class readandwrite {

    public static void main(String[] args) {
        String fileloc = new String();
        
        if (args.length >= 1){
            fileloc = args[0];
        }
        else{
            System.err.println("Please Input a File");
        }
        // try{
        //     File file = new File(fileloc);
        //     FileReader fr = new FileReader(file);
        //     BufferedReader br = new BufferedReader(fr);

        //     int linecount = 0;
        //     ArrayList<String[]> doc = new ArrayList<String[]>();
        //     float max = 0;

        //     while(true){
        //         line = br.readLine();
        //         if (line != null){
        //             String[] linesplit = line.split(",");

        //             if (linecount == 0){
        //                 String[] Headers = linesplit;
        //             }
        //             else{
        //                 doc.add(linesplit);
        //             }
        //             linecount +=1;
        //         }
        //         else{
        //             break;
        //         }
        //     }
            //String[] ex = doc.get(3);
            //to print the list to see
            //System.out.println(Arrays.toString(ex));
        //}

        try{
            List<String[]> doc = Files.lines(Paths.get(fileloc))
            .skip(1) //skip header
            .map(line -> line.split(",")) //split line into commas
            .collect(Collectors.toList());

            //find the row with the max value  because.max gives optional
            Optional<String[]> maxRow = doc.stream()
            .max(Comparator.comparing(row -> Float.parseFloat(row[2])));
            if (maxRow.isPresent()){
                String[] maxR = maxRow.get();
                System.out.println("Max Value Row: " + Arrays.toString(maxR));

                System.out.println(maxR[2]); //get the highest val in col 2 the count start from 0
            }
            else{
                System.out.println("-1");
            }

        }
        catch(FileNotFoundException e){
            System.err.println("File has not been found");
            e.printStackTrace();
        }
        catch(IOException f){
            System.err.println("IOException");
            f.printStackTrace();
        }
        


        

    }
    
}
