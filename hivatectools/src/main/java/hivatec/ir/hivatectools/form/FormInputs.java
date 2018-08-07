package hivatec.ir.hivatectools.form;

import android.content.Context;

/**
 * Created by ashkan on 8/6/18.
 */

public class FormInputs {

	public static HivaTextInput newTextInput(Context context, String placeHolder, int backgroundRes){

		HivaTextInput input = new HivaTextInput(context);

		input.setHint(placeHolder);
		input.setBackgroundResource(backgroundRes);

		return input;
	}
}
