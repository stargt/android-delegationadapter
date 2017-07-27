package im.toss.delegationadapter;

import android.support.annotation.NonNull;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;

import java.util.List;

public class ListItemDelegationAdapter<T> extends ListDelegationAdapter<List<T>> {

    public <I extends T>
    ListItemAdapterDelegate.Builder<I, T> createDelegateBuilder(final Class<I> itemCls) {
        ListItemAdapterDelegate.Builder<I, T> builder = new ListItemAdapterDelegate.Builder<>();
        builder = builder.typeChecker(new ViewTypeChecker<T>() {
            @Override
            public boolean isForViewType(@NonNull T item) {
                return itemCls.isInstance(item);
            }
        });
        return builder;
    }

    public <I extends T>
    ListItemAdapterDelegate.Builder<I, T> createDelegateBuilder(final Class<I> itemCls, final ViewTypeChecker<I> viewTypeChecker) {
        ListItemAdapterDelegate.Builder<I, T> builder = new ListItemAdapterDelegate.Builder<>();
        builder = builder.typeChecker(new ViewTypeChecker<T>() {
            @Override
            public boolean isForViewType(@NonNull T item) {
                if (itemCls.isInstance(item)) {
                    return viewTypeChecker.isForViewType(itemCls.cast(item));
                }
                return false;
            }
        });
        return builder;
    }

    /**
     * @see com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager#addDelegate(AdapterDelegate)
     */
    public void addDelegate(AdapterDelegate<List<T>> delegate) {
        delegatesManager.addDelegate(delegate);
    }

    /**
     * @see com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager#setFallbackDelegate(AdapterDelegate)
     */
    public void setFallbackDelegate(AdapterDelegate<List<T>> fallbackDelegate) {
        delegatesManager.setFallbackDelegate(fallbackDelegate);
    }

    /**
     * Set the items / data source of this adapter
     *
     * @param items The items / data source
     * @param notifyDataSetChanged Notify any registered observers that the data set has changed.
     */
    public void setItems(List<T> items, boolean notifyDataSetChanged) {
        super.setItems(items);
        if (notifyDataSetChanged) {
            notifyDataSetChanged();
        }
    }
}
