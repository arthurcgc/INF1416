import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Provider;
import java.security.Security;

  //
  // lista os providers registrados no Java Runtime
  //
  public class ListProvidersExample
  {
      public static void main(String[]  args)
      {
          Security.insertProviderAt(new BouncyCastleProvider(), 1);
          Provider[] providers = Security.getProviders();

       for (int i = 0; i != providers.length; i++)
       {
        System.out.println("Name: " + providers[i].getName()  
               + "      Version: " + providers[i].getVersion());
       }
      }
  }
