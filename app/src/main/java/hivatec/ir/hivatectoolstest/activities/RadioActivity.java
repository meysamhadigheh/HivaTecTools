package hivatec.ir.hivatectoolstest.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.hivaRadioView.HivaRadioView;
import hivatec.ir.hivatectoolstest.R;

public class RadioActivity extends AppCompatActivity {

	HivaRadioView radioView;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_radio);
			Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
			setSupportActionBar(toolbar);


			ArrayList<String> items = new ArrayList<>();

			items.add("آیتم ۱");
			items.add("آیتم ۲");
			items.add("آیتم ۳");
			items.add("آیتم ۴");

			radioView = findViewById(R.id.hivaRadio);
			radioView.setItems(items);


			findViewById(R.id.testRipple).setBackground(RippleHelper.getRippleDrawableForTransparentColor(Color.parseColor("#aaaaaa"), 0));

		}

	}