import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class MySignatureTest {
    private static java.security.KeyPair GenerateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    private static void RunMySignature (String text, String algorithm) throws Exception {
        byte[] plainText = text.getBytes("UTF8");
        // generate RSA key pair
        System.out.println( "\nStart generating RSA key" );
        KeyPair key = GenerateRSAKeyPair();
        System.out.println( "Finish generating RSA key" );

        // MySignature Magic
        System.out.println("Run: MySignature.getInstance(String)");
        MySignature sig = MySignature.getInstance(algorithm);
        System.out.println("Run: initSign(PrivateKey)");
        sig.initSign(key.getPrivate());
        System.out.println("Run: update(byte[])");
        sig.update(plainText);
        System.out.println("Run: sign()");
        byte[] signature = sig.sign();
        System.out.println( "\nSignature:" );
        System.out.println(MySignature.Hex(signature));

        System.out.println( "\nStart signature verification" );
        sig.initVerify(key.getPublic());
        sig.update(plainText);
        try {
            if (sig.verify(signature)) {
                System.out.println( "Signature verified" );
            } else System.out.println( "Signature failed" );
        } catch (Exception se) {
            System.out.println( "Signature failed: " + se );
        }
    }

    static void validateArgs(String[] args) throws Exception{
        if (args.length !=2) {
            System.err.println("Usage: java DigitalSignatureExample text");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws Exception {
        validateArgs(args);
        RunMySignature(args[0], args[1]);
    }
}
