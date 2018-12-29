package hivatec.ir.hivatectoolstest.model;

import android.view.View;
import android.widget.Button;

import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;
import hivatec.ir.hivatectools.views.HivaButton;
import hivatec.ir.hivatectoolstest.R;

public class ButtonModel implements ItemBinder {

	String title = "";
	boolean isSelected = false;

	public ButtonModel(String title){
		this.title = title;
	}

	@Override
	public int getResourceId() {
		return R.layout.item_button;
	}

	@Override
	public void bindToHolder(ItemHolder binder, Object listener) {

		binder.<HivaButton>find(R.id.button).setTitle(title);
		binder.<HivaButton>find(R.id.button).setOn(isSelected);

		binder.<HivaButton>find(R.id.button).setOnToggleListener(new HivaButton.OnToggleListener() {
			@Override
			public void toggled(HivaButton button) {
				isSelected = button.getOn();
			}
		});
	}
}
