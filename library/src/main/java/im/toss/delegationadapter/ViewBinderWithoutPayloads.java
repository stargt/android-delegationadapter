package im.toss.delegationadapter;

import android.support.annotation.NonNull;

public interface ViewBinderWithoutPayloads<T> {
  void onBindViewHolder(ItemViewHolder<T> vh, @NonNull T item);
}
