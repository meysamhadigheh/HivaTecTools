package hivatec.ir.hivatectools.hivaRadioView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
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

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.helper.ViewUIHelper;

/**
 * Created by ashkan on 7/7/18.
 */

public class HivaRadioView extends LinearLayout {


	public int radioOffDrawable = R.drawable.ic_radio_off;
	public int radioOnDrawable = R.drawable.ic_radio_on;

	ArrayList<RadioItem> items = new ArrayList<>();
	ArrayList<RadioView> views = new ArrayList<>();
	ArrayList<LinearLayout> columns = new ArrayList<>();

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
	int colNum = 1;

	int hideAnimation = R.anim.fade_out;
	int showAnimation = R.anim.fade_in;

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

		colNum = a.getInt(R.styleable.HivaRadioView_colNum, colNum);

		hideAnimation = a.getResourceId(R.styleable.HivaRadioView_hideAnimation, hideAnimation);
		showAnimation = a.getResourceId(R.styleable.HivaRadioView_showAnimation, showAnimation);

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

		for (RadioItem item : items){

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

		if(!(v.getTag() instanceof RadioItem)){
			return;
		}

		RadioItem radioItem = (RadioItem) v.getTag();
		_setSelectedItem(radioItem);
	};


	public void setItems(ArrayList items){

		for(Object item : items){

			if(item instanceof RadioItem) {
				this.items.add((RadioItem) item);
			}
		}

		_reloadItems();
	}

	public void setItems(Object[] titles){

		int i = 0;

		for(Object str : titles){

			items.add(new _RadioItem(i++, str.toString()));
		}

		_reloadItems();
	}

	public RadioItem getSelectedItem(){
		return selectedItem;
	}

	public int getSelectedId(){

		if(selectedItem == null){
			return -1;
		}

		return selectedItem.getId();
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

		if(selectedItem != null && selectedItem.getId() == item.getId()){
			return;
		}

		if(selectedItem == null){

			this.selectedItem = item;
			RadioView selectedView = _getRadioView(selectedItem);

			hideIcon(selectedView.getIconOffView());
			showIcon(selectedView.getIconOnView());

			return;
		}

		RadioView lastSelectedView = _getRadioView(selectedItem);
		RadioView currentlySelectedView = _getRadioView(item);

		this.selectedItem = item;

		if(lastSelectedView == null || currentlySelectedView == null){
			return;
		}

		//hideIcon(lastSelectedView.getIconOnView());
		//showIcon(lastSelectedView.getIconOffView());

		showIcon(currentlySelectedView.getIconOnView());
		hideIcon(currentlySelectedView.getIconOffView());

		lastSelectedView.getIconOnView().setAlpha(0f);
		lastSelectedView.getIconOffView().setAlpha(1f);

	}

	private RadioView _getRadioView(RadioItem byItem){

		for(RadioView v : views){

			if(v.getTag() instanceof RadioItem){

				int vid = ((RadioItem) v.getTag()).getId();
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

	class _RadioItem implements RadioItem {


		private int id;
		private String title;

		public _RadioItem(int id, String title) {
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
	}
}
