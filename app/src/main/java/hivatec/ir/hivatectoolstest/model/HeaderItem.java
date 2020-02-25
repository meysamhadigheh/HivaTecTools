package hivatec.ir.hivatectoolstest.model;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;

import java.util.Random;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 7/26/18.
 */

public class HeaderItem implements ItemBinder {


	int color = 0;

	public HeaderItem(){
		Random rnd = new Random();
		color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
	}

	@Override
	public int getResourceId() {
		return R.layout.item_header;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

		binder.itemView.setBackgroundColor(color);
	}
}
