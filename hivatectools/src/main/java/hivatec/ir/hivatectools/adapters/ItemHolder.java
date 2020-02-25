package hivatec.ir.hivatectools.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

/**
 * Created by ashkan on 2/1/18.
 */

public class ItemHolder extends RecyclerView.ViewHolder {

    public ItemHolder(View itemView) {
		super(itemView);

    }

    HashMap<Integer, View> viewMap = new HashMap<>();
    public Context context;

    public <T extends View> T find(int viewId){

        T view;

        if(viewMap.containsKey(viewId)){

            view = (T) viewMap.get(viewId);
        }else{


            view = itemView.findViewById(viewId);
            viewMap.put(viewId, view);
        }

        return view;
    }

}