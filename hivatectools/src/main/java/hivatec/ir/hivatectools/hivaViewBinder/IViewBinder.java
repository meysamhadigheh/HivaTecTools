package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.res.TypedArray;
import android.view.View;

/**
 * Created by ashkan on 7/5/18.
 */

public interface IViewBinder {

	void getAttrSet(TypedArray a);
	void init();
	int getLayoutId();
	<T extends View> T find(int viewId);
}
