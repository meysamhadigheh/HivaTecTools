package hivatec.ir.hivatectools.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.helper.ViewUIHelper;


/**
 * Created by ashkan on 2/8/18.
 */

public class HivaButton extends RelativeLayout {

    String title = "دکمه";
    int textColor = Color.WHITE;
    int textSize = ViewUIHelper.spToPx(15, getContext());
    String typeface = "";
	TextView textView;
	ImageView imageView;
	LinearLayout linearLayout;
	ProgressBar indicatorView;
	private CircularProgressDrawable circularDrawable;

	int radius = ViewUIHelper.dpToPx(5);

	int widthPadding = ViewUIHelper.dpToPx(0);
	int heightPadding = ViewUIHelper.dpToPx(0);

	boolean isWrapContent = false;

	int backgroundColor = getContext().getResources().getColor(R.color.colorPrimary);
	int backgroundDrawable = 0;
	int disabledDrawable = 0;
	int disabledBackground = Color.LTGRAY;
	int disabledForeground = Color.WHITE;
	int rippleColor = Color.parseColor("#44ffffff");

	int icon = 0;
	int space = -999;
	int iconTint = 0;
	int iconWidth = ViewUIHelper.dpToPx(0);
	int iconPosition = 0;


	Drawable currentRipple = null;

    public HivaButton(Context context) {
        super(context);

        init();
    }

    public HivaButton(Context context, AttributeSet attrs) {
        super(context, attrs);


        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HivaButton, 0, 0);

            title = a.getString(R.styleable.HivaButton_text);
            textColor = a.getColor(R.styleable.HivaButton_textColor, textColor);
            textSize = a.getDimensionPixelSize(R.styleable.HivaButton_textSize, textSize);
            radius = a.getDimensionPixelSize(R.styleable.HivaButton_radius, radius);
            widthPadding = a.getDimensionPixelSize(R.styleable.HivaButton_widthPadding, widthPadding);
            heightPadding = a.getDimensionPixelSize(R.styleable.HivaButton_heightPadding, heightPadding);
            typeface = a.getString(R.styleable.HivaButton_typeface);

			backgroundColor = a.getColor(R.styleable.HivaButton_backgroundColor, backgroundColor);
			backgroundDrawable = a.getResourceId(R.styleable.HivaButton_backgroundDrawable, backgroundDrawable);
			rippleColor = a.getResourceId(R.styleable.HivaRadioView_rippleColor, ColorUtils.setAlphaComponent(textColor, (int) Math.round(255 * 0.5)));

			disabledBackground = a.getColor(R.styleable.HivaButton_disabledBackground, disabledBackground);
			disabledForeground = a.getColor(R.styleable.HivaButton_disabledForeground, disabledForeground);
			disabledDrawable = a.getResourceId(R.styleable.HivaButton_disabledDrawable, disabledDrawable);

			iconWidth = a.getDimensionPixelSize(R.styleable.HivaButton_iconWidth, iconWidth);
			space = a.getDimensionPixelSize(R.styleable.HivaButton_space, space);
			icon = a.getResourceId(R.styleable.HivaButton_icon, icon);
			iconPosition = a.getInt(R.styleable.HivaButton_iconPosition, iconPosition);
			iconTint = a.getColor(R.styleable.HivaButton_iconTint, textColor);

			a.recycle();
        }

        init();
    }


    void init() {

    	this.setClickable(true);

        textView = new TextView(getContext());
        textView.setText(title);
        //textView.setBackgroundColor(Color.GREEN);
        textView.setSingleLine();
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));

        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(textColor);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        if(typeface != null && !typeface.equals("")) {
        	try {
				Typeface t = Typeface.createFromAsset(getContext().getAssets(), String.format("fonts/%s", typeface));
				textView.setTypeface(t);
			}catch (Exception e){
			}
		}

		//set indicator
		indicatorView = new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall);
		circularDrawable = new CircularProgressDrawable(getContext());

		circularDrawable.setStrokeCap(Paint.Cap.ROUND);
		circularDrawable.setColorFilter(new
				PorterDuffColorFilter(textColor, PorterDuff.Mode.SRC_ATOP));

		indicatorView.setAlpha(0.9f);

		indicatorView.setIndeterminateDrawable(circularDrawable);
		LayoutParams indicatorViewParams = null;

		indicatorViewParams = new LayoutParams(
				ViewUIHelper.dpToPx(25) ,
				ViewUIHelper.dpToPx(25) );

		circularDrawable.setStrokeWidth(ViewUIHelper.dpToPx(4));

