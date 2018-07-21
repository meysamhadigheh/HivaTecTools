package hivatec.ir.hivatectools.hivaActionBarView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
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
	}

}


