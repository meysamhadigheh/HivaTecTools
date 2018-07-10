package hivatec.ir.hivatectools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.helper.ViewUIHelper;
import hivatec.ir.hivatectools.hivaRadioView.CheckedItem;
import hivatec.ir.hivatectools.hivaRadioView.HivaRadioView;
import hivatec.ir.hivatectools.hivaRadioView.OnRadioItemSelectedListener;
import hivatec.ir.hivatectools.hivaRadioView.RadioView;

/**
 * Created by ashkan on 7/8/18.
 */

public class HivaCheckBoxView extends LinearLayout {

	public int radioOffDrawable = R.drawable.ic_check_box_outline_blank;
	public int radioOnDrawable = R.drawable.ic_check_box;

	ArrayList<CheckedItem> items = new ArrayList<>();
	ArrayList<RadioView> views = new ArrayList<>();
	ArrayList<LinearLayout> columns = new ArrayList<>();

	ArrayList<CheckedItem> selectedItems = new ArrayList<>();

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
	int colNum = 1;

	int hideAnimation = R.anim.fade_out;
	int showAnimation = R.anim.fade_in;

	public HivaCheckBoxView(Context context) {
		super(context);

		init();
	}

	public HivaCheckBoxView(Context context, AttributeSet attrs) {
		super(context, attrs);

		TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.HivaCheckBoxView, 0, 0);

		iconOn = a.getResourceId(R.styleable.HivaCheckBoxView_iconOn, iconOn);
		iconOff = a.getResourceId(R.styleable.HivaCheckBoxView_iconOff, iconOff);
		iconSize = a.getDimensionPixelSize(R.styleable.HivaCheckBoxView_iconSize, iconSize);
		tint = a.getColor(R.styleable.HivaCheckBoxView_tint, tint);

		textColor = a.getColor(R.styleable.HivaCheckBoxView_textColor, textColor);
		textSize = a.getDimensionPixelSize(R.styleable.HivaCheckBoxView_textSize, textSize);
		typeface = a.getString(R.styleable.HivaCheckBoxView_typeface);

		dividerColor = a.getColor(R.styleable.HivaCheckBoxView_dividerColor, dividerColor);
		rippleColor = a.getColor(R.styleable.HivaCheckBoxView_rippleColor, rippleColor);

		colNum = a.getInt(R.styleable.HivaCheckBoxView_colNum, colNum);

		hideAnimation = a.getResourceId(R.styleable.HivaCheckBoxView_hideAnimation, hideAnimation);
		showAnimation = a.getResourceId(R.styleable.HivaCheckBoxView_showAnimation, showAnimation);

		a.recycle();

