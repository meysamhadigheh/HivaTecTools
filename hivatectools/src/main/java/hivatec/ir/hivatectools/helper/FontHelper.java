package hivatec.ir.hivatectools.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class FontHelper {

    /**
     * list of fonts in app and fonts folder in assets under main folder
     */
    public enum Fonts {

        MAIN_FONT,
        MAIN_FONT_LIGHT,
    }


    public static Typeface getMainFont() {
        return mainFontTf;
    }

    private static Typeface mainFontTf;
    private static Typeface mainFontLightTf;


    /**
     * Easily set the main font for a view
     * @param view
     */
    public static void setMainFont(View view){

        setFontNormal(view, Fonts.MAIN_FONT);
    }

    /**
     * Checks if the font is once load in app, if not loads it once for all
     * @param font
     * @param context
     * @return
     */
    private static Typeface loadFont(Fonts font, Context context) {

        switch (font){

            case MAIN_FONT:
                if (mainFontTf == null) {
                    mainFontTf = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile.ttf");
                }
                return mainFontTf;

            case MAIN_FONT_LIGHT:
                if (mainFontLightTf == null) {
                    mainFontLightTf = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile.ttf");
                }
                return mainFontLightTf;
            ///other fonts


            default:
                if (mainFontTf == null) {
                    mainFontTf = Typeface.createFromAsset(context.getAssets(), "IRANSansMobile.ttf");
                }
                return mainFontTf;
        }



    }

    /**
     * sets a font for a view with NORMAL Typeface
     * @param view
     * @param font
     */
    public static void setFontNormal(View view, Fonts font){

        setViewFont(view, loadFont(font, view.getContext()), Typeface.NORMAL);
    }

    /**
     * sets a font for a view with custom Typeface
     * type 'TypeFace.' to set NORMAL, ITALIC or BOLD
     * @param view
     * @param font
     * @param typeFace
     */
    public static void setFontWithTypeFace(View view, Fonts font, int typeFace){

        setViewFont(view, loadFont(font, view.getContext()), typeFace);
    }


    /**
     * you cannot set font for view, so we check if it is a valid view
     * @param view
     * @param tf
     * @param type
     */
    private static void setViewFont(View view, Typeface tf, int type) {

        if (view instanceof TextView)
            ((TextView) view).setTypeface(tf, type);

        else if (view instanceof EditText)
            ((EditText) view).setTypeface(tf, type);

        else if (view instanceof Button)
            ((Button) view).setTypeface(tf, type);

        else if (view instanceof CheckBox)
            ((CheckBox) view).setTypeface(tf, type);

        else if (view instanceof RadioButton)
            ((RadioButton) view).setTypeface(tf, type);

        else if (view instanceof CheckedTextView)
            ((CheckedTextView) view).setTypeface(tf, type);

    }


}