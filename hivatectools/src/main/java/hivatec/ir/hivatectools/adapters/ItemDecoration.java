package hivatec.ir.hivatectools.adapters;

import android.graphics.Canvas;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ashkan on 7/26/18.
 */

public class ItemDecoration extends RecyclerView.ItemDecoration {


	int currentHeaderId = 0;

	HashMap<Integer, View> headers = new HashMap<>();

	@Override
	public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDraw(c, parent, state);

	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
		super.onDrawOver(c, parent, state);


		View currentHeader = null;
		View nextHeader = null;

		ArrayList<View> views = new ArrayList<>();

		for (int i = 0; i < parent.getChildCount(); i++) {

			View view = parent.getChildAt(i);



			if (view.getTag() instanceof Integer) {

				views.add(view);

				if (!headers.containsKey(getTag(view))) {

					headers.put(getTag(view), view);
				}
			}
		}

		for(View view : headers.values()){

			Log.i("I",  (getTag(view)) + " - " + view.getTop() + "");
		}

		if(currentHeader != null) {

			if(nextHeader != null) {
				//Log.i("I1", currentHeader.getHeight() + " - " + nextHeader.getTop() + " - " + parent.computeVerticalScrollOffset());
			}

			if(nextHeader != null){

				if(nextHeader.getTop() <= currentHeader.getHeight()){

					c.translate(0, -(currentHeader.getHeight() -  nextHeader.getTop()));
				}
			}

			currentHeader.draw(c);

		}


		/*
		boolean currentHeaderRecycled = true;

		for (int i = 0; i < parent.getChildCount(); i++) {

			View view = parent.getChildAt(i);

			if(view == currentHeader){
				currentHeaderRecycled = false;
			}
		}


		for (int i = 0; i < parent.getChildCount(); i++) {

			View view = parent.getChildAt(i);

			if (view.getTag() instanceof Integer && (Integer) view.getTag() == 1000) {

				if(lastHeader == view && currentHeaderRecycled){
					currentHeader = lastHeader;
					nextHeader = currentHeader;
					break;
				}

				if(currentHeader == null) {
					currentHeader = view;
				}

				if(nextHeader == null && !currentHeader.equals(view)){
					nextHeader = view;

					break;
				}
			}
		}

		if(currentHeader != null) {

			if(nextHeader != null) {
				Log.i("I1", currentHeader.getHeight() + " - " + nextHeader.getTop() + " - " + parent.computeVerticalScrollOffset());
			}

			if(nextHeader != null){

				if(nextHeader.getTop() <= currentHeader.getHeight()){

					c.translate(0, -(currentHeader.getHeight() -  nextHeader.getTop()));
				}
			}

			currentHeader.draw(c);

		}

		*/

	}

	private int getTag(View view){

		return (int) view.getTag();
	}
}
