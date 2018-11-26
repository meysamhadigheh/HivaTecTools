package hivatec.ir.hivatectoolstest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import hivatec.ir.hivatectools.activityHelpers.LiveViewModel;
import hivatec.ir.hivatectools.activityHelpers.ParentActivity;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.Movie;
import hivatec.ir.hivatectoolstest.model.User;

public class LiveModelActivity extends ParentActivity<User> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void setContentViewActivity() {
		setContentView(R.layout.activity_live_model);
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

				startActivity(new Intent(context, LiveModelActivity2.class));
			}
		});
	}

	@Override
	protected void setActivityContent() {

	}

	@Override
	protected void refreshContent() {

	}


	@Override
	protected void liveDataUpdated(User data) {

		((TextView) findViewById(R.id.textView)).setText(data.coins + "");
	}

}
