package hivatec.ir.hivatectools.activityHelpers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.activityHelpers.ActionBarView;
import hivatec.ir.hivatectools.helper.FontsOverride;


public abstract class ParentActivity extends AppCompatActivity {

    protected ActionBarView toolbar;
    protected Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = this;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //set what horizontal_item to show
        setContentViewActivity();

        //if activity has any intent data
        getIntentData();
        //set custom settings for toolbar if needed
        setToolbar();

        //instantiate and customise toolbar view
        prepareToolbar();

        //get all views in activity content
        instantiateViews();

        //set listeners for what views should do
        setViewListeners();


        FontsOverride.setDefaultFont(this, "DEFAULT", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "IRANSansMobile.ttf");

        //set views content (this makes sure all views are instantiated)
        setActivityContent();


    }

    protected void setToolbar() {

        toolbar = findViewById(R.id.toolbar);

        if(toolbar == null){
            return;
        }

        this.setSupportActionBar(toolbar.getToolbar());

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.getToolbar().setContentInsetsAbsolute(0, 0);


        toolbar.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    protected void showLoading() {
        hideErrorLayout();
        findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
    }
    protected void hideLoading() {
        findViewById(R.id.loading_layout).setVisibility(View.GONE);

    }
    protected  void showErrorEmpty(String text_error, Boolean refresh){
        TextView error_text=findViewById(R.id.error_text);
        error_text.setText(text_error);
        findViewById(R.id.error_layout).setVisibility(View.VISIBLE);

        hideLoading();

        if(refresh == false){

            findViewById(R.id.refresh_btn).setVisibility(View.GONE);
        }

        findViewById(R.id.refresh_btn).setOnClickListener(v -> refreshContent());
    }

    protected  void hideErrorLayout(){
        findViewById(R.id.error_layout).setVisibility(View.GONE);
    }

    /**
     * dont use setContentView in onCreate
     * use this method instead
     */
    protected abstract void setContentViewActivity();

    protected abstract void getIntentData();

    /**
     * set custom toolbar with id 'toolbar'
     * set toolbarResource if you want to change its id
     */


    protected abstract void prepareToolbar();



    protected abstract void instantiateViews();

    protected abstract void setViewListeners();

    protected abstract void setActivityContent();

    protected abstract void refreshContent();



    /*********
     * gets a single skeleton databaseOpenHelper to work with in activity
     *
     * @return
     */



    //*********
    //easier stuff
    //********
    protected void showToast(String str){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    protected void showToastLong(String str){
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }




    //*******************
    //get resources easier
    //*******************
    protected int getColorRes(int color){
        return getResources().getColor(color);
    }


}