package im.toss.delegationadapter.kotlin

import android.support.annotation.LayoutRes
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate


/**
 * More simple way to create [AbsListItemAdapterDelegate] with less boiler plate code
 * @param <T> The type of the item that is managed by this AdapterDelegate.
</T> */
class ListItemAdapterDelegate<I : T, T> constructor(
    @LayoutRes val layoutId: Int,
    val typeChecker: ((T) -> Boolean)? = null,
    val onViewHolderCreate: ViewHolderCreateListener? = null,
    val viewBinder: ((ItemViewHolder<I>, I) -> Unit)? = null,
    val viewBinderLong: ((ItemViewHolder<I>, I, List<Any>?) -> Unit)? = null
) : AbsListItemAdapterDelegate<I, T, ItemViewHolder<I>>() {

  override fun isForViewType(item: T, items: List<T>, position: Int): Boolean {
    return typeChecker?.invoke(item) ?: false
  }

  override fun onCreateViewHolder(parent: ViewGroup): ItemViewHolder<I> {
    val viewHolder = ItemViewHolder.create<I>(parent, layoutId)
    onViewHolderCreate?.onViewHolderCreate(viewHolder)
    return viewHolder
  }

  override fun onBindViewHolder(item: I, viewHolder: ItemViewHolder<I>, payloads: List<Any>) {
    viewHolder.item = item
    viewBinderLong?.invoke(viewHolder, item, payloads) ?:
        viewBinder?.invoke(viewHolder, item)
  }


  class Builder<I : T, T> {
    private var layoutId: Int = 0
    private var typeChecker: ((T) -> Boolean)? = null
    private var onViewHolderCreate: ViewHolderCreateListener? = null
    private var viewBinder: ((ItemViewHolder<I>, I) -> Unit)? = null
    private var viewBinderLong: ((ItemViewHolder<I>, I, List<Any>?) -> Unit)? = null

    fun typeChecker(typeChecker: (T) -> Boolean): Builder<I, T> {
      this.typeChecker = typeChecker
      return this
    }

    fun layout(@LayoutRes layoutId: Int): Builder<I, T> {
      this.layoutId = layoutId
      return this
    }

    fun binder(binder: (ItemViewHolder<I>, I) -> Unit): Builder<I, T> {
      this.viewBinder = binder
      return this
    }

    fun binder(binder: (ItemViewHolder<I>, I, List<Any>?) -> Unit): Builder<I, T> {
      this.viewBinderLong = binder
      return this
    }

    fun onViewHolderCreate(callback: ViewHolderCreateListener): Builder<I, T> {
      this.onViewHolderCreate = callback
      return this
    }

    fun build(): ListItemAdapterDelegate<I, T> {
      return ListItemAdapterDelegate(
          layoutId = layoutId,
          typeChecker = typeChecker,
          viewBinder = viewBinder,
          viewBinderLong = viewBinderLong,
          onViewHolderCreate = onViewHolderCreate
      )
    }
  }
}
