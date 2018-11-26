package hivatec.ir.hivatectoolstest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.Movie;
import hivatec.ir.hivatectoolstest.model.User;

public class LiveModelActivity2 extends ParentActivity<User> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void setContentViewActivity() {
		setContentView(R.layout.activity_live_model2);
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

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				getLiveData().addOneCoin();
				liveData.saveLiveData();
			}
		});
	}

	@Override
	protected void setActivityContent() {


	}

	@Override
	protected void refreshContent() {

	}
}
