package hivatec.ir.hivatectoolstest.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import hivatec.ir.hivatectools.RetrofitHelper.JsonMap;
import hivatec.ir.hivatectools.RetrofitHelper.RetroCallBack;
import hivatec.ir.hivatectools.RetrofitHelper.Webservice;
import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectools.helper.GlideHelper;
import hivatec.ir.hivatectools.helper.SharedPreference;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.Movie;
import hivatec.ir.hivatectoolstest.model.Notice;
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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

public class RetrofitActivity extends ParentActivity {



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

	}

	@Override
	protected void setViewListeners() {

	}

	@Override
	protected void setActivityContent() {


		//Shared preference
		Movie m = new Movie("a", "b", "c");

		SharedPreference.putObject("movie", m);
		Movie mm = SharedPreference.getObject("movie", Movie.class);


		String BASE_URL = "http://10.0.2.2:3000/";

		Webservice.setUrl(BASE_URL);


		new Webservice().call(new RetroCallBack<NoticeService, ArrayList<Notice>>() {
			@Override
			public Call<ArrayList<Notice>> shouldCall(NoticeService service) {
				return service.getNoticeList();
			}

			@Override
			public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {


				Log.i("", response.body().size() + "");
			}

			@Override
			public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {

			}
		});


		GlideHelper.downloadImage(this, "https://picsum.photos/200/300", "fileeee", new GlideHelper.DownloadCallback() {
			@Override
			public void downloaded(Bitmap bitmap, Uri fileUri) {

				((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);

			/*	new Webservice().call(new RetroCallBack<NoticeService, ArrayList<Notice>>() {
					@Override
					public Call<ArrayList<Notice>> shouldCall(NoticeService service) {

						JsonMap objects = new JsonMap();

						objects.putItem("id", 100);
						objects.putItem("name", "ashkan");
						objects.putItem("price", 100.1);
						objects.putItem("movie", new Movie("titanic", "4.5", "http://image.com"));
						objects.putItem("file1",new File(((Uri) fileUri).getPath()));

						return service.getNoticeList();
					}

					@Override
					public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {

						//Log.i("response", response.body().getName());
					}

					@Override
					public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {

					}
				});*/

			}
		});


	}

	@Override
	protected void refreshContent() {

	}


	interface NoticeService {

		@Multipart
		@POST("notice")
		Call<Notice> getSingleNotice(@Part List<MultipartBody.Part> body);

		@GET("notices")
		Call<ArrayList<Notice>> getNoticeList();
	}



}
