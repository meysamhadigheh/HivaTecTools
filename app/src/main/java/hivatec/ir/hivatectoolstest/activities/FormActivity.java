package hivatec.ir.hivatectoolstest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hivatec.ir.hivatectools.form.FormInputs;
import hivatec.ir.hivatectools.form.HivaForm;
import hivatec.ir.hivatectoolstest.R;

public class FormActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);


		HivaForm form = findViewById(R.id.form);

		form.addInput(FormInputs.newTextInput(this, "نام", R.drawable.form_input));
		form.addInput(FormInputs.newTextInput(this, "نام خانوادگی", R.drawable.form_input));

		findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				form.validate();
			}
		});
	}
}
