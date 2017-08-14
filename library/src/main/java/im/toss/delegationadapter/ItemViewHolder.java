package im.toss.delegationadapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

public class ItemViewHolder<I> extends RecyclerView.ViewHolder {

  private final SparseArray<View> viewCache = new SparseArray<>();
  private WeakReference<I> itemRef;

  public static <I> ItemViewHolder<I> create(ViewGroup parent, @LayoutRes int layoutId) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    return new ItemViewHolder<I>(itemView);
  }

  void setItem(@Nullable I data) {
    if (data != null) {
      itemRef = new WeakReference<>(data);
    } else {
      itemRef = null;
    }
  }

  @Nullable
  public I getItem() {
    if (itemRef != null) {
      return itemRef.get();
    }
    return null;
  }

  private ItemViewHolder(View itemView) {
    super(itemView);
  }

  public View getView(@IdRes int viewId) {
    return getView(viewId, View.class);
  }

  @SuppressWarnings("unchecked, UnusedParameters")
  public <T extends View> T getView(@IdRes int viewId, Class<T> viewType) {
    T view = (T) viewCache.get(viewId);
    if (view == null) {
      view = (T) itemView.findViewById(viewId);
      viewCache.put(viewId, view);
    }
    return view;
  }
}
