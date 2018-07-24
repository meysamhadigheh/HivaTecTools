package hivatec.ir.hivatectools.adapters;

/**
 * Created by ashkan on 3/24/18.
 */

public abstract class ItemHolderIterator<T>{

	public T item;
	public int resourceId;

	public ItemHolderIterator(int resourceId){

		this.resourceId = resourceId;
	}

	public abstract void bindToHolder(ItemHolder binder, T item);
	public abstract void itemClicked(ItemHolder binder, T item);
}