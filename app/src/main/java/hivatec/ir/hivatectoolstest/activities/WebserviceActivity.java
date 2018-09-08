package hivatec.ir.hivatectoolstest.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.provider.Settings;
import android.widget.Toast;

import java.io.IOException;

import hivatec.ir.hivatectools.EasyWebservice.Callback;
import hivatec.ir.hivatectools.EasyWebservice.EasyWS;
import hivatec.ir.hivatectools.EasyWebservice.Method;
import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectools.helper.G;
import hivatec.ir.hivatectools.helper.PhoneInformation;
import hivatec.ir.hivatectoolstest.R;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.POST;

public class WebserviceActivity extends ParentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentViewActivity() {
		setContentView(R.layout.activity_webservice);
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

		//EasyWS.addGlobalHeader("token", "41827364879123648");

//		new EasyWS("http://viewplusapp.ir/api/v1/test/")
//				.method(Method.POST)
////				.addHeader("token", "24i13b123kbj4")
////				.addBody("imei", PhoneInformation.IMEI(G.context))
////				.addBody("buildNumber", PhoneInformation.getSerialNumber(G.context))
////				.addBody("deviceModel", PhoneInformation.BRANDMODEL())
////				.addBody("os", "Android")
//				.addBody("osVersion", PhoneInformation.getAndroidVersion())
//				.perform(new Callback.AB<Boolean, String>("res", "msg") {
//					@Override
//					public void onSuccess(Boolean res, String msg) {
//
//						showToast("success");
//					}
//
//					@Override
//					public void onError(String error) {
//
//						showToast(error);
//
//					}
//				});



		new AsyncTask() {

			@Override
			protected Object doInBackground(Object[] objects) {

				RequestBody requestBody = new MultipartBody.Builder()
						.setType(MultipartBody.FORM)
						.addFormDataPart("token", "someVaksjdnflakjsalue")
						.build();

				Request request = new Request.Builder()
						.url("http://hivatec.ir/hamber/api/v1/auth/token/new")
						.post(requestBody)
						.build();

				try {
					Response response = new OkHttpClient().newCall(request).execute();
					String body = response.body().string();

				} catch (IOException e) {
					e.printStackTrace();
				}

				return null;
			}

		}.execute();

	}

	@Override
	protected void refreshContent() {

	}

}
