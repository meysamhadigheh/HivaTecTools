package hivatec.ir.hivatectoolstest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import hivatec.ir.hivatectools.hivaViewBinder.ViewBinder;
import hivatec.ir.hivatectools.hivaViewBinder.ViewBinderHelper;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/5/18.
 */

public class BlueView extends View implements ViewBinder {




	ViewBinderHelper binder;

	public BlueView(Context context) {
		super(context);
	}

	public BlueView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void init() {

	}

	@Override
	public int getLayoutId() {
		return R.layout.item_blue_view;
	}

	@Override
	public ViewBinderHelper bindToHelper() {
		binder = new ViewBinderHelper(this);
		return binder;
	}
}
