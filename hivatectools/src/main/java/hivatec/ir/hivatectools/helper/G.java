package hivatec.ir.hivatectools.helper;

import android.app.Application;
import android.content.Context;

/**
 * Created by meysam on 12/23/17.
 */

public class G extends Application {

    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();

        context = this;




        FontsOverride.setDefaultFont(this, "DEFAULT", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "IRANSansMobile.ttf");
    }



}