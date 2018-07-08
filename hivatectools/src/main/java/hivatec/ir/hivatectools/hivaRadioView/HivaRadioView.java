package hivatec.ir.hivatectools.hivaRadioView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.helper.ViewUIHelper;
import hivatec.ir.hivatectools.hivaAdapter.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.hivaViewBinder.HivaLinearLayout;

/**
 * Created by ashkan on 7/7/18.
 */

public class HivaRadioView extends LinearLayout {


	public int radioOffDrawable = R.drawable.ic_radio_off;
	public int radioOnDrawable = R.drawable.ic_radio_on;

	ArrayList<RadioItem> items = new ArrayList<>();

	RadioItem selectedItem = null;

	private OnRadioItemSelectedListener listener;

	int iconOn = 0;
	int iconOff = 0;

	int tint = 0;
	String typeface = "";
	int iconSize = 0;
	int textColor = Color.parseColor("#333333");
	int textSize = ViewUIHelper.spToPx(12, getContext());
	int dividerColor = Color.parseColor("#eeeeee");
	int rippleColor = Color.parseColor("#eeeeee");


	public HivaRadioView(Context context) {
		super(context);

		init();
	}

	public HivaRadioView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.HivaRadioView, 0, 0);

		iconOn = a.getResourceId(R.styleable.HivaRadioView_iconOn, iconOn);
		iconOff = a.getResourceId(R.styleable.HivaRadioView_iconOff, iconOff);
		iconSize = a.getDimensionPixelSize(R.styleable.HivaRadioView_iconSize, iconSize);
		tint = a.getColor(R.styleable.HivaRadioView_tint, tint);

		textColor = a.getColor(R.styleable.HivaRadioView_textColor, textColor);
		textSize = a.getDimensionPixelSize(R.styleable.HivaRadioView_textSize, textSize);
		typeface = a.getString(R.styleable.HivaRadioView_typeface);

		dividerColor = a.getColor(R.styleable.HivaRadioView_dividerColor, dividerColor);
		rippleColor = a.getColor(R.styleable.HivaRadioView_rippleColor, rippleColor);

		a.recycle();

		init();
	}

	public void init() {


		this.setClickable(true);
		this.setOrientation(VERTICAL);

		if(iconOn != 0){
			radioOnDrawable = iconOn;
		}

		if(iconOff != 0){
			radioOffDrawable = iconOff;
		}

	}

	private void _reloadItems(){

		this.removeAllViews();

		for (RadioItem item : items){

			RadioView radioView = new RadioView(getContext());
			this.addView(radioView);

			TextView textView = radioView.getTextView();
			ImageView imageView = radioView.getIconView();
			View container = radioView.getContainerView();

			textView.setText(item.getTitle());
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
			textView.setTextColor(textColor);

			imageView.setImageResource(radioOffDrawable);

			if(tint != 0){
				imageView.setColorFilter(tint, PorterDuff.Mode.SRC_IN);
			}

			if(iconSize > 0){

				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageView.getLayoutParams();
				params.width = iconSize;
				params.height = iconSize;
			}

			if(typeface != null && !typeface.equals("")) {
				try {
					Typeface t = Typeface.createFromAsset(getContext().getAssets(), String.format("fonts/%s", typeface));
					textView.setTypeface(t);
				}catch (Exception e){
				}
			}

			container.setBackground(RippleHelper.getRippleDrawableForTransparentColor(rippleColor, 0));
			radioView.getDividerView().setBackgroundColor(dividerColor);

			radioView.setOnClickListener(onRadioItemClickListener);
			radioView.setTag(item);

		}
	}


	private OnClickListener onRadioItemClickListener = v -> {

		if(!(v.getTag() instanceof RadioItem)){
			return;
		}

		RadioItem radioItem = (RadioItem) v.getTag();
		_setSelectedItem(radioItem);
	};


	public void setItems(ArrayList<RadioItem> items){

		this.items = items;
		_reloadItems();
	}

	public void setItems(Object[] titles){


		for(Object str : titles){
			this.items.add(new RadioItem() {

				@Override
				public int getId() {
					return items.size();
				}

				@Override
				public String getTitle() {
					return str.toString();
				}
			});
		}

		_reloadItems();
	}

	public RadioItem getSelectedItem(){
		return selectedItem;
	}

	public void setSelectedItem(RadioItem item){
		_setSelectedItem(item);
	}

	public void setSelectedIndex(int index){
		_setSelectedItem(items.get(index));
	}

	public void setSelectedId(int id){

		RadioItem found = null;

		for (RadioItem item : items){
			if(item.getId() == id){
				found = item;
				break;
			}
		}

		_setSelectedItem(found);
	}

	private void _setSelectedItem(RadioItem item){

		if(selectedItem == null){

		}

		RadioView lastSelectedView = _getRadioView(selectedItem);
		RadioView currentlySelectedView = _getRadioView(item);

		this.selectedItem = item;

		if(lastSelectedView == null || currentlySelectedView == null){
			return;
		}

		lastSelectedView.getIconView().setImageResource(radioOffDrawable);
		currentlySelectedView.getIconView().setImageResource(radioOnDrawable);
	}

	private RadioView _getRadioView(RadioItem byItem){

		for(int i = 0; i < this.getChildCount(); i++){
			View v = this.getChildAt(i);

			if(v.getTag() instanceof RadioItem){

				if(((RadioItem) v.getTag()).getId() == byItem.getId()){
					return (RadioView) v;
				}
			}
		}

		return null;
	}

}
