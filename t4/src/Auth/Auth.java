package Auth;
import java.util.ArrayList;

import javax.crypto.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class Auth {

    public static boolean checkPermission(User user, String index, String fileName, String folderPath) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, SignatureException, InvalidKeyException, NoSuchProviderException, CertificateException {
            String[] lines = index.split("\n");
            ArrayList<SafeFile> files = Parser.ParseIndex(index);
            for (SafeFile file: files) {
                if (fileName.equals(file.Code)) {
                    if (user.Email.equals(file.OwnerEmail) || user.Group.equals(file.Group)) {
                        // decrypt file and write it on disc
                        byte[] fileBytes = decryptFile(user, folderPath, file.Code);

                        Files.write(Paths.get(folderPath + "/" + file.Name), fileBytes);
                        return true;
                    }
                }
            }
            return false;
    }

    private static byte[] getSeed(PrivateKey pKey, String folderPath, String filename) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, pKey);
        cipher.update(Files.readAllBytes(Paths.get(folderPath + "/" + filename + ".env")));
        return cipher.doFinal();
    }

    private static byte[] getFileBytes(byte[] seed, byte[] enc) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom randomObject = SecureRandom.getInstance("SHA1PRNG", "SUN");
        randomObject.setSeed(seed);

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56, randomObject);
        Key symmetricKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, symmetricKey);
        return cipher.doFinal(enc);
    }

    private static void verifySignature(X509Certificate cert,byte[] plainBytes, String folderPath, String filename) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(cert.getPublicKey());
        sig.update(plainBytes);
        byte[] asd = Files.readAllBytes(Paths.get(folderPath + "/" + filename + ".asd"));
        if (sig.verify(asd) == false) {
            throw new SecurityException("archive tainted");
        }
    }

    public static byte[] decryptFile(User user, String folderPath, String filename) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException, SignatureException, CertificateException {
            byte[] enc = Files.readAllBytes(Paths.get(folderPath + "/" + filename + ".enc"));
            byte[] seed = getSeed(user.Pkey, folderPath, filename);
            byte[] indexPlainBytes = getFileBytes(seed, enc);

            System.out.println( new String(indexPlainBytes, "UTF8"));
            verifySignature(user.Certificate, indexPlainBytes, folderPath, filename);

            return indexPlainBytes;
    }

    public static void main(String[] args) {
        User user01 = new User();
        user01.SecretPassphrase = "user01";
        user01.Group = "usuario";
        String filesFolderPath = "safe-folder/Files/";
        try {
            // setup users
            user01.Certificate = CertificateHelper.getCertificate("safe-folder/Keys/user01-x509.crt");
            user01.Email = CertificateHelper.getCertificateEmail(user01.Certificate);
            user01.Pkey = user01.getPrivateKey("safe-folder/Keys/user01-pkcs8-des.key");

            // decrypt index to discover filer list with real names and owner specs
            byte[] indexBytes = decryptFile(user01, filesFolderPath, "index");
            String indexString = new String(indexBytes, "UTF8");

            // decrypt file XXYYZZ11 with user01, email should match so it should be decrypted
            if (checkPermission(user01, indexString, "XXYYZZ11", filesFolderPath)){
                System.out.println("user %s has access to file: ");
            }
            // decrypt file XXYYZZ22 with user01, group should match so it should be decrypted
            if (checkPermission(user01, indexString, "XXYYZZ22", filesFolderPath)){
                System.out.println("user %s has access to file: ");
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}