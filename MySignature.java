import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class MySignature {
    Cipher cipher;
    KeyPair key;
    MessageDigest messageDigest;
    byte[] originalDigest;
    final String sha256 = "SHA-256";
    final String sha512 = "SHA-512";
    final String sha1 = "SHA-1";
    final String md5 = "MD5";
     public static MySignature getInstance(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
        // primeiro passo: criamos o digest
        MySignature mySig = new MySignature();
        MessageDigest messageDigest;
        String digestAlgo;
        switch (algorithm){
            case "SHA256withRSA":
                messageDigest = MessageDigest.getInstance(mySig.sha256);
                digestAlgo = mySig.sha256;
                break;
            case "SHA512withRSA":
                messageDigest = MessageDigest.getInstance(mySig.sha512);
                digestAlgo = mySig.sha512;
                break;
            case "SHA1withRSA":
                messageDigest = MessageDigest.getInstance(mySig.sha1);
                digestAlgo = mySig.sha1;
                break;
            case "MD5withRSA":
                messageDigest = MessageDigest.getInstance(mySig.md5);
                digestAlgo = mySig.md5;
                break;
            default:
                throw new NoSuchAlgorithmException(algorithm + " not found");
        }
        mySig.messageDigest = messageDigest;
        System.out.println( "Successfully created MessageDigest with algorithm: " + digestAlgo);

        // utilizamos o provider que implementa o algoritmo (i.e:SHA256) e o usamos para criar o cipher
        String provider = mySig.messageDigest.getProvider().getName();
        mySig.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", provider);
        System.out.println( "Successfully created CipherInstance with provider: " + provider );

        return mySig;
    }

    public void initSign(PrivateKey key) throws InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
    }

    public void update(byte[] byteText) throws UnsupportedEncodingException {
        this.messageDigest.update(byteText);
    }

    public byte[] sign() throws IllegalBlockSizeException, BadPaddingException {
        this.originalDigest = this.messageDigest.digest();
        return this.cipher.doFinal(this.originalDigest);
    }

    private static String Hex(byte[] signature){
        // converte o cipherText para hexadecimal
        StringBuffer buf = new StringBuffer();
        for(int i = 0; i < signature.length; i++) {
            String hex = Integer.toHexString(0x0100 + (signature[i] & 0x00FF)).substring(1);
            buf.append((hex.length() < 2 ? "0" : "") + hex);
        }

        return buf.toString();
    }

    public void initVerify(PublicKey key) throws InvalidKeyException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
    }

    public boolean verify(byte[] signature) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] currentDigest;
        try {
            currentDigest = this.cipher.doFinal(signature);
        } catch (Exception e) {
            throw e;
        }
        System.out.println("original digest:\n" + Hex(this.originalDigest));
        System.out.println("current digest:\n" + Hex(currentDigest));
        System.out.println();

        return Hex(currentDigest).compareTo(Hex(this.originalDigest)) == 0;
    }

    public static void main (String[] args) throws Exception {
         //
        // verifica args e recebe o texto plano
        if (args.length !=1) {
            System.err.println("Usage: java DigitalSignatureExample text");
            System.exit(1);
        }
        byte[] plainText = args[0].getBytes("UTF8");
        //
        // gera o par de chaves RSA
        System.out.println( "\nStart generating RSA key" );
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair key = keyGen.generateKeyPair();
        System.out.println( "Finish generating RSA key" );
        //
        // define um objeto signature para utilizar SHA1 e RSA
        // e assina o texto plano com a chave privada,
        // o provider utilizado tambem eh impresso
        MySignature sig = MySignature.getInstance("MD5withRSA");
        sig.initSign(key.getPrivate());
        sig.update(plainText);
        byte[] signature = sig.sign();
//        System.out.println( sig.getProvider().getInfo() );
        System.out.println( "\nSignature:" );
        System.out.println(Hex(signature));

        //
        // verifica a assinatura com a chave publica
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
}
