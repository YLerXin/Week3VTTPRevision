package p2;

import java.util.*;
import java.io.*;

public class nextword {

    private Map<String ,Map<String,Integer>> map = new HashMap<>();


    
    public void getNextWord(File filepath)throws FileNotFoundException, IOException{
        FileReader fr = new FileReader(filepath);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while((line = br.readLine()) != null){
            String[] words = line.toLowerCase().split("\\s+");

            for(int i =0;i<(words.length - 1);i++){
                String currentword = words[i];
                String nextWord = words[i+1];

                map.putIfAbsent(currentword, new HashMap<>());
                Map<String,Integer> nextWordMap = map.get(currentword);
                nextWordMap.put(nextWord, nextWordMap.getOrDefault(nextWord,0) +1);

                currentword = nextWord;
            }

        }
        br.close();
        fr.close();
    }

    public String predictNextWord(String currentWord){
        currentWord = currentWord.toLowerCase();
        Map<String,Integer> nextWords = map.get(currentWord);

        if (nextWords == null || nextWords.isEmpty()) {
            return "No prediction available.";
        }
        String predictedWord = null;
        int maxCount = 0;

        for (Map.Entry<String,Integer> entry:nextWords.entrySet()){
            if (entry.getValue() > maxCount){
                predictedWord= entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return predictedWord;


    }



}
