package hivatec.ir.hivatectoolstest.model;

import android.widget.TextView;

import hivatec.ir.hivatectools.hivaAdapter.ItemBinder;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;
import hivatec.ir.hivatectoolstest.R;

/**
 * Created by ashkan on 6/28/18.
 */

public class Movie implements ItemBinder {

	String name;
	String stars;


	public Movie(String name, String stars) {
		this.name = name;
		this.stars = stars;
	}

	@Override
	public int getResourceId() {
		return R.layout.item_movie;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

		binder.<TextView>find(R.id.movie_name).setText(name);
		binder.<TextView>find(R.id.movie_stars).setText(stars);
	}
}
