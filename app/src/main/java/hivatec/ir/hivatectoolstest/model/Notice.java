package hivatec.ir.hivatectoolstest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashkan on 8/7/18.
 */

public class Notice {

	int id;

	@SerializedName("title")
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
