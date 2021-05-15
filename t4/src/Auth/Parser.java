package Auth;

import java.util.ArrayList;

public class Parser {
    static public ArrayList<SafeFile> ParseIndex(String index){
        ArrayList<SafeFile> files = new ArrayList<SafeFile>();
        String[] lines = index.split("\n");
        for (String line: lines) {
            SafeFile curr = new SafeFile();
            String[] fields = line.split(" ");
            curr.Name = fields[1];
            curr.OwnerEmail = fields[2];
            curr.Group = fields[3];
            curr.Code = fields[0];
            files.add(curr);
        }
        return files;
    }
}
