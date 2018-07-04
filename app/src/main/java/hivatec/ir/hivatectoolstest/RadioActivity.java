package hivatec.ir.hivatectoolstest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import hivatec.ir.hivatectools.hivaRadioView.HivaRadioView;

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


	}

}
