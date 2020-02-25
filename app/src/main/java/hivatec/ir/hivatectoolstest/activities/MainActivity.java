package hivatec.ir.hivatectoolstest.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import hivatec.ir.hivatectools.views.HivaButton;
import hivatec.ir.hivatectoolstest.R;

public class MainActivity extends AppCompatActivity {

    HivaButton hivaButtonTest;
    HivaButton hivaAdapterTest;
    HivaButton hivaRadio;
    HivaButton hivaDataPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        hivaButtonTest =findViewById(R.id.hiva_button);
        hivaButtonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ButtonActivity.class));
            }
        });


        hivaAdapterTest =findViewById(R.id.hivaAdapterButton);
        hivaAdapterTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HivaAdapterActivity.class));
            }
        });


        hivaRadio =findViewById(R.id.hivaRadioView);
        hivaRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RadioActivity.class));
            }
        });

        hivaDataPicker=findViewById(R.id.hivaDataPicker);
        hivaDataPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DataPickerActivity.class));


            }
        });



        findViewById(R.id.formActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));


            }
        });


        findViewById(R.id.retrofitActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerLoadMoreTestActivity.class));

            }
        });
    }
}
