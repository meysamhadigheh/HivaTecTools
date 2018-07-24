package hivatec.ir.hivatectoolstest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hivatec.ir.hivatectools.helper.ActivityParent;
import hivatec.ir.hivatectoolstest.R;

public class LoadingActivity extends ActivityParent {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);


		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				showLoadingPage();
			}
		});
	}
}
