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
import android.view.ViewGroup;
import android.widget.RelativeLayout;

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
	ArrayList<ItemBinder> items = new ArrayList<>();

	Delegate delegate;
	int minPageSize = 5;
	int page = 0;
	boolean canLoadMore = true;
	boolean isLoading = false;
	int orientation = LinearLayoutManager.VERTICAL;
	boolean reverseLayout = false;

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
		layoutManager = new LinearLayoutManager(getContext(), orientation, reverseLayout);

		refreshLayout.addView(recyclerView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		refreshLayout.setOnRefreshListener(refreshListener);
		recyclerView.setLayoutManager(layoutManager);
		items.add(getLoadingItem());
		adapter = new HivaRecyclerAdapter(items);
		recyclerView.setAdapter(adapter);

		this.addView(refreshLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));


		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
				super.onScrollStateChanged(recyclerView, newState);

				if(canLoadMore && !isLoading && layoutManager.findLastVisibleItemPosition() == adapter.getItems().size() - 1){

					startLoading();
				}
			}
		});

		setBackground(this.getBackground());


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

		isLoading = true;

		if(delegate != null)
			delegate.loadMore(page);
	}

	public void doneLoading(ArrayList items, int page){

		if(this.page == 0 && page != 0){
			return;
		}

		refreshLayout.setRefreshing(false);

		adapter.removeItem(loadingItem);

		if(page == 0){
			adapter.setItems(items);
		}else{
			adapter.addItems(items);
		}

		if(items.size() < minPageSize){
			canLoadMore = false;
		}else{
			adapter.addItem(loadingItem);
		}

		this.page++;
		adapter.notifyDataSetChanged();

		isLoading = false;
	}

	public ItemBinder getLoadingItem(){

		if(loadingItem == null){
			loadingItem = new LoadingItem();
		}

		return loadingItem;
	}


	public SwipeRefreshLayout getRefreshLayout() {
		return refreshLayout;
	}

	public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
		this.refreshLayout = refreshLayout;
	}

	public RecyclerView getRecyclerView() {
		return recyclerView;
	}

	public void setRecyclerView(RecyclerView recyclerView) {
		this.recyclerView = recyclerView;
	}

	public LinearLayoutManager getLayoutManager() {
		return layoutManager;
	}

	public void setLayoutManager(LinearLayoutManager layoutManager) {
		this.layoutManager = layoutManager;
	}

	public HivaRecyclerAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(HivaRecyclerAdapter adapter) {
		this.adapter = adapter;
	}

	public void setLoadingItem(ItemBinder loadingItem) {
		this.loadingItem = loadingItem;
	}

	public ArrayList<ItemBinder> getItems() {
		return items;
	}

	public void setItems(ArrayList<ItemBinder> items) {
		this.items = items;
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

	public void setLoading(boolean loading) {
		isLoading = loading;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public boolean isReverseLayout() {
		return reverseLayout;
	}

	public void setReverseLayout(boolean reverseLayout) {
		this.reverseLayout = reverseLayout;
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


	public interface Delegate{

		void loadMore(int page);
	}
}
