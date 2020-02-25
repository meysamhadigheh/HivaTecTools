package hivatec.ir.hivatectoolstest.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectools.views.RecyclerLoadMoreAndRefresh;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.EmptyItem;
import hivatec.ir.hivatectoolstest.model.ItemLoading;

public class RecyclerLoadMoreTestActivity extends ParentActivity implements RecyclerLoadMoreAndRefresh.Delegate {


	RecyclerLoadMoreAndRefresh recycler;

	String lord="http://www.taosmemory.com/movies/poster/2002/51.jpg";
	String titanic="https://i.pinimg.com/originals/44/55/d9/4455d96357fb041d1cf3c8a5264ed593.jpg";

	@Override
	protected void setContentViewActivity() {
		setContentView(R.layout.activity_retrofit);
	}

	@Override
	protected void getIntentData() {

	}

	@Override
	protected void prepareToolbar() {

	}

	@Override
	protected void instantiateViews() {

		recycler = findViewById(R.id.recycler);

		recycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
	}

	@Override
	protected void setViewListeners() {

	}

	@Override
	protected void setActivityContent() {

		//recycler.getRefreshLayout().setEnabled(false);
		recycler.setDelegate(this);
		recycler.setLoadingItem(new ItemLoading());
		recycler.setEmptyItem(new EmptyItem());
	}

	@Override
	protected void refreshContent() {

	}


	@Override
	public void loadMore(int page) {

		new EasyWebservice("loading page number =>" + page)
				.fakeJson("[1, 2, 3, 4, 5, 6, 6, 8]")
				.call(new hivatec.ir.easywebservice.Callback.A<ArrayList<Integer>>() {
					@Override
					public void onSuccess(ArrayList<Integer> array) {

						if(Math.random() > 0.3) {
							ArrayList movies = new ArrayList<>();


//							movies.add(new HeaderItem());
//							movies.add(new RecycelerItem());
//
//							for (int i = 0; i < 10; i++) {
//
//								if (Math.random() < 0.4) {
//									movies.add(new Movie("Titanic", "Jack, Rose ...", titanic));
//								} else {
//									movies.add(new Movie("Lord Of The Rings", "Gandalf, Frodo, Bilbo ...", lord));
//								}
//
//							}


							recycler.doneLoading(movies, page);
						}else{

							recycler.doneWithError(page,"خطا در دریافت اطلاعات", true);
						}
					}

					@Override
					public void onError(String s) {

						recycler.doneWithError(page,"خطا در دریافت اطلاعات", true);
						showToast(s);
					}
				});

	}
}
