package im.toss.delegationadapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate;

import java.util.List;

/**
 * More simple way to create {@link AbsListItemAdapterDelegate} with less boiler plate code
 * @param <T> The type of the item that is managed by this AdapterDelegate.
 */
public class ListItemAdapterDelegate<I extends T, T> extends AbsListItemAdapterDelegate<I, T, ItemViewHolder> {

    @LayoutRes
    private int layoutId;
    private ViewTypeChecker<T> typeChecker;
    private ViewHolderCreateListener onViewHolderCreate;
    private ViewBinder<I> viewBinder;
    private ViewBinderWithoutPayloads<I> viewBinderWithoutPayloads;

    private ListItemAdapterDelegate(Builder<I,T> builder) {
        layoutId = builder.layoutId;
        typeChecker = builder.typeChecker;
        onViewHolderCreate = builder.onViewHolderCreate;
        viewBinder = builder.viewBinder;
        viewBinderWithoutPayloads = builder.viewBinderWithoutPayloads;
    }

    @Override
    protected boolean isForViewType(@NonNull T item, @NonNull List<T> items, int position) {
        if (typeChecker != null) {
            return typeChecker.isForViewType(item);
        }
        return false;
    }

    @NonNull
    @Override
    protected ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        ItemViewHolder viewHolder = ItemViewHolder.create(parent, layoutId);
        if (onViewHolderCreate != null) {
            onViewHolderCreate.onViewHolderCreate(viewHolder);
        }
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull I item, @NonNull ItemViewHolder viewHolder, @NonNull List<Object> payloads) {
        if (viewBinder != null) {
            viewBinder.onBindViewHolder(viewHolder, item, payloads);
        } else if (viewBinderWithoutPayloads != null) {
            viewBinderWithoutPayloads.onBindViewHolder(viewHolder, item);
        }
    }

    public static class Builder<I extends T, T> {
        private int layoutId;
        private ViewTypeChecker<T> typeChecker;
        private ViewHolderCreateListener onViewHolderCreate;
        private ViewBinder<I> viewBinder;
        private ViewBinderWithoutPayloads<I> viewBinderWithoutPayloads;

        Builder() {
        }

        Builder<I,T> typeChecker(ViewTypeChecker<T> val) {
            typeChecker = val;
            return this;
        }

        public Builder<I,T> layout(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
            return this;
        }

        public Builder<I,T> binder(ViewBinder<I> val) {
            viewBinder = val;
            return this;
        }

        public Builder<I,T> binder(ViewBinderWithoutPayloads<I> val) {
            viewBinderWithoutPayloads = val;
            return this;
        }

        public Builder<I,T> onViewHolderCreate(ViewHolderCreateListener callback) {
            this.onViewHolderCreate = callback;
            return this;
        }

        public ListItemAdapterDelegate<I,T> build() {
            return new ListItemAdapterDelegate<>(this);
        }
    }
}
