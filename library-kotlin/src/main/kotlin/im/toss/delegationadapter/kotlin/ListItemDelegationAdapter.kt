package im.toss.delegationadapter.kotlin
import android.view.View
import im.toss.delegationadapter.ItemViewHolder
import im.toss.delegationadapter.ListItemAdapterDelegate
import im.toss.delegationadapter.ListItemDelegationAdapter
import im.toss.delegationadapter.ViewTypeChecker

inline fun <reified T : View> ItemViewHolder.findView(viewId: Int) : T {
    return  getView(viewId, T::class.java)
}

open class ListItemDelegationKtAdapter<T> : ListItemDelegationAdapter<T>() {

    inline fun <reified I : T> newDelegateBuilder()
            : ListItemAdapterDelegate.Builder<I, T> {
        var builder = ListItemAdapterDelegate.Builder<I, T>()
        builder = builder.typeChecker({ item -> item is I })
        return builder
    }

    inline fun <reified I : T> newDelegateBuilder(viewTypeChecker: ViewTypeChecker<I>)
            : ListItemAdapterDelegate.Builder<I, T> {
        var builder = ListItemAdapterDelegate.Builder<I, T>()
        builder = builder.typeChecker(ViewTypeChecker<T> { item ->
            if (item is I) {
                return@ViewTypeChecker viewTypeChecker.isForViewType(item)
            }
            false
        })
        return builder
    }

}
