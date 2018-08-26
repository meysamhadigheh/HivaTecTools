package hivatec.ir.hivatectools.form;

import android.view.View;

import java.util.ArrayList;

/**
 * Created by ashkan on 8/15/18.
 */

public class FormValidator {


	ArrayList<ToValidate> inputs = new ArrayList<>();



	public FormValidator addInput(View view, ValidateType type, String value, boolean canEmpty){

		ToValidate input = new ToValidate();

		input.view = view;
		input.type = type;
		input.value = value;

		switch (type){

			case Mobile:
			case Phone:
				input.length = 11;
				break;

			case Number:
				input.length = 20;
				break;

			case Name:
			case Email:
			case LastName:
				input.length = 50;
				break;

			case Url:
				input.length = 150;
				break;

			case Text:
				input.length = 0;
				break;
		}

		inputs.add(input);

		return this;
	}

	public ArrayList<ToValidate> validate(){

		ArrayList<ToValidate> errors = new ArrayList<>();


		for (ToValidate toValidate : inputs){

			boolean hasError = false;

			String value = toValidate.value.trim();

			if(!toValidate.canEmpty){

				if(value.equals("")) {
					hasError = true;
					continue;
				}
			}

			switch (toValidate.type){

				case Mobile:
				case Phone:
					break;

				case Number:
					break;

				case Name:
				case Email:
				case LastName:
					break;

				case Url:

				case Text:
					break;
			}

		}


		return errors;
	}


	public class ToValidate {

		ValidateType type;
		String value;
		int length;
		View view;
		boolean canEmpty;

	}

}
