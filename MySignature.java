import org.bouncycastle.crypto.Digest;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class MySignature {
    private Cipher cipher;
    private Digest digest;

    public static MySignature getInstance(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        // primeiro passo: criamos o digest
        MessageDigest messageDigest;
        if (algorithm.compareTo("SHA256withRSA") == 0){
            messageDigest = MessageDigest.getInstance("SHA-256");
        }
        else if (algorithm.compareTo("SHA512withRSA") == 0){
            messageDigest = MessageDigest.getInstance("SHA-512");
        }
        else if (algorithm.compareTo("SHA1withRSA") == 0){
            messageDigest = MessageDigest.getInstance("SHA-1");
        }
        else if (algorithm.compareTo("MD5withRSA") == 0){
            messageDigest = MessageDigest.getInstance("MD5");
        }
        else {
            throw new NoSuchAlgorithmException(algorithm + " not found");
        }


        // utilizamos o provider que implementa o algoritmo (i.e:SHA256) e o usamos para criar o cipher
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", messageDigest.getProvider().getInfo());


        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(2048);
        KeyPair key = keygen.generateKeyPair();
        System.out.println( "Finish generating RSA key" );
        //
        // define o objeto de cifra RSA e imprime o provider utilizado
        System.out.println( "\n" + cipher.getProvider().getInfo() );
        //



        return new MySignature();
    }
}
