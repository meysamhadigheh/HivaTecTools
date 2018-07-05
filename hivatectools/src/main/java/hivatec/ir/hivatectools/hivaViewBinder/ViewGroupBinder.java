package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;

import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;

/**
 * Created by ashkan on 7/5/18.
 */

public abstract class ViewGroupBinder extends ViewGroup implements IViewBinder {

	public ViewBinderHelper binder;

	public ViewGroupBinder(Context context) {
		super(context);

		initView();
	}

	public ViewGroupBinder(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView();
	}


	private void initView() {
		binder = new ViewBinderHelper(this);
	}

	@Override
	public <T extends View> T find(int viewId) {
		return binder.find(viewId);
	}
}