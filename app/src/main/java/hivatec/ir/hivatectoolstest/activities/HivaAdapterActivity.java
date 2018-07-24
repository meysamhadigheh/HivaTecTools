package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import hivatec.ir.hivatectools.hivaAdapter.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolderIterator;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.Movie;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class HivaAdapterActivity extends AppCompatActivity {

	RecyclerView recyclerView;
	HivaRecyclerAdapter adapter;

	ArrayList<Movie> movies = new ArrayList<Movie>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hiva_adapter);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		recyclerView = findViewById(R.id.recycler);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

		String lord="http://www.taosmemory.com/movies/poster/2002/51.jpg";
		String titanic="https://i.pinimg.com/originals/44/55/d9/4455d96357fb041d1cf3c8a5264ed593.jpg";

		for(int i = 0; i < 100; i++) {
			movies.add(new Movie("Titanic", "Jack, Rose ...",titanic));
			movies.add(new Movie("Lord Of The Rings", "Gandalf, Frodo, Bilbo ...",lord));
		}

		/*


		*/

		adapter = new HivaRecyclerAdapter();

		adapter.forEach(movies, new ItemHolderIterator<Movie>(R.layout.item_movie) {
			@Override
			public void bindToHolder(ItemHolder binder, Movie item) {

				item.bindToHolder(binder, null);
			}

			@Override
			public void itemClicked(ItemHolder binder, Movie item) {

			}
		});

		recyclerView.setAdapter(adapter);


		OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);


	}

}
