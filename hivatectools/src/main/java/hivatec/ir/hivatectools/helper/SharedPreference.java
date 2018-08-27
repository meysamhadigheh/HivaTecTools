package hivatec.ir.hivatectools.helper;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;


/**
 * Created by aliparsa on 8/31/2014.
 */
public class SharedPreference {
    /**
     * save  string values in sharedpreference
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * get string values from sharedpreference
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, defaultValue);
        return value;

    }

    /**
     * save  integer values in sharedpreference
     * @param key
     * @param value
     */
 public static void putInt(String key, int value) {

     Context context = getContext();

     SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * get integer values from sharedpreference
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int value = preferences.getInt(key, defaultValue);
        return value;
    }


    public static void putBoolean(String key, boolean value) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }


    public static boolean getBoolean(String key, boolean defaultValue) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean value = preferences.getBoolean(key, defaultValue);
        return value;
    }

    /**
     * get contains
     * @param key
     * @return
     */
    public static boolean contains(String key) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.contains(key);
    }

    /**
     * remove value from sharedpreference
     * @param key
     */
    public static void removeKey(String key) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void putObject(String key, Object object) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        editor.putString(key, gson.toJson(object));
        editor.apply();
    }

    public static <GenericClass> GenericClass getObject(String key, String defaultValue,Class<GenericClass> classType) {

        Context context = getContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, defaultValue);
        Gson gson = new Gson();


        return gson.fromJson(value, classType);

    }

    public static Context getContext() {
        return G.context;
    }
}
