package hivatec.ir.hivatectools.hivaRadioView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.helper.RippleHelper;
import hivatec.ir.hivatectools.helper.ViewUIHelper;
import hivatec.ir.hivatectools.hivaAdapter.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.hivaAdapter.ItemBinder;
import hivatec.ir.hivatectools.hivaAdapter.ItemHolder;
import hivatec.ir.hivatectools.hivaAdapter.OnItemClickListener;

/**
 * Created by ashkan on 3/14/18.
 */

public class HivaRadioView extends RecyclerView {

	public int radioOffDrawable = R.drawable.ic_radio_off;
	public int radioOnDrawable = R.drawable.ic_radio_on;

	ArrayList<String> items = new ArrayList<>();
	ArrayList<Integer> ids = new ArrayList<>();
	ArrayList<RadioItem> radioItems = new ArrayList<>();
	private HivaRecyclerAdapter adapter;
	boolean needsItemsChange = true;

	int selectedItem = -1;
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

	public HivaRadioView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);


		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.HivaRadioView, 0, 0);

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
		}


		init();
	}

	private void init(){


		if(iconOn != 0){
			radioOnDrawable = iconOn;
		}

		if(iconOff != 0){
			radioOffDrawable = iconOff;
		}

		this.setNestedScrollingEnabled(false);

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext()) {

			@Override
			public boolean canScrollVertically() {
				return false;
			}
		};
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		this.setLayoutManager(layoutManager);

		adapter = new HivaRecyclerAdapter(items);
		adapter.setOnItemClickListener(RadioItem.class, new OnItemClickListener<RadioItem>() {
			@Override
			public void onItemClicked(RadioItem radioItem, ItemHolder itemHolder) {

				setSelectedItem(radioItem.index);

				if(listener != null){
					listener.onRadioSelected(radioItem.index, radioItem.text);
				}
			}
		});

		this.setAdapter(adapter);
	}

	public <T> void forEachItem(ArrayList<T> items, HivaRadioItemIterator<T> iterator){

		ArrayList<String> _items = new ArrayList<>();

		for(T item : items){

			ids.add(iterator.getId(item));
			_items.add(iterator.getTitle(item));
		}

		setItems(_items);
	}

	public int getIdForIndex(int index){

		return ids.get(index);
	}

	public int getIdForSelectedIndex(){

		return ids.get(selectedItem);
	}

	public void setItems(ArrayList<String> items){


		needsItemsChange = true;
		this.items = items;

		adapter.clearItems();
		radioItems = new ArrayList<>();

		int index = 0;
		for (String item : items) {

			radioItems.add(new RadioItem(index++, item));
		}

		adapter.setItems(radioItems);
		adapter.notifyDataSetChanged();
	}

	public void setSelectedItem(int index){

		int lastIndex = selectedItem;
		this.selectedItem = index;

		for (RadioItem item : radioItems){
			item.isSelected = false;
		}

		radioItems.get(index).isSelected = true;

		adapter.notifyItemChanged(lastIndex, new Integer(500));
		adapter.notifyItemChanged(selectedItem, new Integer(5000));
	}

	public int getSelectedItem(){
		return selectedItem;
	}

	public void setOnSelectListener(OnRadioItemSelectedListener listener){

		this.listener = listener;
	}

	private class RadioItem implements ItemBinder {


		public int index = 0;
		public String text = "";
		public Boolean isSelected = false;

		public RadioItem(int index, String text) {

			this.index = index;
			this.text = text;
		}

		@Override
		public int getResourceId() {
			return R.layout.item_radio;
		}

		@Override
		public void bindToHolder(ItemHolder itemHolder, Object o) {

			TextView textView = itemHolder.<TextView>find(R.id.text);
			textView.setText(text);

			if(tint != 0){
				itemHolder.<ImageView>find(R.id.icon).setColorFilter(tint, PorterDuff.Mode.SRC_IN);
			}

			if(iconSize > 0){

				LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) itemHolder.find(R.id.icon).getLayoutParams();
				params.width = iconSize;
				params.height = iconSize;
			}

			if(isSelected) {
				itemHolder.<ImageView>find(R.id.icon).setImageResource(radioOnDrawable);
			}else{
				itemHolder.<ImageView>find(R.id.icon).setImageResource(radioOffDrawable);
			}

			if(typeface != null && !typeface.equals("")) {
				try {
					Typeface t = Typeface.createFromAsset(getContext().getAssets(), String.format("fonts/%s", typeface));
					textView.setTypeface(t);
				}catch (Exception e){
				}
			}

			if(itemHolder.find(R.id.container).getBackground() == null) {
				itemHolder.find(R.id.container).setBackground(RippleHelper.getRippleDrawableForTransparentColor(rippleColor, 0));
			}

			itemHolder.find(R.id.divider).setBackgroundColor(dividerColor);

			textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
			textView.setTextColor(textColor);
		}
	}

	public interface OnRadioItemSelectedListener {

		void onRadioSelected(int index, String title);
	}

	public interface HivaRadioItemIterator<T>{

		int getId(T item);
		String getTitle(T item);
	}

}
