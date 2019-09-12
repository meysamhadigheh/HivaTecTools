package hivatec.ir.hivatectools.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import hivatec.ir.hivatectools.R;
import hivatec.ir.hivatectools.adapters.HivaRecyclerAdapter;
import hivatec.ir.hivatectools.adapters.ItemBinder;
import hivatec.ir.hivatectools.adapters.ItemHolder;

public class RecyclerLoadMoreAndRefresh extends RelativeLayout {

	SwipeRefreshLayout refreshLayout;
	RecyclerView recyclerView;
	LinearLayoutManager layoutManager;
	HivaRecyclerAdapter adapter;
	ItemBinder loadingItem;
	ErrorItemBinder errorItem;
	ItemBinder emptyItem;

	Delegate delegate;
	int minPageSize = 5;
	int page = 0;
	boolean canLoadMore = true;
	boolean isLoading = false;
	String errorString = "خطا در دریافت اطلاعات";
	boolean hasError = false;

	public RecyclerLoadMoreAndRefresh(Context context) {
		super(context);
		init();
	}

	public RecyclerLoadMoreAndRefresh(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RecyclerLoadMoreAndRefresh(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}


	private void init(){

		refreshLayout = new SwipeRefreshLayout(getContext());
		recyclerView = new RecyclerView(getContext());
		layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

		refreshLayout.addView(recyclerView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		refreshLayout.setOnRefreshListener(refreshListener);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setClipToPadding(false);
		adapter = new HivaRecyclerAdapter();
		adapter.addItem(getLoadingItem());
		recyclerView.setAdapter(adapter);

		this.addView(refreshLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));


		addLayoutManagerListener();


		recyclerView.setPadding(this.getPaddingLeft(),this.getPaddingTop(),this.getPaddingRight(),this.getPaddingBottom());
		this.setPadding(0,0,0,0);
	}

	private void addLayoutManagerListener() {

		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				if(canLoadMore && !isLoading && layoutManager.findLastVisibleItemPosition() == adapter.getItems().size() - 1){

					startLoading();
				}
			}
		});
	}

	public void setDelegate(Delegate delegate){

		this.delegate = delegate;
		startLoading();
	}

	private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
		@Override
		public void onRefresh() {

			page = 0;
			startLoading();
		}
	};

	public void startLoading(){

		if(hasError) {
			adapter.removeItem(errorItem);
			adapter.addItem(getLoadingItem());
			hasError = false;
		}

		canLoadMore = true; // give list another chance to load more items
		isLoading = true;

		adapter.notifyDataSetChanged();

		if(delegate != null)
			delegate.loadMore(page);
	}

	public void doneLoading(ArrayList items, int page){

		if(this.page == 0 && page != 0){
			return;
		}

		refreshLayout.setRefreshing(false);

		adapter.removeItem(getLoadingItem());
		adapter.removeItem(getEmptyItem(""));

		this.page++;
		if(items.size() < minPageSize){
			canLoadMore = false;
		}else{
			adapter.addItem(getLoadingItem());
		}

		if(page == 0){

			if(items.size() == 0){
				adapter.addItem(getEmptyItem("محتوایی یافت نشد"));
				canLoadMore = false;
				page = 0;
			}else {
				adapter.setItems(items);
			}
		}else{
			adapter.addItems(items);
		}

		adapter.notifyDataSetChanged();

		isLoading = false;
	}

	public void doneWithError(int page, String error, Boolean canRefresh){
		if(page == 0){
			adapter.clearItems();
		}

		refreshLayout.setRefreshing(false);

		hasError = true;
		adapter.removeItem(getLoadingItem());
		adapter.addItem(getErrorItem(error, canRefresh));
		adapter.notifyDataSetChanged();
	}

	public void resetAndStartLoading() {

		adapter.clearItems();
		adapter.addItem(getLoadingItem());
		page = 0;
		startLoading();
	}

	public ItemBinder getLoadingItem(){

		if(loadingItem == null){
			loadingItem = new LoadingItem();
		}

		return loadingItem;
	}

	public ItemBinder getErrorItem(String error, Boolean canRefresh){

		if(errorItem == null){
			errorItem = new ErrorItem();
			((ErrorItem) errorItem).pagerHelper = this;
		}

		errorItem.setErrorMessage(error);
		errorItem.setCanRefresh(canRefresh);

		return errorItem;
	}

	public ItemBinder getEmptyItem(String error){

		if(emptyItem == null){
			emptyItem = new ErrorItem();
			((ErrorItem) emptyItem).pagerHelper = this;

			((ErrorItem) emptyItem).setErrorMessage(error);
			((ErrorItem) emptyItem).setCanRefresh(false);
		}

		return emptyItem;
	}


	public SwipeRefreshLayout getRefreshLayout() {
		return refreshLayout;
	}

	public RecyclerView getRecyclerView() {
		return recyclerView;
	}

	public LinearLayoutManager getLayoutManager() {
		return layoutManager;
	}

	public void setLayoutManager(LinearLayoutManager layoutManager){

		this.layoutManager = layoutManager;
		recyclerView.setLayoutManager(layoutManager);
		addLayoutManagerListener();
		adapter.notifyDataSetChanged();
	}

	public HivaRecyclerAdapter getAdapter() {
		return adapter;
	}

	public void setLoadingItem(ItemBinder loadingItem) {
		adapter.removeItem(this.loadingItem);
		this.loadingItem = loadingItem;
		adapter.addItem(loadingItem);
		adapter.notifyDataSetChanged();
	}
	public void setErrorItem(ErrorItemBinder errorItem) {
		this.errorItem = errorItem;
	}
	public void setEmptyItem(ItemBinder emptyItem) {
		this.emptyItem = emptyItem;
	}

	public Delegate getDelegate() {
		return delegate;
	}

	public int getMinPageSize() {
		return minPageSize;
	}

	public void setMinPageSize(int minPageSize) {
		this.minPageSize = minPageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isCanLoadMore() {
		return canLoadMore;
	}

	public void setCanLoadMore(boolean canLoadMore) {
		this.canLoadMore = canLoadMore;
	}

	public boolean isLoading() {
		return isLoading;
	}

	public class LoadingItem implements ItemBinder {

		@Override
		public int getResourceId() {
			return R.layout.item_loading;
		}

		@Override
		public void bindToHolder(ItemHolder binder, Object listener) {

		}
	}

	public int getLoadingItemPosition(){

		int i = 0;
		for(Object item : adapter.getItems()){

			if(item == getLoadingItem()){

				return i;
			}
			i++;
		}

		return -1;
	}


	public int getErrorItemPosition(){

		int i = 0;
		for(Object item : adapter.getItems()){

			if(item == errorItem){

				return i;
			}
			i++;
		}

		return -1;
	}

	public class ErrorItem implements ErrorItemBinder {

		public Boolean canRefresh = true;
		public String errorString = "";
		public RecyclerLoadMoreAndRefresh pagerHelper;

		@Override
		public void setErrorMessage(String error) {
			this.errorString = error;
		}

		@Override
		public void setCanRefresh(Boolean canRefresh) {
			this.canRefresh = canRefresh;
		}

		@Override
		public int getResourceId() {
			return R.layout.item_error;
		}

		@Override
		public void bindToHolder(ItemHolder binder, Object listener) {

			binder.<TextView>find(R.id.errorText).setText(errorString);

			if(canRefresh == false){
				binder.<HivaButton>find(R.id.refreshBtn).setVisibility(GONE);
			}

			binder.<HivaButton>find(R.id.refreshBtn).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					pagerHelper.startLoading();
				}
			});
		}


	}

	public interface ErrorItemBinder extends ItemBinder {

		void setErrorMessage(String error);
		void setCanRefresh(Boolean canRefresh);
	}


	public interface Delegate{

		void loadMore(int page);
	}
}
