package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

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

		for(int i = 0; i < 100; i++) {
			movies.add(new Movie("Titanic", "Jack, Rose ..."));
			movies.add(new Movie("Lord Of The Rings", "Gandalf, Frodo, Bilbo ..."));
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
