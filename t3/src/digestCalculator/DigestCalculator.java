package digestCalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class DigestCalculator {
    private static String getFileChecksum(MessageDigest digest, File file) throws IOException, FileNotFoundException {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }

    public static ArrayList<String> walkFolder(String folderPath) throws NoSuchAlgorithmException, IOException {
        URL url = Parser.class.getResource(folderPath);
        File dir = new File(url.getPath());
        File[] fileList = dir.listFiles();
        ArrayList<String> checksums = new ArrayList<String>();
        if (fileList != null ){
            for (File child : fileList){
                checksums.add(getFileChecksum(MessageDigest.getInstance("MD5"), child));
            }
        } else{
            throw new FileNotFoundException();
        }

        return checksums;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        ArrayList<String> checksums = walkFolder("digest-folder");
        for (String hex : checksums){
            System.out.println(hex);
        }
    }
}
