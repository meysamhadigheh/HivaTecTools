package hivatec.ir.hivatectoolstest.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import hivatec.ir.hivatectools.hivaViewBinder.LinearLayoutBinder;
import hivatec.ir.hivatectools.hivaViewBinder.ViewBinderHelper;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/5/18.
 */

public class BlueView extends LinearLayoutBinder {


	int icon = 0;
	String title = "";
	int backgroundColor = Color.parseColor("#ff00ff");
	int textColor = 0;

	public BlueView(Context context) {
		super(context);
	}

	public BlueView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void getAttrSet(ViewBinderHelper binder) {

		icon = binder.getResource("icon", 0);
		title = binder.getStringResource("title");
		backgroundColor = binder.getColorResource("backgroundColor", backgroundColor);
		textColor = binder.getColorResource("textColor", textColor);
	}

	@Override
	public void init() {

		getTextView().setText(title);
		getTextView().setTextColor(textColor);
		getIconView().setImageResource(icon);

		this.setBackgroundColor(Color.parseColor("#ff00ff"));
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

	@Override
	public String getClassName() {
		return "BlueView";
	}
}