		init();
	}

	public void init() {


		this.setClickable(true);
		this.setOrientation(HORIZONTAL);
		this.setGravity(Gravity.RIGHT);
		this.setScaleX(-1);

		for(int i = 0; i < this.colNum; i++){
			LinearLayout column = new LinearLayout(getContext());
			LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			llp.weight = 1;
			llp.gravity = Gravity.RIGHT;
			column.setLayoutParams(llp);
			column.setOrientation(VERTICAL);
			column.setScaleX(-1);
			columns.add(column);
			this.addView(column);
		}

		if(iconOn != 0){
			radioOnDrawable = iconOn;
		}

		if(iconOff != 0){
			radioOffDrawable = iconOff;
		}

	}

	private void _reloadItems(){

		//this.removeAllViews();

		for(LinearLayout ll : columns){
			ll.removeAllViews();
		}

		int lastColumn = 0;

		for (CheckedItem item : items){

			RadioView radioView = new RadioView(getContext());

			TextView textView = radioView.getTextView();
			ImageView imageViewOn = radioView.getIconOnView();
			ImageView imageViewOff = radioView.getIconOffView();
			View container = radioView.getContainerView();

			textView.setText(item.getTitle());
			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
			textView.setTextColor(textColor);

			imageViewOn.setImageResource(radioOnDrawable);
			imageViewOff.setImageResource(radioOffDrawable);

			imageViewOn.setAlpha(0f);

			if(tint != 0){
				imageViewOn.setColorFilter(tint, PorterDuff.Mode.SRC_IN);
				imageViewOff.setColorFilter(tint, PorterDuff.Mode.SRC_IN);
			}

			if(iconSize > 0){

				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageViewOn.getLayoutParams();
				params.width = iconSize;
				params.height = iconSize;

				LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) imageViewOff.getLayoutParams();
				params2.width = iconSize;
				params2.height = iconSize;
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

			views.add(radioView);

			int colId = (lastColumn++) % colNum;
			this.columns.get( colId ) .addView(radioView);

		}
	}


	private OnClickListener onRadioItemClickListener = v -> {

		if(!(v.getTag() instanceof CheckedItem)){
			return;
		}

		CheckedItem radioItem = (CheckedItem) v.getTag();
		_setSelectedItem(radioItem);
	};


	public void setItems(ArrayList<CheckedItem> items){

		this.items = items;
		_reloadItems();
	}

	public void setItems(Object[] titles){

		int i = 0;

		for(Object str : titles){

			items.add(new HivaCheckBoxView._CheckBoxItem(i++, str.toString()));
		}

		_reloadItems();
	}

	public ArrayList<CheckedItem> getSelectedItem(){
		return selectedItems;
	}

	public ArrayList<Integer> getSelectedIds(){
		ArrayList<Integer> ids = new ArrayList<>();

		for(CheckedItem item : selectedItems){
			ids.add(item.getId());
		}

		return ids;
	}

	public void setSelectedItem(CheckedItem item){
		_setSelectedItem(item);
	}

	public void setSelectedIndex(int index){
		_setSelectedItem(items.get(index));
	}

	public void setSelectedId(int id){

		CheckedItem found = null;

		for (CheckedItem item : items){
			if(item.getId() == id){
				found = item;
				break;
			}
		}

		_setSelectedItem(found);
	}

	private void _setSelectedItem(CheckedItem item){


		RadioView currentlySelectedView = _getRadioView(item);

		if(item.isChecked()){
			item.setChecked(false);

			hideIcon(currentlySelectedView.getIconOnView());
			showIcon(currentlySelectedView.getIconOffView());

			selectedItems.remove(item);

		}else{

			item.setChecked(true);

			showIcon(currentlySelectedView.getIconOnView());
			hideIcon(currentlySelectedView.getIconOffView());

			selectedItems.add(item);
		}




	}

	private RadioView _getRadioView(CheckedItem byItem){

		for(RadioView v : views){

			if(v.getTag() instanceof CheckedItem){

				int vid = ((CheckedItem) v.getTag()).getId();
				int itemId = byItem.getId();

				if( vid == itemId ){
					return (RadioView) v;
				}
			}
		}

		return null;
	}

	void hideIcon(View view){

		Animation xmlAnim = AnimationUtils.loadAnimation(getContext(), hideAnimation);
		view.startAnimation(xmlAnim);
		xmlAnim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				view.setAlpha(0f);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}

	void showIcon(View view){
		Animation xmlAnim = AnimationUtils.loadAnimation(getContext(), showAnimation);
		view.startAnimation(xmlAnim);
		xmlAnim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				view.setAlpha(1f);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}
		});
	}

	class _CheckBoxItem implements CheckedItem {


		private int id;
		private String title;
		private Boolean isChecked = false;

		public _CheckBoxItem(int id, String title) {
			this.id = id;
			this.title = title;
		}

		@Override
		public int getId() {
			return id;
		}

		@Override
		public String getTitle() {
			return title;
		}

		@Override
		public boolean isChecked() {
			return isChecked;
		}

		@Override
		public void setChecked(Boolean checked) {
			isChecked = checked;
		}

	}

}
