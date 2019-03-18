package hivatec.ir.hivatectools.helper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.UUID;

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

        if (!SharedPreference.contains("imei")) {
            SharedPreference.putString("imei", randomIMEI());
        }

        return SharedPreference.getString("imei", randomIMEI());
    }

    /**
     * get phone number  from phone
     * @param context
     * @return phone number
     */
    public static String PhoneNumber(Context context) {
        try {
            return "not found";
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

    public static String randomIMEI() {

        return UUID.randomUUID().toString();
    }


}
