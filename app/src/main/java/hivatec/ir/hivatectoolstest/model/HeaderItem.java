package hivatec.ir.hivatectoolstest.model;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/26/18.
 */

public class HeaderItem implements ItemBinder {


	@Override
	public int getResourceId() {
		return R.layout.item_header;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

	}
}
