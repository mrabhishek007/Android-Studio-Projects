package project.android.com.cwp80;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;

import java.security.KeyStore;

/**
 * Created by Vikash on 1/21/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
class FingerPrintHandler extends FingerprintManager.AuthenticationCallback
{

    Context context;

    public FingerPrintHandler(Context context)
    {

        this.context = context;

    }

     public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject)
     {
         CancellationSignal cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);

     }


    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString)
    {
       updateUi("There is an Authentication error : "+errString+"("+errorCode+")",false);
    }


    @Override
    public void onAuthenticationFailed() {
        updateUi("Authentication Failed",false);
    }


    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString)
    {
        updateUi("Error : "+helpString+"("+helpCode+")",false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result)
    {
        updateUi("Permission Granted , you can now use this app",true);
    }


    private void updateUi(String s, boolean b) {
        ((MainActivity) context).tv1.setText(s);
        if (!b)
        {
            ((MainActivity) context).iv1.setBackgroundColor(context.getResources().getColor(R.color.fail));
        }
        else
        {
            ((MainActivity) context).iv1.setImageResource(R.mipmap.success);

        }
    }
}
