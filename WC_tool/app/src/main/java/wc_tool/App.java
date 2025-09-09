package wc_tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {

    private static void byteCounter(Path filePath){
        try{
            long sizeInBytes = Files.size(filePath);
            System.out.println(sizeInBytes);

        }
        catch (IOException e){
            System.out.println("An error occured" + e.getMessage());
        }
    }

    private static void lineCounter(Path filePath){
        try{
            long numberOfLines = Files.lines(filePath).count();
            System.out.println(numberOfLines);

        }
        catch (IOException e){
            System.out.println("An error occured" + e.getMessage());
        }
    }

    private static void wordCounter(String path){
        long wordsCount = 0;
        try{
            BufferedReader bf_reader = new BufferedReader(new FileReader(path));
            String line;

            while((line = bf_reader.readLine()) != null){
                String[] words = line.trim().split("\\s+");

                if(words.length>0 && !words[0].isEmpty()) wordsCount += words.length;
            }

            bf_reader.close();

            System.out.println(wordsCount);

        }
        catch (IOException e){
            System.out.println("An error occured" + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        String path = args[0];
        Path filePath = Paths.get(path);

        if (!Files.exists(filePath)) {
            System.out.println("File not found: " + filePath);
            return;
        }


        if(args.length==1){
            byteCounter(filePath);
            lineCounter(filePath);
            wordCounter(path);
        }
        for(int i=0; i<args.length; i++){
            if(args[i].equals("-c")){
                byteCounter(filePath);
            }
            if(args[i].equals("-l")){
                lineCounter(filePath);
            }
            if(args[i].equals("-w")){
                wordCounter(path);
            }
        }
    }
}
