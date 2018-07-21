package hivatec.ir.hivatectools.hivaActionBarView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import hivatec.ir.hivatectools.R;


public class ActionBarView extends RelativeLayout {




    public ToolBarContentView contentView;

    public TextView titleTextView;
    public ImageButton leftButton;
    public ImageButton rightButton1;
    public ImageButton rightButton2;

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

        this.contentView = findViewById(R.id.toolbar_content);

        this.titleTextView = contentView.titleTextView;
        this.leftButton = contentView.leftButton;
        this.rightButton1 = contentView.rightButton1;
        this.rightButton2 = contentView.rightButton2;


    }
    public Toolbar getToolbar(){

        return findViewById(R.id.toolbar);
    }
}