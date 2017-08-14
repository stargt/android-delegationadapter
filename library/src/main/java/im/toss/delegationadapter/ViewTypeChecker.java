package im.toss.delegationadapter;

import android.support.annotation.NonNull;

public interface ViewTypeChecker<T> {
  boolean isForViewType(@NonNull T item);
}
