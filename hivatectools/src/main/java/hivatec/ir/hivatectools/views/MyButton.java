package hivatec.ir.hivatectools.views;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.RippleHelper;
import top.defaults.drawabletoolbox.DrawableBuilder;

public class MyButton extends RelativeLayout {


	TextView textView;
	ImageView imageView;
	LinearLayout stackView;
	FrameLayout rippleView;



	public MyButton(Context context) {
		super(context);

		init();
	}

	public MyButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		init();
	}


	private void init(){


		this.setClipToPadding(false);
		this.setClipChildren(false);
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){

			this.setClipBounds(new Rect(-9999, -9999, 9999, 9999));
		}

		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

			this.setClipToOutline(false);
		}




		textView = new TextView(getContext());
		imageView = new ImageView(getContext());
		stackView = new LinearLayout(getContext());
		rippleView = new FrameLayout(getContext());

		stackView.setOrientation(LinearLayout.VERTICAL);

		textView.setText("hello");
		imageView.setImageResource(R.drawable.ic_check);

		this.addView(rippleView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		this.addView(stackView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));

		stackView.addView(textView);
		stackView.addView(imageView);

		this.setClickable(true);
		//rippleView.setClickable(true);


		Drawable drawable = new DrawableBuilder()
				.rectangle()
				.hairlineBordered()
				.strokeColor(Color.GREEN)
				.strokeWidth(10)
				.solidColor(Color.MAGENTA)
				.strokeColorPressed(Color.RED)
				.ripple()
				.rippleColor(Color.YELLOW)
				.cornerRadius(50)
				.build();


		this.setBackground(drawable);

	}
}
