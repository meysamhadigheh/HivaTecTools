package hivatec.ir.hivatectools.hivaViewBinder;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Attr;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;

/**
 * Created by ashkan on 7/5/18.
 */

public class ViewBinderHelper {

	public ViewBinderHelper(ViewGroup view)  {

		this.view = view;

		LayoutInflater.from(view.getContext()).inflate(getBinder().getLayoutId(), view, true);
	}

	HashMap<Integer, View> viewMap = new HashMap<>();
	public View view;
	private AttributeSet attrs;

	private IViewBinder getBinder(){
		return ((IViewBinder) view);
	}

	public void setAttributeSet(AttributeSet attrs){

		this.attrs = attrs;
	}

	public AttributeSet getAttributeSet(){

		return  this.attrs;
	}

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


	public static final ArrayList<Field> getResourceDeclareStyleableIntArray(Context context, String name )
	{
		ArrayList<Field>  selectedFields = new ArrayList<>();

		try
		{
			//use reflection to access the resource class
			Field[] allFields = Class.forName( context.getPackageName() + ".R$styleable" ).getFields();

			//browse all fields
			for ( Field f : allFields )
			{
				//pick matching field
				if ( f.getName().contains( name ) )
				{
					//return as int array
					selectedFields.add(f);
				}
			}
		}
		catch ( Throwable t )
		{
		}

		return selectedFields;
	}

	public TypedArray getTypedArray() {

		String className = view.getClass().getSimpleName();
		ArrayList<Field> fields = getResourceDeclareStyleableIntArray(view.getContext(), className);

		try {
			TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs, ((int[]) fields.get(0).get(null)), 0, 0);
			return a;

		}catch (Exception e){
			return null;
		}

	}


/*
	public String getStringResource(String name){

		String val = "";

		if(attrs != null) {

			ArrayList<Field> fields = getResourceDeclareStyleableIntArray(view.getContext(), ((IViewBinder) view).getClassName());

			if (fields.size() == 0) {

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <declare-styleable name=\"" + ((IViewBinder) view).getClassName() + "\"></declare-styleable> in attrs.xml");

			} else {

				for (Field f : fields) {

					String fieldName = f.getName();
					String requestedName = ((IViewBinder) view).getClassName() + "_" + name;
					if (fieldName.equals(requestedName)) {

						try {
							int res = (int) f.get(null);
							TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs, ((int[]) fields.get(0).get(null)), 0, 0);
							val = a.getString(res);
							a.recycle();
							return val;

						}catch (Exception e){

							e.printStackTrace();
						}
					}
				}

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + "_" + name + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <attr name=\"" + name + "\" format=\"string\" />");

			}
		}

		return val;

	}


	public int getResource(String name, int defVal){

		int val = defVal;

		if(attrs != null) {

			ArrayList<Field> fields = getResourceDeclareStyleableIntArray(view.getContext(), ((IViewBinder) view).getClassName());

			if (fields.size() == 0) {

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <declare-styleable name=\"" + ((IViewBinder) view).getClassName() + "\"></declare-styleable> in attrs.xml");

			} else {

				for (Field f : fields) {

					String fieldName = f.getName();
					String requestedName = ((IViewBinder) view).getClassName() + "_" + name;
					if (fieldName.equals(requestedName)) {

						try {
							int res = (int) f.get(null);
							TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs, ((int[]) fields.get(0).get(null)), 0, 0);
							val = a.getResourceId(res, val);
							a.recycle();
							return val;

						}catch (Exception e){

							e.printStackTrace();
						}
					}
				}

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + "_" + name + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <attr name=\"" + name + "\" format=\"string\" />");

			}
		}

		return val;

	}

	public int getColorResource(String name, int defVal){

		int val = defVal;

		if(attrs != null) {

			ArrayList<Field> fields = getResourceDeclareStyleableIntArray(view.getContext(), ((IViewBinder) view).getClassName());

			if (fields.size() == 0) {

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <declare-styleable name=\"" + ((IViewBinder) view).getClassName() + "\"></declare-styleable> in attrs.xml");

			} else {

				for (Field f : fields) {

					String fieldName = f.getName();
					String requestedName = ((IViewBinder) view).getClassName() + "_" + name;
					if (fieldName.equals(requestedName)) {

						try {
							int res = (int) f.get(null);
							TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs, ((int[]) fields.get(0).get(null)), 0, 0);
							val = a.getColor(res, val);
							a.recycle();
							return val;

						}catch (Exception e){

							e.printStackTrace();
						}
					}
				}

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + "_" + name + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <attr name=\"" + name + "\" format=\"string\" />");

			}
		}

		return val;

	}

	public int getInt(String name, int defVal){

		int val = defVal;

		if(attrs != null) {

			ArrayList<Field> fields = getResourceDeclareStyleableIntArray(view.getContext(), ((IViewBinder) view).getClassName());

			if (fields.size() == 0) {

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <declare-styleable name=\"" + ((IViewBinder) view).getClassName() + "\"></declare-styleable> in attrs.xml");

			} else {

				for (Field f : fields) {

					String fieldName = f.getName();
					String requestedName = ((IViewBinder) view).getClassName() + "_" + name;
					if (fieldName.equals(requestedName)) {

						try {
							int res = (int) f.get(null);
							TypedArray a = view.getContext().getTheme().obtainStyledAttributes(attrs, ((int[]) fields.get(0).get(null)), 0, 0);
							val = a.getInt(res, val);
							a.recycle();
							return val;

						}catch (Exception e){

							e.printStackTrace();
						}
					}
				}

				Log.e("HivaViewBinder", "R.styleable." + ((IViewBinder) view).getClassName() + "_" + name + " not found in attrs.xml!");
				Log.e("HivaViewBinder", "Copy and paste <attr name=\"" + name + "\" format=\"string\" />");

			}
		}

		return val;

	}
*/
}
