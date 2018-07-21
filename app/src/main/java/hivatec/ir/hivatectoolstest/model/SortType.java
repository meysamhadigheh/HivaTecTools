package hivatec.ir.hivatectoolstest.model;

import hivatec.ir.hivatectools.hivaRadioView.RadioItem;

/**
 * Created by ashkan on 7/18/18.
 */

public class SortType implements RadioItem {

	int id;
	String title;


	public SortType(int id, String title) {
		this.id = id;
		this.title = title;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTitle() {
		return title;
	}
}
