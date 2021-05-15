package Auth;

import javax.crypto.*;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;

public class User {
    public String Name;
    public String Email;
    public String Group;
    public String SecretPassphrase;
    public X509Certificate Certificate;
    public PublicKey PublicKey;
    public PrivateKey Pkey;

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
