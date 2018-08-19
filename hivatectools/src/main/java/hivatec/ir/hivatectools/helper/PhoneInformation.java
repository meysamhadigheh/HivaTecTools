package hivatec.ir.hivatectools.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

/**
 * Created by Saeed on 6/2/2015.
 */
public class PhoneInformation {
    /**
     *  get brand model from phone
     * @return BRANDMODEL
     */
    public static String BRANDMODEL() {
        return Build.BRAND.toString() + "/" + Build.MODEL.toString();
    }

    /**
     * get imei from phone
     * @param context
     * @return imei
     */
    public static String IMEI(Context context) {
        try {

            String identifier = null;
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null)
                if (ActivityCompat.checkSelfPermission(G.context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return "token";
                }identifier = tm.getDeviceId();
            if (identifier == null || identifier.length() == 0)
                identifier = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

            return identifier;

        } catch (Exception e) {
            String dd = e.getMessage();
            return "token";
        }
    }

    /**
     * get phone number  from phone
     * @param context
     * @return phone number
     */
    public static String PhoneNumber(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(G.context, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(G.context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "token";
            }
            return telephonyManager.getLine1Number();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * get serial number from phone
     * @param context
     * @return serial number
     */
    public static String getSerialNumber(Context context) {
        return Build.SERIAL;
    }


    public static String getAndroidVersion(){
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return sdkVersion + " (" + release +")";
    }
}
