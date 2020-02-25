package hivatec.ir.hivatectoolstest.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectoolstest.R;

public class LoadingActivity extends ParentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);


		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				//showLoadingPage();
			}
		});
	}

	@Override
	protected void setContentViewActivity() {

	}

	@Override
	protected void getIntentData() {

	}

	@Override
	protected void prepareToolbar() {

	}

	@Override
	protected void instantiateViews() {

	}

	@Override
	protected void setViewListeners() {

	}

	@Override
	protected void setActivityContent() {

	}

	@Override
	protected void refreshContent() {

	}
}
