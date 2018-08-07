package hivatec.ir.hivatectools.RetrofitHelper;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ashkan on 8/7/18.
 */

public abstract class RetroCallBack<T, Y> implements Callback<Y> {

	public abstract Call<Y> shouldCall(T service);

	public Class<T> classT;
	public Class<Y> classY;

	public RetroCallBack(){

		Type superclass = getClass().getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		classT = (Class<T>) $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
		classY = (Class<Y>) $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[1]);

	}

	public abstract void onResponse(Call<Y> call, Response<Y> response);
	public abstract void onFailure(Call<Y> call, Throwable t);

}
