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
        FileInputStream fis = new FileInputStream(file);

        //cria array de bytes (chunk)
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //cria o hash a partir do conteudo do arquivo
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };
        fis.close();

        byte[] bytes = digest.digest();

        // Transformando os bytes em Hexadecimal
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static DigestResult checkDigestFiles(ArrayList<DigestFile> digestFiles, String currentDigest, String dType, String fileName) {
        DigestResult res = new DigestResult();
        res.filename = fileName;
        res.status = "NOT FOUND";
        res.dHex = currentDigest;
        res.dType = dType;
        for (DigestFile digestFile : digestFiles){
            if (digestFile.dMap.containsKey(dType)){
                if ( digestFile.dMap.get(dType).compareTo(res.dHex) == 0 && digestFile.fileName.compareTo(res.filename) != 0 ) {
                        res.status = "COLLISION";
                        return res;
                    }
                }
                if (digestFile.fileName.compareTo(res.filename) == 0 ) {
                    if(digestFile.dMap.get(dType).compareTo(res.dHex) == 0 ){
                        res.status = "OK";
                    }
                    else {
                        res.status = "NOT OK";
                    }
                }
        }

        return res;
    }

    public static ArrayList<DigestResult> walkFolder(ArrayList<DigestFile> digestFiles, String dType, String folderPath) throws NoSuchAlgorithmException, IOException {
        File dir = new File(folderPath);
        File[] fileList = dir.listFiles();
        ArrayList<DigestResult> finalResults = new ArrayList<DigestResult>();
        if (fileList != null ){
            ArrayList<DigestResult> partialResults = new ArrayList<DigestResult>();
            for (File child : fileList){
                String currentDigest = getFileChecksum(MessageDigest.getInstance(dType), child);
                DigestResult partialResult = checkDigestFiles(digestFiles, currentDigest, dType, child.getName());
                partialResults.add(partialResult);
            }

            // crio um novo array para marcar que agora iremos obter nosso array final
            finalResults = partialResults;
            for (DigestResult partial : finalResults){
                for (DigestResult comp : finalResults ) {
                    if (partial.filename.compareTo(comp.filename) == 0) {
                        continue;
                    }
                    if ( partial.dHex.compareTo(comp.dHex) == 0 ){
                        partial.status = "CONFLICT";
                    }
                }
            }
        } else{
            throw new FileNotFoundException();
        }

        return finalResults;
    }

    public static String printRes(DigestResult res){
        return res.filename + " " + res.dType + " " + res.dHex + " " + "(" + res.status + ")" + "\n";
    }

    public static void parseArgs(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.printf("wrong number of arguments, need 3, got: %d\n", args.length);
            throw new IOException();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        try {
            parseArgs(args);
        }
        catch (Exception e){
            System.out.println("Usage: DigestCalculator Tipo_Digest Caminho_ArqListaDigest Caminho_da_Pasta_dos_Arquivos");
            return;
        }

        ArrayList<DigestFile> dFiles = Parser.ParseDigests(args[1]);
        String dType = args[0];
        String digestFolder = args[2];
        ArrayList<DigestResult> results = walkFolder(dFiles, dType, digestFolder);
        for ( DigestResult res : results ){
            System.out.println(printRes(res));
        }
    }
}
