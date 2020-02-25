package hivatec.ir.hivatectools.activityHelpers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hivatec.ir.hivatectools.helper.SharedPreference;

public class LiveViewModel<T> extends ViewModel {

	Class clazz;
	private MutableLiveData<T> data;

	public void setClass(Class<T> _class) {
		this.clazz = _class;
	}

	public LiveData<T> get() {
		if (data == null) {
			data = new MutableLiveData<T>();
		}

		update();

		return data;
	}


	public void saveLiveData(){

		SharedPreference.putObject(clazz.getName() + "_livedata", data.getValue());
		update();
	}

	public void update(){

		T object = (T) SharedPreference.getObject(clazz.getName() + "_livedata", clazz);
		data.setValue(object);
	}

	public static <Y> void setData(Class<Y> type, Y data){

		SharedPreference.putObject(type.getName() + "_livedata", data);

	}

	public static <Y> Class<LiveViewModel<Y>> getClass(Class<Y> type){

		LiveViewModel<Y> model = new LiveViewModel<Y>();
		Class<LiveViewModel<Y>> clazz = (Class<LiveViewModel<Y>>) model.getClass();
		return clazz;
	}


}
