package project.android.com.cwp80;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity
{

    private KeyStore keyStore;
    private String KEY_NAME = "KEY";
    private Cipher cipher;
    FingerprintManager fingerprintManager;
    KeyguardManager keyguardManager;
    TextView tv1;
    ImageView iv1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       tv1 =  findViewById(R.id.tv1);
       iv1 =  findViewById(R.id.iv1);

               // Check1 - Android version is greater or equals to Android Marshmallow
              //  Check2 - Device has Fingerprint Scanner
             //   Check3 - Provide Fingerprint Permission in app
            //    Check4 - Lock Screen is Secured with atleast 1 type of lock method
           //     Check5 - Atleast 1 fingureprint is registered in device

            //Check 1
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)

       {


           fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
           keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

           //Check 2
           if(!fingerprintManager.isHardwareDetected()) //Check whether the fingerprint hardware is present or not
           {
               tv1.setText("Fingerprint Sensor is not detected");
           }
           else
           {
               //Check3
              if(ContextCompat.checkSelfPermission(this,Manifest.permission.USE_FINGERPRINT)!=PackageManager.PERMISSION_GRANTED)
              {
                  tv1.setText("Permission Not Granted");
              }
              else

              {

                  //Check4
                  if(!keyguardManager.isDeviceSecure())
                  {
                      tv1.setText("Please protect device with atleast one lock method in Settings");
                  }
                  else
                  {
                      //Check5
                      if(!fingerprintManager.hasEnrolledFingerprints())
                      {
                          tv1.setText("Please register atleast 1 fingerprint in your device");
                      }
                      else
                      {
                          //When All Conditions are Satisfied

                          tv1.setText("OK!Place your fingure on fingureprint Scanner");


                          generateKey();

                          if(cipherInit())
                          {
                              FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);

                              FingerPrintHandler fingerPrintHandler = new FingerPrintHandler(this);

                              fingerPrintHandler.startAuth(fingerprintManager, cryptoObject);

                          }

                      }
                  }

              }

           }
       }//end of parent if()
       else
       {
           Toast.makeText(this, "Your Operating System doesn't support this feature ", Toast.LENGTH_SHORT).show();

       }

       }//onCreate()

    @TargetApi(Build.VERSION_CODES.M)
    void generateKey()
    {
        try
        {
          keyStore =   keyStore.getInstance("AndroidKeyStore");
         KeyGenerator keyGenerator =  KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
         keyStore.load(null);
         keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT).setBlockModes(KeyProperties.BLOCK_MODE_CBC).setUserAuthenticationRequired(true).setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
         keyGenerator.generateKey();



        }
        catch(KeyStoreException e)
        {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit()
    {

        try
        {
           cipher =  Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        }
        catch (NoSuchPaddingException e)
        {
            throw new RuntimeException("Not able to get Cipher Object",e);
        } catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Not able to get Cipher Object",e);

        }

        try
        {
         keyStore.load(null);
         SecretKey secretKey =    (SecretKey) keyStore.getKey(KEY_NAME,null);
         cipher.init(Cipher.ENCRYPT_MODE,secretKey);
         return  true;

        }
        catch(KeyPermanentlyInvalidatedException e)
        {
            return  false;
        }
         catch (CertificateException e) {
             throw new RuntimeException("Failed to init Cipher", e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (KeyStoreException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }


    }

