package hivatec.ir.hivatectools.RetrofitHelper;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

/**
 * Created by ashkan on 8/9/18.
 */

public class JsonMap extends HashMap<String, Object> {


	private final MultipartBody.Builder mBody;

	public JsonMap(){

		mBody = new MultipartBody.Builder();
		mBody.setType(MultipartBody.FORM);

	}



	public void putItem(String key, Object value) {

			if (value instanceof File) {

				File file = (File) value;
				mBody.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse(MEDIA_TYPE), file));

			} else if (value instanceof String) {

				mBody.addFormDataPart(key, value.toString());
			} else {

				mBody.addFormDataPart(key, new Gson().toJson(value));
			}

	}


	public MultipartBody getBody() {

		return mBody.build();
	}

	public List<MultipartBody.Part> getParts() {
		return getBody().parts();
	}
}
