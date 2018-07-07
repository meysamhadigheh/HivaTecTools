package hivatec.ir.hivatectoolstest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import hivatec.ir.hivatectools.hivaViewBinder.HivaConstraintLayout;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/7/18.
 */

public class GalleryView extends HivaConstraintLayout {

	String title = "";
	String desc = "";
	int image = 0;
	int titleTextColor = 0;

	public GalleryView(Context context) {
		super(context);
	}

	public GalleryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void getAttrSet(TypedArray a) {

		title = a.getString(R.styleable.GalleryView_title);
		desc = a.getString(R.styleable.GalleryView_desc);
		image = a.getResourceId(R.styleable.GalleryView_image, image);
		titleTextColor = a.getColor(R.styleable.GalleryView_title_textColor, titleTextColor);
	}

	@Override
	public void init() {

		getTitleView().setText(title);
		getTitleView().setTextColor(titleTextColor);
		getDescView().setText(desc);
		getImageView().setImageResource(image);
	}


	public TextView getTitleView(){
		return find(R.id.title);
	}

	public TextView getDescView(){
		return find(R.id.desc);
	}

	public ImageView getImageView(){
		return find(R.id.image);
	}


	@Override
	public int getLayoutId() {
		return R.layout.item_gallery_view;
	}
}
