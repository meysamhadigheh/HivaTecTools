package hivatec.ir.hivatectoolstest.model;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

public class EmptyItem implements ItemBinder {
	@Override
	public int getResourceId() {
		return R.layout.item_empty_big;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

	}
}
