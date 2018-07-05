package hivatec.ir.hivatectools.hivaViewBinder;

import android.view.View;

/**
 * Created by ashkan on 7/5/18.
 */

public interface IViewBinder {

	void getAttrSet(ViewBinderHelper binder);
	void init();
	int getLayoutId();
	String getClassName();
	<T extends View> T find(int viewId);
}
