package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by ashkan on 7/5/18.
 */

public abstract class LinearLayoutBinder extends LinearLayout implements IViewBinder {

	public ViewBinderHelper binder;

	public LinearLayoutBinder(Context context) {
		super(context);

		initView(null);
	}

	public LinearLayoutBinder(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(attrs);

	}


	private void initView(AttributeSet attrs) {
		binder = new ViewBinderHelper(this);

		if(attrs != null){
			binder.setAttributeSet(attrs);
			getAttrSet(binder);
		}

		init();
	}

	@Override
	public <T extends View> T find(int viewId) {
		return binder.find(viewId);
	}

}
