package hivatec.ir.hivatectools.RetrofitHelper;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ashkan on 8/7/18.
 */

public class Webservice{


	public static String url = "";
	public static int timeout = 10;

	public static void setUrl(String baseUrl){
		url = baseUrl;
	}


	public Webservice setTimeOut(int timeOut){

		timeout = timeOut;
		return this;
	}


	public static Retrofit getRetrofit(){


		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.readTimeout(timeout, TimeUnit.SECONDS)
				.connectTimeout(timeout, TimeUnit.SECONDS)
				.addInterceptor(interceptor)
				.build();

		Retrofit retrofit = new retrofit2.Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.client(okHttpClient)
				.build();


		timeout = 10;

		return retrofit;
	}



	public <T, Y> void  call(RetroCallBack<T, Y> callback) {



		T service = getRetrofit().create(callback.classT);
		Call<Y> call = callback.shouldCall(service);

		Log.wtf("URL Called", call.request().url() + "");

		call.enqueue(new Callback<Y>() {
			@Override
			public void onResponse(Call<Y> call, Response<Y> response) {

				try {
					Log.i("retrofit", "response from " + call.request().url() + " ->>> " + response.body().toString());
				}catch (Exception e){

				}

				callback.onResponse(call, response);
			}

			@Override
			public void onFailure(Call<Y> call, Throwable t) {
				Log.e("retrofit",  "response from " + call.request().url() + " ->>> " +  t.getMessage());

				callback.onFailure(call, t);
			}
		});
	}
}
