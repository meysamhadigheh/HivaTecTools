package hivatec.ir.hivatectoolstest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import hivatec.ir.hivatectools.form.FormInputs;
import hivatec.ir.hivatectools.form.HivaForm;
import hivatec.ir.hivatectools.views.PickerView;
import hivatec.ir.hivatectoolstest.R;

public class FormActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);


		PickerView picker = findViewById(R.id.picker);
		picker.setOnItemPickedListener(new PickerView.OnItemPickedListener() {
			@Override
			public void onItemPicked(PickerView.PickerItem item, int index) {

				Toast.makeText(FormActivity.this, "item selected : " + item.getTitle() + " : " + index, Toast.LENGTH_SHORT).show();
			}
		});

	}
}
