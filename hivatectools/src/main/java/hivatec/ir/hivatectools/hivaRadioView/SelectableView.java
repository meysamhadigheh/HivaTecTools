package hivatec.ir.hivatectools.hivaRadioView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.hivaViewBinder.HivaLinearLayout;
import hivatec.ir.hivatectools.hivaViewBinder.HivaRelativeLayout;

/**
 * Created by ashkan on 7/7/18.
 */

public class SelectableView extends RelativeLayout {

	ImageView iconOn;
	ImageView iconOff;
	TextView textView;
	View dividerView;
	RelativeLayout container;

	public SelectableView(Context context) {
		super(context);

		init();
	}

	public SelectableView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	public void init() {

		LayoutInflater inflater = LayoutInflater.from(getContext());
		inflater.inflate(R.layout.item_radio, this);

		this.iconOn = findViewById(R.id.iconOn);
		this.iconOff = findViewById(R.id.iconOff);
		this.textView = findViewById(R.id.text);
		this.dividerView = findViewById(R.id.divider);
		this.container = findViewById(R.id.container);
	}

	public ImageView getIconOnView(){
		return iconOn;
	}

	public ImageView getIconOffView(){
		return iconOff;
	}

	public TextView getTextView(){
		return textView;
	}

	public View getDividerView(){
		return dividerView;
	}

	public RelativeLayout getContainerView(){
		return container;
	}

}
