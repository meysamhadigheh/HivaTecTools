package hivatec.ir.hivatectools.hivaViewBinder;

import android.view.View;

import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;

/**
 * Created by ashkan on 7/5/18.
 */

public interface ViewBinder {

	void init();
	int getLayoutId();
	ViewBinderHelper bindToHelper();
}
