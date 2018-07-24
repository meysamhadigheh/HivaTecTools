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
        hivaButton=findViewById(R.id.hiva_button);

        hivaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                startLoading(v);


            }
        });


        hivaButton2=findViewById(R.id.hiva_button2);

        hivaButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading(v);



            }
        });
        hivaButton3=findViewById(R.id.hiva_button3);

        hivaButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading(v);


            }
        });
        hivaButton4=findViewById(R.id.hiva_button4);

        //hivaButton.setEnabled(false);
        hivaButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoading(v);


            }
        });
    }

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
