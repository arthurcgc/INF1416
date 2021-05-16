package Auth;

import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class User {
    public String Email;
    public String Group;
    public String SecretPassphrase;
    public X509Certificate Certificate;
    // public PublicKey PublicKey;
    public PrivateKey PrivateKey;
    public byte[] Hash;
    public byte[] Salt;

    public PrivateKey getPrivateKey(String pathString) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG", "SUN");
        rand.setSeed(this.SecretPassphrase.getBytes());

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56, rand);
        Key key = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);

        Path path = Paths.get(pathString);
        byte[] bytes = Files.readAllBytes(path);

        String b64Key = new String(cipher.doFinal(bytes), "UTF8");
        String rawKey = b64Key.replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").trim();
        byte[] pKeyBytes = DatatypeConverter.parseBase64Binary(rawKey);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(new PKCS8EncodedKeySpec(pKeyBytes));
    }

    public int GetGroupID() {
        int n = 0;
        // access each character
        for (int i = 0; i < this.Group.length(); i++)
            n += Character.getNumericValue(this.Group.charAt(i)) * (int) Math.pow(10, i);

        return n;
    }

    public static int GetUserID(String email) {
        int n = 0;
        // access each character
        // generate user id for the first 6 characters of the email
        for (int i = 0; i < 9   ; i++)
            n += Character.getNumericValue(email.charAt(i)) * (int) Math.pow(10, i);

        return n;
    }

    public static User NewUser(String passPhrase, String group, String keyFileName, String certificateFileName, String password) throws Exception {
        User u = new User();
        u.Group = group;
        u.SecretPassphrase = passPhrase;

        String filesFolderPath = "safe-folder/Files/";
        u.Certificate = CertificateHelper.getCertificate(String.format("safe-folder/Keys/%s", certificateFileName));
        u.Email = CertificateHelper.getCertificateEmail(u.Certificate);
        u.PrivateKey = u.getPrivateKey(String.format("safe-folder/Keys/%s", keyFileName));
        u.Hash = u.generatePasswordHash(password);

        return u;
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[10];

        String validChars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0; i < salt.length; i++)
            salt[i] = (byte)validChars.charAt(secureRandom.nextInt(validChars.length()));

        return salt;
    }

    private byte[] generatePasswordHash(String password) throws NoSuchAlgorithmException {
        // Select the message digest for the hash computation -> SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Generate the random salt
        this.Salt = generateSalt();

        // Passing the salt to the digest for the computation
        md.update(this.Salt);

        // Generate the salted hash
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
}

//    public static boolean testaChavePrivada(PrivateKey chavePrivada, HashMap user) {
//        try {
//            byte[] teste = new byte[1024];
//            SecureRandom.getInstanceStrong().nextBytes(teste);
//            Signature assinatura = Signature.getInstance("MD5withRSA");
//            assinatura.initSign(chavePrivada);
//            assinatura.update(teste);
//            byte[] resp = assinatura.sign();
//
//            PublicKey chavePublica = Auth.getCertificate(((String) user.get("certificado")).getBytes()).getPublicKey();
//            assinatura.initVerify(chavePublica);
//            assinatura.update(teste);
//
//            if (assinatura.verify(resp)) {
//                System.out.println("Chave válida!");
//                return true;
//            }
//            else {
//                DBManager.incrementaNumChavePrivadaErrada((String) user.get("email"));
//                System.out.println("Chave rejeitada!");
//                return false;
//            }
//        }
//        catch (Exception e) {
//            System.out.println("Erro ao testar chave privada");
//            return false;
//        }
//    }
