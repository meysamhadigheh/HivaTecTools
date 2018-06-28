package hivatec.ir.hivatectoolstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import hivatec.ir.hivatectools.view.HivaButton;

public class ButtonActivity extends AppCompatActivity {

    HivaButton hivaButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        hivaButton=findViewById(R.id.hiva_button);
        hivaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
