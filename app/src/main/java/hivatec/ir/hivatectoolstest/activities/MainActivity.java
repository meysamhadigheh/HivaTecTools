package hivatec.ir.hivatectoolstest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hivatec.ir.hivatectoolstest.R;

public class MainActivity extends AppCompatActivity {

    Button hivaButtonTest;
    Button hivaAdapterTest;

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
    }
}
