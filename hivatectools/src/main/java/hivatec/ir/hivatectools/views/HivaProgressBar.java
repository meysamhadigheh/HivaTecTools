package hivatec.ir.hivatectools.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.ViewUIHelper;

/**
 * Created by ashkan on 4/24/18.
 */

public class HivaProgressBar extends ProgressBar {


	int tintColor = 0;
	int lineWidth = 0;

	public HivaProgressBar(Context context) {
		super(context);

		init();
	}

	public HivaProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);


		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HivaProgressBar, 0, 0);

			tintColor = a.getColor(R.styleable.HivaProgressBar_tintColor, tintColor);
			lineWidth = a.getDimensionPixelSize(R.styleable.HivaProgressBar_lineWidth, lineWidth);

			a.recycle();
		}

		init();
	}

	void init(){

		if(tintColor == 0) {
			tintColor = getContext().getResources().getColor(R.color.colorPrimary);
		}
		if(lineWidth == 0) {
			lineWidth = ViewUIHelper.dpToPx(4);
		}

		CircularProgressDrawable circularDrawable = new CircularProgressDrawable(getContext());

		circularDrawable.setStrokeWidth(lineWidth);
		circularDrawable.setStrokeCap(Paint.Cap.ROUND);
		circularDrawable.setColorFilter(new
				PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_ATOP));

		this.setIndeterminateDrawable(circularDrawable);

	}
}
