package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import hivatec.ir.hivatectools.views.HivaButton;
import hivatec.ir.hivatectoolstest.R;

public class ButtonActivity extends AppCompatActivity {

    HivaButton hivaButton,hivaButton2,hivaButton3,hivaButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);


        findViewById(R.id.hiva_button).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button2).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button3).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button4).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button_top).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button_bottom).setOnClickListener(clickListener);
        findViewById(R.id.hiva_button_nobg).setOnClickListener(clickListener);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startLoading(v);
        }
    };

    private void startLoading(View button) {


        ((HivaButton) button).startLoadingState();

        button.postDelayed(new Runnable() {
            @Override
            public void run() {

                ((HivaButton) button).stopLoadingState();
            }
        }, 3000);

    }


}
