package hivatec.ir.hivatectools.hivaRadioView;

/**
 * Created by ashkan on 7/7/18.
 */

public interface CheckedItem {

	int getId();
	String getTitle();
	boolean isChecked();
	void setChecked(Boolean checked);
}
