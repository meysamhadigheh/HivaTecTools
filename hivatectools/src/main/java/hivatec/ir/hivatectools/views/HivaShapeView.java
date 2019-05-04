package hivatec.ir.hivatectools.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Space;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.ViewUIHelper;
import top.defaults.drawabletoolbox.DrawableBuilder;


/**
 * Created by ashkan on 2/8/18.
 */

public class HivaShapeView extends FrameLayout {


	
	//global settings
	int radius = 0;
	boolean rounded = false;
	int strokeWidth = 0;
	int topLeftCorner = 0;
	int bottomLeftCorner = 0;
	int topRightCorner = 0;
	int bottomRightCorner = 0;

	//on state
	int backgroundColor = 0;
	int backgroundDrawable = 0;
	int backgroundSecondColor = 0;
	int gradientAngle = 0;
	int rippleColor = 0;
	int strokeColor = 0;
	int strokeDashGap = 0;
	int strokePressedColor = 0;
	
	//extra
	Drawable currentBackground = null;



	public HivaShapeView(Context context) {
		super(context);

		init();
	}

	public HivaShapeView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}


	public HivaShapeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HivaShapeView, defStyle, 0);

			radius = a.getDimensionPixelSize(R.styleable.HivaShapeView_radius, radius);
			rounded = a.getBoolean(R.styleable.HivaShapeView_rounded, rounded);

			backgroundColor = a.getColor(R.styleable.HivaShapeView_backgroundColor, backgroundColor);
			backgroundDrawable = a.getResourceId(R.styleable.HivaShapeView_backgroundDrawable, backgroundDrawable);
			rippleColor = a.getColor(R.styleable.HivaShapeView_rippleColor, rippleColor);

			backgroundSecondColor = a.getColor(R.styleable.HivaShapeView_backgroundSecondColor, backgroundSecondColor);
			gradientAngle = a.getInt(R.styleable.HivaShapeView_gradientAgnle, 90);
			
			strokeColor = a.getColor(R.styleable.HivaShapeView_strokeColor, strokeColor);
			strokePressedColor = a.getColor(R.styleable.HivaShapeView_strokePressedColor, strokeColor);
			strokeWidth = a.getDimensionPixelSize(R.styleable.HivaShapeView_strokeWidth, strokeWidth);
			strokeDashGap = a.getInt(R.styleable.HivaShapeView_strokeDashGap, strokeDashGap);

			topLeftCorner = a.getDimensionPixelSize(R.styleable.HivaShapeView_topLeftCorner, topLeftCorner);
			bottomLeftCorner = a.getDimensionPixelSize(R.styleable.HivaShapeView_bottomLeftCorner, bottomLeftCorner);
			topRightCorner = a.getDimensionPixelSize(R.styleable.HivaShapeView_topRightCorner, topRightCorner);
			bottomRightCorner = a.getDimensionPixelSize(R.styleable.HivaShapeView_bottomRightCorner, bottomRightCorner);
			
			a.recycle();
		}

		init();
	}




	//////////////////
	/// main functions
	/////////////////

	void init() {
		

		//this.setClipToPadding(false);

		createcurrentBackground();
		
		setBackground(currentBackground);

		this.invalidate();
	}

	private void createcurrentBackground(){
		DrawableBuilder builder = new DrawableBuilder();

		builder.rectangle()
				.strokeColor(strokeColor)
				.strokeWidth(strokeWidth)
				.strokeColorPressed(strokePressedColor);

		if(backgroundSecondColor != 0){

			builder.gradient()
					.linearGradient()
					.startColor(backgroundSecondColor)
					.endColor(backgroundColor)
					.angle(gradientAngle);
		}else{

			builder.solidColor(backgroundColor);
		}

		if(strokeDashGap > 0) {
			builder.dashWidth(strokeDashGap)
					.dashGap(strokeDashGap);
		}

		if(rounded){
			builder.rounded();

		} else {

			topLeftCorner = topLeftCorner != 0 ? topLeftCorner : radius;
			topRightCorner = topRightCorner != 0 ? topRightCorner : radius;
			bottomLeftCorner = bottomLeftCorner != 0 ? bottomLeftCorner : radius;
			bottomRightCorner = bottomRightCorner != 0 ? bottomRightCorner : radius;

			builder.cornerRadii(topLeftCorner, topRightCorner, bottomRightCorner, bottomLeftCorner);
		}

		if(rippleColor > 0) {
			builder.ripple().rippleColor(rippleColor);
			this.setClickable(true);

			if(backgroundColor == Color.TRANSPARENT) {
				builder.solidColorPressedWhenRippleUnsupported(rippleColor);
			}else {
				builder.solidColorPressedWhenRippleUnsupported(ColorUtils.setAlphaComponent(backgroundColor,(int) Math.round(255 * 0.7)));
			}
		}


		//builder.


		if(backgroundDrawable != 0){
			builder.baseDrawable(getContext().getResources().getDrawable(backgroundDrawable));
		}

		currentBackground = builder.build();

	}

}
