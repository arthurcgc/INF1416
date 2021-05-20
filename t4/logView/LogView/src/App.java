import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        File yourFile = new File("log.txt");
        yourFile.createNewFile(); // if file already exists will do nothing 
        FileOutputStream oFile = new FileOutputStream(yourFile, false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oFile));

        ResultSet resultSet = Database.getLogs();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<Integer> uid = new ArrayList<Integer>();
        while (resultSet.next()) {
            String line = "";
            for (int i = columnsNumber; i >= 1; i--) {
                String columnValue = resultSet.getString(i);
                if ( i == 2) {
                    // uid column
                    uid.add(Integer.parseInt(columnValue));
                    continue;
                }
                if (i < columnsNumber) line += (" - ");
                // if (i < columnsNumber) bw.write(" - ");
                // bw.write(columnValue);
                line += columnValue;
            }
            lines.add(line);
            // bw.write("\n");
        }
        // resultSet.close();
        for( int i = 0; i < lines.size(); i++ ){
            String line = lines.get(i);
            if (uid.get(i) == - 1){
                line += " - [system]";
            }
            else {
                line += String.format(" - [%s]", Database.getUserEmail(uid.get(i)));
            }
            bw.write(line);
            bw.write("\n");
        }

        bw.close();
        
    }
}
