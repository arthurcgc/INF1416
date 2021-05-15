package Auth;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CertificateHelper {
    public static X509Certificate getCertificate(String pathString) throws Exception {
        Path path = Paths.get(pathString);
        byte[] certBytes = Files.readAllBytes(path);

        InputStream certificateInputStream = new ByteArrayInputStream(certBytes);
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

        return (X509Certificate) certificateFactory.generateCertificate(certificateInputStream);
    }

    public static String getCertificateField(X509Certificate x509Certificate, String field) throws Exception {
        String regex = ".*" + field + "=([^,]*).*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(x509Certificate.getSubjectX500Principal().toString());
        matcher.matches();

        return matcher.group(1);
    }

    public static String getCertificateEmail(X509Certificate x509Certificate) throws Exception {
        return getCertificateField(x509Certificate, "EMAILADDRESS");
    }

    public static String getCertificateCN(X509Certificate x509Certificate) throws Exception {
        return getCertificateField(x509Certificate, "CN");
    }
}