//		if(iconWidth == 0) {
//			indicatorViewParams = new LayoutParams(
//					ViewUIHelper.dpToPx(25) ,
//					ViewUIHelper.dpToPx(25) );
//
//			circularDrawable.setStrokeWidth(ViewUIHelper.dpToPx(4));
//
//		}else{
//			indicatorViewParams = new LayoutParams(
//					iconWidth,
//					iconWidth);
//
//			circularDrawable.setStrokeWidth(ViewUIHelper.dpToPx(4));
//		}

		indicatorViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);

		indicatorView.setVisibility(GONE);
		indicatorView.setLayoutParams(indicatorViewParams);


		linearLayout = new LinearLayout(getContext());

		//linearLayout.setBackgroundColor(Color.RED);

		LayoutParams linearLayoutLayoutParams =
				new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
						ViewGroup.LayoutParams.WRAP_CONTENT);

		linearLayoutLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		linearLayout.setLayoutParams(linearLayoutLayoutParams);
		linearLayout.setClipToPadding(false);
		linearLayout.setClipChildren(false);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			linearLayout.setClipToOutline(false);
		}


		imageView = new ImageView(getContext());
		//imageView.setBackgroundColor(Color.YELLOW);
		Space spaceView = new Space(getContext());

		if(icon != 0 && iconWidth == 0){
			imageView.setLayoutParams(new LayoutParams(textSize,textSize));

		}else{
			imageView.setLayoutParams(new LayoutParams(iconWidth,iconWidth));
		}
		imageView.setImageResource(icon);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		if(iconTint != Color.TRANSPARENT) {
			imageView.setColorFilter(iconTint, PorterDuff.Mode.SRC_IN);
		}


		if(icon != 0 && space == -999){
			space = textSize / 3;
		}


		linearLayout.setGravity(Gravity.CENTER);

		switch (iconPosition) {
			case 0 : //right
			default:
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				spaceView.setLayoutParams(new LayoutParams(space,1));

				break;
			case 1 : //left
				linearLayout.setOrientation(LinearLayout.HORIZONTAL);
				spaceView.setLayoutParams(new LayoutParams(space,1));
				break;

			case 2: //top
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				spaceView.setLayoutParams(new LayoutParams(1,space));
				break;
			case 3 :
				linearLayout.setOrientation(LinearLayout.VERTICAL);
				spaceView.setLayoutParams(new LayoutParams(1,space));
				break;
		}

		if(widthPadding > 0  && linearLayout.getOrientation() == LinearLayout.HORIZONTAL){
			Space widthPaddingSapce = new Space(getContext());
			widthPaddingSapce.setLayoutParams(new LayoutParams(widthPadding,1));
			linearLayout.addView(widthPaddingSapce);
		}

		if(heightPadding > 0  && linearLayout.getOrientation() == LinearLayout.VERTICAL){
			Space heightPaddingSapce = new Space(getContext());
			heightPaddingSapce.setLayoutParams(new LayoutParams(1,heightPadding));
			linearLayout.addView(heightPaddingSapce);
		}

		switch (iconPosition) {
			case 0 : //right
			default:
				linearLayout.addView(textView);
				linearLayout.addView(spaceView);
				linearLayout.addView(imageView);
				break;
			case 1 : //left
				linearLayout.addView(imageView);
				linearLayout.addView(spaceView);
				linearLayout.addView(textView);
				break;

			case 2: //top
				linearLayout.addView(imageView);
				linearLayout.addView(spaceView);
				linearLayout.addView(textView);
				break;
			case 3 :
				linearLayout.addView(textView);
				linearLayout.addView(spaceView);
				linearLayout.addView(imageView);
				break;
		}

		if(title.equals("")){
			textView.setVisibility(GONE);
		}


		if(widthPadding > 0 && linearLayout.getOrientation() == LinearLayout.HORIZONTAL){
			View widthPaddingSapce = new View(getContext());
			widthPaddingSapce.setLayoutParams(new LayoutParams(widthPadding,1));
			linearLayout.addView(widthPaddingSapce);
		}

		if(heightPadding > 0 && linearLayout.getOrientation() == LinearLayout.VERTICAL){
			View heightPaddingSapce = new View(getContext());
			heightPaddingSapce.setLayoutParams(new LayoutParams(1, heightPadding));
			linearLayout.addView(heightPaddingSapce);
		}

		if(linearLayout.getOrientation() == LinearLayout.HORIZONTAL) {
			ViewUIHelper.setPaddingPx(this, 0, heightPadding, 0, heightPadding);
		}

		if(linearLayout.getOrientation() == LinearLayout.VERTICAL) {
			ViewUIHelper.setPaddingPx(this, widthPadding, 0, widthPadding, 0);
		}



		this.addView(indicatorView, indicatorViewParams);
        this.addView(linearLayout);

		this.setClipToPadding(false);
		this.invalidate();


		if(backgroundColor == Color.TRANSPARENT) {

			currentRipple = RippleHelper.getRippleDrawableForTransparentColor(rippleColor, radius);

		}else if(backgroundDrawable == 0){

			currentRipple = RippleHelper.getRippleDrawableByColor(backgroundColor, radius, rippleColor);
		}else{

			currentRipple = RippleHelper.getRippleDrawableByDrawable(
					getContext().getResources().getDrawable(backgroundDrawable), rippleColor);
		}

		this.setBackground(currentRipple);

	}

    public void startLoadingState(){

		linearLayout.setVisibility(INVISIBLE);
		indicatorView.setVisibility(VISIBLE);
		indicatorView.setIndeterminate(false);
        this.setClickable(false);
    }

    public void setTitle(String setTitle){

		title = setTitle;
		textView.setVisibility(VISIBLE);
		textView.setText(setTitle);
    }

    public void stopLoadingState(){

		linearLayout.setVisibility(VISIBLE);
		indicatorView.setVisibility(GONE);
		indicatorView.setIndeterminate(true);

        this.setClickable(true);
    }

    public void setForegroundColor(int color){

    	this.textView.setTextColor(color);
		this.imageView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
		this.circularDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
	}

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if(enabled == true){

			enable();
		}else {

        	disable();
		}
	}


	private void enable(){

		this.setBackground(currentRipple);
		this.setForegroundColor(textColor);

	}

	private void disable(){

    	if(disabledDrawable == 0) {
			this.setBackground(RippleHelper.getRippleDrawableByColor(disabledBackground, radius));
		}else{
    		setBackground(RippleHelper.getRippleDrawableByDrawable(
					getContext().getResources().getDrawable(disabledDrawable), Color.TRANSPARENT));
		}

		this.setForegroundColor(disabledForeground);
	}
}
