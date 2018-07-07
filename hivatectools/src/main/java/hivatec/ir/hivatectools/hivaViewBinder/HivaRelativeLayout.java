package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by ashkan on 7/5/18.
 */

public abstract class HivaRelativeLayout extends RelativeLayout implements IViewBinder {

	public ViewBinderHelper binder;

	public HivaRelativeLayout(Context context) {
		super(context);

		initView(null);
	}

	public HivaRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(attrs);
	}


	private void initView(AttributeSet attrs) {
		binder = new ViewBinderHelper(this);

		if(attrs != null){
			binder.setAttributeSet(attrs);
			TypedArray a = binder.getTypedArray();
			getAttrSet(a);
			a.recycle();
		}

		init();
	}

	@Override
	public <T extends View> T find(int viewId) {
		return binder.find(viewId);
	}

}
