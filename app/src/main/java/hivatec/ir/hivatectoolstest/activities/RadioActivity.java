package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import hivatec.ir.hivatectools.hivaRadioView.HivaCheckBoxView;
import hivatec.ir.hivatectools.hivaRadioView.HivaRadioView;
import hivatec.ir.hivatectoolstest.R;
import hivatec.ir.hivatectoolstest.model.SortType;

public class RadioActivity extends AppCompatActivity {

	HivaRadioView radioView;
	HivaCheckBoxView checkBoxView;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_radio);
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);


			ArrayList<SortType> sorts = new ArrayList<>();
			sorts.add(new SortType(0, "price"));
			sorts.add(new SortType(10, "duration"));
			sorts.add(new SortType(22, "alphabet"));
			sorts.add(new SortType(30, "date"));

			radioView = findViewById(R.id.hivaRadio);
			radioView.setItems(sorts);

			radioView.setSelectedIndex(1);


			ArrayList<String> items = new ArrayList<>();

			items.add("آیتم ۱");
			items.add("آیتم ۲");
			items.add("آیتم ۳");
			items.add("آیتم ۴");

			checkBoxView = findViewById(R.id.hivaCheck);
			checkBoxView.setItems(items.toArray());

			//findViewById(R.id.testRipple).setBackground(RippleHelper.getRippleDrawableForTransparentColor(Color.parseColor("#aaaaaa"), 0));

		}

		public void check(View view){

			Log.i("radio", radioView.getSelectedId() + "");
			Log.i("checkbox", checkBoxView.getSelectedIds() + "");

		}

	}