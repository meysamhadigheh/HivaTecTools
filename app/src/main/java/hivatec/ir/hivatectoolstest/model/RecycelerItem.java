package hivatec.ir.hivatectoolstest.model;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;

import hivatec.ir.hivatectools.adapters.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

public class RecycelerItem implements ItemBinder {

	HivaRecyclerAdapter adapter;
	ArrayList items = new ArrayList();

	public RecycelerItem(){


		for(int i = 0; i < 50; i++){
			items.add(new ButtonModel("button " + i));
		}

		adapter = new HivaRecyclerAdapter(items);

	}

	@Override
	public int getResourceId() {
		return R.layout.item_recycler;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

		binder.<RecyclerView>find(R.id.recycler).setAdapter(adapter);
		binder.<RecyclerView>find(R.id.recycler).setLayoutManager(new LinearLayoutManager(binder.context, LinearLayoutManager.HORIZONTAL, false));
	}
}
