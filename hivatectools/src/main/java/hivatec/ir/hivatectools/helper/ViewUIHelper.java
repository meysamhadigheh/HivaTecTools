package hivatec.ir.hivatectools.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;


public class ViewUIHelper {

    /**
     * set width and height for view
     * @param view
     * @param width
     * @param height
     */
    public static void setWidthHeightPixel(View view, int width, int height){

        view.getLayoutParams().width = width;
        view.getLayoutParams().height = height;
    }


    /**
     * set width and height for view
     * @param view
     * @param width
     * @param height
     */
    public static void setWidthHeightPixel(View view, float width, float height){

        view.getLayoutParams().width = (int) width;
        view.getLayoutParams().height = (int) height;
    }




    /**
     * set margin for view
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */

    public static void setMarginDp(View view, int left, int top, int right, int bottom){

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        if(params == null)
            return;

        left = dpToPx(left);
        top = dpToPx(top);
        right = dpToPx(right);
        bottom = dpToPx(bottom);

        params.setMargins(left, top, right, bottom);

        view.requestLayout();
    }


    public static void setMarginPx(View view, int left, int top, int right, int bottom){

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();

        if(params == null)
            return;

        params.setMargins(left, top, right, bottom);

        view.requestLayout();
    }


    /**
     * set padding for view
     * @param view
     * @param padding
     */
    public static void setPadding(View view, int padding) {

        setPadding(view, padding, padding, padding, padding);
    }

    /**
     * set padding with custom size for view
     * @param view
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public static void setPadding(View view, int left, int top, int right, int bottom){

        left = dpToPx(left);
        top = dpToPx(top);
        right = dpToPx(right);
        bottom = dpToPx(bottom);

        view.setPadding(left, top, right, bottom);
    }

    /**
     * convert dp to px
     * @param dp
     * @return
     */
    public static int dpToPx(int dp)
    {
        if(G.context != null)
            return (int) (dp * G.context.getResources().getSystem().getDisplayMetrics().density);

        return dp;
    }

    /**
     * convert px to dp
     * @param px
     * @return
     */
    public static int pxToDp(int px)
    {
        if(G.context != null)
            return (int) (px / G.context.getResources().getSystem().getDisplayMetrics().density);

        return px;
    }

    /**
     * convert sp to dp
     * @param sp
     * @return
     */
    public static int spToPx(float sp, Context context) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                context.getResources().getDisplayMetrics());
        return px;
    }

    /**
     * convert dp to sp
     * @param dp
     * @return
     */
    public static int dpToSp(int dp, Context context) {
        int sp = (int) (dpToPx(dp) / (float) spToPx(dp, context));
        return sp;
    }


    /**
     * convert sp to dp
     * @param sp
     * @return
     */
    public static int spToDp(int sp, Context context) {
        int dp = (int) ((float) spToPx(sp, context) / dpToPx(sp));
        return dp;
    }

    /**
     * set background for view with protected padding
     * @param view
     * @param resId
     */
    public static void setBackgroundResource(@NonNull View view, @DrawableRes int resId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            int paddingTop = view.getPaddingTop();
            int paddingLeft = view.getPaddingLeft();
            int paddingRight = view.getPaddingRight();
            int paddingBottom = view.getPaddingBottom();
            view.setBackgroundResource(resId);
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
        } else {
            view.setBackgroundResource(resId);
        }
    }
    public static void hideError(@NonNull View view) {

            view.setVisibility(View.GONE);

    }



    public static Bitmap scaleCenterCrop(Bitmap source, int newHeight, int newWidth) {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        // Compute the scaling factors to fit the new height and width, respectively.
        // To cover the final image, the final scaling will be the bigger
        // of these two.
        float xScale = (float) newWidth / sourceWidth;
        float yScale = (float) newHeight / sourceHeight;
        float scale = Math.max(xScale, yScale);

        // Now get the size of the source bitmap when scaled
        float scaledWidth = scale * sourceWidth;
        float scaledHeight = scale * sourceHeight;

        // Let's find out the upper left coordinates if the scaled bitmap
        // should be centered in the new size give by the parameters
        float left = (newWidth - scaledWidth) / 2;
        float top = (newHeight - scaledHeight) / 2;

        // The target rectangle for the new, scaled version of the source bitmap will now
        // be
        RectF targetRect = new RectF(left, top, left + scaledWidth, top + scaledHeight);

        // Finally, we create a new bitmap of the specified size and draw our new,
        // scaled bitmap onto it.
        Bitmap dest = Bitmap.createBitmap(newWidth, newHeight, source.getConfig());
        Canvas canvas = new Canvas(dest);
        canvas.drawBitmap(source, null, targetRect, null);

        return dest;
    }




}
