import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.net.URL;

public class Parser {
    private static DigestFile parseLine(String line){
        DigestFile df = new DigestFile();
        String[] ss = line.split(" ");
        df.fileName = ss[0];
        df.dMap = new HashMap<String, String>();
        for (int i = 1; i < ss.length; i+=2){
            df.dMap.put(ss[i], ss[i+1]);
        }

        return df;
    }
    public static ArrayList<DigestFile> ParseDigests(String filepath) throws FileNotFoundException {
        // URL url = Parser.class.getResource(filepath);
        File F = new File(filepath);
        Scanner buffer = new Scanner(F);
        ArrayList<DigestFile> dFiles = new ArrayList<DigestFile>();
        while(buffer.hasNextLine()){
            String line = buffer.nextLine();
            dFiles.add(parseLine(line));
        }

        return dFiles;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<DigestFile> dFiles = ParseDigests("digests.txt");
        System.out.println(dFiles);
    }
}
