package hivatec.ir.hivatectools.helper;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hivatec.ir.hivatectools.R;

/**
 * Created by ashkan on 7/24/18.
 */

public class ActivityParent extends AppCompatActivity {




	protected void showLoadingPage(){

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		//fragmentTransaction.add(R.layout.layout_loading, "loading");
		//fragmentTransaction.commit();
	}
}
