package Auth;

import Database.*;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class Validation {

    // public static ResultSet user;
    private static ArrayList<String[]> passwordPossibilities;
    public static PrivateKey privateKey;
    public static PublicKey publicKey;
    public static User user;

    private static User rebuildUser(ResultSet dbUser) throws SQLException {
        User u = new User();
        // u.SecretPassphrase = passphrase;
        u.Email = dbUser.getString("email");
        u.SaltString = dbUser.getString("salt");
        // u.Salt = saltString.getBytes(StandardCharsets.UTF_8);
        u.HashString = dbUser.getString("hash");
        // u.Hash = hashString.getBytes(StandardCharsets.UTF_8);
        u.CertificateBase64 = dbUser.getString("certificate");
        u.AccessCounter = dbUser.getInt("access_counter");
        u.GroupID = dbUser.getInt("group_id");
        return u;
    }
    // step 1
    public static boolean verifyEmail(String email) {
        try{
            Database db = Database.getInstance();
            ResultSet dbUser = db.getUser(email);
            // ResultSet dbGroup = db.getGroupName(dbUser.getString(""))
            Validation.user = rebuildUser(dbUser);
            Validation.user.Group = db.getGroupName(user.GroupID);
            if(dbUser.isClosed())
                return false;
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    private static Tree[] createTrees(ArrayList<String[]> passwordPhonemes){
        ArrayList<Node[]> nodes = new ArrayList<Node[]>();
        for( int i = 0; i < passwordPhonemes.size(); i++){
            int n = passwordPhonemes.get(i).length;
            Node[] line = new Node[n];
            for ( int j = 0; j < n; j++){
                line[j] = Node.Node(passwordPhonemes.get(i)[j]);
            }
            nodes.add(i, line);
        }

        Tree[] trees = new Tree[]{new Tree(), new Tree(), new Tree()};
        for (int i = 0; i < nodes.size(); i++){
            for (int j = 0; j < nodes.get(i).length; j++){
                Node root = nodes.get(i)[j];
                if (i + 1 == nodes.size()){
                    break;
                }
                root.children = nodes.get(i+1);
                if ( i == 0){
                    trees[j].root = root;
                }
            }
        }

        return trees;
    }

    static boolean traversePreorder(Node node, int level, int max, ArrayList<String> sequence) throws Exception {
        ArrayList<String> ss = new ArrayList<String>();
        ss.add("FA");
        ss.add("CA");
        ss.add("FE");
        ss.add("HO");
        if (guess(ss)){
            return true;
        }

        return false;
    }

    public static boolean guessPassword(ArrayList<String[]> passwordPhonemes) throws Exception {
        Tree[] trees = createTrees(passwordPhonemes);

        for( Tree t : trees){
            if ( traversePreorder(t.root, 1, passwordPhonemes.size(), new ArrayList<String>()) ){
                return true;
            }
        }

        return false;
    }

//    private static ArrayList<String> addToSequence(ArrayList<String> sequence, int line, int lineIndex){
////        if (sequence.size() == passwordPossibilities.size()){
////            return sequence;
////        }
////        sequence.add(passwordPossibilities.get(line)[lineIndex]);
////
//    }

    private static boolean guess(ArrayList<String> sequence) throws Exception {
        String salt = Validation.user.SaltString;
        String stringSequence = "";
        for (String s : sequence){
            stringSequence+=s;
        }

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update((stringSequence + salt).getBytes());

        String digestCurrent = DatatypeConverter.printHexBinary(messageDigest.digest());
        String digest = Validation.user.HashString;
        if(digest.equals(digestCurrent))
            return true;

        return false;
    }

    private static boolean verifyCertificateWithPrivateKey(X509Certificate cert, PrivateKey pKey) throws IOException, SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        // create randomic array of 2048 bytes
        byte[] rArr = new byte[2048];
        (new SecureRandom()).nextBytes(rArr);
        // sign using the private key
        Signature sig = Signature.getInstance(cert.getSigAlgName());
        sig.initSign(pKey);
        sig.update(rArr);
        byte[] signature = sig.sign();

        // verify signature using the public key
        sig.initVerify(cert.getPublicKey());
        sig.update(rArr);

        return sig.verify(signature);
    }


    public static boolean validatePrivateKey(String passphrase, Path keyPath) throws Exception {
        user.SecretPassphrase = passphrase;

        try{
            user.PrivateKey = user.getPrivateKey(keyPath.toString());
        }
        catch (Exception e){
            Database.log(Registry.RegistryWithTimestamp(4005, user.Email));
            return false;
        }

        user.Certificate = CertificateHelper.decodeBase64Certificate(user.CertificateBase64);
        if ( verifyCertificateWithPrivateKey(user.Certificate, user.PrivateKey) ){
            return true;
        }

        Database.log(Registry.RegistryWithTimestamp(4006, user.Email));
        return false;
    }
}
