package hivatec.ir.hivatectools.hivaActionBarView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hivatec.ir.hivatectools.R;


public class ActionBarView extends RelativeLayout {




    public TextView actionBarTitle;
    public ImageView actionBarRightImg;
    public ImageView actionBarLeftImg;
    public RelativeLayout actionBarRightLayout;
    public RelativeLayout actionBarLeftLayout;

    public ActionBarView(Context context) {
        super(context);
        init();
    }

    public ActionBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        inflater.inflate(R.layout.custom_action_bar, this);
        this.actionBarTitle=findViewById(R.id.actionbar_title);
        this.actionBarRightImg=findViewById(R.id.actionbar_right_img);
        this.actionBarLeftImg=findViewById(R.id.actionbar_left_img);
        this.actionBarRightLayout=findViewById(R.id.actionbar_right_layout);
        this.actionBarLeftLayout=findViewById(R.id.actionbar_left_layout);

    }
    public Toolbar getToolbar(){

        return findViewById(R.id.toolbar);
    }
}