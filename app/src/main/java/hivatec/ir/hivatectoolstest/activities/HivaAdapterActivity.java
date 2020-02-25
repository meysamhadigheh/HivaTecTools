package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import hivatec.ir.hivatectools.adapters.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.adapters.ItemDecoration;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.HeaderItem;
import hivatec.ir.hivatectoolstest.model.Movie;

public class HivaAdapterActivity extends AppCompatActivity {

	RecyclerView recyclerView;
	HivaRecyclerAdapter adapter;

	ArrayList movies = new ArrayList();

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


		movies.add(new HeaderItem());

		for(int i = 0; i < 100; i++) {
			movies.add(new Movie("Titanic", "Jack, Rose ...",titanic));
			movies.add(new Movie("Lord Of The Rings", "Gandalf, Frodo, Bilbo ...",lord));
		}


		movies.add(2, new HeaderItem());
		movies.add(5, new HeaderItem());
		movies.add(10, new HeaderItem());
		movies.add(13, new HeaderItem());

		adapter = new HivaRecyclerAdapter();

		adapter.setItems(movies);

		adapter.makeClassHeader(HeaderItem.class);

		recyclerView.setAdapter(adapter);

		recyclerView.addItemDecoration(new ItemDecoration(), 0);

	}

}
