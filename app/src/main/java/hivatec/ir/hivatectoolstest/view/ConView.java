package hivatec.ir.hivatectoolstest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import hivatec.ir.hivatectools.hivaViewBinder.HivaConstraintLayout;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/5/18.
 */

public class ConView extends HivaConstraintLayout {


	private int image;
	private String title;
	private String desc;

	public ConView(Context context) {
		super(context);
	}

	public ConView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void getAttrSet(TypedArray a) {

		image = a.getResourceId(R.styleable.ConView_image, image);
		title = a.getString(R.styleable.ConView_title);
		desc = a.getString(R.styleable.ConView_desc);
	}

	@Override
	public void init() {

		getImageView().setImageResource(image);
		getTitleTextView().setText(title);
		getDescTextView().setText(desc);
	}

	public ImageView getImageView(){
		return find(R.id.imageView);
	}

	public TextView getTitleTextView(){
		return find(R.id.title);
	}

	public TextView getDescTextView(){
		return find(R.id.desc);
	}


	@Override
	public int getLayoutId() {
		return R.layout.item_con_view;
	}
}
