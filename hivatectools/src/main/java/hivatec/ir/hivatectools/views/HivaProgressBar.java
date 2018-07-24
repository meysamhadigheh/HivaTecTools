package hivatec.ir.hivatectools.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.ViewUIHelper;

/**
 * Created by ashkan on 4/24/18.
 */

public class HivaProgressBar extends ProgressBar {

	public HivaProgressBar(Context context) {
		super(context);
		init();
	}

	public HivaProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init(){


		CircularProgressDrawable circularDrawable = new CircularProgressDrawable(getContext());

		circularDrawable.setStrokeWidth(ViewUIHelper.dpToPx(4));
		circularDrawable.setStrokeCap(Paint.Cap.ROUND);
		circularDrawable.setColorFilter(new
				PorterDuffColorFilter(getContext().getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP));

		this.setAlpha(0.8f);
		this.setIndeterminateDrawable(circularDrawable);

	}
}
