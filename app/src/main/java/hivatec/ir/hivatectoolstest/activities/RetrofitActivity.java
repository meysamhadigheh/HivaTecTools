package hivatec.ir.hivatectoolstest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import hivatec.ir.hivatectools.RetrofitHelper.RetroCallBack;
import hivatec.ir.hivatectools.RetrofitHelper.Webservice;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.Notice;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RetrofitActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrofit);


		String BASE_URL = "http://demo0617111.mockable.io/";

		Webservice.setUrl(BASE_URL);


		new Webservice().call(new RetroCallBack<NoticeService, Notice>() {
			@Override
			public Call<Notice> shouldCall(NoticeService service) {
				return service.getSingleNotice();
			}

			@Override
			public void onResponse(Call<Notice> call, Response<Notice> response) {

				Log.i("response", response.body().getName());
			}

			@Override
			public void onFailure(Call<Notice> call, Throwable t) {

			}
		});

		new Webservice().call(new RetroCallBack<NoticeService, ArrayList<Notice>>() {
			@Override
			public Call<ArrayList<Notice>> shouldCall(NoticeService service) {
				return service.getNoticeList();
			}

			@Override
			public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {

				Log.i("response", response.body().size() + "");
			}

			@Override
			public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {

			}
		});


	}


	interface NoticeService {

		@GET("notice")
		Call<Notice> getSingleNotice();

		@GET("notices")
		Call<ArrayList<Notice>> getNoticeList();
	}



}
