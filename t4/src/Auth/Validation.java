package Auth;

import Database.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Base64;

public class Validation {

    public static ResultSet user;
    private static ArrayList<String[]> passwordPossibilities;
    public static PrivateKey privateKey;
    public static PublicKey publicKey;

    // step 1
    public static boolean verifyEmail(String email) throws Exception {
        Database db = Database.getInstance();
        Validation.user = db.getUser(email);

        if(Validation.user.isClosed())
            return false;

        return true;
    }

    public static boolean guessPassword(ArrayList<String[]> passwordPhonemes) throws Exception {
        passwordPossibilities = passwordPhonemes;
//        ArrayList<String> sequence = new ArrayList<String>();
//        for ( int index = 0; index < 3; index++){
//            ArrayList<String> seq = addToSequence(sequence, 0, 0);
//        }

        return false;
    }

//    private static ArrayList<String> addToSequence(ArrayList<String> sequence, int line, int lineIndex){
////        if (sequence.size() == passwordPossibilities.size()){
////            return sequence;
////        }
////        sequence.add(passwordPossibilities.get(line)[lineIndex]);
////
//    }

    private static boolean guess(String sequenceNumber) throws Exception {
        String salt = Validation.user.getString("salt");

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update((sequenceNumber + salt).getBytes());

        String digestCurrent = DatatypeConverter.printHexBinary(messageDigest.digest());
        String digest = Validation.user.getString("hash");

        if(digest.equals(digestCurrent))
            return true;

        return false;
    }

    public static PrivateKey getPrivateKey(String password, Path cipherPemPath) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());

        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
        keyGenerator.init(56, secureRandom);
        Key key = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherPemBytes = Files.readAllBytes(cipherPemPath);
        byte[] pemBytes = cipher.doFinal(cipherPemBytes);

        String pemString = new String(pemBytes);
        pemString = pemString.replace("-----BEGIN PRIVATE KEY-----\n","");
        pemString = pemString.replace("-----END PRIVATE KEY-----\n","");

        byte[] privateKeyBytes = Base64.getMimeDecoder().decode(pemString);
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    public static PublicKey getPublicKey() throws Exception {
        byte[] certificateBytes = Validation.user.getBytes("certificate");

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream certificateInputStream = new ByteArrayInputStream(certificateBytes);
        X509Certificate x509Certificate = (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);

        return x509Certificate.getPublicKey();
    }

    public static boolean isPrivateKeyValid(String password, Path cipherPemPath) throws Exception {
//        try {
//            privateKey = getPrivateKey(password, cipherPemPath);
//        } catch (Exception e) {
//            Database.log(4005, Validation.user.getString("email"));
//            return false;
//        }
//
//        publicKey = getPublicKey();
//
//        byte[] message = new byte[2048];
//        (new SecureRandom()).nextBytes(message);
//
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initSign(privateKey);
//        signature.update(message);
//        byte[] cipherMessage = signature.sign();
//
//        signature.initVerify(publicKey);
//        signature.update(message);
//
//        if(signature.verify(cipherMessage))
//            return true;
//
//        Database.log(4006, Validation.user.getString("email"));

        // return false;

        return true;
    }
}
