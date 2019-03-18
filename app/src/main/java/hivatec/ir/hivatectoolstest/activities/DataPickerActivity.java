package hivatec.ir.hivatectoolstest.activities;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hivatec.ir.hivatectools.views.LoopView;
import hivatec.ir.hivatectoolstest.R;


public class DataPickerActivity extends AppCompatActivity {


    Button button;
    TextView textView;

    String selectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_picker);
        button = findViewById(R.id.button);

        textView = findViewById(R.id.textview);

        ArrayList<String> items;
        items = new ArrayList();

        items.add("item 1");
        items.add("item 2");
        items.add("item 3");
        items.add("item 4");
        items.add("item 5");
        items.add("item 6");
        items.add("item 7");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picker(items, 0, new LoopViewItemSelected() {
                    @Override
                    public void itemSelected(int index) {
                        textView.setText(items.get(index));

                    }
                });
            }
        });
    }

    private void picker(ArrayList<String> items, int lastIndexSelected, LoopViewItemSelected listener) {


        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        LoopView loopView = sheetView.findViewById(R.id.data_picker);
        LoopView loopView2 = sheetView.findViewById(R.id.data_picker2);
        ImageView acceptBtn = sheetView.findViewById(R.id.accept_btn);
        ImageView cancelBtn=sheetView.findViewById(R.id.cancel_btn);
        loopView.setItems(items);
        loopView2.setItems(items);
        loopView.setItemsVisibleCount(4);
        loopView.setTextSizeNotCenter(14);
        loopView.setTextSizeCenter(20);
        loopView.setNotLoop();

        loopView.setInitPosition(lastIndexSelected);
        loopView.setDividerColor(getResources().getColor(R.color.Gray));

        mBottomSheetDialog.setContentView(sheetView);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) sheetView.getParent());
        //mBehavior.setPeekHeight(1200);
        mBottomSheetDialog.show();

        loopView.setInitPosition(lastIndexSelected);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText(selectedItem);
                mBottomSheetDialog.dismiss();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });





        loopView.setListener(new LoopView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int var1) {

                selectedItem=items.get(var1);
            }
        });

    }

    private interface LoopViewItemSelected {

        void itemSelected(int index);
    }
}
