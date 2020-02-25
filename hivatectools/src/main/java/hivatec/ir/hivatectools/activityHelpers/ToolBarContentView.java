package hivatec.ir.hivatectools.activityHelpers;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hivatec.ir.hivatectools.R;

/**
 * Created by ashkan on 7/11/18.
 */

public class ToolBarContentView extends RelativeLayout {

	public TextView titleTextView;
	public ImageButton leftButton;
	public ImageButton rightButton1;
	public ImageButton rightButton2;

	public int tintColor = Color.WHITE;

	public ToolBarContentView(Context context) {
		super(context);
		init();
	}

	public ToolBarContentView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = LayoutInflater.from(getContext());
		inflater.inflate(R.layout.toolbar_view, this);

		this.titleTextView=findViewById(R.id.actionbar_title);
		this.leftButton=findViewById(R.id.leftButton);
		this.rightButton1=findViewById(R.id.rightButton1);
		this.rightButton2=findViewById(R.id.rightButton2);

		this.titleTextView.setTextColor(tintColor);
		this.leftButton.setColorFilter(tintColor);
		this.rightButton1.setColorFilter(tintColor);
		this.rightButton2.setColorFilter(tintColor);
	}

	public void setTintColor(int tintColor){

		this.tintColor = tintColor;

		this.titleTextView.setTextColor(tintColor);
		this.leftButton.setColorFilter(tintColor);
		this.rightButton1.setColorFilter(tintColor);
		this.rightButton2.setColorFilter(tintColor);
	}

}



