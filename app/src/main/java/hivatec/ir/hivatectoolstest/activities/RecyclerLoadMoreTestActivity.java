package hivatec.ir.hivatectoolstest.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hivatec.ir.easywebservice.EasyWebservice;
import hivatec.ir.hivatectools.RetrofitHelper.JsonMap;
import hivatec.ir.hivatectools.RetrofitHelper.RetroCallBack;
import hivatec.ir.hivatectools.RetrofitHelper.Webservice;
import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectools.helper.GlideHelper;
import hivatec.ir.hivatectools.helper.SharedPreference;
import hivatec.ir.hivatectools.views.RecyclerLoadMoreAndRefresh;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.EmptyItem;
import hivatec.ir.hivatectoolstest.model.HeaderItem;
import hivatec.ir.hivatectoolstest.model.ItemLoading;
import hivatec.ir.hivatectoolstest.model.Movie;
import hivatec.ir.hivatectoolstest.model.Notice;
import hivatec.ir.hivatectoolstest.model.RecycelerItem;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

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
