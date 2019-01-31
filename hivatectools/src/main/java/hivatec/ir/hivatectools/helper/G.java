package hivatec.ir.hivatectools.helper;

import android.app.Application;
import android.content.Context;

import com.appizona.yehiahd.fastsave.FastSave;

/**
 * Created by meysam on 12/23/17.
 */

public class G extends Application {

    public static Context context;



    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        FastSave.init(getApplicationContext());

        FontsOverride.setDefaultFont(this, "DEFAULT", getDefaultFontName());
        FontsOverride.setDefaultFont(this, "MONOSPACE", getDefaultFontName());
        FontsOverride.setDefaultFont(this, "SERIF", getDefaultFontName());
        FontsOverride.setDefaultFont(this, "SANS_SERIF", getDefaultFontName());
    }


    public String getDefaultFontName(){
        return "IRANSansMobile.ttf";
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

        context = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        context = this;
    }
}
