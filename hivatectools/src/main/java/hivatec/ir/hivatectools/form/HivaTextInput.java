package hivatec.ir.hivatectools.form;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by ashkan on 8/6/18.
 */

public class HivaTextInput extends android.support.v7.widget.AppCompatEditText implements FormInput<String> {


	public HivaTextInput(Context context) {
		super(context);
	}

	public HivaTextInput(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean validate() {

		if(getText().toString().trim().length() == 0)
			return false;


		return true;
	}

	@Override
	public void showError(String error) {

	}

	@Override
	public String getErrorString() {
		if(getText().toString().trim().length() == 0)
			return "مقدار وارد نشده.";

		return "";
	}

	@Override
	public void hideError() {

	}

	@Override
	public String getValue() {
		return getText().toString().trim();
	}
}
