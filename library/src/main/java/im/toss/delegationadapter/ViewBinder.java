package im.toss.delegationadapter;

import android.support.annotation.NonNull;

import java.util.List;

public interface ViewBinder<T> {
    void onBindViewHolder(ItemViewHolder vh, @NonNull T item, @NonNull List<Object> payloads);
}
