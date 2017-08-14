package im.toss.delegationadapter.kotlin

import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.lang.ref.WeakReference

class ItemViewHolder<T> private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

  val viewCache = SparseArray<View>()
  private var itemRef: WeakReference<T>? = null

  var item: T?
    get() {
      if (itemRef != null) {
        return itemRef!!.get()
      }
      return null
    }
    set(data) = if (data != null) {
      itemRef = WeakReference(data)
    } else {
      itemRef = null
    }

  inline fun <reified T : View> findView(@IdRes viewId: Int): T? {
    var view: T? = viewCache.get(viewId) as T?
    if (view == null) {
      view = itemView.findViewById(viewId) as T
      viewCache.put(viewId, view)
    }
    return view
  }

  companion object {

    fun <I> create(parent: ViewGroup, @LayoutRes layoutId: Int): ItemViewHolder<I> {
      val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
      return ItemViewHolder<I>(itemView)
    }
  }
}
