package hivatec.ir.hivatectools.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ActivityHelper {
    public static void log(Context context, String message){
        if(message!=null&&message.length()>0){
            Log.i(context.getClass().getSimpleName(), message);
        }
    }
    public static void toast(Context context, String message){
        if(message!=null&&message.length()>0){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}
