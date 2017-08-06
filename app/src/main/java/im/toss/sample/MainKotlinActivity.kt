package im.toss.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import im.toss.delegationadapter.kotlin.ListItemDelegationKtAdapter
import im.toss.delegationadapter.kotlin.findView
import im.toss.sample.model.*
import java.util.*

/**
 * Kotlin Example
 */

class MainKotlinActivity : AppCompatActivity() {

    class AnimalAdapter : ListItemDelegationKtAdapter<DisplayableItem>() {

        init {
            // setup adapter delegates
            addDelegate(newDelegateBuilder<Advertisement>()
                    .layout(R.layout.item_advertisement)
                    .build())

            addDelegate(newDelegateBuilder<Cat>()
                    .layout(R.layout.item_cat)
                    .binder { vh, cat -> vh.findView<TextView>(R.id.name).text = cat.name }
                    .build())

            addDelegate(newDelegateBuilder<Dog>()
                    .layout(R.layout.item_dog)
                    .binder { vh, dog -> vh.findView<TextView>(R.id.name).text = dog.name }
                    .build())

            addDelegate(createDelegateBuilder(Gecko::class.java)
                    .layout(R.layout.item_gecko)
                    .binder { vh, gecko ->
                        vh.findView<TextView>(R.id.name).text = gecko.name
                        vh.findView<TextView>(R.id.race).text = gecko.race
                    }
                    .build())

            addDelegate(createDelegateBuilder(Snake::class.java)
                    .layout(R.layout.item_snake)
                    .binder { vh, snake ->
                        vh.findView<TextView>(R.id.name).text = snake.name
                        vh.findView<TextView>(R.id.race).text = snake.race
                    }
                    .build())

            setFallbackDelegate(createDelegateBuilder(DisplayableItem::class.java)
                    .layout(android.R.layout.simple_list_item_1)
                    .binder { vh, item -> vh.findView<TextView>(android.R.id.text1).text = item.toString() }
                    .build())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_simple_main)

        val listAdapter = AnimalAdapter()

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter

        listAdapter.setItems(getAnimals(), true)

    }

    private fun getAnimals(): List<DisplayableItem> {
        val animals = ArrayList<DisplayableItem>()

        animals.add(Cat("American Curl"))
        animals.add(Cat("Baliness"))
        animals.add(Cat("Bengal"))
        animals.add(Cat("Corat"))
        animals.add(Cat("Manx"))
        animals.add(Cat("Nebelung"))
        animals.add(Dog("Aidi"))
        animals.add(Dog("Chinook"))
        animals.add(Dog("Appenzeller"))
        animals.add(Dog("Collie"))
        animals.add(Snake("Mub Adder", "Adder"))
        animals.add(Snake("Texas Blind Snake", "Blind snake"))
        animals.add(Snake("Tree Boa", "Boa"))
        animals.add(Gecko("Fat-tailed", "Hemitheconyx"))
        animals.add(Gecko("Stenodactylus", "Dune Gecko"))
        animals.add(Gecko("Leopard Gecko", "Eublepharis"))
        animals.add(Gecko("Madagascar Gecko", "Phelsuma"))
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())
        animals.add(Advertisement())

        Collections.shuffle(animals)
        return animals
    }

}
