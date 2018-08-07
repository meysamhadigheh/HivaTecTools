package hivatec.ir.hivatectools.RetrofitHelper;

import android.util.Log;

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

	public static void setUrl(String baseUrl){
		url = baseUrl;
	}




	public static Retrofit getRetrofit(){

		Retrofit retrofit = new retrofit2.Retrofit.Builder()
				.baseUrl(url)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return retrofit;
	}



	public <T, Y> void  call(RetroCallBack<T, Y> callback) {


		T service = getRetrofit().create(callback.classT);
		Call<Y> call = callback.shouldCall(service);

		Log.wtf("URL Called", call.request().url() + "");
		call.enqueue(new Callback<Y>() {
			@Override
			public void onResponse(Call<Y> call, Response<Y> response) {

				Log.i("retrofit", response.message());

				callback.onResponse(call, response);
			}

			@Override
			public void onFailure(Call<Y> call, Throwable t) {
				Log.e("retrofit", "has error");

				callback.onFailure(call, t);
			}
		});
	}
}
