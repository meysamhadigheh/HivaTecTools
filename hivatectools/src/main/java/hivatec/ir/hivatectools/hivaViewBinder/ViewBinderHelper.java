package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;

/**
 * Created by ashkan on 7/5/18.
 */

public class ViewBinderHelper {

	public ViewBinderHelper(View view)  {

		this.view = view;

		LayoutInflater inflater = LayoutInflater.from(view.getContext());
		inflater.inflate(((ViewBinder) view).getLayoutId(), null);

		((ViewBinder) view).init();
	}

	HashMap<Integer, View> viewMap = new HashMap<>();
	public View view;

	public <T extends View> T find(int viewId){

		T _view;

		if(viewMap.containsKey(viewId)){

			_view = (T) viewMap.get(viewId);
		}else{

			_view = view.findViewById(viewId);
			viewMap.put(viewId, _view);
		}

		return _view;
	}

}
