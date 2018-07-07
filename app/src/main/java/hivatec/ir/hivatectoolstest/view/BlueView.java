package hivatec.ir.hivatectoolstest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import hivatec.ir.hivatectools.hivaViewBinder.HivaLinearLayout;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/5/18.
 */

public class BlueView extends HivaLinearLayout {


	int icon = 0;
	String title = "";
	int backgroundColor = Color.parseColor("#ff00ff");
	int textColor = 0;
	int textSize = 0;

	public BlueView(Context context) {
		super(context);
	}

	public BlueView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void getAttrSet(TypedArray a) {
		icon = a.getResourceId(R.styleable.BlueView_icon, icon);
		title = a.getString(R.styleable.BlueView_title);
		backgroundColor = a.getColor(R.styleable.BlueView_backgroundColor, backgroundColor);
		textColor = a.getColor(R.styleable.BlueView_textColor, textColor);
		textSize = a.getDimensionPixelSize(R.styleable.BlueView_textSize, textSize);
	}

	@Override
	public void init() {

		getTextView().setText(title);
		getTextView().setTextColor(textColor);
		getTextView().setTextSize(textSize);
		getIconView().setImageResource(icon);

		this.setBackgroundColor(backgroundColor);
	}

	public TextView getTextView(){

		return find(R.id.textView);
	}

	public ImageView getIconView(){

		return find(R.id.icon);
	}

	@Override
	public int getLayoutId() {
		return R.layout.item_blue_view;
	}

}
