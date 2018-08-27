package hivatec.ir.hivatectools.form;

/**
 * Created by ashkan on 8/6/18.
 */

public interface FormInput<T> {

	boolean validate();
	void showError(String error);
	String getErrorString();
	void hideError();
	T getValue();
}
