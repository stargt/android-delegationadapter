package im.toss.delegationadapter;

import android.support.annotation.NonNull;

public interface ViewBinderWithoutPayloads<T> {
    void onBindViewHolder(ItemViewHolder vh, @NonNull T item);
}
