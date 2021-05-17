import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class App {
    public static void main(String[] args) throws Exception {
        File yourFile = new File("log.txt");
        yourFile.createNewFile(); // if file already exists will do nothing 
        FileOutputStream oFile = new FileOutputStream(yourFile, false);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(oFile));

        ResultSet resultSet = Database.getLogs();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) bw.write(" - ");
                String columnValue = resultSet.getString(i);
                bw.write(columnValue + " [" + rsmd.getColumnName(i)+ "]");
            }
            bw.write("\n");
        }
        bw.close();
    }
}
