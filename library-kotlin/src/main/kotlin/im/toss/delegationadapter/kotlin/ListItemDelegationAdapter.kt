package im.toss.delegationadapter.kotlin

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

open class ListItemDelegationAdapter<T> : ListDelegationAdapter<List<T>>() {

  inline fun <reified I : T> newDelegateBuilder()
      : ListItemAdapterDelegate.Builder<I, T> {
    var builder = ListItemAdapterDelegate.Builder<I, T>()
    builder = builder.typeChecker({ item -> item is I })
    return builder
  }

  inline fun <reified I : T> newDelegateBuilder(crossinline viewTypeChecker: ((I) -> Boolean))
      : ListItemAdapterDelegate.Builder<I, T> {
    var builder = ListItemAdapterDelegate.Builder<I, T>()
    builder = builder.typeChecker({ item ->
      when (item) {
        is I -> viewTypeChecker.invoke(item)
        else -> false
      }
    })
    return builder
  }

  /**
   * @see com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager.addDelegate
   */
  fun addDelegate(delegate: AdapterDelegate<List<T>>) {
    delegatesManager.addDelegate(delegate)
  }

  /**
   * @see com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager.setFallbackDelegate
   */
  fun setFallbackDelegate(fallbackDelegate: AdapterDelegate<List<T>>) {
    delegatesManager.fallbackDelegate = fallbackDelegate
  }

  /**
   * Set the items / data source of this adapter

   * @param items The items / data source
   * *
   * @param notifyDataSetChanged Notify any registered observers that the data set has changed.
   */
  fun setItems(items: List<T>, notifyDataSetChanged: Boolean) {
    super.setItems(items)
    if (notifyDataSetChanged) {
      notifyDataSetChanged()
    }
  }
}
