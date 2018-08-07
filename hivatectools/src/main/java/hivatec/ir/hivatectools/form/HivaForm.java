package hivatec.ir.hivatectools.form;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import com.github.florent37.viewtooltip.ViewTooltip;

import java.util.ArrayList;
import java.util.HashMap;

import hivatec.ir.hivatectools.helper.ViewUIHelper;
import kotlin.TypeCastException;


/**
 * Created by ashkan on 8/6/18.
 */

public class HivaForm extends LinearLayout {


	private ArrayList<FormInput> views = new ArrayList<>();
	private HashMap<FormInput, TextView> errors = new HashMap<>();

	public HivaForm(Context context) {
		super(context);

		init();
	}

	public HivaForm(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	private void init(){

		this.setOrientation(VERTICAL);
	}

	public void addInput(FormInput input){
		if(!(input instanceof View)){
			throw new TypeCastException("Input should be a view");
		}


		if(views.size() > 0){

			View space = new View(getContext());
			space.setLayoutParams(new LinearLayout.LayoutParams(1, ViewUIHelper.dpToPx(8)));
			this.addView(space);
		}

		this.views.add(input);
		this.addView((View) input);

		TextView errorTxt = new TextView(getContext());
		errorTxt.setText("خطا در فیلد");
		errorTxt.setTextColor(Color.RED);
		ViewUIHelper.setPadding(errorTxt, 4);
		errorTxt.setVisibility(GONE);
		this.addView(errorTxt);
		errors.put(input, errorTxt);

	}

	public boolean validate(){

		boolean flag = true;

		for(FormInput input : views){

			if(!input.validate()){


				input.showError(input.getErrorString());

				ViewTooltip
						.on((View) input)
						.autoHide(true, 1000)
						.corner(30)
						.position(ViewTooltip.Position.TOP)
						.text(input.getErrorString())
						.show();


				flag = false;
			}else{

				input.hideError();
			}
		}

		return flag;
	}
}
