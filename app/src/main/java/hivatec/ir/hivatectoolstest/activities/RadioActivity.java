package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import hivatec.ir.hivatectools.views.HivaCheckBoxView;
import hivatec.ir.hivatectools.views.HivaRadioView;
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
        sorts.add(new SortType(1, "duration"));
        sorts.add(new SortType(2, "alphabet"));
        sorts.add(new SortType(3, "date"));

        radioView = findViewById(R.id.hivaRadio);
        radioView.setItems(sorts);

        radioView.setSelectedIndex(1);


        ArrayList<String> items = new ArrayList<>();

        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");


        checkBoxView = findViewById(R.id.hivaCheck);
        checkBoxView.setItems(items.toArray());

        //findViewById(R.id.testRipple).setBackground(RippleHelper.getRippleDrawableForTransparentColor(Color.parseColor("#aaaaaa"), 0));

    }


    public void check_radio(View view) {
        Log.i("radio", radioView.getSelectedId() + "");
        Toast.makeText(this, radioView.getSelectedId() + " ", Toast.LENGTH_SHORT).show();


    }

    public void check_checklist(View view) {
        Log.i("radio", checkBoxView.getSelectedIds() + "");

        String text="";

        for (int i = 0; i <checkBoxView.getSelectedIds().size() ; i++) {

            text=text +"  "+ checkBoxView.getSelectedIds().get(i) ;

        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();


    }
}