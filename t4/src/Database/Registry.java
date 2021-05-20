package Database;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Registry {
    public String Timestamp;
    public String UserEmail;
    public int Code;
    public long nanoSecondsTimestamp;

    public static Registry RegistryWithTimestamp(){
        Registry r = new Registry();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        r.Timestamp = formatter.format(date);
        r.nanoSecondsTimestamp = System.currentTimeMillis();

        return r;
    }

    public static Registry RegistryWithTimestamp(int code){
        Registry r = new Registry();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        r.Timestamp = formatter.format(date);
        r.Code = code;
        r.nanoSecondsTimestamp = System.currentTimeMillis();
        return r;
    }

    public static Registry RegistryWithTimestamp(int code, String email){
        Registry r = new Registry();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        r.Timestamp = formatter.format(date);
        r.Code = code;
        r.UserEmail = email;
        r.nanoSecondsTimestamp = System.currentTimeMillis();

        return r;
    }
}