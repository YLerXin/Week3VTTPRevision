package p2;

import java.util.*;
import java.io.*;

public class main {

    public static void main(String[] args) throws FileNotFoundException, IOException{

        String fileloc = null;

        if (args.length >=1){
            fileloc = args[0];
        }else{
            System.out.println("Please input file to read");
        }
        Console cons = System.console();
        removestopword remove = new removestopword();
        File filtered = remove.removeStopWord(fileloc)
;
        nextword nw = new nextword();
        while(true){

        String input = cons.readLine("Please input word to predict: ");
        nw.getNextWord(filtered);
        if ("break".equalsIgnoreCase(input)) {
            System.out.println("Exiting the program.");
            break; // Exit the loop
        }
        String predictedWord = nw.predictNextWord(input);

        System.out.println("Predicted next word: " + predictedWord);
        }


    }
    
}
