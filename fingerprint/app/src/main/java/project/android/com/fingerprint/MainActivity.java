package project.android.com.fingerprint;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.security.keystore.UserNotAuthenticatedException;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;


//   Android Confirm Credential API

public class MainActivity extends AppCompatActivity
{
    private static final String KEY_NAME = "my_key";
    private static final byte[] SECRET_BYTE_ARRAY = new byte[] {1, 2, 3, 4, 5, 6};
    private static final int REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS = 1;
    private static final int ANDROID_SECURITY_SETTING = 2;

    /**
     * If the user has unlocked the device Within the last this number of seconds,
     * it can be considered as an authenticator.
     */
    private static final int AUTHENTICATION_DURATION_SECONDS = 30;
    private KeyguardManager mKeyguardManager;
    private Button validate;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       credentialLoginSteps();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void credentialLoginSteps()
    {

        validate = findViewById(R.id.validate);

        mKeyguardManager   =  (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

        if(!mKeyguardManager.isKeyguardSecure())
        {

            // Show a message that the user hasn't set up a lock screen.

//            Toast.makeText(MainActivity.this, "Secure lock screen hasn't set up.\n"
//                    + "Go to 'Settings -> Security -> Screenlock' to set up a lock screen", Toast.LENGTH_SHORT).show();

            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.viewll), "Setup Lockscreen!", Snackbar.LENGTH_LONG)
                    .setAction("SETUP", new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {
                            Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                            try {

                                //Start activity for result
                                startActivityForResult(intent, ANDROID_SECURITY_SETTING);
                            } catch (Exception ex) {

                                //If app is unable to find any Security settings then user has to set screen lock manually
                                Toast.makeText(MainActivity.this, "Unable to find security setting", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

// Changing message text color
            snackbar.setActionTextColor(Color.RED);

// Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();

            validate.setEnabled(false);

            return;
        }
        createKey();

        validate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view)
            {

                // Test to encrypt something. It might fail if the timeout expired (30s).

                tryEncrypt();
            }

        });

    }

    /**
     * Tries to encrypt some data with the generated key in {@link #createKey} which
     * only works if the user has just authenticated via device credentials.
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean tryEncrypt()
    {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            SecretKey secretKey = (SecretKey) keyStore.getKey(KEY_NAME, null);
            Cipher cipher = Cipher.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/"
                            + KeyProperties.ENCRYPTION_PADDING_PKCS7);

            // Try encrypting something, it will only work if the user authenticated within
            // the last AUTHENTICATION_DURATION_SECONDS seconds.

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.doFinal(SECRET_BYTE_ARRAY);

            // If the user has recently authenticated, you will reach here.
            showAlreadyAuthenticated();
            return true;

        } catch (UserNotAuthenticatedException e) {
            // User is not authenticated, let's authenticate with device credentials.
            showAuthenticationScreen();
            return false;

        } catch (KeyPermanentlyInvalidatedException e) {
            // This happens if the lock screen has been disabled or reset after the key was
            // generated after the key was generated.
            Toast.makeText(this, "Keys are invalidated after created. Retry the purchase\n"
                            + e.getMessage(),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        catch (BadPaddingException | IllegalBlockSizeException | KeyStoreException |
                CertificateException | UnrecoverableKeyException | IOException
                | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }


    }

    private void showAuthenticationScreen() {
        // Create the Confirm Credentials screen. You can customize the title and description. Or
        // we will provide a generic one for you if you leave it null
        Intent intent = mKeyguardManager.createConfirmDeviceCredentialIntent("Confirm ", null);
        if (intent != null) {
            startActivityForResult(intent, REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS);
        }
    }

    private void showAlreadyAuthenticated()
    {
        TextView staus =  findViewById(R.id.validation_status);
        staus.setVisibility(View.VISIBLE);
        staus.setText("Already authenticated in"+ AUTHENTICATION_DURATION_SECONDS+" sec" );
        validate.setEnabled(true);

    }

    /**
     * Creates a symmetric key in the Android Key Store which can only be used after the user has
     * authenticated with device credentials within the last X seconds.
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createKey()
    {
        // Generate a key to decrypt payment credentials, tokens, etc.
        // This will most likely be a registration step for the user when they are setting up your app.

        try
        {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);

            KeyGenerator keyGenerator = KeyGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");

            // Set the alias of the entry in Android KeyStore where the key will appear
            // and the constrains (purposes) in the constructor of the Builder
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    // Require that the user has unlocked in the last 30 seconds
                    .setUserAuthenticationValidityDurationSeconds(AUTHENTICATION_DURATION_SECONDS)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        }
        catch (NoSuchAlgorithmException | NoSuchProviderException
                | InvalidAlgorithmParameterException | KeyStoreException
                | CertificateException | IOException e) {
            throw new RuntimeException("Failed to create a symmetric key", e);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONFIRM_DEVICE_CREDENTIALS) {
            // Challenge completed, proceed with using cipher
            if (resultCode == RESULT_OK) {
                if (tryEncrypt())
                {
                    showConfirmation();
                }
            } else {
                // The user canceled or didn’t complete the lock screen
                // operation. Go to error/cancellation flow.
                Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                System.exit(0);
            }
        }

        if(requestCode ==ANDROID_SECURITY_SETTING)
        {
            if(mKeyguardManager.isDeviceSecure())
            {
                Toast.makeText(this, "You successfully setup lock", Toast.LENGTH_SHORT).show();
                credentialLoginSteps();
            }
            else
            {
                Toast.makeText(this, "You need to add security ", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void showConfirmation()
    {
        Toast.makeText(this, "FingerPrint is sucessfully validated ", Toast.LENGTH_SHORT).show();

    }

}
