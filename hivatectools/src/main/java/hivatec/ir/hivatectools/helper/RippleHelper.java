package hivatec.ir.hivatectools.helper;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.StateSet;

/**
 * Created by ashkan on 3/6/18.
 */

public class RippleHelper {

	public static Drawable getRippleDrawableForTransparentColor(int color, int radius)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ColorStateList pressedColor = ColorStateList.valueOf(color); //lightenOrDarken(color, 0.3D));
			Drawable shape =  getRoundDrawable(Color.TRANSPARENT, radius);
			Drawable rippleColor = getRoundDrawable(color, radius);

			RippleDrawable ripple =  new RippleDrawable(
					pressedColor,
					shape,
					rippleColor
			);

			return ripple;

		}else{

			return getDrawableFromColor( Color.TRANSPARENT, color, radius);
		}
	}

	public static Drawable getRippleDrawableByColor(int color, int radius)
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ColorStateList pressedColor = ColorStateList.valueOf(lightenOrDarken(color, 0.3D));
			Drawable shape =  getRoundDrawable(color, radius);
			Drawable rippleColor = getRoundDrawable(color, radius);
			return new RippleDrawable(
					pressedColor,
					shape,
					rippleColor
			);

		}else{

			return getDrawableFromColor(color, lightenOrDarken(color, 0.2D), radius);
		}
	}

	public static Drawable getRippleDrawableByDrawable(Drawable drawable, int pressedColor){

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ColorStateList pressedColorState = ColorStateList.valueOf(pressedColor);
			Drawable shape =   drawable;
			Drawable rippleColor = drawable;
			return new RippleDrawable(
					pressedColorState,
					shape,
					rippleColor
			);

		}else{

			return drawable;
		}
	}

	public static Drawable getRippleMask(int radius, int color) {
		GradientDrawable shape =  new GradientDrawable();
		shape.setCornerRadius( radius);
		shape.setColor(color);

		return shape;
	}

	public static ColorStateList getPressedColorSelector(int normalColor, int pressedColor)
	{
		return ColorStateList.valueOf(pressedColor);
	}

	public static ColorDrawable getColorDrawableFromColor(int color)
	{
		return new ColorDrawable(color);
	}

	public static Drawable getDrawableFromColor(int color, int pressed, int radius)
	{
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, getRoundDrawable(pressed, radius));
		drawable.addState(StateSet.WILD_CARD, getRoundDrawable(color, radius));

		return drawable;
	}


	private static Drawable getRoundDrawable(int color, int radius) {
		GradientDrawable shape =  new GradientDrawable();
		shape.setCornerRadius( radius );
		shape.setColor(color);

		return shape;
	}


	/// lighten darken
	private static int lightenOrDarken(int color, double fraction) {
		if (canLighten(color, fraction)) {
			return lighten(color, fraction);
		} else {
			return darken(color, fraction);
		}
	}

	private static int lighten(int color, double fraction) {
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		red = lightenColor(red, fraction);
		green = lightenColor(green, fraction);
		blue = lightenColor(blue, fraction);
		int alpha = Color.alpha(color);
		return Color.argb(alpha, red, green, blue);
	}

	private static int darken(int color, double fraction) {
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		red = darkenColor(red, fraction);
		green = darkenColor(green, fraction);
		blue = darkenColor(blue, fraction);
		int alpha = Color.alpha(color);

		return Color.argb(alpha, red, green, blue);
	}

	private static boolean canLighten(int color, double fraction) {
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return canLightenComponent(red, fraction)
				&& canLightenComponent(green, fraction)
				&& canLightenComponent(blue, fraction);
	}

	private static boolean canLightenComponent(int colorComponent, double fraction) {
		int red = Color.red(colorComponent);
		int green = Color.green(colorComponent);
		int blue = Color.blue(colorComponent);
		return red + (red * fraction) < 255
				&& green + (green * fraction) < 255
				&& blue + (blue * fraction) < 255;
	}

	private static int darkenColor(int color, double fraction) {
		return (int) Math.max(color - (color * fraction), 0);
	}

	private static int lightenColor(int color, double fraction) {
		return (int) Math.min(color + (color * fraction), 255);
	}
}
